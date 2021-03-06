package todoapp.exceptions;

public class EmptyFieldException extends Exception {

    private String field;
    private String defaultValueString;
    
    public EmptyFieldException() {
        super();
    }

    public EmptyFieldException(Throwable t) {
        super(t);
    }

    public EmptyFieldException(String field, String defaultValue) {
        super();
        this.field = field;
        this.defaultValueString = defaultValue;
    }

    public EmptyFieldException(String msg) {
        super(msg);
    }

    public EmptyFieldException(String msg, String field, String defaultValue) {
        super(msg);
        this.field = field;
        this.defaultValueString = defaultValue;
    }

    public EmptyFieldException(String msg, Throwable t) {
        super(msg, t);
    }

    public EmptyFieldException(String msg, String field, String defaultValue, Throwable t) {
        super(msg, t);
        this.field = field;
        this.defaultValueString = defaultValue;
    }

    public EmptyFieldException(String field, String defaultValue, Throwable t) {
        super(t);
        this.field = field;
        this.defaultValueString = defaultValue;
    }

    /**
     * Get the name of the field that throws the exception.
     * @return the name of the field.
     */
    public String getField() {
        return field;
    }

    /**
     * Get the default value that have to be set instead of leaving it empty.
     * @return the expected default value.
     */
    public String getDefaultValue() {
        return defaultValueString;
    }

}
