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

    /* ************************ INSTANCE VARIABLES ************************ */

    /* *************************** CONSTRUCTORS *************************** */

    public LeaveRequestRepositoryImpl() {
        super(LeaveRequest.class);
    }

    /* **************************** OPERATIONS **************************** */

    public List<LeaveRequest> searchAllByEmployee(final Employee employee) {
        return this.getByCriterionList(Restrictions.eq("employee", employee));
    }

    @SuppressWarnings(UNCHECKED)
    public List<LeaveRequest> searchAllByDateAndEmployee(final DateTime dateTime, final Employee employee) {
        Criteria criteria = this.getSession().createCriteria(this.getEntityClass());
        criteria.add(Restrictions.eq("employee", employee));
        return criteria.list();
    }

    /**
     * Busca una licencia por su fecha de inicio y empleado correspondiente.
     */
    @SuppressWarnings(UNCHECKED)
    public LeaveRequest searchByStartDateAndEmployee(final DateTime dateTime, final Employee employee) {
        // @formatter:off
        Criteria criteria = this.getSession().createCriteria(this.getEntityClass())
                .add(Restrictions.eq("employee", employee))
                .createCriteria("durationStrategy")
                    .add(Restrictions.disjunction()
                        .add(Restrictions.eq("date", dateTime))
                        .add(Restrictions.sqlRestriction("{alias}.start_date = '" + dateTime.toString("yyyy-MM-dd HH:mm:ss.SSS") + "'"))
                    );
        // @formatter:on
        List<LeaveRequest> results = criteria.list();
        if (results.isEmpty()) {
            throw new UserException("no se pudo encontrar la licencia con los datos dados");
        }
        return results.get(0);
    }

    @SuppressWarnings(UNCHECKED)
    public List<LeaveRequest> searchAllByDate(final DateTime dateTime) {
        Criteria criteria = this.getSession().createCriteria(this.getEntityClass());
        return criteria.list();
    }

    @SuppressWarnings(UNCHECKED)
    public List<LeaveRequest> searchAllByReasonAndEmployee(final String reason, final Employee employee) {
        String searchReason = reason == null ? "" : reason;
        Criteria criteria;
        if (employee == null) {
            criteria = this.getSession().createCriteria(this.getEntityClass()).createCriteria("type")
                    .add(Restrictions.like("reason", "%" + searchReason + "%"));
        } else {
            criteria = this.getSession().createCriteria(this.getEntityClass())
                    .add(Restrictions.eq("employee", employee)).createCriteria("type")
                    .add(Restrictions.like("reason", "%" + searchReason + "%"));
        }
        return criteria.list();
    }
}
