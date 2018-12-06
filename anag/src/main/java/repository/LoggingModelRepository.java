package repository;

import model.LoggingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoggingModelRepository extends JpaRepository<LoggingModel, String> {

    List<LoggingModel> findByEndPointContaining(String endPoint);
}
