package todoapp.exceptions;

public class InvalidFieldException extends Exception {
    
    public InvalidFieldException() {
        super();
    }

    public InvalidFieldException(String msg) {
        super(msg);
    }

    public InvalidFieldException(String msg, Throwable t) {
        super(msg, t);
    }

    public InvalidFieldException(Throwable t) {
        super(t);
    }

}