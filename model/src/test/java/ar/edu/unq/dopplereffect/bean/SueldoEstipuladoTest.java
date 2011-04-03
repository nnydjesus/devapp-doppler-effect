package ar.edu.unq.dopplereffect.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import ar.edu.unq.dopplereffect.bean.enums.Nivel;
import ar.edu.unq.dopplereffect.bean.enums.PlanDeCarrera;
import ar.edu.unq.dopplereffect.exception.UserException;

public class SueldoEstipuladoTest {

    private SueldoEstipulado crearSueldoConsistente() {
        return new SueldoEstipulado(2011, PlanDeCarrera.TESTER, Nivel.SEMISENIOR);
    }

    private SueldoEstipulado crearSueldoConsistente(final int sMin, final int sMax) {
        SueldoEstipulado sueldoEstipulado = new SueldoEstipulado(2011, PlanDeCarrera.TESTER, Nivel.SEMISENIOR);
        sueldoEstipulado.setSueldoMinimo(sMin);
        sueldoEstipulado.setSueldoMaximo(sMax);
        return sueldoEstipulado;
    }

    @Test
    public void testGetSueldoMinimo() {
        SueldoEstipulado baseSueldo = this.crearSueldoConsistente();
        baseSueldo.setSueldoMinimo(3000);
        assertEquals("El sueldo minimo debe ser 3000", 3000, baseSueldo.getSueldoMinimo());
    }

    @Test
    public void testGetSueldoMaximo() {
        SueldoEstipulado baseSueldo = this.crearSueldoConsistente();
        baseSueldo.setSueldoMaximo(4500);
        assertEquals("El sueldo maximo debe ser 4500", 4500, baseSueldo.getSueldoMaximo());
    }

    @Test
    public void testGetSueldoParaPorcentajeEnBandaExistente() {
        SueldoEstipulado baseSueldo = this.crearSueldoConsistente(3000, 4500);
        baseSueldo.setBanda(new int[] { 0, 33, 66, 100 });
        assertEquals("El sueldo para 0% debe ser 3000", 3000, baseSueldo.getSueldo(0));
        assertEquals("El sueldo para 33% debe ser 3495", 3495, baseSueldo.getSueldo(33));
        assertEquals("El sueldo para 66% debe ser 3990", 3990, baseSueldo.getSueldo(66));
        assertEquals("El sueldo para 100% debe ser 4500", 4500, baseSueldo.getSueldo(100));
    }

    @Test(expected = UserException.class)
    public void testGetSueldoParaPorcentajeEnBandaNoValido() {
        SueldoEstipulado baseSueldo = this.crearSueldoConsistente(3000, 4500);
        baseSueldo.setBanda(new int[] { 0, 33, 66, 100 });
        baseSueldo.getSueldo(17);
        baseSueldo.getSueldo(28);
        baseSueldo.getSueldo(96);
    }

    @Test
    public void testTienePorcentajeEnBanda() {
        SueldoEstipulado baseSueldo = this.crearSueldoConsistente(3000, 4500);
        baseSueldo.setBanda(new int[] { 0, 33, 66, 100 });
        assertTrue("0% debe ser valido para la banda [0, 33, 66, 100]", baseSueldo.tienePorcentajeEnBanda(0));
        assertTrue("33% debe ser valido para la banda [0, 33, 66, 100]", baseSueldo.tienePorcentajeEnBanda(33));
        assertTrue("66% debe ser valido para la banda [0, 33, 66, 100]", baseSueldo.tienePorcentajeEnBanda(66));
        assertTrue("100% debe ser valido para la banda [0, 33, 66, 100]", baseSueldo.tienePorcentajeEnBanda(100));
    }

    @Test
    public void testNoTienePorcentajeEnBanda() {
        SueldoEstipulado baseSueldo = this.crearSueldoConsistente(3000, 4500);
        baseSueldo.setBanda(new int[] { 0, 33, 66, 100 });
        assertFalse("10% NO debe ser valido para la banda [0, 33, 66, 100]", baseSueldo.tienePorcentajeEnBanda(10));
        assertFalse("50% NO debe ser valido para la banda [0, 33, 66, 100]", baseSueldo.tienePorcentajeEnBanda(50));
        assertFalse("78% NO debe ser valido para la banda [0, 33, 66, 100]", baseSueldo.tienePorcentajeEnBanda(78));
        assertFalse("99% NO debe ser valido para la banda [0, 33, 66, 100]", baseSueldo.tienePorcentajeEnBanda(99));
    }

    @Test
    public void cambioBandaAfectaAEmpleadoEnEsaBanda() {
        SueldoEstipulado baseSueldo = this.crearSueldoConsistente();
        baseSueldo.setBanda(new int[] { 0, 33, 66, 100 });
        Empleado afectado = mock(Empleado.class);
        when(afectado.getPorcentaje()).thenReturn(33).thenReturn(50);
        Set<Empleado> empleadosAfectados = new HashSet<Empleado>();
        empleadosAfectados.add(afectado);
        int[] nuevaBanda = { 0, 50, 100 };
        baseSueldo.cambiarBanda(nuevaBanda, empleadosAfectados);
        verify(afectado).cambiarPorcentajeSueldo(nuevaBanda);
        assertEquals("El porcentaje del empleado debe haber cambiado de 33 a 50", 50, afectado.getPorcentaje());
    }

    @Test
    public void cambioBandaNoAfectaAEmpleado() {
        SueldoEstipulado baseSueldo = this.crearSueldoConsistente();
        baseSueldo.setBanda(new int[] { 0, 50, 100 });
        Empleado noAfectado = mock(Empleado.class);
        when(noAfectado.getPorcentaje()).thenReturn(50);
        Set<Empleado> empleados = new HashSet<Empleado>();
        empleados.add(noAfectado);
        int[] nuevaBanda = { 0, 25, 50, 75, 100 };
        baseSueldo.cambiarBanda(nuevaBanda, empleados);
        assertEquals("El porcentaje del empleado debe permanecer en 50", 50, noAfectado.getPorcentaje());
    }

    @Test(expected = UserException.class)
    public void testCambioBandaErroneaSin0() {
        int[] nuevaBanda = { 33, 66, 100 };
        this.crearSueldoConsistente().cambiarBanda(nuevaBanda, new HashSet<Empleado>());
    }

    @Test(expected = UserException.class)
    public void testCambioBandaErroneaSin100() {
        int[] nuevaBanda = { 0, 25, 50, 75 };
        this.crearSueldoConsistente().cambiarBanda(nuevaBanda, new HashSet<Empleado>());
    }
}