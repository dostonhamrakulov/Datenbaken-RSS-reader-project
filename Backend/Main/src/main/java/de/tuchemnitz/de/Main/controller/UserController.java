package de.tuchemnitz.de.Main.controller;

import de.tuchemnitz.de.Main.entity.User;
import de.tuchemnitz.de.Main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.jws.soap.SOAPBinding;
import java.util.*;

@RestController
@RequestMapping(path="/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    public static final String REST_SERVICE_URI = "http://localhost:8080/";
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

        User user1 = userRepository.save(user);
        if (user1 != null){
            return new ResponseEntity<>("Created", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Not Created", HttpStatus.BAD_REQUEST);
        }
    }

    public User getUserIn(int id){
        restTemplate = new RestTemplate();
        ResponseEntity<User> re = restTemplate.getForEntity(REST_SERVICE_URI+"/user/?id="+id, User.class);
        return re.getBody();
    }


}
