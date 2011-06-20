package ar.edu.unq.dopplereffect.persistence.leaverequest;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Junction;
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

    private static final String EMPLOYEE_FIELD = "employee";

    /* ************************ INSTANCE VARIABLES ************************ */

    /* *************************** CONSTRUCTORS *************************** */

    public LeaveRequestRepositoryImpl() {
        super(LeaveRequest.class);
    }

    /* **************************** OPERATIONS **************************** */

    public List<LeaveRequest> searchAllByEmployee(final Employee employee) {
        return this.getByCriterionList(Restrictions.eq(EMPLOYEE_FIELD, employee));
    }

    @SuppressWarnings(UNCHECKED)
    public List<LeaveRequest> searchAllByDateAndEmployee(final DateTime dateTime, final Employee employee) {
        Criteria criteria = this.getSession().createCriteria(this.getEntityClass());
        criteria.add(Restrictions.eq(EMPLOYEE_FIELD, employee));
        return criteria.list();
    }

    /**
     * Busca una licencia por su fecha de inicio y empleado correspondiente.
     */
    @SuppressWarnings(UNCHECKED)
    public LeaveRequest searchByStartDateAndEmployee(final DateTime dateTime, final Employee employee) {
        Criteria criteria = this.getSession().createCriteria(this.getEntityClass())
                .add(Restrictions.eq(EMPLOYEE_FIELD, employee)).createCriteria("durationStrategy")
                .add(this.getStartDateRestriction(dateTime));
        List<LeaveRequest> results = criteria.list();
        if (results.isEmpty()) {
            throw new UserException("no se pudo encontrar la licencia con los datos dados");
        }
        return results.get(0);
    }

    @SuppressWarnings(UNCHECKED)
    public List<LeaveRequest> searchAllByDate(final DateTime dateTime) {
        Criteria criteria = this.getSession().createCriteria(this.getEntityClass()).createCriteria("durationStrategy")
                .add(this.getStartDateRestriction(dateTime));
        return criteria.list();
    }

    @SuppressWarnings(UNCHECKED)
    public List<LeaveRequest> searchAllByReasonAndEmployee(final String reason, final Employee employee) {
        String searchReason = reason == null ? "" : reason;
        Criteria criteria;
        if (employee == null) {
            criteria = this.getSession().createCriteria(this.getEntityClass()).createCriteria("type")
                    .add(Restrictions.ilike("reason", "%" + searchReason + "%"));
        } else {
            criteria = this.getSession().createCriteria(this.getEntityClass())
                    .add(Restrictions.eq(EMPLOYEE_FIELD, employee)).createCriteria("type")
                    .add(Restrictions.ilike("reason", "%" + searchReason + "%"));
        }
        return criteria.list();
    }

    @SuppressWarnings(UNCHECKED)
    public List<LeaveRequest> searchLeaveRequestsContainingDate(final DateTime dateTime) {
        if (dateTime == null) {
            return new LinkedList<LeaveRequest>();
        } else {
            String dateString = dateTime.toString("yyyy-MM-dd HH:mm:ss.SSS");
            // @formatter:off
            String includedInIntervalCondition = 
                "{alias}.start_date <= '" + dateString
                + "' and {alias}.end_date >= '" + dateString + "'";
            Criteria criteria = this.getSession().createCriteria(this.getEntityClass())
                .createCriteria("durationStrategy")
                    .add(Restrictions.disjunction()
                        .add(Restrictions.eq("date", dateTime))
                        .add(Restrictions.sqlRestriction(includedInIntervalCondition))
            );
            // @formatter:on
            return criteria.list();
        }
    }

    private Junction getStartDateRestriction(final DateTime dateTime) {
        String isStartDateFromAnInterval = "{alias}.start_date = '" + dateTime.toString("yyyy-MM-dd HH:mm:ss.SSS")
                + "'";
        return Restrictions.disjunction().add(Restrictions.eq("date", dateTime))
                .add(Restrictions.sqlRestriction(isStartDateFromAnInterval));
    }
}
