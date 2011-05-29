package ar.edu.unq.dopplereffect.presentation.pages;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.effects.EffectBehavior;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.ui.effects.PulsateEffect;
import org.odlabs.wiquery.ui.effects.PulsateEffect.PulsateMode;
import org.odlabs.wiquery.ui.themes.ThemeUiHelper;
import org.odlabs.wiquery.ui.themes.WiQueryCoreThemeResourceReference;

import ar.edu.unq.dopplereffect.presentation.panel.HeaderPanel;
import ar.edu.unq.dopplereffect.presentation.panel.employee.EmployeeSearchPanel;
import ar.edu.unq.dopplereffect.presentation.panel.project.ProjectSearchPanel;
import ar.edu.unq.dopplereffect.presentation.panel.project.SkillSearchPanel;
import ar.edu.unq.dopplereffect.presentation.panel.salaryspec.SalarySpecSearchPanel;
import ar.edu.unq.dopplereffect.presentation.util.AjaxCallBack;

/**
 * Simple home page.
 */
public class Home extends StylePage<Component> implements IWiQueryPlugin {
    private static final long serialVersionUID = 1L;

    public Home() {
        super();
        EffectBehavior effect1 = new EffectBehavior(new PulsateEffect(PulsateMode.show, 10, 1000));
        // effect2 = new EffectBehavior(new Show());
        this.add(effect1);
        final AjaxCallBack<Component> callback = this.generateCallback();
        this.add(this.createPanelLink("projects", new ProjectSearchPanel("body", callback)));
        this.add(this.createPanelLink("skills", new SkillSearchPanel("body", callback)));
        this.add(this.createPanelLink("employees", new EmployeeSearchPanel("body", callback)));
        this.add(this.createPanelLink("salary_percentages", new SalarySpecSearchPanel("body", callback)));
        this.add(new HeaderPanel("items"));
    }

    private Component createPanelLink(final String id, final Panel panel) {
        return new AjaxLink<Object>(id) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(final AjaxRequestTarget target) {
                Home.this.generateCallback().execute(target, panel);
            }

        };
    }

    protected AjaxCallBack<Component> generateCallback() {
        return new AjaxCallBack<Component>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void execute(final AjaxRequestTarget ajaxTarget, final Component component) {
                if (component != null) {
                    Home.this.setBody(component);
                } else {
                    Home.this.setDefaultBody();
                }
                ajaxTarget.addComponent(Home.this.getAjaxPanel());
            }
        };
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.odlabs.wiquery.core.commons.IWiQueryPlugin#contribute(org.odlabs.wiquery.core.commons.WiQueryResourceManager)
     */
    @Override
    public void contribute(final WiQueryResourceManager wiQueryResourceManager) {
        wiQueryResourceManager.addCssResource(new WiQueryCoreThemeResourceReference("le-frog"));
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.odlabs.wiquery.core.commons.IWiQueryPlugin#statement()
     */
    @Override
    public JsStatement statement() {
        // ThemeUiHelper.shadowComponent(this.getAjaxPanel());
        return ThemeUiHelper.hover(this.getPage());
    }
}
