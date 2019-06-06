package de.tuchemnitz.de.Main.controller;


import de.tuchemnitz.de.Main.entity.Web_feed_providers;
import de.tuchemnitz.de.Main.repository.Web_feed_providersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path="/web-feed-provider")
public class Web_feed_providersController {


    @Autowired
    Web_feed_providersRepository web_feed_providersRepository;

    @GetMapping(path="/all")
    public @ResponseBody
    Iterable<Web_feed_providers> allWeb_feed_providers(){
        return web_feed_providersRepository.findAll();
    }

    @GetMapping("/{provider_id}")
    public Optional<Web_feed_providers> web_feed_providersById(@PathVariable("provider_id") int provider_id){
        return web_feed_providersRepository.findById(provider_id);
    }

    @DeleteMapping("/{provider_id}")
    public void deleteWeb_feed_providers(@PathVariable("provider_id") int provider_id){
        web_feed_providersRepository.deleteById(provider_id);
    }
}
