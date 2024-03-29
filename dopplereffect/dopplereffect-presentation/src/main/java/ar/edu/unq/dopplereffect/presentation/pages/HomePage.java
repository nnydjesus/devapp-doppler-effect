package ar.edu.unq.dopplereffect.presentation.pages;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.unq.dopplereffect.presentation.panel.HeaderPanel;
import ar.edu.unq.dopplereffect.presentation.panel.LoginStatusPanel;
import ar.edu.unq.dopplereffect.presentation.panel.SidebarPanel;
import ar.edu.unq.dopplereffect.presentation.panel.utils.AbstractCallbackPanel;
import ar.edu.unq.dopplereffect.presentation.project.AssignmentProjectSearchModel;
import ar.edu.unq.dopplereffect.presentation.project.ProjectSearchModel;
import ar.edu.unq.dopplereffect.presentation.search.SearchModel;
import ar.edu.unq.dopplereffect.presentation.search.employee.EmployeeSearchModel;
import ar.edu.unq.dopplereffect.presentation.search.leaverequest.LeaveRequestSearchModel;
import ar.edu.unq.dopplereffect.presentation.search.salaryspec.SalarySpecSearchModel;
import ar.edu.unq.dopplereffect.presentation.util.AjaxCallBack;
import ar.edu.unq.dopplereffect.presentation.util.HandlerErrorAction;
import ar.edu.unq.dopplereffect.service.AddDefaultValuesService;

/**
 * Simple home page.
 */
public class HomePage extends AbstractWebPage<Component> {

    private static final long serialVersionUID = 1L;

    @SpringBean(name = "search_model.employee")
    private EmployeeSearchModel employeeSearchModel;

    @SpringBean(name = "search_model.project")
    private ProjectSearchModel projectSearchModel;

    @SpringBean(name = "search_model.salary_spec")
    private SalarySpecSearchModel salarySpecSearchModel;

    @SpringBean(name = "search_model.leave_request")
    private LeaveRequestSearchModel leaveReqSearchModel;

    @SpringBean(name = "search_model.projectAssignment")
    private AssignmentProjectSearchModel projectAssignment;

    @SpringBean(name = "service.default_values")
    private AddDefaultValuesService addDefaultValuesService;

    @Override
    protected void makePage() {
        addDefaultValuesService.init();
        super.makePage();
    }

    @Override
    protected Component createHeader() {
        return new HeaderPanel(AbstractWebPage.HEADER);
    }

    @Override
    protected Component createSidebar() {
        return new SidebarPanel(SIDEBAR, BODY, this.generateCallback(), this);
    }

    public Component createPanelLink(final String id, final AbstractCallbackPanel<?> panel) {
        return new AjaxLink<Object>(id) {
            private static final long serialVersionUID = 1L;

            @Override
            @SuppressWarnings("rawtypes")
            public void onClick(final AjaxRequestTarget target) {
                ((SearchModel) panel.getDefaultModelObject()).reset();
                panel.reset();
                HomePage.this.generateCallback().execute(target, panel);
            }
        };
    }

    protected AjaxCallBack<Component> generateCallback() {
        return new AjaxCallBack<Component>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void execute(final AjaxRequestTarget ajaxTarget, final Component component) {
                new HandlerErrorAction() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void onExecute() {
                        if (component == null) {
                            HomePage.this.setDefaultBody();
                        } else {
                            HomePage.this.setBody(component);
                        }
                        ajaxTarget.addComponent(HomePage.this.getAjaxPanel());

                        HomePage.this.setFooter(new LoginStatusPanel(HomePage.this.getFooter().getId()));
                        ajaxTarget.addComponent(HomePage.this.getFooter());
                    }
                }.execute();
            }
        };
    }

    /* definidos porque el PMD chilla */

    public EmployeeSearchModel getEmployeeSearchModel() {
        return employeeSearchModel;
    }

    public void setEmployeeSearchModel(final EmployeeSearchModel employeeSearchModel) {
        this.employeeSearchModel = employeeSearchModel;
    }

    public ProjectSearchModel getProjectSearchModel() {
        return projectSearchModel;
    }

    public void setProjectSearchModel(final ProjectSearchModel projectSearchModel) {
        this.projectSearchModel = projectSearchModel;
    }

    public SalarySpecSearchModel getSalarySpecSearchModel() {
        return salarySpecSearchModel;
    }

    public void setSalarySpecSearchModel(final SalarySpecSearchModel salarySpecSearchModel) {
        this.salarySpecSearchModel = salarySpecSearchModel;
    }

    public LeaveRequestSearchModel getLeaveReqSearchModel() {
        return leaveReqSearchModel;
    }

    public void setLeaveReqSearchModel(final LeaveRequestSearchModel leaveReqSearchModel) {
        this.leaveReqSearchModel = leaveReqSearchModel;
    }

    public AddDefaultValuesService getAddDefaultValuesService() {
        return addDefaultValuesService;
    }

    public void setAddDefaultValuesService(final AddDefaultValuesService addDefaultValuesService) {
        this.addDefaultValuesService = addDefaultValuesService;
    }

    public void setProjectAssignment(final AssignmentProjectSearchModel projectAssignment) {
        this.projectAssignment = projectAssignment;
    }

    public AssignmentProjectSearchModel getProjectAssignment() {
        return projectAssignment;
    }
}
