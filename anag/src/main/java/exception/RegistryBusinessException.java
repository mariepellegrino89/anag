package exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class RegistryBusinessException extends Exception{


    private String id;
    private String module;
    private String type;
    private String customMessage;
    private UUID uuid;
    private Logger logger;

    public RegistryBusinessException(String id, String module, String customMessage, Logger logger){
        this.id = id;
        this.module = module;
        this.type = "B";
        this.customMessage = customMessage;
        this.uuid = UUID.randomUUID();
        this.logger = logger;
    }

    @ExceptionHandler(value = RegistryBusinessException.class)


    public String getId() {
        return id;
    }

    public String getModule() {
        return module;
    }

    public String getType() {
        return type;
    }

    public String getCustomMessage() {
        return customMessage;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Logger getLogger() {
        return logger;
    }
}