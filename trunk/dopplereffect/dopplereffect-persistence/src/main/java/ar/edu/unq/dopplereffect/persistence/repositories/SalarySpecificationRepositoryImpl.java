package ar.edu.unq.dopplereffect.persistence.repositories;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import ar.edu.unq.dopplereffect.employees.CareerPlan;
import ar.edu.unq.dopplereffect.employees.CareerPlanLevel;
import ar.edu.unq.dopplereffect.salaries.SalarySpecification;

public class SalarySpecificationRepositoryImpl extends HibernatePersistentRepository<SalarySpecification> {

    private static final long serialVersionUID = 1L;

    public SalarySpecificationRepositoryImpl() {
        super(SalarySpecification.class);
    }

    public SalarySpecification getByPlanAndLevel(final CareerPlan plan, final CareerPlanLevel level) {
        Criteria criteria = this.getSession().createCriteria(this.getEntityClass());
        criteria.add(Restrictions.eq("plan", plan));
        criteria.add(Restrictions.eq("level", level));
        @SuppressWarnings("unchecked")
        List<SalarySpecification> results = criteria.list();
        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }

}
