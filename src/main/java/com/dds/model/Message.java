package com.dds.model;


import java.util.Calendar;
import java.util.List;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


class Message {
    
    private Long id;
    private SETool seTool;
    private Collaborator collaborator;
    private Content content;
    private Calendar datetime;
    
    @ManyToOne
    private CommunicationUnit communicationUnit;

    public Message(SETool seTool, Collaborator collaborator, Content content, Calendar datetime) {
        this.seTool = seTool;
        this.collaborator = collaborator;
        this.content = content;
        this.datetime = datetime;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SETool getSeTool() {
        return seTool;
    }

    public void setSeTool(SETool seTool) {
        this.seTool = seTool;
    }

    public Collaborator getCollaborator() {
        return collaborator;
    }

    public void setCollaborator(Collaborator collaborator) {
        this.collaborator = collaborator;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public Calendar getDatetime() {
        return datetime;
    }

    public void setDatetime(Calendar datetime) {
        this.datetime = datetime;
    }
    
}
