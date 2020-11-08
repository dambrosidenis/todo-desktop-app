package todoapp.exceptions;

public class BackupFailedException extends Exception {
    
    public BackupFailedException() {
        super();
    }

    public BackupFailedException(String msg) {
        super(msg);
    }

    public BackupFailedException(String msg, Throwable t) {
        super(msg, t);
    }

    public BackupFailedException(Throwable t) {
        super(t);
    }

}
