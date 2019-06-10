package de.tuchemnitz.de.Main;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import de.tuchemnitz.de.Main.entity.Web_feed;
import de.tuchemnitz.de.Main.entity.Web_feed_providers;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static de.tuchemnitz.de.Main.Common_code.*;

public class ImportRSS {

    public ImportRSS() {
    }


    static RestTemplate restTemplate;

    public static void add_web_feeds() throws Exception {

        restTemplate = new RestTemplate();

        ResponseEntity<Iterable<Web_feed_providers>> web_feed_providers = restTemplate.exchange(REST_SERVICE_URI + "/web-feed-provider/all",
                HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<Web_feed_providers>>() {

                });
        Iterable<Web_feed_providers> webFeedProviders = web_feed_providers.getBody();

        List<Web_feed_providers> web_feed_providersList = new ArrayList<>();
        webFeedProviders.forEach(web_feed_providersList::add);
        Web_feed_providers wfp;
        for (int i = 0; i < web_feed_providersList.size(); i++) {

            wfp = web_feed_providersList.get(i);

            adding_single(wfp);
        }
    }

    public static void adding_single(Web_feed_providers wfp) throws Exception{

            wfp.setUpdateddate(Common_code.getCurrentDate());

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
                    date = convertDateToString(syndEntry.getPublishedDate());
                } else {
                    date = "null";
                }

                Web_feed w = new Web_feed(syndEntry.getTitle(), syndEntry.getLink(), syndEntry.getDescription().getValue(), date, Common_code.getCurrentDate(), wfp.getId(), "src_img");

                HttpHeaders headers = new HttpHeaders();

                headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                headers.setContentType(MediaType.APPLICATION_JSON);

                //TODO - find a link from DB
                HttpEntity<Web_feed> requestEntity = new HttpEntity<>(w, headers);
                ResponseEntity<List<Web_feed>> re1 = restTemplate.exchange(REST_SERVICE_URI+"/feeds/link",
                        HttpMethod.POST, requestEntity, new ParameterizedTypeReference<List<Web_feed>>(){});

                List<Web_feed> list = re1.getBody();

                if (re1.getStatusCode() == HttpStatus.FOUND){
                    System.out.println("\n =========== Feed is FOUND ==========");
                    System.out.println(list.get(0));
                    Web_feed web_feed = list.get(0);
                    if ((w.getTitle() != web_feed.getTitle()) ||
                            (w.getPublisheddate() != web_feed.getPublisheddate())){
                        System.out.println("Different");
                        HttpEntity<Web_feed> requestEntity1 = new HttpEntity<>(w, headers);
                        ResponseEntity<String> re12 = restTemplate.exchange(REST_SERVICE_URI+"/feeds/update",
                                HttpMethod.PUT, requestEntity1, String.class);
                        System.out.println(re12.getBody() + re12.getStatusCode());
                    }

                } else if (re1.getStatusCode() == HttpStatus.NO_CONTENT){

                    System.out.println("\n =========== Feed is ENTERED ==========");
                    ResponseEntity<Web_feed> responseEntity = restTemplate.exchange(REST_SERVICE_URI+"/feeds/add",
                            HttpMethod.POST, requestEntity, Web_feed.class);


                    if(responseEntity.getStatusCode() == HttpStatus.BAD_GATEWAY){
                        System.out.println("\n\n\n===================== BAD_GATEWAY =====================");

                        System.out.println("Implement TODO - update error status of the provider");

                    }
                } else {
                    System.out.println("\n\n\n===================== SOMETHING ELSE happened in Feed creation =====================");

                    System.out.println("Implement TODO - MAIN APplication");
                }




            }

//
    }
}
