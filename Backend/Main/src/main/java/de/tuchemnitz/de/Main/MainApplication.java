package de.tuchemnitz.de.Main;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import de.tuchemnitz.de.Main.entity.Web_feed;
import de.tuchemnitz.de.Main.entity.Web_feed_providers;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static de.tuchemnitz.de.Main.ImportRSS.add_web_feeds;

@SpringBootApplication

public class MainApplication {

	public static final String REST_SERVICE_URI = "http://localhost:8080/";
	static RestTemplate restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);



		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				// task to run goes here
//				System.out.println("\n\n\n\n\n\n\n\nUpdated at " + formatter.format(new Date())+"\n");

                try {
                    add_web_feeds();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("Date: " + Common_code.getCurrentDate());
            }
		};
		Timer timer = new Timer();
		long delay = 0;
		long intevalPeriod = 300 * 1000;
		// schedules the task to be run in an interval
		timer.scheduleAtFixedRate(task, delay, intevalPeriod);

//        add_web_feeds();

	}

	public static void error_status_provider(){

		restTemplate = new RestTemplate();

	}

}
