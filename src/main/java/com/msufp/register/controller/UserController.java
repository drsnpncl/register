package com.msufp.register.controller;

import com.msufp.register.model.User;
import com.msufp.register.repository.UserRepository;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
@Api(value = "UserResourceAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/users", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody User user){
        log.debug("Checking user..");
        if(userRepository.findByEmail(user.getEmail()) != null){
            log.debug("Email already exists");
            return ResponseEntity.badRequest().header("Account Already Exists!").body(null);
        } else {
            try {
                log.debug("Saving user...");
                userRepository.save(user);
                log.debug("User saved...");
                return ResponseEntity.created(new URI("/users/" + user.getEmail())).header("User Created!").body(user);
            } catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.badRequest().header("Error while redirecting!").body(null);
            }
        }
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        log.debug("Updating user...");
        User existingUser = userRepository.findByEmail(user.getEmail());
        System.out.println("Found user..." + existingUser);
        log.debug("Checking user...");
        if (existingUser != null) {
            if (!existingUser.getFirstName().equals(user.getFirstName())) {
                System.out.println("Found.. " + existingUser.getFirstName() + " Sent.. " + user.getFirstName());
                log.debug("Name does not match...");
                return ResponseEntity.badRequest().header("Account does not belong to you.").body(null);
            } else {
                System.out.println("Updating User");
                existingUser.setFirstName(user.getFirstName());
                existingUser.setLastName(user.getLastName());
                existingUser.setEmail(user.getEmail());
                existingUser.setContact(user.getContact());
                existingUser.setCity(user.getCity());
                existingUser.setCollege(user.getCollege());
                existingUser.setDepartment(user.getDepartment());
                existingUser.setGender(user.getGender());
                existingUser.setYear(user.getYear());
                log.debug("Saving user...");
                userRepository.save(user);
                return ResponseEntity.ok().header("User updated!").body(existingUser);
            }
        } else {
            return ResponseEntity.badRequest().header("Account does not exist.").body(null);
        }
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = new ArrayList<User>();
        log.debug("Getting users data...");
        userRepository.findAll().forEach(users::add);
        return ResponseEntity.ok().header("User updated!").body(users);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        log.debug("Finding user");
        return userRepository.findById(id).map(user -> {
            log.debug("User Found!");
            return ResponseEntity.ok().header("User Found").body(user);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeUser(@PathVariable Long id) {
        try {
            userRepository.delete(id);
            return ResponseEntity.ok().header("User Removed").body(id);
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
