package com.msufp.register.controller;

import com.msufp.register.model.Group;
import com.msufp.register.repository.GroupRepository;
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
@Api(value = "GroupResourceAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class GroupController {

    private final Logger log = LoggerFactory.getLogger(GroupController.class);

    @Autowired
    GroupRepository groupRepository;

    @RequestMapping(value = "/groups", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createGroup(@RequestBody Group group){
        try {
            groupRepository.save(group);
            return ResponseEntity.created(new URI("/group/" + group.getId())).header("Group Created!").body(group);
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/groups", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Group> updateGroup(@RequestBody Group group) {
        System.out.println("Updating Group");
        return groupRepository.findById(group.getId()).map(group1 -> {
            group1.setMembers(group.getMembers());
            groupRepository.save(group1);
            return ResponseEntity.ok().header("group updated!").body(group1);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @RequestMapping(value = "/groups", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Group>> getAllGroups() {
        List<Group> groups = new ArrayList<Group>();
        log.debug("Getting user groups data...");
        groupRepository.findAll().forEach(groups::add);
        return ResponseEntity.ok().header("User updated!").body(groups);
    }

    @RequestMapping(value = "/groups/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Group> getGroupById(@PathVariable Long id) {
        log.debug("Finding group");
        return groupRepository.findById(id).map(group -> {
            log.debug("Group Found!");
            return ResponseEntity.ok().header("User Found").body(group);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @RequestMapping(value = "/groups/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeGroup(@PathVariable Long id) {
        try {
            groupRepository.delete(id);
            return ResponseEntity.ok().header("Group Removed").body(id);
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
