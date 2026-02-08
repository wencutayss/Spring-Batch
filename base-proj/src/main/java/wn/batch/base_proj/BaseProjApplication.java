package wn.batch.base_proj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootApplication
@EnableScheduling
public class BaseProjApplication {

	static Logger log = LoggerFactory.getLogger(BaseProjApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(BaseProjApplication.class, args);
		log.info(">>>BaseProjApplication Started...");
	}

}
