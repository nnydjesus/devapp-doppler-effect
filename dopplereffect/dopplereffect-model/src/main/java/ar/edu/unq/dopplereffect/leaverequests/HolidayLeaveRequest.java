package ar.edu.unq.dopplereffect.leaverequests;

import java.util.LinkedList;
import java.util.List;

import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.exceptions.UserException;

/**
 * Pedido de vacaciones, que contempla la antiguedad del empleado a la hora de
 * validar.
 */
public class HolidayLeaveRequest extends LeaveRequestCustomType {

    private static final long serialVersionUID = 772168027727676027L;

    /* ************************ INSTANCE VARIABLES ************************ */

    private List<HolidayDaysConfiguration> configuration;

    /* *************************** CONSTRUCTORS *************************** */

    public HolidayLeaveRequest(final int minLimit, final int maxLimit) {
        super();
        configuration = new LinkedList<HolidayDaysConfiguration>();
        this.setReason("Holiday");
        this.setMinLimit(minLimit);
        this.setMaxLimit(maxLimit);
    }

    public HolidayLeaveRequest() {
        super();
    }

    /* **************************** ACCESSORS ***************************** */

    public List<HolidayDaysConfiguration> getConfiguration() {
        return configuration;
    }

    public void setConfiguration(final List<HolidayDaysConfiguration> configuration) {
        this.configuration = configuration;
    }

    /* **************************** OPERATIONS **************************** */

    @Override
    public boolean isValidFor(final LeaveRequest leaveReq, final Employee employee) {
        return super.isValidFor(leaveReq, employee) && this.satisfiesConfiguration(leaveReq, employee);
    }

    /**
     * Configura los dias correspondientes de vacaciones a cada año de
     * antiguedad. Esta es la unica forma permitida de configurar esta licencia,
     * puesto que mantiene un orden para determinar luego los dias
     * correspondientes.
     */
    public void configure(final int minYear, final int correspondingDays) {
        HolidayDaysConfiguration newConfig = new HolidayDaysConfiguration(minYear, correspondingDays);
        for (int i = 0; i < this.getConfiguration().size(); i++) {
            if (this.getConfiguration().get(i).getMinYear() == minYear) {
                // si ya existe la conf, la reemplaza
                this.getConfiguration().set(i, newConfig);
            }
            if (this.getConfiguration().get(i).getMinYear() > minYear) {
                // inserta de manera ordenada
                this.getConfiguration().add(i, newConfig);
            }
        }
        // si no hay ninguna, se agrega directamente
        this.getConfiguration().add(newConfig);
    }

    /**
     * Dado un año de antiguedad, retorna los dias correspondientes de
     * vacaciones que corresponden. Precondicion: asume que la configuracion de
     * dias se encuentra ordenada, satisfecho por el metodo configure();
     */
    public int getCorrespondingDays(final int year) {
        HolidayDaysConfiguration previous = null;
        for (HolidayDaysConfiguration config : this.getConfiguration()) {
            if (config.getMinYear() == year) {
                // si el año coincide exactamente
                return config.getCorrespondingDays();
            }
            if (config.getMinYear() > year) {
                // si se paso, retorna el anterior
                if (previous == null) {
                    throw new UserException("No existe configuracion para el año dado");
                } else {
                    return previous.getCorrespondingDays();
                }
            }
            previous = config;
        }
        if (this.getConfiguration().isEmpty()) {
            throw new UserException("No existe configuracion para el año dado");
        } else {
            return this.getConfiguration().get(this.getConfiguration().size() - 1).getCorrespondingDays();
        }
    }

    /* ************************* PRIVATE METHODS ************************** */

    private boolean satisfiesConfiguration(final LeaveRequest leaveReq, final Employee employee) {
        int correspondingDays = this.getCorrespondingDays(leaveReq.getYear());
        int daysThatCanRequest = correspondingDays - employee.daysRequestedInYear(this, leaveReq.getYear());
        return leaveReq.getAmountOfDays() <= daysThatCanRequest;
    }
}
