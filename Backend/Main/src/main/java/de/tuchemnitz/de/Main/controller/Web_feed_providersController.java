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

import static de.tuchemnitz.de.Main.Common_code.getCurrentDate;
import static de.tuchemnitz.de.Main.ImportRSS.adding_single;

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
            web_feed_providers.setUpdated_date(getCurrentDate());

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
            try {
                adding_single(web_feed_providers);
            } catch (Exception e) {
                e.printStackTrace();
            }
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

    @RequestMapping(path = "delete-by-userid/", method = RequestMethod.DELETE)
    public @ResponseBody ResponseEntity<String> deleteByUserid(@RequestParam("userid") int userid){
        int affected_rows = web_feed_providersRepository.deleteByUserid(userid);

        if (affected_rows > 0){
            return new ResponseEntity<>("Deleted", HttpStatus.OK);

        } else {
            return new ResponseEntity<>("cannot deleted", HttpStatus.NOT_FOUND);
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
