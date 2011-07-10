package ar.edu.unq.dopplereffect.presentation.panel.project;

import java.util.Arrays;
import java.util.Date;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.joda.time.DateTime;
import org.odlabs.wiquery.ui.datepicker.DatePicker;

import ar.edu.unq.dopplereffect.presentation.components.CustomComponent;
import ar.edu.unq.dopplereffect.presentation.panel.project.assignment.AssignmentProjectAjaxDataTablePage;
import ar.edu.unq.dopplereffect.presentation.panel.project.assignment.DetailsIntervalProjectAssignment;
import ar.edu.unq.dopplereffect.presentation.project.AssignmentProjectSearchModel;
import ar.edu.unq.dopplereffect.presentation.search.AbstractSearchPanel;
import ar.edu.unq.dopplereffect.presentation.search.SearchModel;
import ar.edu.unq.dopplereffect.presentation.util.AjaxCallBack;
import ar.edu.unq.dopplereffect.presentation.util.AjaxDataTable;
import ar.edu.unq.dopplereffect.presentation.util.Model;
import ar.edu.unq.dopplereffect.service.project.ProjectAssignmentDTO;
import ar.edu.unq.dopplereffect.service.project.ProjectDTO;

/**
 * TODO: description
 */
public class AutomaticAssignmentProjectPanel extends AbstractSearchPanel<AssignmentProjectSearchModel> {
    private static final long serialVersionUID = 1L;

    private Date fromDate;

    private DropDownChoice<ProjectDTO> projects;

    private DatePicker<Date> datePicker;

    private Component aceptAutomaticAssignment;

    public AutomaticAssignmentProjectPanel(final String id, final AjaxCallBack<Component> parentPage,
            final AssignmentProjectSearchModel model) {
        super(id, parentPage, model, Arrays.asList("employeeDTO", "projectName"),
                DetailsIntervalProjectAssignment.class);
    }

    @Override
    protected AjaxDataTable<ProjectAssignmentDTO, AssignmentProjectSearchModel> createAjaxTable() {
        return new AssignmentProjectAjaxDataTablePage(this, this.getTableWicketId(), this.getSortName(),
                (AssignmentProjectSearchModel) (SearchModel<?>) this.getDefaultModelObject(), this.getCallback(),
                this.getFields(), this.getAbmClass());
    }

    @Override
    protected void addNewEntityButton(final Form<AssignmentProjectSearchModel> form) {
    }

    @Override
    protected void addInputSearch(final Form<AssignmentProjectSearchModel> form) {
        this.setProjects(new DropDownChoice<ProjectDTO>("searchByProject", new Model<ProjectDTO>(), this
                .getModelObject().getAllProjects()));
        this.getProjects().setNullValid(false);
        this.getProjects().setRequired(true);
        this.getProjects().setOutputMarkupId(true);
        form.add(this.getProjects());
        this.setDatePicker(new DatePicker<Date>("fromDate", new PropertyModel<Date>(this, "fromDate")));
        this.getDatePicker().setRequired(true);
        this.getDatePicker().setOutputMarkupId(true);
        form.add(this.getDatePicker());
        form.add(new FeedbackPanel("feedbak"));

    }

    @Override
    protected void init(final Form<AssignmentProjectSearchModel> formulario) {
        super.init(formulario);
        aceptAutomaticAssignment = CustomComponent.addButtonSking(this.createAceptButton());
        this.getAjaxSectionResult().add(aceptAutomaticAssignment);
    }

    private AjaxLink<String> createAceptButton() {
        return new AjaxLink<String>("aceptButton") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(final AjaxRequestTarget target) {
                AutomaticAssignmentProjectPanel.this.getModelObject().automaticAssignment(
                        AutomaticAssignmentProjectPanel.this.getProjects().getModelObject(),
                        new DateTime(AutomaticAssignmentProjectPanel.this.getFromDate()));
                AutomaticAssignmentProjectPanel.this.getModelObject().reset();
                AutomaticAssignmentProjectPanel.this.getProjects().setModelObject(null);
                AutomaticAssignmentProjectPanel.this.getDatePicker().setModelObject(null);
                target.addComponent(AutomaticAssignmentProjectPanel.this.getDatePicker());
                target.addComponent(AutomaticAssignmentProjectPanel.this.getProjects());
                target.addComponent(AutomaticAssignmentProjectPanel.this.getAjaxSectionResult());
            }
        };
    }

    @Override
    public void search() {
        this.getModelObject().automaticRecommendation(this.getProjects().getModelObject(), new DateTime(fromDate));
        this.getAjaxSectionResult().setVisible(true);
    }

    @Override
    protected String getSortName() {
        return "employeeName";
    }

    @Override
    protected String getFormWicketId() {
        return "automaticAssignmentForm";
    }

    @Override
    public Boolean cantEdit() {
        return false;
    }

    @Override
    public Boolean cantDelete() {
        return false;
    }

    public void setFromDate(final Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setDatePicker(final DatePicker<Date> datePicker) {
        this.datePicker = datePicker;
    }

    public DatePicker<Date> getDatePicker() {
        return datePicker;
    }

    public void setProjects(final DropDownChoice<ProjectDTO> projects) {
        this.projects = projects;
    }

    public DropDownChoice<ProjectDTO> getProjects() {
        return projects;
    }

}
