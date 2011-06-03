package ar.edu.unq.dopplereffect.service;

import java.util.List;

import org.joda.time.DateTime;

import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.leaverequests.LeaveRequest;
import ar.edu.unq.dopplereffect.persistence.repositories.LeaveRequestRepositoryImpl;

public class LeaveRequestServiceImpl extends ServiceImpl<LeaveRequest> {

    private static final long serialVersionUID = 7638787132914568094L;

    /**
     * Retorna todos aquellas licencias que esten comprendidas en una fecha
     * dada.
     */
    public List<LeaveRequest> searchByDate(final DateTime date) {
        return ((LeaveRequestRepositoryImpl) this.getRepository()).searchByDate(date);
    }

    public List<LeaveRequest> searchByEmployee(final Employee employee) {
        return ((LeaveRequestRepositoryImpl) this.getRepository()).searchByEmployee(employee);
    }
}
