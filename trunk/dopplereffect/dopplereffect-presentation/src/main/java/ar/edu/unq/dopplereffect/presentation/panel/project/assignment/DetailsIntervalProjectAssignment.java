package ar.edu.unq.dopplereffect.presentation.panel.project.assignment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

import ar.edu.unq.dopplereffect.mail.LocaleManager;
import ar.edu.unq.dopplereffect.presentation.panel.utils.AbstractCallbackPanel;
import ar.edu.unq.dopplereffect.presentation.panel.utils.AbstractPanel;
import ar.edu.unq.dopplereffect.presentation.panel.utils.PanelCallbackLink;
import ar.edu.unq.dopplereffect.presentation.util.AjaxCallBack;
import ar.edu.unq.dopplereffect.presentation.util.SelectableBehavior;
import ar.edu.unq.dopplereffect.service.project.ProjectAssignmentDTO;
import ar.edu.unq.dopplereffect.time.IntervalDurationStrategy;

public class DetailsIntervalProjectAssignment extends AbstractCallbackPanel<ProjectAssignmentDTO> {

    private static final long serialVersionUID = 1L;

    private DateFormat dateFormat;

    /* *************************** CONSTRUCTORS *************************** */

    public DetailsIntervalProjectAssignment(final String id, final ProjectAssignmentDTO model,
            final AjaxCallBack<Component> callBack, final AbstractPanel<?> backPanel) {
        super(id, model);
        this.setCallback(callBack);
        this.setBackPanel(backPanel);
        this.addComponent(model);
        this.setDateFormat(new SimpleDateFormat("dd/MM/yyyy", LocaleManager.getLocaleManager().getLocale()));
    }

    /* ************************* PRIVATE METHODS ************************** */

    private void addComponent(final ProjectAssignmentDTO projectAssignmentDTO) {
        String title = "Asignaciones de " + projectAssignmentDTO.getEmployeeName() + " en el proyecto"
                + projectAssignmentDTO.getProjectName();
        this.add(new Label("panel_title", new Model<String>(title)));
        WebMarkupContainer selectableWicket = new WebMarkupContainer("availableWicket");
        selectableWicket.setMarkupId("availableWicket");
        selectableWicket.add(this.createListView("intervals", new ArrayList<IntervalDurationStrategy>(
                projectAssignmentDTO.getIntervals())));

        SelectableBehavior availableSelectableBehavior = new SelectableBehavior();
        selectableWicket.add(availableSelectableBehavior);
        this.add(selectableWicket);
        this.addNavigationButtons();
    }

    private ListView<IntervalDurationStrategy> createListView(final String id,
            final List<IntervalDurationStrategy> intervals) {
        return new ListView<IntervalDurationStrategy>(id, intervals) {
            private static final long serialVersionUID = 1L;

            @Override
            public void populateItem(final ListItem<IntervalDurationStrategy> listItem) {
                final IntervalDurationStrategy employeeViewDto = listItem.getModelObject();
                listItem.add(new Label("startDate", new Model<String>(DetailsIntervalProjectAssignment.this
                        .getDateFormat().format(employeeViewDto.getStartDate().toDate()))).setOutputMarkupId(true));
                listItem.add(new Label("endDate", new Model<String>(DetailsIntervalProjectAssignment.this
                        .getDateFormat().format(employeeViewDto.getEndDate().toDate()))).setOutputMarkupId(true));
                listItem.setOutputMarkupId(true);
            }
        };
    }

    protected void addNavigationButtons() {
        this.add(new PanelCallbackLink("back_button", this.getCallback(), this.getBackPanel(), new StringResourceModel(
                "back_button", new Model<String>(""))));
    }

    public void setDateFormat(final DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }
}
