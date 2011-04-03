package ar.edu.unq.dopplereffect.bean;

import java.util.Arrays;
import java.util.Set;

import ar.edu.unq.dopplereffect.bean.enums.Nivel;
import ar.edu.unq.dopplereffect.bean.enums.PlanDeCarrera;
import ar.edu.unq.dopplereffect.exception.UserException;

/**
 * Representa aquellos parametros que sirven para determinar el sueldo de un
 * empleado, como por ejemplo el monto minimo y maximo, y el plan de carrera.
 */
public class SueldoEstipulado {

    private int anio;

    private PlanDeCarrera plan;

    private Nivel nivel;

    private int[] banda;

    private int sueldoMinimo;

    private int sueldoMaximo;

    /**
     * Constructor.
     * 
     * @param anio
     *            el anio en el que estos parametros del sueldo tienen validez.
     * @param plan
     *            el plan de carrera al que se aplican los parametros del
     *            sueldo.
     * @param nivel
     *            el nivel del empleado al cual se le aplican los parametros del
     *            sueldo.
     */
    public SueldoEstipulado(final int anio, final PlanDeCarrera plan, final Nivel nivel) {
        this.anio = anio;
        this.plan = plan;
        this.nivel = nivel;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(final int anio) {
        this.anio = anio;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(final Nivel nivel) {
        this.nivel = nivel;
    }

    public PlanDeCarrera getPlan() {
        return plan;
    }

    public void setPlan(final PlanDeCarrera plan) {
        this.plan = plan;
    }

    public int[] getBanda() {
        return banda;
    }

    public void setBanda(final int[] banda) {
        this.banda = banda;
    }

    public void setSueldoMinimo(final int sueldoMinimo) {
        this.sueldoMinimo = sueldoMinimo;
    }

    public int getSueldoMinimo() {
        return sueldoMinimo;
    }

    public void setSueldoMaximo(final int sueldoMaximo) {
        this.sueldoMaximo = sueldoMaximo;
    }

    public int getSueldoMaximo() {
        return sueldoMaximo;
    }

    /**
     * Verifica si un porcentaje dado es valido para la banda.
     * 
     * @param porcentaje
     *            el porcentaje que se desea comprobar.
     * @return true si el porcentaje es valido, false en caso contrario.
     */
    public boolean tienePorcentajeEnBanda(final int porcentaje) {
        for (int porc : this.getBanda()) {
            if (porc == porcentaje) {
                return true;
            }
        }
        return false;
    }

    /**
     * Calcula el sueldo para un porcentaje dado. El valor calculado debe estar
     * comprendido entre el rango dado por el sueldo minimo y el maximo. El
     * porcentaje ademas debe ser valido, es decir, debe pertenecer a la banda
     * definida.
     * 
     * @param porcentaje
     *            el porcentaje con el cual se regula el monto del sueldo.
     */
    public int getSueldo(final int porcentaje) {
        if (this.tienePorcentajeEnBanda(porcentaje)) {
            return (int) (this.getSueldoMinimo() + (float) porcentaje / 100
                    * (this.getSueldoMaximo() - this.getSueldoMinimo()));
        } else {
            throw new UserException("El porcentaje no figura dentro de la banda");
        }
    }

    /**
     * Cambia la banda del sueldo estipulado. Si es necesario actualiza
     * empleados para que queden consistentes acorde a la nueva banda.
     * 
     * @param nuevaBanda
     *            un array de porcentajes que representan a la nueva banda.
     * @param empleados
     *            los empleados que pueden estar afectados y deben actualizarse
     *            sus sueldos.
     */
    public void cambiarBanda(final int[] nuevaBanda, final Set<Empleado> empleados) {
        Arrays.sort(nuevaBanda);
        if (nuevaBanda[0] != 0 || nuevaBanda[nuevaBanda.length - 1] != 100) {
            throw new UserException("La banda debe contener 0 y 100");
        }
        this.setBanda(nuevaBanda);
        for (Empleado empleado : empleados) {
            if (this.afectaCambioBanda(empleado)) {
                empleado.cambiarPorcentajeSueldo(this.getBanda());
            }
        }
    }

    private boolean afectaCambioBanda(final Empleado empleado) {
        // si el porcentaje del empleado no esta en la banda,
        // entonces esta afectado por el cambio de banda
        return !this.tienePorcentajeEnBanda(empleado.getPorcentaje());
    }
}
