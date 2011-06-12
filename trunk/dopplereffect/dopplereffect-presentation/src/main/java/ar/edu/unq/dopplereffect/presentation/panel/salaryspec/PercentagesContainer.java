package ar.edu.unq.dopplereffect.presentation.panel.salaryspec;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;

public class PercentagesContainer extends WebMarkupContainer {

    private static final long serialVersionUID = -2207567781255108511L;

    public PercentagesContainer(final String id, final List<PercentageView> percentages) {
        super(id);
        this.setOutputMarkupId(true);
        this.add(new ListView<PercentageView>("percentage", percentages) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<PercentageView> item) {
                item.add(new AjaxCheckBox("selected", new PropertyModel<Boolean>(item.getDefaultModel(), "selected")) {

                    private static final long serialVersionUID = 1L;

                    @Override
                    protected void onUpdate(final AjaxRequestTarget target) {
                    }
                });
                item.add(new Label("value", new PropertyModel<Integer>(item.getDefaultModel(), "value")));
            }
        });
    }
}
