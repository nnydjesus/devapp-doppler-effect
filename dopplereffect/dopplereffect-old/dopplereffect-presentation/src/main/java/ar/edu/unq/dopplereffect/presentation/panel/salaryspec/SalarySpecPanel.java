package ar.edu.unq.dopplereffect.presentation.panel.salaryspec;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;

import ar.edu.unq.dopplereffect.presentation.pages.basic.EntityPanel;
import ar.edu.unq.dopplereffect.salaries.SalarySpecification;

public class SalarySpecPanel extends EntityPanel<SalarySpecification> {

    private static final long serialVersionUID = -6555172387296650022L;

    public SalarySpecPanel(final String id, final SalarySpecSearchPanel previousPage, final SalarySpecification model,
            final Boolean editMode) {
        super(id, model, previousPage, editMode);
    }

    public SalarySpecPanel(final String id, final SalarySpecSearchPanel previousPage, final SalarySpecification model) {
        super(id, model, previousPage);
    }

    public SalarySpecPanel(final String id, final SalarySpecSearchPanel previousPage) {
        super(id, new SalarySpecification(), previousPage);
    }

    @Override
    protected void addFields(final Form<SalarySpecification> form) {
        form.add(this.getFeedbackPanel());
        form.add(new RequiredTextField<Integer>("year"));
        form.add(new RequiredTextField<Float>("minSalary"));
        form.add(new RequiredTextField<Float>("maxSalary"));
    }

    @Override
    protected String getFormWicketId() {
        return "salarySpecForm";
    }

}
