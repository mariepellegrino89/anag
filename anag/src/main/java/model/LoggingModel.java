package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class LoggingModel {

    @Id
    private String requestId;
    private String correlationId;
    private String endPoint;
    private String operation;
    private String inputPayload;
    private String outputPayload;
    private Timestamp requestTimestamp;
    private Timestamp responseTimestamp;

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getInputPayload() {
        return inputPayload;
    }

    public void setInputPayload(String inputPayload) {
        this.inputPayload = inputPayload;
    }

    public String getOutputPayload() {
        return outputPayload;
    }

    public void setOutputPayload(String outputPayload) {
        this.outputPayload = outputPayload;
    }

    public Timestamp getRequestTimestamp() {
        return requestTimestamp;
    }

    public void setRequestTimestamp(Timestamp requestTimestamp) {
        this.requestTimestamp = requestTimestamp;
    }

    public Timestamp getResponseTimestamp() {
        return responseTimestamp;
    }

    public void setResponseTimestamp(Timestamp responseTimestamp) {
        this.responseTimestamp = responseTimestamp;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public String toString() {
        return "LoggingModel{" +
                "requestId='" + requestId + '\'' +
                ", correlationId='" + correlationId + '\'' +
                ", endPoint='" + endPoint + '\'' +
                ", operation='" + operation + '\'' +
                ", inputPayload='" + inputPayload + '\'' +
                ", outputPayload='" + outputPayload + '\'' +
                ", requestTimestamp=" + requestTimestamp +
                ", responseTimestamp=" + responseTimestamp +
                '}';
    }
}
