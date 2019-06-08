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

@SpringBootApplication

public class MainApplication {

	public static final String REST_SERVICE_URI = "http://localhost:8080/";
	static RestTemplate restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);

		DateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
		String date;

		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				// task to run goes here
				System.out.println("\n\n\n\n\n\n\n\nUpdated at " + formatter.format(new Date())+"\n");

				add_web_feeds();
			}
		};
		Timer timer = new Timer();
		long delay = 0;
		long intevalPeriod = 40 * 1000;
		// schedules the task to be run in an interval
		timer.scheduleAtFixedRate(task, delay, intevalPeriod);

//        add_web_feeds();

	}

	public static void add_web_feeds(){

		restTemplate = new RestTemplate();

		ResponseEntity<Iterable<Web_feed_providers>> web_feed_providers = restTemplate.exchange(REST_SERVICE_URI+"/web-feed-provider/all",
				HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<Web_feed_providers>>(){

				});
		Iterable<Web_feed_providers> webFeedProviders = web_feed_providers.getBody();

		List<Web_feed_providers> web_feed_providersList = new ArrayList<>();
		webFeedProviders.forEach(web_feed_providersList::add);
		Web_feed_providers wfp;
		for (int i = 0; i < web_feed_providersList.size(); i++) {




			wfp = web_feed_providersList.get(i);

			add_single_feeds(wfp);
//
//			Web_feed wf = new Web_feed(wfp.)
//
//			ResponseEntity<Web_feed> re = restTemplate.exchange(REST_SERVICE_URI+"/feeds/add", )
//			System.out.println(wf.getName());
//			System.out.println(wf.getId());
//			System.out.println(wf.getLink());
//			System.out.println(wf.getNum_feeds());
		}
	}

	public static void add_single_feeds(Web_feed_providers wfp){
		try {

			DateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
			Date d = new Date();
			String current_date = formatter.format(d);

			wfp.setUpdated_date(current_date);

			URL url  = new URL(wfp.getLink());

			System.out.println("Link: " + wfp.getLink());

			XmlReader reader = new XmlReader(url);

			SyndFeed feed = new SyndFeedInput().build(reader);
			List<SyndEntry> syndEntryList = feed.getEntries();

			SyndEntry syndEntry;

			for (int i = 0; i < syndEntryList.size(); i++) {
				restTemplate = new RestTemplate();

				syndEntry = syndEntryList.get(i);

				String date;
				if (syndEntry.getPublishedDate() != null){
					date = formatter.format(syndEntry.getPublishedDate());
				} else {
					date = "null";
				}

				Web_feed w = new Web_feed(syndEntry.getTitle(), syndEntry.getLink(), syndEntry.getDescription().getValue(), date, current_date, wfp.getId(), "src_img");

				HttpHeaders headers = new HttpHeaders();

				headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
				headers.setContentType(MediaType.APPLICATION_JSON);

				//TODO - find a link from DB
                HttpEntity<Web_feed> requestEntity = new HttpEntity<>(w, headers);
                ResponseEntity<List<Web_feed>> re1 = restTemplate.exchange(REST_SERVICE_URI+"/feeds/link",
                        HttpMethod.POST, requestEntity, new ParameterizedTypeReference<List<Web_feed>>(){});

                List<Web_feed> list = re1.getBody();

                if (re1.getStatusCode() == HttpStatus.FOUND){
					System.out.println(list.get(0));
                    System.out.println("\n =========== Feed is FOUND ==========");
                } else if (re1.getStatusCode() == HttpStatus.OK){

					System.out.println("\n =========== Feed is ENTERED ==========");
                    ResponseEntity<Web_feed> responseEntity = restTemplate.exchange(REST_SERVICE_URI+"/feeds/add",
                            HttpMethod.POST, requestEntity, Web_feed.class);


                    if(responseEntity.getStatusCode() == HttpStatus.BAD_GATEWAY){
                        System.out.println("\n\n\n===================== BAD_GATEWAY =====================");

                        System.out.println("Implement TODO - update error status of the provider");

                    }
                } else {
                    System.out.println("\n\n\n===================== BAD_GATEWAY =====================");

                    System.out.println("Implement TODO - MAIN APplication");
                }




			}

//			web_feed_providers.setNum_feeds(syndEntryList.size());
//			web_feed_providers.setName(feed.getTitle());



			reader.close();

		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void error_status_provider(){

		restTemplate = new RestTemplate();

	}

}
