package ar.edu.unq.dopplereffect.bean;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unq.dopplereffect.exception.UserException;

public class ProyectTest {

    private Proyect proyect;

    private Empleado empleado1;

    private Empleado empleado2;

    @Before
    public void setUp() {
        proyect = new Proyect("p1", 3);
        empleado1 = mock(Empleado.class);
        empleado2 = mock(Empleado.class);
    }

    /**
     * Verifica que en la asigne a los empleados al proyect
     */
    @Test
    public void manualAssignment1() {
        assertSame("Error el proyecto no tiene empleados asignados", 0, proyect.getAssignedEmployee().size());
        proyect.manualAssignment(empleado1, 1);
        assertSame("Error Se agrego un empleado al proyecto", 1, proyect.getAssignedEmployee().size());
        assertTrue("Error el empleado si esta asignado en el proyecto",
                proyect.getAssignedEmployee().containsKey(empleado1));
        assertFalse("Error el empleado no esta asignado en el proyecto",
                proyect.getAssignedEmployee().containsKey(empleado2));
    }

    /**
     * manual assignment 2 y 3 verifica que cuando queremos agregar un empleado
     * al proyecto con un tiempo determinado, ese tiempo junto no supere al
     * tiempo restante
     */

    @Test
    public void manualAssignment2() {
        try {
            proyect.manualAssignment(empleado1, 4);
            fail();
        } catch (UserException e) {
            // succeed;
            assertTrue(true);
        }
    }

    @Test
    public void manualAssignment3() {
        proyect.manualAssignment(empleado1, 1);
        assertEquals("", 2, proyect.getRemainingTime());
        proyect.manualAssignment(empleado2, 2);
        assertEquals("", 0, proyect.getRemainingTime());
        try {
            proyect.manualAssignment(empleado1, 1);
            fail();
        } catch (UserException e) {
            // succeed;
            assertTrue(true);
        }
    }
}
