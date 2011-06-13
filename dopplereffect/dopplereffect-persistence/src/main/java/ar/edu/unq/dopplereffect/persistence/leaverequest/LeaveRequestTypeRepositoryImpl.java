package ar.edu.unq.dopplereffect.persistence.leaverequest;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import ar.edu.unq.dopplereffect.leaverequests.LeaveRequestType;
import ar.edu.unq.dopplereffect.persistence.repositories.HibernatePersistentRepository;
import ar.edu.unq.tpi.util.commons.exeption.UserException;

public class LeaveRequestTypeRepositoryImpl extends HibernatePersistentRepository<LeaveRequestType> {

    private static final long serialVersionUID = 8356707238183454593L;

    public LeaveRequestTypeRepositoryImpl() {
        super(LeaveRequestType.class);
    }

    @SuppressWarnings("unchecked")
    public LeaveRequestType searchByReason(final String reason) {
        Criteria criteria = this.getSession().createCriteria(this.getEntityClass());
        criteria.add(Restrictions.eq("reason", reason));
        List<LeaveRequestType> results = criteria.list();
        if (results.isEmpty()) {
            throw new UserException("No se puede encontrar un tipo de  licencia con una razon igual a la especificada");
        }
        return results.get(0);
    }

    public List<String> searchAllReasons() {
        List<LeaveRequestType> types = this.searchAll();
        List<String> results = new LinkedList<String>();
        for (LeaveRequestType type : types) {
            results.add(type.getReason());
        }
        return results;
    }
}
