package de.tuchemnitz.de.Main.controller;


import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import de.tuchemnitz.de.Main.entity.Web_feed;
import de.tuchemnitz.de.Main.entity.Web_feed_providers;
import de.tuchemnitz.de.Main.repository.Web_feed_providersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(path="/web-feed-provider")
public class Web_feed_providersController {


    @Autowired
    Web_feed_providersRepository web_feed_providersRepository;

    public static final String REST_SERVICE_URI = "http://localhost:8080/feeds";

    @PostMapping(path="/add", consumes = "application/json")
    public Web_feed_providers addWeb_feed_providers(@RequestBody Web_feed_providers web_feed_providers){

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
            Date d = new Date();
            String current_date = sdf.format(d);

            web_feed_providers.setUpdated_date(current_date);

            URL url  = new URL(web_feed_providers.getLink());
            System.out.println("Link: " + web_feed_providers.getLink());

            XmlReader reader = new XmlReader(url);

            SyndFeed feed = new SyndFeedInput().build(reader);
            List<SyndEntry> syndEntryList = feed.getEntries();

            web_feed_providers.setNum_feeds(syndEntryList.size());
            web_feed_providers.setName(feed.getTitle());

//            for (int i = 0; i < syndEntryList.size(); i++) {
//                SyndEntry syndEntry = syndEntryList.get(i);
//                Web_feed web_feed = new Web_feed(syndEntry.getTitle(), syndEntry.getLink(), syndEntry.getDescription(),
//                        )
//            }


            reader.close();

        } catch (Exception e){
            e.printStackTrace();
        }


        return web_feed_providersRepository.save(web_feed_providers);
    }

    @GetMapping(path="/all")
    public @ResponseBody
    Iterable<Web_feed_providers> allWeb_feed_providers(){
        return web_feed_providersRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Web_feed_providers> web_feed_providersById(@PathVariable("id") int id) {

        return web_feed_providersRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteWeb_feed_providers(@PathVariable("id") int id){
        web_feed_providersRepository.deleteById(id);
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

    public void add_web_feeds(Web_feed web_feed_){

        RestTemplate restTemplate = new RestTemplate();
        Web_feed web_feed = web_feed_;
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/user/", web_feed, Web_feed.class);

    }
}
