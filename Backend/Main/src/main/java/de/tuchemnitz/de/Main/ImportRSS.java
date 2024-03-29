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

                String date= "";
                String error = "False";
                String title = "";
                String link= "";
                String desc= "";
                String deleted = "False";

//                checking if a feed is broken
                if (syndEntry.getPublishedDate() == null){
                    if (syndEntry.getUpdatedDate() == null){
                        date = "null";
                        error = "True";
                    } else {
                        date = Common_code.convertDateToString(syndEntry.getUpdatedDate());

                    }
                } else {
                    date = Common_code.convertDateToString(syndEntry.getPublishedDate());
                }

                if (syndEntry.getTitle() != null){
                    title = syndEntry.getTitle();
                } else{
                    title = "null";
                    error = "title";
                }

                if (syndEntry.getDescription() != null){
                    desc = syndEntry.getDescription().getValue();
                } else {
                    error = "desc";
                    desc = "null";}

                if (syndEntry.getLink() != null){
                    link = syndEntry.getLink();
                } else {
                    error = "link";
                    link = "null";
                }

                Web_feed w = new Web_feed(title, link, desc, date, Common_code.getCurrentDate(), wfp.getId(), deleted, error);

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

//                        System.out.println("Implement TODO - update error status of the provider");

                    }

                    // update provider
                    pubDate = w.getPublisheddate();


                } else {
//                    System.out.println("\n\n\n===================== SOMETHING ELSE happened in Feed creation =====================");

//                    System.out.println("Implement TODO - MAIN APplication");

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

                if (lastUpdateDate.before(getCurrentDateinDate())){

                    adding_single(feed_p);
//                    deleteOldFeeds(user, feed_p);
//                    updateErrorProvider(feed_p);

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

    public static void updateNumberOfFeeds(int id, int numfeed){
        restTemplate = new RestTemplate();


        Web_feed_providers wfp = new Web_feed_providers();
        wfp.setId(id);
        wfp.setNumfeeds(numfeed);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Web_feed_providers> requestEntity1 = new HttpEntity<>(wfp, headers);

        ResponseEntity<String> re4 = restTemplate.exchange(
                REST_SERVICE_URI+"web-feed-provider/update-num-feed-of-provider", HttpMethod.PUT,
                requestEntity1, String.class
        );

        if (re4.getStatusCode() == HttpStatus.OK){
            System.out.println("Num_feed is updated!");
        }
    }

    public static void updateProvider(Web_feed_providers wfp){
        restTemplate = new RestTemplate();

        int numFeed = getNumberFeeds(wfp.getId());

        updateNumberOfFeeds(wfp.getId(), numFeed);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Web_feed_providers> requestEntity1 = new HttpEntity<>(wfp, headers);

//        if (res1.getStatusCode() == HttpStatus.OK){

            ResponseEntity<Integer> res2 = restTemplate.exchange(
                    REST_SERVICE_URI+"web-feed-provider/update-provider",
                    HttpMethod.PUT, requestEntity1, Integer.class);
            if (res2.getStatusCode() == HttpStatus.OK){
                System.out.println("updatedProvider");
            }
//        }

    }

    public static int getNumberFeeds(int id){
        restTemplate = new RestTemplate();
        ResponseEntity<Integer> res1 = restTemplate.exchange(
        REST_SERVICE_URI + "feeds/num-of-feeds-of-provider?providerid="+id,
        HttpMethod.GET, null, Integer.class);

        return res1.getBody();

    }

    public static void updateErrorProvider(Web_feed_providers wfp){
        restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Web_feed web_feed = new Web_feed();
        web_feed.setProviderid(wfp.getId());
        HttpEntity<Web_feed> entity = new HttpEntity<>(web_feed, headers);
        System.out.println("Providerid: " + wfp.getId());
        ResponseEntity<Integer> er = restTemplate.exchange(
                REST_SERVICE_URI + "feeds/number-of-errors",
                HttpMethod.POST, entity, Integer.class);

//        ResponseEntity<Integer> er = restTemplate.postForEntity(REST_SERVICE_URI + "feeds/number-of-errors",
//                entity, Integer.class);
//        restTemplate.post
        if (er.getBody() != null){
            wfp.setError(er.getBody());
        } else {
            wfp.setError(0);
        }

        HttpEntity<Web_feed_providers> requestEntity1 = new HttpEntity<>(wfp, headers);


        ResponseEntity<String> er1 = restTemplate.exchange(
                REST_SERVICE_URI+"web-feed-provider/update-error-of-provider",
                HttpMethod.PUT, requestEntity1, String.class);
        if (er1.getStatusCode() == HttpStatus.OK){
            System.out.println("updatedProvider");
        }
//        }


    }





}
