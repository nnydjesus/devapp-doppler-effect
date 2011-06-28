package ar.edu.unq.dopplereffect.service.validations;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import ar.edu.unq.dopplereffect.exceptions.ValidationException;
import ar.edu.unq.tpi.util.common.ReflectionUtils;

/**
 * Validaciones comunes sobre objetos.
 */
public class Validator {

    private transient Object object;

    public Validator(final Object object) {
        this.object = object;
    }

    /**
     * Lanza una {@link ValidationException} si al obtener el valor de la
     * propiedad pasada como parametro, este es vacio o bien tiene solo
     * espacios.
     */
    public Validator notBlank(final String field) {
        String value = (String) this.getProperty(field);
        if (value != null) {
            boolean hasAllSpaces = value.matches("^[ \\t]+");
            if (value.isEmpty() || hasAllSpaces) {
                throw new ValidationException("validations.blank." + field);
            }
        }
        return this;
    }

    /**
     * Lanza una {@link ValidationException} si al obtener el valor de la
     * propiedad pasada como parametro, este es <code>null</code>.
     */
    public Validator notNull(final String field) {
        Object value = this.getProperty(field);
        if (value == null) {
            throw new ValidationException("validations.null." + field);
        }
        return this;
    }

    /**
     * Lanza una {@link ValidationException} si al obtener el valor de la
     * propiedad pasada como parametro, este no tiene formato de mail.
     */
    public Validator isEmail(final String field) {
        String value = (String) this.getProperty(field);
        if (value != null && !value.matches(".+@.+\\.[a-z]+")) {
            throw new ValidationException("validations.email." + field);
        }
        return this;
    }

    /**
     * Lanza una {@link ValidationException} si al obtener el valor de la
     * propiedad pasada como parametro, este no es alguno de los valores pasados
     * como parametro.
     */
    public Validator in(final String field, final List<?> objects) {
        Object value = this.getProperty(field);
        if (!objects.contains(value)) {
            throw new ValidationException("validations.email." + field);
        }
        return this;
    }

    private Object getProperty(final String field) {
        return ReflectionUtils
                .invokeMethod(object, "get" + StringUtils.capitalize(field));
    }
}
