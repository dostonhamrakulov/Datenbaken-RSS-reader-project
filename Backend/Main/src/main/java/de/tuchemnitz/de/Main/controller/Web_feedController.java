package de.tuchemnitz.de.Main.controller;

import de.tuchemnitz.de.Main.entity.Web_feed;
import de.tuchemnitz.de.Main.repository.Web_feedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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




}
