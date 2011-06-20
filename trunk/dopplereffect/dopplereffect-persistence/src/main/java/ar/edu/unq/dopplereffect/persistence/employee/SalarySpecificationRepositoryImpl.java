package ar.edu.unq.dopplereffect.persistence.employee;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import ar.edu.unq.dopplereffect.employees.CareerPlan;
import ar.edu.unq.dopplereffect.employees.CareerPlanLevel;
import ar.edu.unq.dopplereffect.persistence.repositories.HibernatePersistentRepository;
import ar.edu.unq.dopplereffect.salaries.SalarySpecification;

public class SalarySpecificationRepositoryImpl extends HibernatePersistentRepository<SalarySpecification> {

    private static final long serialVersionUID = 1L;

    private static final String UNCHECKED = "unchecked";

    /* ************************ INSTANCE VARIABLES ************************ */

    /* *************************** CONSTRUCTORS *************************** */

    public SalarySpecificationRepositoryImpl() {
        super(SalarySpecification.class);
    }

    /* **************************** ACCESSORS ***************************** */

    /* **************************** OPERATIONS **************************** */

    @SuppressWarnings(UNCHECKED)
    public SalarySpecification getByPlanAndLevel(final CareerPlan plan, final CareerPlanLevel level) {
        Criteria criteria = this.getSession().createCriteria(this.getEntityClass());
        criteria.add(Restrictions.eq("plan", plan));
        criteria.add(Restrictions.eq("level", level));
        List<SalarySpecification> results = criteria.list();
        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }

    public boolean checkForExistentSalarySpec(final SalarySpecification salarySpec) {
        Criteria criteria = this.getSession().createCriteria(this.getEntityClass());
        criteria.add(Restrictions.eq("year", salarySpec.getYear()));
        criteria.add(Restrictions.eq("plan", salarySpec.getPlan()));
        criteria.add(Restrictions.eq("level", salarySpec.getLevel()));
        return !criteria.list().isEmpty();
    }

    @SuppressWarnings(UNCHECKED)
    public List<SalarySpecification> searchByCareerPlanAndLevel(final CareerPlan careerPlan, final CareerPlanLevel level) {
        Criteria criteria = this.getSession().createCriteria(this.getEntityClass());
        if (careerPlan != null) {
            criteria.add(Restrictions.eq("plan", careerPlan));
        }
        if (level != null) {
            criteria.add(Restrictions.eq("level", level));
        }
        return criteria.list();
    }

    @SuppressWarnings(UNCHECKED)
    public SalarySpecification searchByYearCareerPlanAndLevel(final int year, final CareerPlan plan,
            final CareerPlanLevel level) {
        Criteria criteria = this.getSession().createCriteria(this.getEntityClass());
        criteria.add(Restrictions.eq("year", year));
        if (plan != null) {
            criteria.add(Restrictions.eq("plan", plan));
        }
        if (level != null) {
            criteria.add(Restrictions.eq("level", level));
        }
        List<SalarySpecification> results = criteria.list();
        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }
}
