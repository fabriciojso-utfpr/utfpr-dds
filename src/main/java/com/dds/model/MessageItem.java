/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dds.model;

import java.util.Calendar;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

@Entity
public class MessageItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE)
    private SETool seTool;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Collaborator collaborator;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Content content;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar datetime;

    public MessageItem() {
    }

    public MessageItem(SETool sETool, Collaborator collaborator, Content content, Calendar datetime) {
        this.seTool = sETool;
        this.collaborator = collaborator;
        this.content = content;
        this.datetime = datetime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
