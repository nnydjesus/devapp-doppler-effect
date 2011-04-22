package ar.edu.unq.dopplereffect.exceptions;

/**
 * Exepcion de negocio.
 */
public class UserException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UserException(final String string) {
        super(string);
    }

    public UserException(final Throwable throwable) {
        super(throwable);
    }
}
