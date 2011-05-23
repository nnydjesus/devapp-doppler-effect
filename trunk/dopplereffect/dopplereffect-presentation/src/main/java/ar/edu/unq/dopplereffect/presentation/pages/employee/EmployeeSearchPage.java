package ar.edu.unq.dopplereffect.presentation.pages.employee;

import java.util.Arrays;

import org.apache.wicket.markup.html.WebPage;

import ar.edu.unq.dopplereffect.presentation.search.AbstractSearchPage;

/**
 * Representa la pagina de busqueda de empleados.
 */
public class EmployeeSearchPage extends AbstractSearchPage<EmployeeSearch> {

    private static final long serialVersionUID = -7425688577267166062L;
    
    public EmployeeSearchPage(final WebPage parentPage) {
        super(parentPage, new EmployeeSearch(), Arrays.asList("firstName", "lastName", "dni", "phoneNumber", "email", "joinDate"), EmployeePage.class);
    }

}
