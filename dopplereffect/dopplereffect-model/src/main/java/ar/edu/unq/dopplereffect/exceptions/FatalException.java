package ar.edu.unq.dopplereffect.exceptions;

/**
 * exception que es grosa
 */
public class FatalException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public FatalException(final String string) {
        super(string);
    }

    public FatalException(final Throwable throwable) {
        super(throwable);
    }

    public FatalException() {
        super();
    }

    public FatalException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
