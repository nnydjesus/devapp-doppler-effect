package ar.edu.unq.dopplereffect.persistence.employee;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import ar.edu.unq.dopplereffect.data.PersonalData;
import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.exceptions.UserException;
import ar.edu.unq.dopplereffect.persistence.repositories.HibernatePersistentRepository;

public class EmployeeRepositoryImpl extends HibernatePersistentRepository<Employee> {

    private static final long serialVersionUID = 1L;

    private static final String UNCHECKED = "unchecked";

    /* ************************ INSTANCE VARIABLES ************************ */

    /* *************************** CONSTRUCTORS *************************** */

    public EmployeeRepositoryImpl() {
        super(Employee.class);
    }

    /* **************************** ACCESSORS ***************************** */

    /* **************************** OPERATIONS **************************** */

    @SuppressWarnings(UNCHECKED)
    public Employee findFirstWithName(final String firstName, final String lastName) {
        Criteria criteria = this.getSession().createCriteria(this.getEntityClass());
        Criteria personalDataCriteria = this.getSession().createCriteria(PersonalData.class);
        personalDataCriteria.add(Restrictions.eq("firstName", firstName));
        personalDataCriteria.add(Restrictions.eq("lastName", lastName));
        List<PersonalData> personalDataResults = personalDataCriteria.list();
        if (personalDataResults.isEmpty()) {
            throw new UserException("No se pudieron encontrar los datos personales del empleado");
        }
        PersonalData personaldata = personalDataResults.get(0);
        criteria.add(Restrictions.eq("personalData", personaldata));
        List<Employee> results = criteria.list();
        if (results.isEmpty()) {
            throw new UserException("No se pudo encontrar ningun empleado con los nombres dados");
        }
        return results.get(0);
    }

    @SuppressWarnings(UNCHECKED)
    public Employee searchByDni(final int dni) {
        Criteria criteria = this.getSession().createCriteria(this.getEntityClass());
        criteria.add(Restrictions.eq("personalData", this.getPersonalDataByDni(dni)));
        List<Employee> results = criteria.list();
        if (results.isEmpty()) {
            throw new UserException("No se pudo encontrar ningun empleado con el dni dado");
        }
        return results.get(0);
    }

    /**
     * Realiza una busqueda de aquellos empleados, por nombre y apellido.
     */
    @SuppressWarnings(UNCHECKED)
    public List<Employee> searchByFirstAndLastName(final String firstName, final String lastName) {
        String searchFirstName = firstName == null ? "" : firstName;
        String searchLastName = lastName == null ? "" : lastName;
        Criteria criteria = this.getSession().createCriteria(this.getEntityClass()).createCriteria("personalData")
                .add(Restrictions.ilike("firstName", "%" + searchFirstName + "%"))
                .add(Restrictions.ilike("lastName", "%" + searchLastName + "%"));
        return criteria.list();
    }

    /* ************************* PRIVATE METHODS ************************** */

    @SuppressWarnings(UNCHECKED)
    private PersonalData getPersonalDataByDni(final int dni) {
        Criteria personalDataCriteria = this.getSession().createCriteria(PersonalData.class);
        personalDataCriteria.add(Restrictions.eq("dni", dni));
        List<PersonalData> personalDataResults = personalDataCriteria.list();
        if (personalDataResults.isEmpty()) {
            throw new UserException("No se pudieron encontrar los datos personales del empleado");
        }
        return personalDataResults.get(0);
    }
}
