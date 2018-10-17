package com.msufp.register.controller;

import com.msufp.register.model.Event;
import com.msufp.register.repository.EventRepository;
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
@Api(value = "EventControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class EventController {

    private final Logger log = LoggerFactory.getLogger(EventController.class);

    @Autowired
    EventRepository eventRepository;

    @RequestMapping(value = "/events", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createEvent(@RequestBody Event event){
        log.debug("Checking event..");
        if(eventRepository.findByName(event.getName()).isPresent()){
            System.out.println("Event already exists.. " + eventRepository.findByName(event.getName()));
            log.debug("Event already exists");
            return ResponseEntity.badRequest().header("Event Already Exists!").body(null);
        } else {
            try {
                log.debug("Saving event...");
                eventRepository.save(event);
                log.debug("event saved...");
                return ResponseEntity.created(new URI("/events/" + event.getName())).header("event Created!").body(event);
            } catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.badRequest().header("Error while redirecting!").body(null);
            }
        }
    }

    @RequestMapping(value = "/events", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> updateEvent(@RequestBody Event event) {
        log.debug("Updating event...");
        return eventRepository.findById(event.getId()).map(eventTemp -> {
            eventTemp.setName(event.getName());
            eventTemp.setParentEvent(event.getParentEvent());
            eventTemp.setNumberOfMembers(event.getNumberOfMembers());
            eventTemp.setAbstractSelect(event.getAbstractSelect());
            eventTemp.setEventType(event.getEventType());
            eventTemp.setFee(event.getFee());
            eventTemp.setFreeEvent(event.getFreeEvent());
            eventTemp.setStatus(event.getStatus());
            eventTemp.setTest(event.getTest());
            eventTemp.setTypeOfParticipation(event.getTypeOfParticipation());
            eventRepository.save(eventTemp);
            return ResponseEntity.ok().header("Event Updated").body(eventTemp);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @RequestMapping(value = "/events", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = new ArrayList<Event>();
        log.debug("Getting events data...");
        eventRepository.findAll().forEach(events::add);
        return ResponseEntity.ok().header("event updated!").body(events);
    }

    @RequestMapping(value = "/events/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        return eventRepository.findById(id).map(event -> {
            log.debug("Event found...");
            return ResponseEntity.ok().header("Event Found").body(event);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @RequestMapping(value = "/events/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeEvent(@PathVariable Long id) {
        try {
            eventRepository.delete(id);
            return ResponseEntity.ok().header("Event Removed").body(id);
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
