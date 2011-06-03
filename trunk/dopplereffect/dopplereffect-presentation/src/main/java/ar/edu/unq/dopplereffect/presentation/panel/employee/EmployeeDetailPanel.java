package ar.edu.unq.dopplereffect.presentation.panel.employee;

import java.io.Serializable;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.PropertyModel;

import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.presentation.pages.basic.NavigablePanel;
import ar.edu.unq.dopplereffect.presentation.panel.utils.AbstractCallbackPanel;

/**
 * Representa un panel que muestra de manera detallada todos los datos de un
 * empleado.
 */
public class EmployeeDetailPanel extends NavigablePanel<Employee> {

    private static final long serialVersionUID = 7612811876399884445L;

    public EmployeeDetailPanel(final String id, final Employee model,
            final AbstractCallbackPanel<? extends Serializable> previousPage) {
        super(id, model, previousPage);
        this.addEmployeeDetails();
        this.addNavigationButtons();
    }

    protected void addEmployeeDetails() {
        this.addLabelFor("firstName");
        this.addLabelFor("lastName");
        this.addLabelFor("dni");
        this.addLabelFor("address", "personalData.address");
        this.addLabelFor("phoneNumber", "personalData.phoneNumber");
        this.addLabelFor("email", "personalData.email");
        this.addLabelFor("joinDate", "careerData.joinDate");
        this.addLabelFor("careerPlan", "careerData.careerPlan");
        this.addLabelFor("careerLevel", "careerData.level");
        this.addLabelFor("percentage", "careerData.percentage");
    }

    protected void addLabelFor(final String wicketId) {
        this.addLabelFor(wicketId, wicketId);
    }

    protected void addLabelFor(final String wicketId, final String property) {
        this.add(new Label(wicketId, new PropertyModel<String>(this.getDefaultModelObject(), property)));
    }

    protected void addNavigationButtons() {
        // TODO
    }

}
