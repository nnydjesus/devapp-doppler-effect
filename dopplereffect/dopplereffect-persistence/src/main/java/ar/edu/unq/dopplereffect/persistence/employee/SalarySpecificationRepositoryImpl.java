package ar.edu.unq.dopplereffect.persistence.employee;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import ar.edu.unq.dopplereffect.employees.CareerPlan;
import ar.edu.unq.dopplereffect.employees.CareerPlanLevel;
import ar.edu.unq.dopplereffect.exceptions.UserException;
import ar.edu.unq.dopplereffect.persistence.HibernatePersistentRepository;
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

    @Override
    public void save(final SalarySpecification salarySpec) {
        this.checkForExistentSalarySpec(salarySpec);
        super.save(salarySpec);
    }

    private void checkForExistentSalarySpec(final SalarySpecification salarySpec) {
        Criteria criteria = this.getSession().createCriteria(this.getEntityClass());
        criteria.add(Restrictions.eq("year", salarySpec.getYear()));
        criteria.add(Restrictions.eq("plan", salarySpec.getPlan()));
        criteria.add(Restrictions.eq("level", salarySpec.getLevel()));
        if (!criteria.list().isEmpty()) {
            throw new UserException("Ya existe una banda de sueldo para los parametros dados");
        }
    }

    @SuppressWarnings("unchecked")
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
}
