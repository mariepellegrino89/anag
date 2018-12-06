package exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RegistryExceptionController {

    @ExceptionHandler(value = RegistryBusinessException.class)
    public ResponseEntity<Object> exception(RegistryBusinessException exception) {
        exception.getLogger().error(String.format("Id:[%s], Message: [%s], Module: [%s], Type:[%s], UUID:[%s]",
                exception.getId(), exception.getCustomMessage(), exception.getModule(),
                exception.getType(),exception.getUuid().toString()));
        return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
    }
}
