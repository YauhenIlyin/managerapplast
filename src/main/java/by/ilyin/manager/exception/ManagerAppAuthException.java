package by.ilyin.manager.exception;

public class ManagerAppAuthException extends Exception {

    public ManagerAppAuthException(String message, Exception e) {
        super(message, e);
    }

    public ManagerAppAuthException(String message) {
        super(message);
    }

    public ManagerAppAuthException(Exception e) {
        super(e);
    }

}
