package de.tuchemnitz.de.Main.controller;


import de.tuchemnitz.de.Main.entity.Web_feed;
import de.tuchemnitz.de.Main.entity.Web_feed_providers;
import de.tuchemnitz.de.Main.repository.Web_feed_providersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping(path="/web-feed-provider")
public class Web_feed_providersController {


    @Autowired
    Web_feed_providersRepository web_feed_providersRepository;

    @PostMapping(path="/add", consumes = "application/json")
    public Web_feed_providers addWeb_feed_providers(@RequestBody Web_feed_providers web_feed_providers){

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy hh:mm:ss a zzz");
        Date d = new Date();
        String current_date = sdf.format(d);
        web_feed_providers.setUpdated_date(current_date);
        return web_feed_providersRepository.save(web_feed_providers);
    }

    @GetMapping(path="/all")
    public @ResponseBody
    Iterable<Web_feed_providers> allWeb_feed_providers(){
        return web_feed_providersRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Web_feed_providers> web_feed_providersById(@PathVariable("id") int id){
        return web_feed_providersRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteWeb_feed_providers(@PathVariable("id") int id){
        web_feed_providersRepository.deleteById(id);
    }
}
