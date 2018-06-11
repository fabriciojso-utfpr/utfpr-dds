package com.dds.model;

import javax.persistence.Entity;

@Entity
public class Slack extends SETool{
    
    private String slackID;
    private String token;
    
    public Slack() { }
    
    public Slack(String slackID, String token) {
        super("Slack");
        this.slackID = slackID;
        this.token = token;
    }

    public String getSlackID() {
        return slackID;
    }

    public void setSlackID(String slackID) {
        this.slackID = slackID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    
}
