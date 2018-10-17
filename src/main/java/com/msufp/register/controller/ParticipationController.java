package com.msufp.register.controller;

import com.msufp.register.model.Participation;
import com.msufp.register.repository.ParticipationRepository;
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
public class ParticipationController {

    private final Logger log = LoggerFactory.getLogger(ParticipationController.class);

    @Autowired
    ParticipationRepository participationRepository;

    @RequestMapping(value = "/participation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createParticipation(@RequestBody Participation participation){
        try {
            log.debug("Saving entry...");
            participationRepository.save(participation);
            log.debug("Entry saved...");
            return ResponseEntity.created(new URI("/paarticipation/" + participation.getId())).header("Entry Created!").body(participation);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().header("Error while redirecting!").body(null);
        }
    }

    @RequestMapping(value = "/participation", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Participation> updateParticipation(@RequestBody Participation participation) {
        log.debug("Updating Entry...");
        return participationRepository.findById(participation.getId()).map(entryTemp -> {
            entryTemp.setId(participation.getId());
            entryTemp.setEventId(participation.getEventId());
            entryTemp.setGroupId(participation.getGroupId());
            entryTemp.setAbstractSelect(participation.getAbstractSelect());
            entryTemp.setPaymentAccept(participation.getPaymentAccept());
            entryTemp.setPaymentMode(participation.getPaymentMode());
            entryTemp.setRegDate(participation.getRegDate());
            participationRepository.save(entryTemp);
            return ResponseEntity.ok().header("Event Updated").body(entryTemp);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @RequestMapping(value = "/participation", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Participation>> getAllParticipation() {
        List<Participation> participations = new ArrayList<Participation>();
        log.debug("Getting All Entries...");
        participationRepository.findAll().forEach(participations::add);
        return ResponseEntity.ok().header("event updated!").body(participations);
    }

    @RequestMapping(value = "/participation/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Participation> getParticipationById(@PathVariable Long id) {
        return participationRepository.findById(id).map(participation -> {
            log.debug("Entry found");
            return ResponseEntity.ok().header("Entry Found").body(participation);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @RequestMapping(value = "/participation/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeParticipation(@PathVariable Long id) {
        try {
            participationRepository.delete(id);
            return ResponseEntity.ok().header("Participation Removed").body(id);
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
