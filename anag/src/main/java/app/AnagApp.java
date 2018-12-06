package app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan({"business", "model", "controllers", "exception", "filters", "scheduler"})
@EnableJpaRepositories("repository")
@EntityScan("model")
@SpringBootApplication
@EnableScheduling
@EnableEurekaClient
@EnableZuulProxy
public class AnagApp{
	private static final Logger LOGGER = LoggerFactory.getLogger(AnagApp.class);

	public static void main(String[] args) {
		SpringApplication.run(AnagApp.class, args);
		LOGGER.info("info LOGGER for starting application");
		LOGGER.error("err LOGGER for starting application");
		LOGGER.warn("warn LOGGER for starting application");
	}
}
