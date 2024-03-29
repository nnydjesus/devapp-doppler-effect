package ar.edu.unq.dopplereffect.exceptions;

public class ValidationException extends UserException {

    private static final long serialVersionUID = 7731219048760928671L;

    public ValidationException() {
        super();
    }

    public ValidationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ValidationException(final String message) {
        super(message);
    }

    public ValidationException(final Throwable cause) {
        super(cause);
    }
}
