package scheduler;

import model.LoggingModel;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import repository.LoggingModelRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoggingTableCleanScheduler {

    @Autowired
    private LoggingModelRepository loggingModelRepository;

    @Scheduled(fixedRate = 10000)
    public void deleteNotWantedRecord() {
        List<LoggingModel> notWantedModels = loggingModelRepository.findByEndPointContaining("h2-console");
        loggingModelRepository.deleteAll(notWantedModels);
    }
}
