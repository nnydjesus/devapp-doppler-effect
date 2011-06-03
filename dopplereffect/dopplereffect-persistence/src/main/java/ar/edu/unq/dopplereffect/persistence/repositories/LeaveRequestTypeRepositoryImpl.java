package ar.edu.unq.dopplereffect.persistence.repositories;

import ar.edu.unq.dopplereffect.leaverequests.LeaveRequestType;

public class LeaveRequestTypeRepositoryImpl extends HibernatePersistentRepository<LeaveRequestType> {

    private static final long serialVersionUID = 8356707238183454593L;

    public LeaveRequestTypeRepositoryImpl() {
        super(LeaveRequestType.class);
    }
}
