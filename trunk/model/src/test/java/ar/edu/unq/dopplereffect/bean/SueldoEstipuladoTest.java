package ar.edu.unq.dopplereffect.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unq.dopplereffect.bean.enums.Nivel;
import ar.edu.unq.dopplereffect.bean.enums.PlanDeCarrera;
import ar.edu.unq.dopplereffect.exception.UserException;

public class SueldoEstipuladoTest {

    private transient SueldoEstipulado baseSueldo;

    @Before
    public void setUp() {
        baseSueldo = new SueldoEstipulado(2011, PlanDeCarrera.TESTER, Nivel.SEMISENIOR);
        LinkedList<Integer> banda = new LinkedList<Integer>();
        Collections.addAll(banda, 0, 33, 66, 100);
        baseSueldo.setBanda(banda);
        baseSueldo.setSueldoMinimo(3000);
        baseSueldo.setSueldoMaximo(4500);
    }

    @Test
    public void testGetSueldoMinimo() {
        assertEquals("El sueldo minimo debe ser 3000", 3000, baseSueldo.getSueldoMinimo());
    }

    @Test
    public void testGetSueldoMaximo() {
        assertEquals("El sueldo maximo debe ser 4500", 4500, baseSueldo.getSueldoMaximo());
    }

    @Test
    public void testGetSueldoParaPorcentajeEnBandaExistente() {
        assertEquals("El sueldo para 0% debe ser 3000", 3000, baseSueldo.getSueldo(0));
        assertEquals("El sueldo para 33% debe ser 3495", 3495, baseSueldo.getSueldo(33));
        assertEquals("El sueldo para 66% debe ser 3990", 3990, baseSueldo.getSueldo(66));
        assertEquals("El sueldo para 100% debe ser 4500", 4500, baseSueldo.getSueldo(100));
    }

    @Test(expected = UserException.class)
    public void testGetSueldoParaPorcentajeEnBandaNoValido() {
        baseSueldo.getSueldo(17);
        baseSueldo.getSueldo(28);
        baseSueldo.getSueldo(96);
    }

    @Test
    public void testTienePorcentajeEnBanda() {
        assertTrue("0% debe ser valido para la banda [0, 33, 66, 100]", baseSueldo.tienePorcentajeEnBanda(0));
        assertTrue("33% debe ser valido para la banda [0, 33, 66, 100]", baseSueldo.tienePorcentajeEnBanda(33));
        assertTrue("66% debe ser valido para la banda [0, 33, 66, 100]", baseSueldo.tienePorcentajeEnBanda(66));
        assertTrue("100% debe ser valido para la banda [0, 33, 66, 100]", baseSueldo.tienePorcentajeEnBanda(100));
    }

    @Test
    public void testNoTienePorcentajeEnBanda() {
        assertFalse("10% NO debe ser valido para la banda [0, 33, 66, 100]", baseSueldo.tienePorcentajeEnBanda(10));
        assertFalse("50% NO debe ser valido para la banda [0, 33, 66, 100]", baseSueldo.tienePorcentajeEnBanda(50));
        assertFalse("78% NO debe ser valido para la banda [0, 33, 66, 100]", baseSueldo.tienePorcentajeEnBanda(78));
        assertFalse("99% NO debe ser valido para la banda [0, 33, 66, 100]", baseSueldo.tienePorcentajeEnBanda(99));
    }
}
