package ar.edu.unq.dopplereffect.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class EmployeeTest {

    @Test
    public void testGetFirstName() {
        Employee empl = new EmployeeBuilder().withFirstName("Pepe").build();
        assertEquals("El nombre del empleado debe ser Pepe", "Pepe", empl.getFirstName());
    }

    @Test
    public void testGetLastName() {
        Employee empl = new EmployeeBuilder().withLastName("Garcia").build();
        assertEquals("El apellido del empleado debe ser Garcia", "Garcia", empl.getLastName());
    }

    @Test
    public void testGetDNI() {
        Employee empl = new EmployeeBuilder().withDNI(35247459).build();
        assertEquals("El DNI del empleado debe ser 35247459", 35247459, empl.getDni());
    }

    @Test
    public void testGetPhoneNumber() {
        Employee empl = new EmployeeBuilder().withPhoneNumber("11-3434-3434").build();
        assertEquals("El numero de telefono del empleado debe ser 11-3434-3434", "11-3434-3434", empl.getPhoneNumber());
    }

    @Test
    public void testGetEmail() {
        Employee empl = new EmployeeBuilder().withEmail("empleado@alkasoft.com").build();
        assertEquals("El mail del empleado debe ser empleado@alkasoft.com", "empleado@alkasoft.com", empl.getEmail());
    }

    @Test
    public void testEqualityByDNI() {
        Employee empl1 = new EmployeeBuilder().withDNI(123456789).build();
        Employee empl2 = new EmployeeBuilder().withDNI(123456789).build();
        assertEquals("empl1 y empl2 deben ser el mismo empleado", empl1, empl2);
        empl2.setDni(23456789);
        assertFalse("empl1 y empl2 no deberian ser el mismo empleado", empl1.equals(empl2));
    }

    @Test
    public void igualdadNotDefinedForFirstOrLastName() {
        Employee empl1 = new EmployeeBuilder().withFirstName("Juan").withFirstName("Perez").withDNI(12345678).build();
        Employee empl2 = new EmployeeBuilder().withFirstName("Juan").withFirstName("Perez").withDNI(23456789).build();
        assertFalse("empl1 y empl2 no deberian ser el mismo empleado", empl1.equals(empl2));
    }

    @Test
    public void testChangeSalaryPercentage() {
        Employee empl = new EmployeeBuilder().withPercentage(33).build();
        empl.changeSalaryPercentage(new int[] { 0, 50, 100 });
        assertEquals("el porcentaje deberia cambiar de 33 a 50", 50, empl.getPercentage());
    }
}
