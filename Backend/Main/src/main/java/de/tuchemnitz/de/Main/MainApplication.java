package de.tuchemnitz.de.Main;

import static de.tuchemnitz.de.Main.ImportRSS.update_all_users_providers;

import java.util.Timer;
import java.util.TimerTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication

public class MainApplication {


	static RestTemplate restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);



		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				// task to run goes here
//				System.out.println("\n\n\n\n\n\n\n\nUpdated at " + formatter.format(new Date())+"\n");

                try {
                    update_all_users_providers();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
		};
		Timer timer = new Timer();
		long delay = 0;
		long intevalPeriod = 60 * 1000;
		// schedules the task to be run in an interval
		timer.scheduleAtFixedRate(task, delay, intevalPeriod);

//        add_web_feeds();

	}

	public static void error_status_provider(){

		restTemplate = new RestTemplate();

	}

}
