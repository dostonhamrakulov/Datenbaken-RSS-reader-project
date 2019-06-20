package de.tuchemnitz.de.Main.controller;


import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import de.tuchemnitz.de.Main.Feed_operation;
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
import static de.tuchemnitz.de.Main.ImportRSS.*;

@RestController
@RequestMapping(path = "/web-feed-provider")
public class Web_feed_providersController {


    @Autowired
    Web_feed_providersRepository web_feed_providersRepository;



    @PostMapping(path = "/add", consumes = "application/json")
    public ResponseEntity<Web_feed_providers> addWeb_feed_providers(@RequestBody Web_feed_providers web_feed_providers) {

        try {
            web_feed_providers.setUpdateddate(getCurrentDate());

            URL url = new URL(web_feed_providers.getLink());

            System.out.println("Link: " + web_feed_providers.getLink());

            XmlReader reader = new XmlReader(url);

            SyndFeed feed = new SyndFeedInput().build(reader);
            List<SyndEntry> syndEntryList = feed.getEntries();

            web_feed_providers.setNumfeeds(syndEntryList.size());
            web_feed_providers.setLastattempt(getCurrentDate());
            web_feed_providers.setLatestrecorddate(getCurrentDate());


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

        Feed_operation.deleteSingleFeed(id);
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

    @DeleteMapping(path = "/delete-by-id-and-userid")
    public @ResponseBody ResponseEntity<String> deleteByUserid(@RequestBody Web_feed_providers w){
        int affected_rows = web_feed_providersRepository.deleteProvider(w.getId(), w.getUserid());

        if (affected_rows > 0){
            return new ResponseEntity<>("Deleted", HttpStatus.OK);

        } else {
            return new ResponseEntity<>("cannot deleted", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/update")
    public @ResponseBody ResponseEntity<String> updateProviders(){
        try {

            update_all_users_providers();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Updated", HttpStatus.OK);

    }

    @PutMapping(path = "/update-provider")
    public @ResponseBody ResponseEntity<Integer> updateProvider(@RequestBody Web_feed_providers wfp){
        int affected_row = 0;
        affected_row = web_feed_providersRepository.updateProvider(wfp.getNumfeeds(), wfp.getUpdateddate(),
                wfp.getLastattempt(), wfp.getLatestrecorddate(), wfp.getId());

        if (affected_row > 0){
            return new ResponseEntity<>(affected_row, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(0, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "/update-only-provider")
    public @ResponseBody ResponseEntity<String> updateProviderOnly(@RequestBody Web_feed_providers wfp){
        int affected_row = 0;
        affected_row = web_feed_providersRepository.updateProviderOnly(wfp.getName(), wfp.getLink(), wfp.getId());

        if (affected_row > 0){
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("cannot update", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "/update-num-feed-of-provider")
    public @ResponseBody ResponseEntity<String> updateNumfeeds(@RequestBody Web_feed_providers wfp){
        int affected_row = 0;
        affected_row = web_feed_providersRepository.updateNumfeeds(wfp.getNumfeeds(), wfp.getId());


        System.out.println("---------------------------------------");
        System.out.println("update number of feeds for a provider " + wfp.getId());
        System.out.println("---------------------------------------");

        if (affected_row > 0){
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("cannot update", HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping(path = "/update-error-of-provider")
    public @ResponseBody ResponseEntity<String> updateError(@RequestBody Web_feed_providers wf){
        int affected_row = web_feed_providersRepository.updateError(wf.getError(), wf.getId());
        if (affected_row > 0){
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
