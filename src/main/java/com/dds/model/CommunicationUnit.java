package com.dds.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class CommunicationUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "communicationUnit")
    private List<Message> messages = new ArrayList<>();

    private List<Collaborator> hosts = new ArrayList<>();

    private TypeChannel type;

    public CommunicationUnit(TypeChannel type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
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

    public void addMessage(Message message) {
        this.messages.add(message);
    }

}
