package ar.edu.unq.dopplereffect.presentation.panel.project;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections15.CollectionUtils;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

import ar.edu.unq.dopplereffect.presentation.panel.AjaxActionPanel;
import ar.edu.unq.dopplereffect.presentation.panel.NavigablePanel;
import ar.edu.unq.dopplereffect.presentation.panel.utils.AbstractPanel;
import ar.edu.unq.dopplereffect.presentation.panel.utils.PanelCallbackLink;
import ar.edu.unq.dopplereffect.presentation.project.ProjectSearchModel;
import ar.edu.unq.dopplereffect.presentation.search.AbstractSearchPanel;
import ar.edu.unq.dopplereffect.presentation.search.SearchModel;
import ar.edu.unq.dopplereffect.presentation.util.AjaxCallBack;
import ar.edu.unq.dopplereffect.presentation.util.SelectableBehavior;
import ar.edu.unq.dopplereffect.service.employee.EmployeeViewDTO;
import ar.edu.unq.dopplereffect.service.project.ProjectAssignmentDTO;
import ar.edu.unq.dopplereffect.service.project.ProjectDTO;
import ar.edu.unq.dopplereffect.service.project.ProjectServiceImpl;
import ar.edu.unq.dopplereffect.time.IntervalDurationStrategy;

public class AssignmentProjectPanel extends NavigablePanel<String> {

    private static final long serialVersionUID = 7612811876399884445L;

    /* ************************ INSTANCE VARIABLES ************************ */

    private ProjectSearchModel projectSearchModel;

    private ProjectDTO project;

    /* *************************** CONSTRUCTORS *************************** */

    public AssignmentProjectPanel(final String id, final ProjectDTO projectDTO, final AjaxCallBack<Component> callback,
            final AbstractPanel<ProjectDTO> backPanel, final AbstractSearchPanel<SearchModel<ProjectDTO>> searchPanel) {
        super(id, "");
        project = projectDTO;
        projectSearchModel = (ProjectSearchModel) searchPanel.getModelObject();
        this.init(callback, backPanel);
        this.makePanel();
        this.addNavigationButtons();
    }

    /* **************************** ACCESSORS ***************************** */

    public ProjectSearchModel getProjectSearchModel() {
        return projectSearchModel;
    }

    public void setProjectSearchModel(final ProjectSearchModel projectSearchModel) {
        this.projectSearchModel = projectSearchModel;
    }

    public ProjectDTO getProject() {
        return project;
    }

    public void setProject(final ProjectDTO project) {
        this.project = project;
    }

    /* **************************** OPERATIONS **************************** */

    public void makePanel() {
        List<ProjectAssignmentDTO> assignments = project.getAssignment();
        List<EmployeeViewDTO> allEmployee = (List<EmployeeViewDTO>) CollectionUtils.disjunction(
                ((ProjectServiceImpl) projectSearchModel.getService()).getEmployeeService().searchAllEmployees(),
                this.getEmployeeAvailable(assignments));
        ListView<EmployeeViewDTO> availableListView = this.createListView("available", allEmployee);
        WebMarkupContainer selectableWicket = new WebMarkupContainer("availableWicket");
        selectableWicket.setMarkupId("availableWicket");
        selectableWicket.add(availableListView);

        SelectableBehavior availableSelectableBehavior = new SelectableBehavior();
        selectableWicket.add(availableSelectableBehavior);
        this.add(selectableWicket);

        AddIntervalDuration addIntervaDuration = this.createIntervalDurationDialog(allEmployee,
                availableSelectableBehavior);

        AjaxActionPanel addEmployee = this.createAddemployeeButton(availableSelectableBehavior, addIntervaDuration);

        ListView<ProjectAssignmentDTO> assignmentListView = this.createAssignmentListView("selected", assignments);

        WebMarkupContainer selectableAjaxWicket = new WebMarkupContainer("selectableWicket");
        selectableAjaxWicket.setMarkupId("selectableWicket");
        final SelectableBehavior selectedSelecableBehavior = new SelectableBehavior();
        selectableAjaxWicket.add(selectedSelecableBehavior);
        selectableAjaxWicket.add(assignmentListView);

        this.add(this.createremoveEmployeeButton(allEmployee, assignments, selectedSelecableBehavior));
        this.add(addIntervaDuration);
        this.add(addEmployee);
        this.add(selectableAjaxWicket);
    }

