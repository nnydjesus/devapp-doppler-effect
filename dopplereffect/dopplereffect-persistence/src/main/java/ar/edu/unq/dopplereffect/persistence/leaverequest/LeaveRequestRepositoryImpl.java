package ar.edu.unq.dopplereffect.persistence.leaverequest;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;

import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.exceptions.UserException;
import ar.edu.unq.dopplereffect.leaverequests.LeaveRequest;
import ar.edu.unq.dopplereffect.persistence.repositories.HibernatePersistentRepository;

public class LeaveRequestRepositoryImpl extends HibernatePersistentRepository<LeaveRequest> {

    private static final long serialVersionUID = 8356707238183454593L;

    // esto lo hice por el PMD, es realmente absurdo
    private static final String UNCHECKED = "unchecked";

    public LeaveRequestRepositoryImpl() {
        super(LeaveRequest.class);
    }

    @SuppressWarnings(UNCHECKED)
    public List<LeaveRequest> searchAllByEmployee(final Employee employee) {
        Criteria criteria = this.getSession().createCriteria(this.getEntityClass());
        criteria.add(Restrictions.eq("employee", employee));
        return criteria.list();
    }

    @SuppressWarnings(UNCHECKED)
    public List<LeaveRequest> searchAllByDateAndEmployee(final DateTime dateTime, final Employee employee) {
        Criteria criteria = this.getSession().createCriteria(this.getEntityClass());
        criteria.add(Restrictions.eq("employee", employee));
        // se me complica para buscar si la fecha cae dentro de inicio y fin
        // necesito joinear
        // DurationStrategy oneDayDS = new OneDayDurationStrategy(dateTime);
        // DurationStrategy intervalDS = new IntervalDurationStrategy(dateTime,
        // dateTime);
        // criteria.add(Restrictions.or(Restrictions.eq("durationStrategy",
        // oneDayDS),
        // Restrictions.eq("durationStrategy", intervalDS)));
        return criteria.list();
    }

    @SuppressWarnings(UNCHECKED)
    public LeaveRequest searchByStartDateAndEmployee(final DateTime dateTime, final Employee employee) {
        Criteria criteria = this.getSession().createCriteria(this.getEntityClass());
        criteria.add(Restrictions.eq("employee", employee));
        // se me complica para buscar si la fecha cae dentro de inicio y fin
        // necesito joinear
        // DurationStrategy oneDayDS = new OneDayDurationStrategy(dateTime);
        // DurationStrategy intervalDS = new IntervalDurationStrategy(dateTime,
        // dateTime);
        // criteria.add(Restrictions.or(Restrictions.eq("durationStrategy",
        // oneDayDS),
        // Restrictions.eq("durationStrategy", intervalDS)));
        List<LeaveRequest> results = criteria.list();
        if (results.isEmpty()) {
            throw new UserException("no se pudo encontrar la licencia");
        }
        return results.get(0);
    }

    @SuppressWarnings(UNCHECKED)
    public List<LeaveRequest> searchAllByDate(final DateTime dateTime) {
        Criteria criteria = this.getSession().createCriteria(this.getEntityClass());
        // necesito joinear
        // DurationStrategy oneDayDS = new OneDayDurationStrategy(dateTime);
        // DurationStrategy intervalDS = new IntervalDurationStrategy(dateTime,
        // dateTime);
        // criteria.add(Restrictions.or(Restrictions.eq("durationStrategy",
        // oneDayDS),
        // Restrictions.eq("durationStrategy", intervalDS)));
        return criteria.list();
    }
}
