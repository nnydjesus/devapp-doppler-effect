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

    public EmployeeRepositoryImpl() {
        super(Employee.class);
    }

    @SuppressWarnings("unchecked")
    public Employee findFirstWithName(final String firstName, final String lastName) {
        Criteria personalDataCriteria = this.getSession().createCriteria(PersonalData.class);
        personalDataCriteria.add(Restrictions.eq("firstName", firstName));
        personalDataCriteria.add(Restrictions.eq("lastName", lastName));
        List<PersonalData> personalDataResults = personalDataCriteria.list();
        if (personalDataResults.isEmpty()) {
            throw new UserException("No se pudieron encontrar los datos personales del empleado");
        }
        PersonalData personaldata = personalDataResults.get(0);
        return this.getByCriterion(Restrictions.eq("personalData", personaldata));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Employee> searchByName(final String name) {
        Criteria personalDataCriteria = this.getSession().createCriteria(PersonalData.class);
        personalDataCriteria.add(Restrictions.like("firstName", "%" + name + "%"));
        List<PersonalData> personalDataResults = personalDataCriteria.list();
        return this.getByCriterionList(Restrictions.in("personalData", personalDataResults));
    }

    @SuppressWarnings("unchecked")
    public Employee searchByDni(final int dni) {
        Criteria personalDataCriteria = this.getSession().createCriteria(PersonalData.class);
        personalDataCriteria.add(Restrictions.eq("dni", dni));
        List<PersonalData> personalDataResults = personalDataCriteria.list();
        if (personalDataResults.isEmpty()) {
            throw new UserException("No se pudieron encontrar los datos personales del empleado");
        }
        PersonalData personaldata = personalDataResults.get(0);
        return this.getByCriterion(Restrictions.eq("personalData", personaldata));
    }
}
