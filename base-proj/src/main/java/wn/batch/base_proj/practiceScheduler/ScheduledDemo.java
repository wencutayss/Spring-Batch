package wn.batch.base_proj.practiceScheduler;

import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import wn.batch.base_proj.BaseProjApplication;

import java.util.Date;

import org.slf4j.Logger;

// @Component
public class ScheduledDemo {

	static Logger log = LoggerFactory.getLogger(BaseProjApplication.class);

	// @Scheduled(fixedRate = 2000L) /*Ignores Thread.sleep */
	// @Scheduled(fixedDelay = 2000L) /*Considers The Thread.sleep */
	// @Scheduled(fixedDelay = 2000L, initialDelay = 2000L) /*Initially hold for initialDelay then starts Scheduling */
	// @Scheduled(fixedDelayString = "PT2S") /*Input in String Formate */
	@Scheduled(cron = "*/2 * * * * *")
	public void job() throws InterruptedException {
		log.info("Job Current Time : {}", new Date());
		Thread.sleep(1000L);
	}

	@Scheduled(cron = "*/2 * * * * *")
	public void jo2b() throws InterruptedException {
		log.info("Job2 Current Time : {}", new Date());
		Thread.sleep(1000L);
	}
}
