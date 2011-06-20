package ar.edu.unq.dopplereffect.exceptions;

/**
 * Exception de asignacion
 */
public class AssignmentException extends UserException {
    private static final long serialVersionUID = 1L;

    private String key;

    private String extraData = "";

    public AssignmentException(final String key) {
        this.key = key;
    }

    public AssignmentException(final String key, final Throwable throwable) {
        this(key, "", throwable);
    }

    public AssignmentException(final String key, final String message) {
        super(message);
        this.key = key;
    }

    public AssignmentException(final String key, final String message, final String extradata) {
        this(key, message);
        extraData = extradata;
    }

    public AssignmentException(final String key, final String message, final Throwable cause) {
        super(message, cause);
        this.key = key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setExtraData(final String extraData) {
        this.extraData = extraData;
    }

    public String getExtraData() {
        return extraData;
    }

}
