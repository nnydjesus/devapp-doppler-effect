package ar.edu.unq.dopplereffect.presentation.pages;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.panel.Panel;

import ar.edu.unq.dopplereffect.presentation.panel.employee.EmployeeSearchPanel;
import ar.edu.unq.dopplereffect.presentation.panel.project.ProjectSearchPanel;
import ar.edu.unq.dopplereffect.presentation.panel.project.SkillSearchPanel;
import ar.edu.unq.dopplereffect.presentation.panel.utils.PageLink;
import ar.edu.unq.dopplereffect.presentation.util.CallBack;

/**
 * Simple home page.
 */
public class Home extends StylePage<Component> {
    private static final long serialVersionUID = 1L;

    public Home(final Page parent) {
        super();
        final CallBack<Component> callback = this.generateCallback(new AjaxRequestTarget(this));
        this.add(this.createPanelLink("projects", new ProjectSearchPanel("body", callback)));
        this.add(this.createPanelLink("skills", new SkillSearchPanel("body", callback)));
        this.add(this.createPanelLink("employee", new EmployeeSearchPanel("body", callback)));
        this.add(new PageLink("logout", parent));
    }

    private Component createPanelLink(final String id, final Panel panel) {
        return new AjaxLink<Object>(id) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(final AjaxRequestTarget target) {
                Home.this.generateCallback(target).execute(panel);
            }

        };
    }

    protected CallBack<Component> generateCallback(final AjaxRequestTarget ajaxTarget) {
        return new CallBack<Component>() {
            private static final long serialVersionUID = 1L;

            private AjaxRequestTarget target = ajaxTarget;

            @Override
            public void execute(final Component component) {
                if (component != null) {
                    Home.this.setBody(component);
                } else {
                    Home.this.setDefaultBody();
                }
                target.add(Home.this.getAjaxPanel());
            }
        };
    }
}
