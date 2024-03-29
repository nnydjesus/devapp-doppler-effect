package ar.edu.unq.dopplereffect.leaverequests;

import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.entity.IEntity;

/**
 * Representa un tipo de licencia. Cada tipo de licencia se valida de diferente
 * manera.
 */
public interface LeaveRequestType extends IEntity {

    /**
     * Valida la licencia contra un empleado recibido. Las licencias que tiene
     * el empleado pueden influir en la validacion, como asi tambien la cantidad
     * de dias solicitados.
     * 
     * @param leaveRequest
     *            la licencia a la cual el tipo de licencia pertenece.
     * @param employee
     *            el empleado que se requiere para realizar la validacion.
     * @return <code>true</code> si la licencia es valida para el empleado dado,
     *         <code>false</code> en caso contrario.
     */
    boolean isValidFor(LeaveRequest leaveRequest, Employee employee);

    /**
     * Obtiene el motivo de la licencia. Usado para agrupar licencias en los
     * empleados por su motivo.
     * 
     * @return un String representando el motivo del tipo de licencia.
     */
    String getReason();
}
