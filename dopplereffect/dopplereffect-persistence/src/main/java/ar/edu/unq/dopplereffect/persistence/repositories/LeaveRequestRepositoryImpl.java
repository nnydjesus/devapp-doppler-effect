package ar.edu.unq.dopplereffect.persistence.repositories;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;

import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.leaverequests.LeaveRequest;

public class LeaveRequestRepositoryImpl extends HibernatePersistentRepository<LeaveRequest> {

    private static final long serialVersionUID = 8356707238183454593L;

    public LeaveRequestRepositoryImpl() {
        super(LeaveRequest.class);
    }

    public List<LeaveRequest> searchByDate(final DateTime date) {
        // TODO implementar con criteria's
        return super.searchAll();
    }

    @SuppressWarnings({ "unchecked", "cast" })
    public List<LeaveRequest> searchByEmployee(final Employee employee) {
        Criteria criteria = this.getSession().createCriteria(this.getEntityClass());
        criteria.add(Restrictions.eq("employee", employee));
        return (List<LeaveRequest>) criteria.list();
    }

}
