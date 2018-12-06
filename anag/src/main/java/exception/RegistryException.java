package exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.UUID;

public class RegistryException extends Exception{

    private static final Logger logger = LoggerFactory.getLogger(RegistryException.class);

    private String id;
    private String module;
    private String type;
    private String message;
    private UUID uuid;
    private Exception e;

    public RegistryException(String id, String module, String message, Exception e){
        this.id = id;
        this.module = module;
        this.type = "S";
        this.e = e;
        this.uuid = UUID.randomUUID();
    }
    @ExceptionHandler(value = RegistryException.class)
    public ResponseEntity<Object> exception(@NotNull RegistryException exception) {
        logger.error(String.format("Id:[%s], Module: [%s], Type:[%s], UUID:[%s] /n %s",
                exception.getId(), exception.getModule(), exception.getType(), exception.getUuid().toString(),
                Arrays.toString(exception.getE().getStackTrace())));
        return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
    }

    public static Logger getLogger() {
        return logger;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Exception getE() {
        return e;
    }

    public void setE(Exception e) {
        this.e = e;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