    /* ************************* PRIVATE METHODS ************************** */

    protected List<EmployeeViewDTO> getEmployeeAvailable(final List<ProjectAssignmentDTO> assignments) {
        List<EmployeeViewDTO> result = new ArrayList<EmployeeViewDTO>();
        for (ProjectAssignmentDTO assignment : assignments) {
            result.add(assignment.getEmployeeDTO());
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    protected AjaxActionPanel createremoveEmployeeButton(final List<EmployeeViewDTO> values,
            final List<ProjectAssignmentDTO> employees, final SelectableBehavior selectedSelecableBehavior) {
        return new AjaxActionPanel("remove", "remove.png") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onAction(final AjaxRequestTarget target) {
                if (selectedSelecableBehavior.getComponentModel() != null) {
                    ProjectAssignmentDTO modelObject = ((ListItem<ProjectAssignmentDTO>) selectedSelecableBehavior
                            .getComponentModel()).getModelObject();
                    values.add(modelObject.getEmployeeDTO());
                    employees.remove(modelObject);
                    selectedSelecableBehavior.clean();
                    target.addComponent(AssignmentProjectPanel.this);
                }
            }
        };
    }

    protected AjaxActionPanel createAddemployeeButton(final SelectableBehavior availableSelectableBehavior,
            final AddIntervalDuration addIntervaDuration) {
        return new AjaxActionPanel("add", "add.png") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onAction(final AjaxRequestTarget target) {
                if (availableSelectableBehavior.getComponentModel() != null) {
                    target.appendJavascript(addIntervaDuration.open().render().toString());
                }
            }
        };
    }

    @SuppressWarnings("unchecked")
    protected AddIntervalDuration createIntervalDurationDialog(final List<EmployeeViewDTO> values,
            final SelectableBehavior availableSelectableBehavior) {
        return new AddIntervalDuration("dialog") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onAccept(final IntervalDurationStrategy intervalDurationStrategy, final AjaxRequestTarget target) {
                EmployeeViewDTO modelObject = ((ListItem<EmployeeViewDTO>) availableSelectableBehavior
                        .getComponentModel()).getModelObject();
                AssignmentProjectPanel.this.getProjectSearchModel().assignmentEmployee(
                        AssignmentProjectPanel.this.getProject(), modelObject, intervalDurationStrategy);
                values.remove(modelObject);
                availableSelectableBehavior.clean();
                target.addComponent(AssignmentProjectPanel.this);
            }
        };
    }

    private ListView<EmployeeViewDTO> createListView(final String id, final List<EmployeeViewDTO> employees) {
        return new ListView<EmployeeViewDTO>(id, employees) {
            private static final long serialVersionUID = 1L;

            @Override
            public void populateItem(final ListItem<EmployeeViewDTO> listItem) {
                final EmployeeViewDTO employeeViewDto = listItem.getModelObject();
                listItem.add(new Label("firstName_" + id, new Model<String>(employeeViewDto.getFirstName()))
                        .setOutputMarkupId(true));
                listItem.add(new Label("lastName_" + id, new Model<String>(employeeViewDto.getLastName()))
                        .setOutputMarkupId(true));
                listItem.setOutputMarkupId(true);
            }
        };
    }

    private ListView<ProjectAssignmentDTO> createAssignmentListView(final String id,
            final List<ProjectAssignmentDTO> assignments) {
        return new ListView<ProjectAssignmentDTO>(id, assignments) {
            private static final long serialVersionUID = 1L;

            @Override
            public void populateItem(final ListItem<ProjectAssignmentDTO> listItem) {
                final ProjectAssignmentDTO assignment = listItem.getModelObject();
                listItem.add(new Label("firstName_" + id, new Model<String>(assignment.getEmployeeDTO().getFirstName()))
                        .setOutputMarkupId(true));
                listItem.add(new Label("lastName_" + id, new Model<String>(assignment.getEmployeeDTO().getLastName()))
                        .setOutputMarkupId(true));
                listItem.setOutputMarkupId(true);
            }
        };
    }

    protected void addNavigationButtons() {
        this.add(new PanelCallbackLink("back_button", this.getCallback(), this.getBackPanel(), new StringResourceModel(
                "back_button", new Model<String>(""))));
    }
}
