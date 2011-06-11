package ar.edu.unq.dopplereffect.presentation.panel.employee;

import java.io.Serializable;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.StringResourceModel;

import ar.edu.unq.dopplereffect.presentation.pages.basic.NavigablePanel;
import ar.edu.unq.dopplereffect.presentation.panel.utils.AbstractCallbackPanel;
import ar.edu.unq.dopplereffect.presentation.panel.utils.PanelCallbackLink;
import ar.edu.unq.dopplereffect.service.employee.EmployeeDetailDTO;

/**
 * Representa un panel que muestra de manera detallada todos los datos de un
 * empleado.
 */
public class EmployeeDetailPanel extends NavigablePanel<EmployeeDetailDTO> {

    private static final long serialVersionUID = 7612811876399884445L;

    public EmployeeDetailPanel(final String id, final EmployeeDetailDTO employeeViewDTO,
            final AbstractCallbackPanel<? extends Serializable> previousPage) {
        super(id, employeeViewDTO, previousPage);
        this.addEmployeeDetails();
        this.addNavigationButtons();
    }

    protected void addEmployeeDetails() {
        this.addLabelFor("firstName");
        this.addLabelFor("lastName");
        this.addLabelFor("dni");
        this.addLabelFor("address");
        this.addLabelFor("phoneNumber");
        this.addLabelFor("email");
        this.addLabelFor("joinDate");
        this.addLabelFor("careerPlan");
        this.addLabelFor("careerPlanLevel");
        this.addLabelFor("percentage");
    }

    protected void addLabelFor(final String wicketId) {
        this.addLabelFor(wicketId, wicketId);
    }

    protected void addLabelFor(final String wicketId, final String property) {
        this.add(new Label(wicketId, new PropertyModel<String>(this.getDefaultModelObject(), property)));
    }

    protected void addNavigationButtons() {
        this.add(new PanelCallbackLink("back_button", this.getPreviousPage().getCallback(), null,
                new StringResourceModel("back_button", new Model<String>(""))));
    }

}
