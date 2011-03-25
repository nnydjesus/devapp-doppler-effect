package ar.edu.unq.dopplereffect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

public class EmpleadoTest {
    private transient Empleado empleado;

    @Before
    public void setUp() {
        empleado = new Empleado();
    }

    @Test
    public void testGetNombre() {
        empleado.setNombre("Pepe");
        assertEquals("El nombre del empleado debe ser Pepe", "Pepe", empleado.getNombre());
    }

    @Test
    public void testGetApellido() {
        empleado.setApellido("Garcia");
        assertEquals("El apellido del empleado debe ser Garcia", "Garcia", empleado.getApellido());
    }

    @Test
    public void testGetDNI() {
        empleado.setDni(35247459);
        assertEquals("El DNI del empleado debe ser 35247459", 35247459, empleado.getDni());
    }

    @Test
    public void testGetTelefono() {
        empleado.setNroTelefono("11-3434-3434");
        assertEquals("El numero de telefono del empleado debe ser 11-3434-3434", "11-3434-3434",
                empleado.getNroTelefono());
    }

    @Test
    public void testGetEmail() {
        empleado.setEmail("empleado@alkasoft.com");
        assertEquals("El mail del empleado debe ser empleado@alkasoft.com", "empleado@alkasoft.com",
                empleado.getEmail());
    }

    @Test
    public void testIgualdadDadaPorDNI() {
        Empleado empleado2 = new Empleado();
        empleado.setDni(12354678);
        empleado2.setDni(12354678);
        assertEquals("empleado y empleado2 deben ser el mismo empleado", empleado, empleado2);
        empleado2.setDni(23456789);
        assertFalse("empleado y empleado2 no deberian ser el mismo empleado", empleado.equals(empleado2));
    }

    @Test
    public void igualdadNoDadaPorNombreYApellido() {
        Empleado empleado2 = new Empleado();
        empleado.setNombre("Juan");
        empleado.setNombre("Perez");
        empleado.setDni(12345678);
        empleado2.setNombre("Juan");
        empleado2.setNombre("Perez");
        empleado.setDni(23456789);
        assertFalse("empleado y empleado2 no deberian ser el mismo empleado", empleado.equals(empleado2));
    }
}
