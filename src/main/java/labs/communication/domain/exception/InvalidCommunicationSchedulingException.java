package labs.communication.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidCommunicationSchedulingException extends ConstraintViolationException {

    public InvalidCommunicationSchedulingException(Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(constraintViolations);
    }

}
