package ar.edu.unq.dopplereffect.bean;

import ar.edu.unq.dopplereffect.bean.enums.Nivel;
import ar.edu.unq.dopplereffect.bean.enums.PlanDeCarrera;

/**
 * Esta clase provee los datos del empleo del {@link Empleado}, como por ejemplo
 * su plan de carrera, su nivel y su porcentaje de sueldo.
 */
public class DatosPlanDeCarrera {

    private PlanDeCarrera planDeCarrera;

    private Nivel nivel;

    private int porcentaje;

    public PlanDeCarrera getPlanDeCarrera() {
        return planDeCarrera;
    }

    public void setPlanDeCarrera(final PlanDeCarrera planDeCarrera) {
        this.planDeCarrera = planDeCarrera;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(final Nivel nivel) {
        this.nivel = nivel;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(final int porcentaje) {
        this.porcentaje = porcentaje;
    }
}
