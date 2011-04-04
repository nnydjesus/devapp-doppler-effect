package ar.edu.unq.dopplereffect.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class EmployeeTest {

    @Test
    public void testGetFirstName() {
        Employee empl = new Employee();
        empl.setFirstName("Pepe");
        assertEquals("El nombre del empleado debe ser Pepe", "Pepe", empl.getFirstName());
    }

    @Test
    public void testGetLastName() {
        Employee empl = new Employee();
        empl.setLastName("Garcia");
        assertEquals("El apellido del empleado debe ser Garcia", "Garcia", empl.getLastName());
    }

    @Test
    public void testGetDNI() {
        Employee empl = new Employee();
        empl.setDni(35247459);
        assertEquals("El DNI del empleado debe ser 35247459", 35247459, empl.getDni());
    }

    @Test
    public void testGetPhoneNumber() {
        Employee empl = new Employee();
        empl.setPhoneNumber("11-3434-3434");
        assertEquals("El numero de telefono del empleado debe ser 11-3434-3434", "11-3434-3434", empl.getPhoneNumber());
    }

    @Test
    public void testGetEmail() {
        Employee empl = new Employee();
        empl.setEmail("empleado@alkasoft.com");
        assertEquals("El mail del empleado debe ser empleado@alkasoft.com", "empleado@alkasoft.com", empl.getEmail());
    }

    @Test
    public void testEqualityByDNI() {
        Employee empl1 = new Employee(), empl2 = new Employee();
        empl1.setDni(12354678);
        empl2.setDni(12354678);
        assertEquals("empl1 y empl2 deben ser el mismo empleado", empl1, empl2);
        empl2.setDni(23456789);
        assertFalse("empl1 y empl2 no deberian ser el mismo empleado", empl1.equals(empl2));
    }

    @Test
    public void igualdadNotDefinedForFirstOrLastName() {
        Employee empl1 = new Employee(), empl2 = new Employee();
        empl1.setFirstName("Juan");
        empl1.setLastName("Perez");
        empl1.setDni(12345678);
        empl2.setFirstName("Juan");
        empl2.setLastName("Perez");
        empl1.setDni(23456789);
        assertFalse("empl1 y empl2 no deberian ser el mismo empleado", empl1.equals(empl2));
    }

    @Test
    public void testChangeSalaryPercentage() {
        Employee empl = new Employee();
        empl.setPercentage(33);
        empl.changeSalaryPercentage(new int[] { 0, 50, 100 });
        assertEquals("el porcentaje deberia cambiar de 33 a 50", 50, empl.getPercentage());
    }
}
