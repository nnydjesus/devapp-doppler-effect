package ar.edu.unq.dopplereffect.presentation.panel;

import org.apache.wicket.Component;

import ar.edu.unq.dopplereffect.presentation.pages.HomePage;
import ar.edu.unq.dopplereffect.presentation.panel.calendar.CalendarPanel;
import ar.edu.unq.dopplereffect.presentation.panel.employee.EmployeeSearchPanel;
import ar.edu.unq.dopplereffect.presentation.panel.leaverequest.LeaveRequestSearchPanel;
import ar.edu.unq.dopplereffect.presentation.panel.project.ProjectSearchPanel;
import ar.edu.unq.dopplereffect.presentation.panel.salaryspec.SalarySpecSearchPanel;
import ar.edu.unq.dopplereffect.presentation.panel.utils.AbstractPanel;
import ar.edu.unq.dopplereffect.presentation.util.AjaxCallBack;
import ar.edu.unq.dopplereffect.presentation.util.Model;
import ar.edu.unq.dopplereffect.service.employee.EmployeeViewDTO;

/**
 */
public class SidebarPanel extends AbstractPanel<Model<String>> {

    private static final long serialVersionUID = 1L;

    public SidebarPanel(final String id, final String idPanel, final AjaxCallBack<Component> callback,
            final HomePage homePage) {
        super(id);
        this.add(homePage.createPanelLink("projects",
                new ProjectSearchPanel(idPanel, callback, homePage.getProjectSearchModel())));
        this.add(homePage.createPanelLink(
                "employees",
                new EmployeeSearchPanel(idPanel, callback, homePage.getEmployeeSearchModel(), homePage
                        .getLeaveReqSearchModel())));
        this.add(homePage.createPanelLink("salary_percentages",
                new SalarySpecSearchPanel(idPanel, callback, homePage.getSalarySpecSearchModel())));
        this.add(homePage.createPanelLink("leave_requests",
                new LeaveRequestSearchPanel(idPanel, callback, homePage.getLeaveReqSearchModel())));
        // this.add(this.createPanelLink("career_plans", new
        // CareerPlanPanel(bodyId, callback)));

        this.add(homePage.createPanelLink(
                "calendar",
                new CalendarPanel<EmployeeViewDTO>(idPanel, homePage.getEmployeeSearchModel(), homePage
                        .getLeaveReqSearchModel(), callback)));
    }

}
