package ar.edu.unq.dopplereffect.presentation.pages.employee;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;

import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.presentation.component.PageLink;
import ar.edu.unq.dopplereffect.presentation.search.AbstractSearchPage2;
import ar.edu.unq.dopplereffect.presentation.search.CustomListView;

/**
 * Representa la pagina de busqueda de empleados.
 */
public class EmployeeSearchPage extends AbstractSearchPage2<Employee, EmployeeSearch> {

    private static final long serialVersionUID = -7425688577267166062L;

    public EmployeeSearchPage(final WebPage previousPage) {
        super(new EmployeeSearch(), previousPage);
        this.getModelObject().setSourcePage(this);
    }

    @Override
    protected void buildForm(final Form<EmployeeSearch> form) {
        form.add(new TextField<String>("searchName"));
    }

    @Override
    protected PageLink getCreateObjectLink() {
        return new PageLink("newEmployeeLink", new EmployeePage(this));
    }

    @Override
    protected CustomListView<Employee, EmployeeSearch> getListView() {
        // @formatter:off
        return new CustomListView<Employee, EmployeeSearch>("results", 
                new String[]{"firstName", "lastName", "dni", "phoneNumber", "email", "joinDate"},
                EmployeePage.class);
        // @formatter:on
    }
}
