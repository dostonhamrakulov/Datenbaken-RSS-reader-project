package de.tuchemnitz.de.Main.controller;


import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import de.tuchemnitz.de.Main.entity.Web_feed;
import de.tuchemnitz.de.Main.entity.Web_feed_providers;
import de.tuchemnitz.de.Main.repository.Web_feed_providersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(path = "/web-feed-provider")
public class Web_feed_providersController {


    @Autowired
    Web_feed_providersRepository web_feed_providersRepository;

    public static final String REST_SERVICE_URI = "http://localhost:8080/";
    static RestTemplate restTemplate;

//    public static final String REST_SERVICE_URI = "http://localhost:8080/feeds";

    @PostMapping(path = "/add", consumes = "application/json")
    public ResponseEntity<Web_feed_providers> addWeb_feed_providers(@RequestBody Web_feed_providers web_feed_providers) {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
            Date d = new Date();
            String current_date = sdf.format(d);

            web_feed_providers.setUpdated_date(current_date);

            URL url = new URL(web_feed_providers.getLink());
            System.out.println("Link: " + web_feed_providers.getLink());

            XmlReader reader = new XmlReader(url);

            SyndFeed feed = new SyndFeedInput().build(reader);
            List<SyndEntry> syndEntryList = feed.getEntries();

            web_feed_providers.setNum_feeds(syndEntryList.size());


            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        Web_feed_providers wf = web_feed_providersRepository.save(web_feed_providers);
        if (wf != null) {
            add_single_feeds(web_feed_providers);
            return new ResponseEntity<>(web_feed_providers, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    ResponseEntity<List<Web_feed_providers>> allWeb_feed_providers() {
        Iterable<Web_feed_providers> list = web_feed_providersRepository.findAll();

        List<Web_feed_providers> web_feedList = new ArrayList<>();
        list.forEach(web_feedList::add);
        if (!web_feedList.isEmpty()) {
            return new ResponseEntity<>(web_feedList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Web_feed_providers> web_feed_providersById(@PathVariable("id") int id) {

        Optional<Web_feed_providers> optionalWeb_feed = web_feed_providersRepository.findById(id);

        if (optionalWeb_feed.isPresent()) {
            return new ResponseEntity<>(optionalWeb_feed.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteWeb_feed_providers(@PathVariable("id") int id) {
        web_feed_providersRepository.deleteById(id);
    }

    @GetMapping(path = "/feed-providers-of-user/{userid}")
    public @ResponseBody
    ResponseEntity<List<Web_feed_providers>> getWeb_feed_by_link(@PathVariable("userid") int userid) {

        List<Web_feed_providers> wl = web_feed_providersRepository.findByUserid(userid);
        if (wl != null) {
            return new ResponseEntity<>(wl, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public static void add_single_feeds(Web_feed_providers wfp) {
        try {

            DateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
            Date d = new Date();
            String current_date = formatter.format(d);

            wfp.setUpdated_date(current_date);

            URL url = new URL(wfp.getLink());

            System.out.println("Link: " + wfp.getLink());

            XmlReader reader = new XmlReader(url);

            SyndFeed feed = new SyndFeedInput().build(reader);
            List<SyndEntry> syndEntryList = feed.getEntries();

            SyndEntry syndEntry;

            for (int i = 0; i < syndEntryList.size(); i++) {
                restTemplate = new RestTemplate();

                syndEntry = syndEntryList.get(i);

                String date;
                if (syndEntry.getPublishedDate() != null) {
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
                ResponseEntity<List<Web_feed>> re1 = restTemplate.exchange(REST_SERVICE_URI + "/feeds/link",
                        HttpMethod.POST, requestEntity, new ParameterizedTypeReference<List<Web_feed>>() {
                        });

                List<Web_feed> list = re1.getBody();

                if (re1.getStatusCode() == HttpStatus.FOUND) {
                    System.out.println(list.get(0));
                    System.out.println("\n =========== Feed is FOUND ==========");
                } else if (re1.getStatusCode() == HttpStatus.NO_CONTENT) {

                    System.out.println("\n =========== Feed is ENTERED ==========");
                    ResponseEntity<Web_feed> responseEntity = restTemplate.exchange(REST_SERVICE_URI + "/feeds/add",
                            HttpMethod.POST, requestEntity, Web_feed.class);


                    if (responseEntity.getStatusCode() == HttpStatus.BAD_GATEWAY) {
                        System.out.println("\n\n\n===================== BAD_GATEWAY =====================");

                        System.out.println("Implement TODO - update error status of the provider");

                    }
                } else {
                    System.out.println("\n\n\n===================== SOMETHING ELSE happened in Feed creation =====================");

                    System.out.println("Implement TODO - MAIN APplication");
                }


            }


            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //TODO - implement updating error status of the provider

//    @PutMapping(path = "/update", consumes = "application/json")
//    public @ResponseBody
//    ResponseEntity<Web_feed_providers> update_provider(@RequestBody Web_feed_providers web_feed_providers){
//
//        Web_feed_providers web_feed_providers1 = web_feed_providersRepository.
//
//    }

//    public void addingWeb_feedProvider(String url_){
//        try {
//            URL url  = new URL(url_);
//
//            XmlReader reader = null;
//            reader = new XmlReader(url);
//            SyndFeed feed = new SyndFeedInput().build(reader);
////            feed
//            System.out.println("Feed Author: "+ feed.getAuthor());
//            System.out.println("Feed Title: " + feed.getTitle());
//            System.out.println("SyndEntry started: ----------- ");
//
//            List<SyndEntry> syndEntryList = feed.getEntries();
//            SyndEntry syndEntry;
//            for (int i = 0; i < syndEntryList.size(); i++) {
//                syndEntry = syndEntryList.get(i);
//                System.out.println("\n item number: " + i);
//                System.out.println("Title: " + syndEntry.getTitle());
//                String date;
//                if (syndEntry.getPublishedDate() != null){
//                    DateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
//                    date = formatter.format(syndEntry.getPublishedDate());
//                } else {
//                    date = "null";
//                }
//
//
//                System.out.println("PubDate: " + date);
//                System.out.println("Link: " + syndEntry.getLink());
//                System.out.println("Desc: " + syndEntry.getDescription().getValue());
//            }
//
//            reader.close();
//
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//    }

//    public void add_web_feeds(Web_feed web_feed_){
//
//        RestTemplate restTemplate = new RestTemplate();
//        Web_feed web_feed = web_feed_;
//        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/user/", web_feed, Web_feed.class);
//
//    }
}
