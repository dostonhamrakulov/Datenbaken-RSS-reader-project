package de.tuchemnitz.de.Main.controller;

import de.tuchemnitz.de.Main.entity.Web_feed;
import de.tuchemnitz.de.Main.repository.Web_feedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/feeds")
public class Web_feedController {

    @Autowired
    Web_feedRepository feedRepository;

    @GetMapping(path="/all")
    public @ResponseBody
    Iterable<Web_feed> allFeeds() {
        return feedRepository.findAll();
    }


}
