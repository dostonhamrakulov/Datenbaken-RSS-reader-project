package de.tuchemnitz.de.Main.controller;

import de.tuchemnitz.de.Main.entity.User;
import de.tuchemnitz.de.Main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

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

    @GetMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<User> getUser(@PathVariable("id") int userid){
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


}
