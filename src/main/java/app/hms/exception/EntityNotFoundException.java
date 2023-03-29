package app.hms.exception;


import org.springframework.web.bind.annotation.ResponseStatus;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
