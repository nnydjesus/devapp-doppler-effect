package ar.edu.unq.dopplereffect.presentation.pages;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.unq.dopplereffect.presentation.App;
import ar.edu.unq.dopplereffect.presentation.employee.EmployeeSearchModel;
import ar.edu.unq.dopplereffect.presentation.panel.HeaderPanel;
import ar.edu.unq.dopplereffect.presentation.panel.calendar.CalendarPanel;
import ar.edu.unq.dopplereffect.presentation.panel.employee.EmployeeSearchPanel;
import ar.edu.unq.dopplereffect.presentation.panel.leaverequest.LeaveRequestSearchPanel;
import ar.edu.unq.dopplereffect.presentation.panel.project.ProjectSearchPanel;
import ar.edu.unq.dopplereffect.presentation.panel.project.SkillSearchPanel;
import ar.edu.unq.dopplereffect.presentation.panel.salaryspec.SalarySpecSearchPanel;
import ar.edu.unq.dopplereffect.presentation.project.ProjectSearchModel;
import ar.edu.unq.dopplereffect.presentation.project.SkillSearchModel;
import ar.edu.unq.dopplereffect.presentation.search.SearchModel;
import ar.edu.unq.dopplereffect.presentation.search.leaverequest.LeaveRequestSearchModel;
import ar.edu.unq.dopplereffect.presentation.search.salaryspec.SalarySpecSearchModel;
import ar.edu.unq.dopplereffect.presentation.util.AjaxCallBack;
import ar.edu.unq.dopplereffect.service.AddDefaultValuesService;

/**
 * Simple home page.
 */
public class HomePage extends AbstractWebPage<Component> {

    private static final long serialVersionUID = 1L;

    @SpringBean(name = "employeeSearchModel")
    private EmployeeSearchModel employeeSearchModel;

    @SpringBean(name = "projectSearchModel")
    private ProjectSearchModel projectSearchModel;

    @SpringBean(name = "skillSearchModel")
    private SkillSearchModel skillSearchModel;

    @SpringBean(name = "salarySpecSearchModel")
    private SalarySpecSearchModel salarySpecSearchModel;

    @SpringBean(name = "leaveReqSearchModel")
    private LeaveRequestSearchModel leaveReqSearchModel;

    @SpringBean(name = "addDefaultValuesService")
    private AddDefaultValuesService addDefaultValuesService;

    public HomePage() {
        super();
        if (!App.isCreate()) {
            addDefaultValuesService.addAllData();
            App.setCreate(true);
        }

        final AjaxCallBack<Component> callback = this.generateCallback();
        String bodyId = "body";
        this.add(this.createPanelLink("projects", new ProjectSearchPanel(bodyId, callback, projectSearchModel)));
        this.add(this.createPanelLink("skills", new SkillSearchPanel(bodyId, callback, skillSearchModel)));
        this.add(this.createPanelLink("employees", new EmployeeSearchPanel(bodyId, callback, employeeSearchModel,
                leaveReqSearchModel)));
        this.add(this.createPanelLink("salary_percentages", new SalarySpecSearchPanel(bodyId, callback,
                salarySpecSearchModel)));
        this.add(this.createPanelLink("leave_requests", new LeaveRequestSearchPanel(bodyId, callback,
                leaveReqSearchModel)));
        // this.add(this.createPanelLink("career_plans", new
        // CareerPlanPanel(bodyId, callback)));

        this.add(this.createPanelLink("calendar", new CalendarPanel(bodyId, employeeSearchModel)));
        this.add(new HeaderPanel("items"));
    }

    private Component createPanelLink(final String id, final Panel panel) {
        return new AjaxLink<Object>(id) {
            private static final long serialVersionUID = 1L;

            @Override
            @SuppressWarnings("rawtypes")
            public void onClick(final AjaxRequestTarget target) {
                ((SearchModel) panel.getDefaultModelObject()).reset();
                HomePage.this.generateCallback().execute(target, panel);
            }

            // @Override
            // protected IAjaxCallDecorator getAjaxCallDecorator() {
            // return new AjaxCallDecorator() {
            //
            // private static final long serialVersionUID = 1L;
            //
            // @Override
            // public CharSequence decorateOnSuccessScript(final CharSequence
            // script) {
            // SlideDown effect = new SlideDown(EffectSpeed.SLOW);
            // return new
            // JsStatement().$(panel).chain(effect).render(true).toString() +
            // script + ";";
            // }
            //
            // @Override
            // public CharSequence decorateScript(final CharSequence script) {
            // Effect effect = new FadeIn(EffectSpeed.SLOW);
            // effect.setCallback(new JsScope() {
            // private static final long serialVersionUID = 1L;
            //
            // @Override
            // protected void execute(final JsScopeContext scopeContext) {
            // scopeContext.append(script);
            // }
            // });
            // return new JsStatement().$(panel).chain(effect).render(true);
            // }
            // };
            // }

        };
    }

    protected AjaxCallBack<Component> generateCallback() {
        return new AjaxCallBack<Component>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void execute(final AjaxRequestTarget ajaxTarget, final Component component) {
                if (component == null) {
                    HomePage.this.setDefaultBody();
                } else {
                    HomePage.this.setBody(component);
                }
                ajaxTarget.addComponent(HomePage.this.getAjaxPanel());
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

    public SkillSearchModel getSkillSearchModel() {
        return skillSearchModel;
    }

    public void setSkillSearchModel(final SkillSearchModel skillSearchModel) {
        this.skillSearchModel = skillSearchModel;
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

    public void setAddDefaultValuesService(final AddDefaultValuesService addDefaultValuesService) {
        this.addDefaultValuesService = addDefaultValuesService;
    }

    public AddDefaultValuesService getAddDefaultValuesService() {
        return addDefaultValuesService;
    }
}
