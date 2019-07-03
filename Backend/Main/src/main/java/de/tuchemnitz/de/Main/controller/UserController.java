package de.tuchemnitz.de.Main.controller;

import de.tuchemnitz.de.Main.Common_code;
import de.tuchemnitz.de.Main.entity.User;
import de.tuchemnitz.de.Main.entity.Web_feed_providers;
import de.tuchemnitz.de.Main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.jws.soap.SOAPBinding;
import java.util.*;

import static de.tuchemnitz.de.Main.Common_code.*;
import static de.tuchemnitz.de.Main.Common_code.getCurrentDateinDate;
import static de.tuchemnitz.de.Main.ImportRSS.adding_single;

@RestController
@RequestMapping(path="/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    static RestTemplate restTemplate;

    User user_queried;

    @GetMapping(path = "/all")
    public @ResponseBody
    ResponseEntity<List<User>> getAllUser(){
        List<User> userList = new ArrayList<>();
        Iterable<User> userIterable = userRepository.findAll();
        userIterable.forEach(userList::add);
        if (!userList.isEmpty()){
            return new ResponseEntity<>(userList, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<User> getUser(@RequestParam("id") int userid){
        Optional<User> optional = userRepository.findById(userid);
        User user = null;
        if (optional.isPresent()){
            user = optional.get();
        }
        if (user != null){
            return new ResponseEntity<>(user, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "/update-feed-age")
    public @ResponseBody ResponseEntity<User> updateFeedAge(@RequestBody User user){
       int affected_rows = userRepository.updateByFeedage(user.getFeedage(), user.getId());

        user_queried = getUserIn(user.getId());
        if (affected_rows > 0){
            System.out.println("Updated");
            return new ResponseEntity<>(user_queried, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(user_queried, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/update-updateperiod")
    public @ResponseBody ResponseEntity<User> updateUpdateperiod(@RequestBody User user){
        int affected_rows = userRepository.updateByUpdateperiod(user.getUpdateperiod(), user.getId());

        user_queried = getUserIn(user.getId());
        if (affected_rows > 0){
            System.out.println("Updated");
            return new ResponseEntity<>(user_queried, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(user_queried, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/add-user")
    public @ResponseBody ResponseEntity<String> addUser(@RequestBody User user){

        user.setFeedage(30);
        user.setUpdateperiod(10);
        User user1 = userRepository.save(user);
        if (user1 != null){
            return new ResponseEntity<>("Created", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Not Created", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/get-user")
    public @ResponseBody ResponseEntity<User> getUser(@RequestBody User user){
        User user1 = userRepository.getUser(user.getEmail(), user.getPassword());

        if (user1 != null){
            return new ResponseEntity<>(user1, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path="/update-button")
    public @ResponseBody ResponseEntity<String> updateButton(@RequestParam("id") int userid){

        restTemplate = new RestTemplate();

        String url = REST_SERVICE_URI+"user/?id=" + userid;
        ResponseEntity<User> re = restTemplate.exchange(url, HttpMethod.GET,
                null, User.class);

        User user = new User();
        if (re.getStatusCode() == HttpStatus.FOUND){
            user = re.getBody();
        }

        ResponseEntity<List<Web_feed_providers>> res2 = restTemplate.exchange(
                REST_SERVICE_URI+"web-feed-provider/feed-providers-of-user/"+user.getId(), HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Web_feed_providers>>(){});

        List<Web_feed_providers> wpl = new ArrayList<>();
        if (res2.getStatusCode() == HttpStatus.FOUND){
            wpl = res2.getBody();
        }

        String responseString = "";

        for (int j = 0; j < wpl.size(); j++) {

            try{
                System.out.println("\n\n\n\n");
                System.out.println("User: " + user.getId());

                Web_feed_providers feed_p = wpl.get(j);
                Date lastUpdateDate = Common_code.convertStringToDate(feed_p.getUpdateddate());
                lastUpdateDate = Common_code.addMinutes(lastUpdateDate, user.getUpdateperiod());

                if (lastUpdateDate.before(getCurrentDateinDate())){
                    adding_single(feed_p);
                    responseString = "True";
                } else {
                    responseString = "False";
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(responseString, HttpStatus.OK);
    }

    public User getUserIn(int id){
        restTemplate = new RestTemplate();
        ResponseEntity<User> re = restTemplate.getForEntity(REST_SERVICE_URI+"/user/?id="+id, User.class);
        return re.getBody();
    }


}
