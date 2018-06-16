package com.dds.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class CommunicationUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<MessageItem> messages = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Collaborator> hosts = new ArrayList<>();
    
    @Enumerated(EnumType.STRING) 
    private TypeChannel type;
    
    private String alternativeId;

    public String getAlternativeId() {
        return alternativeId;
    }

    public void setAlternativeId(String alternativeId) {
        this.alternativeId = alternativeId;
    }
    
    
    
    public CommunicationUnit() { }

    public CommunicationUnit(TypeChannel type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public List<MessageItem> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageItem> messages) {
        this.messages = messages;
    }

    public List<Collaborator> getHosts() {
        return hosts;
    }

    public void setHosts(List<Collaborator> hosts) {
        this.hosts = hosts;
    }

    public TypeChannel getType() {
        return type;
    }

    public void setType(TypeChannel type) {
        this.type = type;
    }

    public void addHost(Collaborator host) {
        this.hosts.add(host);
    }

    public void addMessage(MessageItem message) {
        this.messages.add(message);
    }

}
