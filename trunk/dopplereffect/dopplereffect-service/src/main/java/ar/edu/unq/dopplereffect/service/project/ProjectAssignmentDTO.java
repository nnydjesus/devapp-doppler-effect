package ar.edu.unq.dopplereffect.service.project;

import java.util.HashSet;
import java.util.Set;

import ar.edu.unq.dopplereffect.service.DTO;
import ar.edu.unq.dopplereffect.service.employee.EmployeeViewDTO;
import ar.edu.unq.dopplereffect.time.IntervalDurationStrategy;

/**
 */
public class ProjectAssignmentDTO implements DTO {
    private static final long serialVersionUID = 1L;

    private EmployeeViewDTO employeeDTO;

    private Set<IntervalDurationStrategy> intervals = new HashSet<IntervalDurationStrategy>();

    public ProjectAssignmentDTO(final EmployeeViewDTO employeeViewDTO,
            final Set<IntervalDurationStrategy> intervalDurationStrategies) {
        super();
        employeeDTO = employeeViewDTO;
        intervals = intervalDurationStrategies;
    }

    public void setEmployeeDTO(final EmployeeViewDTO employeeDTO) {
        this.employeeDTO = employeeDTO;
    }

    public EmployeeViewDTO getEmployeeDTO() {
        return employeeDTO;
    }

    public void setIntervals(final Set<IntervalDurationStrategy> intervals) {
        this.intervals = intervals;
    }

    public Set<IntervalDurationStrategy> getIntervals() {
        return intervals;
    }

}
