package de.tuchemnitz.de.Main.controller;

import de.tuchemnitz.de.Main.entity.Web_feed;
import de.tuchemnitz.de.Main.entity.Web_feed_providers;
import de.tuchemnitz.de.Main.repository.Web_feedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/feeds")
public class Web_feedController {

    @Autowired
    Web_feedRepository feedRepository;
    Web_feed web_feed;
    @GetMapping(path="/all")
    public @ResponseBody
    ResponseEntity<Iterable<Web_feed>> allFeeds() {

//        return feedRepository.findAll();
        return new ResponseEntity<>(feedRepository.findAll(), HttpStatus.OK);
    }

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

    @PostMapping(path = "/link", consumes = "application/json")
    public @ResponseBody ResponseEntity<List<Web_feed>> getWeb_feed_by_link(@RequestBody Web_feed web_feed2){

        List<Web_feed> web_feedList = feedRepository.findByLink(web_feed2.getLink());
        if (web_feedList != null){
            return new ResponseEntity<>(web_feedList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping(path = "/add", consumes = "application/json")
    public @ResponseBody ResponseEntity<Web_feed> addWeb_feed(@RequestBody Web_feed web_feed1){

//        System.out.println(web_feed1.getTitle());
//        System.out.println(web_feed1.getDescription());
//        System.out.println(web_feed1.getImported_date());
//        System.out.println(web_feed1.getImage());
//        System.out.println(web_feed1.getProvider_id());
        web_feed = feedRepository.save(web_feed1);
        if (web_feed != null){
            return new ResponseEntity<>(new Web_feed(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(new Web_feed(), HttpStatus.BAD_GATEWAY);
        }
    }






}
