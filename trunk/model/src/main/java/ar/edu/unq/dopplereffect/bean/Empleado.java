package ar.edu.unq.dopplereffect.bean;

/**
 * Persona que trabaja en la empresa. Un empleado posee datos personales, como
 * por ejemplo su nombre y apellido, pero tambien posee datos relacionados al
 * trabajo, como su plan de carrera.
 */
public class Empleado {

    private int dni;

    private String apellido;

    private String nombre;

    private String nroTelefono;

    private String email;

    /**
     * Getter de nombre.
     * 
     * @return el nombre del empleado.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna un nuevo nombre al empleado.
     * 
     * @param nombre
     *            el nuevo nombre.
     */
    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    /**
     * Getter de apellido.
     * 
     * @return el apellido del empleado.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Asigna un nuevo apellido al empleado.
     * 
     * @param apellido
     *            el nuevo apellido.
     */
    public void setApellido(final String apellido) {
        this.apellido = apellido;
    }

    /**
     * Asigna el DNI al empleado.
     * 
     * @param dni
     *            el DNI a asignar.
     */
    public void setDni(final int dni) {
        this.dni = dni;
    }

    /**
     * Getter de dni.
     * 
     * @return el DNI del empleado.
     */
    public int getDni() {
        return dni;
    }

    /**
     * Getter de nroTelefono.
     * 
     * @return el numero de telefono del empleado.
     */
    public String getNroTelefono() {
        return nroTelefono;
    }

    /**
     * Asigna el numero de telefono al empleado.
     * 
     * @param tel
     *            el nuevo numero de telefono del empleado.
     */
    public void setNroTelefono(final String tel) {
        nroTelefono = tel;
    }

    /**
     * Getter de email.
     * 
     * @return el E-mail del empleado.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Asigna un email al empleado.
     * 
     * @param email
     *            el nuevo e-mail del empleado.
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Dos empleados son iguales si sus DNI lo son.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        Empleado other = (Empleado) obj;
        if (dni != other.dni)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        return prime + dni;
    }
}
