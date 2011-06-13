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

    private boolean linkVisible;

    private WebMarkupContainer showPercentages;

    private List<PercentageView> percentages;

    private SalarySpecDTO salarySpec;

    public AddPercentagesContainer(final String id, final List<PercentageView> percentages,
            final WebMarkupContainer showPercentages, final SalarySpecDTO salarySpec) {
        super(id);
        this.salarySpec = salarySpec;
        this.percentages = percentages;
        this.percentages.addAll(this.toPercentageView(salarySpec.getPercentages()));
        this.showPercentages = showPercentages;
        linkVisible = true;
        this.setOutputMarkupId(true);
        this.add(new AddPercentageLink("link").add(new ButtonBehavior()));
        this.add(new RemoveCheckedPercentagesLink("remove").add(new ButtonBehavior()));
        this.add(new AddPercentageForm("form"));
    }

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
        @SuppressWarnings("synthetic-access")
        public boolean isVisible() {
            return linkVisible;
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
        @SuppressWarnings("synthetic-access")
        public boolean isVisible() {
            return linkVisible;
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
        @SuppressWarnings("synthetic-access")
        public boolean isVisible() {
            return !linkVisible;
        }
    }

    public void onShowForm(final AjaxRequestTarget target) {
        linkVisible = false;
        target.addComponent(this);
    }

    public void onRemoveCompletedPercentages(final AjaxRequestTarget target) {
        List<PercentageView> ready = new LinkedList<PercentageView>();
        List<Integer> percs = new LinkedList<Integer>();
        for (PercentageView perc : percentages) {
            if (perc.isSelected()) {
                ready.add(perc);
                percs.add(perc.getValue());
            }
        }
        percentages.removeAll(ready);
        salarySpec.getPercentages().removeAll(percs);
        target.addComponent(this);
        target.addComponent(showPercentages);
    }

    public void onAdd(final PercentageView item, final AjaxRequestTarget target) {
        boolean validValue = item.getValue() >= 0 && item.getValue() <= 100;
        boolean nonExistentPercentage = !salarySpec.getPercentages().contains(item.getValue());
        if (nonExistentPercentage && validValue) {
            salarySpec.getPercentages().add(item.getValue());
            Collections.sort(salarySpec.getPercentages());
            percentages.add(new PercentageView(item));
            Collections.sort(percentages);
            item.setSelected(false);
            item.setValue(0);
            linkVisible = true;
            target.addComponent(this);
            target.addComponent(showPercentages);
        }
    }

    public void onCancelPercentage(final AjaxRequestTarget target) {
        linkVisible = true;
        target.addComponent(this);
    }
}
