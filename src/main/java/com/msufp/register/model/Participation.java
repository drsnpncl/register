package com.msufp.register.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Participation {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Long groupId;
    private Long eventId;
    private Date regDate;
    private String paymentMode;
    private Boolean paymentAccept;
    private Boolean abstractSelect;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Boolean getPaymentAccept() {
        return paymentAccept;
    }

    public void setPaymentAccept(Boolean paymentAccept) {
        this.paymentAccept = paymentAccept;
    }

    public Boolean getAbstractSelect() {
        return abstractSelect;
    }

    public void setAbstractSelect(Boolean abstractSelect) {
        this.abstractSelect = abstractSelect;
    }

    protected Participation(){}

    public Participation(Long groupId, Long eventId, Date regDate, String paymentMode, Boolean paymentAccept, Boolean abstractSelect) {
        this.groupId = groupId;
        this.eventId = eventId;
        this.regDate = regDate;
        this.paymentMode = paymentMode;
        this.paymentAccept = paymentAccept;
        this.abstractSelect = abstractSelect;
    }
}
