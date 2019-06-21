package de.tuchemnitz.de.Main.controller;

import de.tuchemnitz.de.Main.Common_code;
import de.tuchemnitz.de.Main.ImportRSS;
import de.tuchemnitz.de.Main.entity.Web_feed;
import de.tuchemnitz.de.Main.entity.Web_feed_providers;
import de.tuchemnitz.de.Main.repository.Web_feedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static de.tuchemnitz.de.Main.Common_code.getCurrentDate;


@RestController
@RequestMapping(path="/feeds")
public class Web_feedController {

    @Autowired
    Web_feedRepository feedRepository;
    Web_feed web_feed;

//    EntityManagerFactory emf =
//            Persistence.createEntityManagerFactory("Web_feedController");
//
//    EntityManager em = ...;
//    Cache cache = em.getEntityManagerFactory().getCache();
//cache.evictAll();

    @GetMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<Web_feed> getWed_feed(@PathVariable("id") int id){
        Optional<Web_feed> optionalWeb_feed = feedRepository.findById(id);

        if (optionalWeb_feed.isPresent()){
            web_feed = optionalWeb_feed.get();
            return new ResponseEntity<>(optionalWeb_feed.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path="/all")
    public @ResponseBody
    ResponseEntity<Iterable<Web_feed>> allFeeds() {

//        return feedRepository.findAll();
        Iterable<Web_feed> web_feedIterable = feedRepository.findAll();
        List<Web_feed> web_feedList = new ArrayList<>();
        web_feedIterable.forEach(web_feedList::add);

        if(web_feedList.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(feedRepository.findAll(), HttpStatus.OK);
        }
    }



    @PostMapping(path = "/link", consumes = "application/json")
    public @ResponseBody ResponseEntity<List<Web_feed>> getWeb_feed_by_link(@RequestBody Web_feed web_feed2){

        List<Web_feed> web_feedList = feedRepository.findByLink(web_feed2.getLink(), web_feed2.getProviderid());
        if (!web_feedList.isEmpty()){
            return new ResponseEntity<>(web_feedList, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }


    }

    @PostMapping(path = "/add", consumes = "application/json")
    public @ResponseBody ResponseEntity<Web_feed> addWeb_feed(@RequestBody Web_feed web_feed1){

//        System.out.println(web_feed1.getTitle());
//        System.out.println(web_feed1.getDescription());
//        System.out.println(web_feed1.getImported_date());
//        System.out.println(web_feed1.getImage());
//        System.out.println(web_feed1.getProvider_id());

        web_feed1.setImporteddate(getCurrentDate());
//        web_feed1.setPublisheddate(getCurrentDate());
        web_feed = feedRepository.save(web_feed1);
        if (web_feed != null){
            return new ResponseEntity<>(new Web_feed(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(new Web_feed(), HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping(path = "/number-of-feeds")
    public @ResponseBody ResponseEntity<Integer> numFeed(){
        Iterable<Web_feed> list = feedRepository.findAll();

        List<Web_feed> web_feedList = new ArrayList<>();
        list.forEach(web_feedList::add);
        if(web_feedList.size() > 0){
            return new ResponseEntity<>(web_feedList.size(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(0, HttpStatus.BAD_GATEWAY);
        }

    }


    @RequestMapping(value= "/feeds-of-provider/", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<Web_feed>> feeds_of_provider(@RequestParam("providerid") int providerid){
        List<Web_feed> list = feedRepository.findByProviderid(providerid);
        if (!list.isEmpty()){
            return new ResponseEntity<>(list, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/delete-by-providerid")
    public @ResponseBody ResponseEntity<String> rvAllforProviderId(@RequestParam("providerid") int providerid){
        int affected_rows = feedRepository.rvAllforProviderId(providerid);

        if (affected_rows > 0){
            return new ResponseEntity<>("Deleted", HttpStatus.OK);

        } else {
            return new ResponseEntity<>("cannot deleted", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "/update")
    public @ResponseBody ResponseEntity<String> updateFeed(@RequestBody Web_feed web_feed1){
        int affected_rows = feedRepository.updateFeed(web_feed1.getTitle(), web_feed1.getPublisheddate(), web_feed1.getLink());

        if (affected_rows > 0){
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("cannot updated", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/export")
    public @ResponseBody ResponseEntity<List<Web_feed>> exportFeeds(@RequestParam("ids") String ids){
        List<Web_feed> web_feedList = null;
        try {
            web_feedList = ImportRSS.exportFeedsJSON(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(web_feedList, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete")
    public @ResponseBody ResponseEntity<String> deleteFeed(@RequestParam("id") int id){
        feedRepository.deleteById(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

    @GetMapping(path = "/num-of-feeds-of-provider")
    public @ResponseBody ResponseEntity<Integer> numFeedsOfProvider(@RequestParam("providerid") int providerid){

        int number_of_feeds = 0;
        number_of_feeds = feedRepository.numOfFeedsOfAProvider(providerid);

        if (number_of_feeds > 0){
            System.out.println("OUT");
            return new ResponseEntity<>(number_of_feeds, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(0, HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping(path = "/delete-feed")
    public @ResponseBody ResponseEntity<String> updateDeletedFeed(@RequestParam("id") int id){
        int affected_row = feedRepository.updateDeleted("True", id);
        if (affected_row > 0){
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/update-feed-by-user")
    public @ResponseBody ResponseEntity<String> updateFeedByUser(@RequestBody Web_feed wf){
        int affected_row = feedRepository.updateFeedByUser(wf.getTitle(), wf.getLink(), wf.getId());
        if (affected_row > 0){
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error occured", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/number-of-errors")
    public @ResponseBody ResponseEntity<Integer> getNumberErrorsww(@RequestBody Web_feed wf){
        int affected_row = 0;
        affected_row = feedRepository.getNumberErrors("True", wf.getProviderid());
        if (affected_row > 0){
            return new ResponseEntity<>(affected_row, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(0, HttpStatus.NOT_FOUND);
        }
    }

}
