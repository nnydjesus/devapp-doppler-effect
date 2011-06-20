package ar.edu.unq.dopplereffect.presentation.panel.salaryspec;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.odlabs.wiquery.ui.button.ButtonBehavior;

import ar.edu.unq.dopplereffect.service.salaryspec.SalarySpecDTO;

public class AddPercentagesContainer extends WebMarkupContainer {

    private static final long serialVersionUID = 7384716813413600237L;

    /* ************************ INSTANCE VARIABLES ************************ */

    private boolean linkVisible;

    private WebMarkupContainer showPercentages;

    private List<PercentageView> percentages;

    private SalarySpecDTO salarySpec;

    /* *************************** CONSTRUCTORS *************************** */

    public AddPercentagesContainer(final String id, final List<PercentageView> percentages,
            final WebMarkupContainer showPercentages, final SalarySpecDTO salarySpec) {
        super(id);
        this.setSalarySpec(salarySpec);
        this.setPercentages(percentages);
        this.getPercentages().addAll(this.toPercentageView(salarySpec.getPercentages()));
        this.setShowPercentages(showPercentages);
        this.setLinkVisible(true);
        this.setOutputMarkupId(true);
        this.add(new AddPercentageLink("link").add(new ButtonBehavior()));
        this.add(new RemoveCheckedPercentagesLink("remove").add(new ButtonBehavior()));
        this.add(new AddPercentageForm("form"));
    }

    /* **************************** ACCESSORS ***************************** */

    public boolean isLinkVisible() {
        return linkVisible;
    }

    public void setLinkVisible(final boolean linkVisible) {
        this.linkVisible = linkVisible;
    }

    public WebMarkupContainer getShowPercentages() {
        return showPercentages;
    }

    public void setShowPercentages(final WebMarkupContainer showPercentages) {
        this.showPercentages = showPercentages;
    }

    public List<PercentageView> getPercentages() {
        return percentages;
    }

    public void setPercentages(final List<PercentageView> percentages) {
        this.percentages = percentages;
    }

    public SalarySpecDTO getSalarySpec() {
        return salarySpec;
    }

    public void setSalarySpec(final SalarySpecDTO salarySpec) {
        this.salarySpec = salarySpec;
    }

    /* **************************** OPERATIONS **************************** */

    public void onShowForm(final AjaxRequestTarget target) {
        this.setLinkVisible(false);
        target.addComponent(this);
    }

    public void onRemoveCompletedPercentages(final AjaxRequestTarget target) {
        List<PercentageView> ready = new LinkedList<PercentageView>();
        List<Integer> percs = new LinkedList<Integer>();
        for (PercentageView perc : this.getPercentages()) {
            if (perc.isSelected()) {
                ready.add(perc);
                percs.add(perc.getValue());
            }
        }
        this.getPercentages().removeAll(ready);
        this.getSalarySpec().getPercentages().removeAll(percs);
        target.addComponent(this);
        target.addComponent(this.getShowPercentages());
    }

    public void onAdd(final PercentageView item, final AjaxRequestTarget target) {
        boolean validValue = item.getValue() >= 0 && item.getValue() <= 100;
        boolean nonExistentPercentage = !this.getSalarySpec().getPercentages().contains(item.getValue());
        if (nonExistentPercentage && validValue) {
            this.getSalarySpec().getPercentages().add(item.getValue());
            Collections.sort(this.getSalarySpec().getPercentages());
            this.getPercentages().add(new PercentageView(item));
            Collections.sort(this.getPercentages());
            item.setSelected(false);
            item.setValue(0);
            this.setLinkVisible(true);
            target.addComponent(this);
            target.addComponent(this.getShowPercentages());
        }
    }

    public void onCancelPercentage(final AjaxRequestTarget target) {
        this.setLinkVisible(true);
        target.addComponent(this);
    }

    /* ************************* PRIVATE METHODS ************************** */

    private List<PercentageView> toPercentageView(final List<Integer> percs) {
        List<PercentageView> results = new LinkedList<PercentageView>();
        for (int perc : percs) {
            results.add(this.toPercentageView(perc));
        }
        return results;
    }

    private PercentageView toPercentageView(final int perc) {
        PercentageView result = new PercentageView();
        result.setSelected(false);
        result.setValue(perc);
        return result;
    }

    private final class AddPercentageLink extends AjaxFallbackLink<Object> {

        private static final long serialVersionUID = 1L;

        public AddPercentageLink(final String id) {
            super(id);
        }

        @Override
        public void onClick(final AjaxRequestTarget target) {
            AddPercentagesContainer.this.onShowForm(target);
        }

        @Override
        public boolean isVisible() {
            return AddPercentagesContainer.this.isLinkVisible();
        }
    }

    private final class RemoveCheckedPercentagesLink extends AjaxFallbackLink<Object> {

        private static final long serialVersionUID = 1L;

        public RemoveCheckedPercentagesLink(final String id) {
            super(id);
        }

        @Override
        public void onClick(final AjaxRequestTarget target) {
            AddPercentagesContainer.this.onRemoveCompletedPercentages(target);
        }

        @Override
        public boolean isVisible() {
            return AddPercentagesContainer.this.isLinkVisible();
        }
    }

    private final class AddPercentageForm extends Form<PercentageView> {

        private static final long serialVersionUID = 1L;

        public AddPercentageForm(final String id) {
            super(id, new CompoundPropertyModel<PercentageView>(new PercentageView()));
            AddPercentagesContainer.this.setOutputMarkupId(true);
            this.add(new TextField<Integer>("value"));
            this.add(new AjaxButton("add", this) {
                private static final long serialVersionUID = 1L;

                @Override
                protected void onSubmit(final AjaxRequestTarget target, final Form<?> form) {
                    PercentageView item = (PercentageView) this.getParent().getDefaultModelObject();
                    AddPercentagesContainer.this.onAdd(item, target);
                }

                @Override
                protected void onError(final AjaxRequestTarget target, final Form<?> form) {
                    // x
                }
            }.add(new ButtonBehavior()));

            this.add(new AjaxButton("cancel", this) {
                private static final long serialVersionUID = 1L;

                @Override
                public void onSubmit(final AjaxRequestTarget target, final Form<?> form) {
                    AddPercentagesContainer.this.onCancelPercentage(target);
                }

                @Override
                protected void onError(final AjaxRequestTarget target, final Form<?> form) {
                    // x
                }
            }.add(new ButtonBehavior()));
        }

        @Override
        public boolean isVisible() {
            return !AddPercentagesContainer.this.isLinkVisible();
        }
    }
}
