package ar.edu.unq.dopplereffect.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class EmpleadoTest {

    @Test
    public void testGetNombre() {
        Empleado empleado = new Empleado();
        empleado.setNombre("Pepe");
        assertEquals("El nombre del empleado debe ser Pepe", "Pepe", empleado.getNombre());
    }

    @Test
    public void testGetApellido() {
        Empleado empleado = new Empleado();
        empleado.setApellido("Garcia");
        assertEquals("El apellido del empleado debe ser Garcia", "Garcia", empleado.getApellido());
    }

    @Test
    public void testGetDNI() {
        Empleado empleado = new Empleado();
        empleado.setDni(35247459);
        assertEquals("El DNI del empleado debe ser 35247459", 35247459, empleado.getDni());
    }

    @Test
    public void testGetTelefono() {
        Empleado empleado = new Empleado();
        empleado.setNroTelefono("11-3434-3434");
        assertEquals("El numero de telefono del empleado debe ser 11-3434-3434", "11-3434-3434",
                empleado.getNroTelefono());
    }

    @Test
    public void testGetEmail() {
        Empleado empleado = new Empleado();
        empleado.setEmail("empleado@alkasoft.com");
        assertEquals("El mail del empleado debe ser empleado@alkasoft.com", "empleado@alkasoft.com",
                empleado.getEmail());
    }

    @Test
    public void testIgualdadDadaPorDNI() {
        Empleado empleado1 = new Empleado(), empleado2 = new Empleado();
        empleado1.setDni(12354678);
        empleado2.setDni(12354678);
        assertEquals("empleado y empleado2 deben ser el mismo empleado", empleado1, empleado2);
        empleado2.setDni(23456789);
        assertFalse("empleado y empleado2 no deberian ser el mismo empleado", empleado1.equals(empleado2));
    }

    @Test
    public void igualdadNoDadaPorNombreYApellido() {
        Empleado empleado1 = new Empleado(), empleado2 = new Empleado();
        empleado1.setNombre("Juan");
        empleado1.setApellido("Perez");
        empleado1.setDni(12345678);
        empleado2.setNombre("Juan");
        empleado2.setApellido("Perez");
        empleado1.setDni(23456789);
        assertFalse("empleado y empleado2 no deberian ser el mismo empleado", empleado1.equals(empleado2));
    }

    @Test
    public void testCambiarPorcentajeSueldo() {
        Empleado empl = new Empleado();
        empl.setPorcentaje(33);
        empl.cambiarPorcentajeSueldo(new int[] { 0, 50, 100 });
        assertEquals("el porcentaje deberia cambiar de 33 a 50", 50, empl.getPorcentaje());
    }
}
