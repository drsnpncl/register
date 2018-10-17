package com.msufp.register.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private int fee;
    private String numberOfMembers;
    private String typeOfParticipation; //Compulsory or Maximum
    private Boolean freeEvent;
    private Boolean abstractSelect;
    private Boolean test;
    private String status; //Open or Closed
    private String parentEvent;
    private String eventType; //Parent Event or Child Event

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public String getNumberOfMembers() {
        return numberOfMembers;
    }

    public void setNumberOfMembers(String numberOfMembers) {
        this.numberOfMembers = numberOfMembers;
    }

    public String getTypeOfParticipation() {
        return typeOfParticipation;
    }

    public void setTypeOfParticipation(String typeOfParticipation) {
        this.typeOfParticipation = typeOfParticipation;
    }

    public Boolean getFreeEvent() {
        return freeEvent;
    }

    public void setFreeEvent(Boolean freeEvent) {
        this.freeEvent = freeEvent;
    }

    public Boolean getAbstractSelect() {
        return abstractSelect;
    }

    public void setAbstractSelect(Boolean abstractSelect) {
        this.abstractSelect = abstractSelect;
    }

    public Boolean getTest() {
        return test;
    }

    public void setTest(Boolean test) {
        this.test = test;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getParentEvent() {
        return parentEvent;
    }

    public void setParentEvent(String parentEvent) {
        this.parentEvent = parentEvent;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    protected Event() {}

    public Event(String name, int fee, String numberOfMembers, String typeOfParticipation, Boolean freeEvent, Boolean abstractSelect, Boolean test, String status, String parantEvent, String eventType) {
        this.name = name;
        this.fee = fee;
        this.numberOfMembers = numberOfMembers;
        this.typeOfParticipation = typeOfParticipation;
        this.freeEvent = freeEvent;
        this.abstractSelect = abstractSelect;
        this.test = test;
        this.status = status;
        this.parentEvent = parantEvent;
        this.eventType = eventType;
    }
}
