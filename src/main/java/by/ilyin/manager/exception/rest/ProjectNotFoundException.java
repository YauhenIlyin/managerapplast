package by.ilyin.manager.exception.rest;

public class ProjectNotFoundException extends Exception {

    public ProjectNotFoundException(String message, Exception e) {
        super(message, e);
    }

    public ProjectNotFoundException(String message) {
        super(message);
    }

    public ProjectNotFoundException(Exception e) {
        super(e);
    }

    public ProjectNotFoundException() {

    }

}
