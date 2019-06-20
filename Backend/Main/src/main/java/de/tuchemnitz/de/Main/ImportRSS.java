package de.tuchemnitz.de.Main;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import de.tuchemnitz.de.Main.entity.User;
import de.tuchemnitz.de.Main.entity.Web_feed;
import de.tuchemnitz.de.Main.entity.Web_feed_providers;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.net.URL;
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
            wfp.setLastattempt(Common_code.getCurrentDate());
            String pubDate = "";


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

                Web_feed w = new Web_feed(syndEntry.getTitle(), syndEntry.getLink(), syndEntry.getDescription().getValue(), date, Common_code.getCurrentDate(), wfp.getId(), "False");

                HttpHeaders headers = new HttpHeaders();

                headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                headers.setContentType(MediaType.APPLICATION_JSON);

                //TODO - find a link from DB
                HttpEntity<Web_feed> requestEntity = new HttpEntity<>(w, headers);
                ResponseEntity<List<Web_feed>> re1 = restTemplate.exchange(REST_SERVICE_URI+"feeds/link",
                        HttpMethod.POST, requestEntity, new ParameterizedTypeReference<List<Web_feed>>(){});

                List<Web_feed> list = re1.getBody();

                if (re1.getStatusCode() == HttpStatus.FOUND){
//                    System.out.println("\n =========== Feed is FOUND ==========");
                    Web_feed web_feed = list.get(0);
                    if ((w.getTitle() != web_feed.getTitle()) ||
                            (w.getPublisheddate() != web_feed.getPublisheddate())){
//                        System.out.println("Different");
                        HttpEntity<Web_feed> requestEntity1 = new HttpEntity<>(w, headers);
                        ResponseEntity<String> re12 = restTemplate.exchange(REST_SERVICE_URI+"feeds/update",
                                HttpMethod.PUT, requestEntity1, String.class);
//                        System.out.println(re12.getBody()+ " "  + re12.getStatusCode());

                    }

                } else if (re1.getStatusCode() == HttpStatus.NO_CONTENT){

//                    System.out.println("\n =========== Feed is ENTERED ==========");
                    ResponseEntity<Web_feed> responseEntity = restTemplate.exchange(REST_SERVICE_URI+"feeds/add",
                            HttpMethod.POST, requestEntity, Web_feed.class);


                    if(responseEntity.getStatusCode() == HttpStatus.BAD_GATEWAY){
                        System.out.println("\n\n\n===================== BAD_GATEWAY =====================");

                        System.out.println("Implement TODO - update error status of the provider");

                    }

                    // update provider
                    pubDate = w.getPublisheddate();


                } else {
                    System.out.println("\n\n\n===================== SOMETHING ELSE happened in Feed creation =====================");

                    System.out.println("Implement TODO - MAIN APplication");

                    wfp.setError(1);
                }



            }

        wfp.setLatestrecorddate(pubDate);

        updateProvider(wfp);

    }

    public static List<Web_feed> exportFeedsJSON(String ids) throws Exception{
        restTemplate = new RestTemplate();
        List<Web_feed> web_feedList = new ArrayList<>();
        String[] splited_ids = ids.split(",");
        for (int i = 0; i < splited_ids.length; i++) {
            ResponseEntity<Web_feed> re = restTemplate.getForEntity(REST_SERVICE_URI+"feeds/"+splited_ids[i], Web_feed.class);
            web_feedList.add(re.getBody());
        }
        return web_feedList;
    }

    public static void update_all_users_providers() throws Exception{
        restTemplate = new RestTemplate();

        ResponseEntity<List<User>> re = restTemplate.exchange(REST_SERVICE_URI+"user/all", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<User>>(){});


        List<User> userList = new ArrayList<>();
        if (re.getStatusCode() == HttpStatus.FOUND){
             userList = re.getBody();
        }
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            String current_date = getCurrentDate();


            ResponseEntity<List<Web_feed_providers>> re2 = restTemplate.exchange(
                    REST_SERVICE_URI+"web-feed-provider/feed-providers-of-user/"+user.getId(), HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Web_feed_providers>>(){});


            List<Web_feed_providers> wpl = new ArrayList<>();
            if (re2.getStatusCode() == HttpStatus.FOUND){
                wpl = re2.getBody();
            }
            for (int j = 0; j < wpl.size(); j++) {
                System.out.println("\n\n\n\n");
                System.out.println("User: " + user.getId());

                Web_feed_providers feed_p = wpl.get(j);
                Date lastUpdateDate = Common_code.convertStringToDate(feed_p.getUpdateddate());
                lastUpdateDate = Common_code.addMinutes(lastUpdateDate, user.getUpdateperiod());

                System.out.println("Current: " + getCurrentDate());
                System.out.println("Updated_original: " + feed_p.getUpdateddate());
                System.out.println("Updated_minutes_added: " + convertDateToString(lastUpdateDate));
                System.out.println("Minutes: " + user.getUpdateperiod());

                if (lastUpdateDate.before(getCurrentDateinDate())){

                    adding_single(feed_p);
                    deleteOldFeeds(user, feed_p);

                } else {
                    System.out.println("\n\n\nSkippen because of time");

                }


            }
        }


    }

    public static void deleteOldFeeds(User user, Web_feed_providers feed_p) throws Exception {
        restTemplate = new RestTemplate();
        ResponseEntity<List<Web_feed>> re3 = restTemplate.exchange(
                REST_SERVICE_URI + "feeds/feeds-of-provider/?providerid=" + feed_p.getId(), HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Web_feed>>() {
                });
        List<Web_feed> web_feedList = new ArrayList<>();
        if (re3.getStatusCode() == HttpStatus.FOUND){
            web_feedList = re3.getBody();
        }
        for (int k = 0; k < web_feedList.size(); k++) {
            Web_feed web_feed = web_feedList.get(k);
            Date importedDate = Common_code.convertStringToDate(web_feed.getImporteddate());
            importedDate = Common_code.addDays(importedDate, user.getFeedage());

            // if imported date + feed-age is still before current date then delete a feed
            if (importedDate.before(getCurrentDateinDate())){
                ResponseEntity<String> re4 = restTemplate.exchange(
                        REST_SERVICE_URI+"feeds/delete?id="+web_feed.getId(), HttpMethod.DELETE,
                        null, String.class
                );
                if (re4.getStatusCode() == HttpStatus.OK){
                    System.out.println("--------- deleted ---------");
                }
            }
        }
    }

    public static void updateProvider(Web_feed_providers wfp){
        restTemplate = new RestTemplate();
        ResponseEntity<Integer> res1 = restTemplate.exchange(
                REST_SERVICE_URI + "feeds/num-of-feeds-of-provider?providerid="+wfp.getId(),
                HttpMethod.GET, null, Integer.class);

        wfp.setNumfeeds(res1.getBody());
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Web_feed_providers> requestEntity1 = new HttpEntity<>(wfp, headers);

        if (res1.getStatusCode() == HttpStatus.OK){

            ResponseEntity<Integer> res2 = restTemplate.exchange(
                    REST_SERVICE_URI+"web-feed-provider/update-provider",
                    HttpMethod.PUT, requestEntity1, Integer.class);
            if (res2.getStatusCode() == HttpStatus.OK){
                System.out.println("updatedProvider");
            }
        }

        System.out.println("\n==========");
        System.out.println("Current: " + getCurrentDate());
        System.out.println("Updated: " + wfp.getUpdateddate());


    }

}
