package ar.edu.unq.dopplereffect.presentation.panel.employee;

import java.util.Arrays;

import ar.edu.unq.dopplereffect.presentation.search.AbstractSearchPanel;
import ar.edu.unq.dopplereffect.presentation.util.CallBack;

/**
 * Representa la pagina de busqueda de empleados.
 */
public class EmployeeSearchPanel extends AbstractSearchPanel<EmployeeSearch> {

    private static final long serialVersionUID = -7425688577267166062L;

    public EmployeeSearchPanel(final String id, final CallBack parentPage) {
        super(id, parentPage, new EmployeeSearch(), Arrays.asList("firstName", "lastName", "dni", "phoneNumber",
                "email"), EmployeePanel.class);
    }

    @Override
    protected String getSortName() {
        return "personalData.firstName";
    }

}
