package com.dds.model;

import java.util.ArrayList;
import java.util.Calendar;

public class Site {
    
    private Long id;
    private String description;
    private Calendar birth;
    private Coordinates geocoors;
    private ArrayList<Collaborator> collaborators;

    public Site(String description, Calendar birth, Coordinates geocoors) {
        this.description = description;
        this.birth = birth;
        this.geocoors = geocoors;
        this.collaborators = new ArrayList<>();
    }

    public ArrayList<Collaborator> getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(ArrayList<Collaborator> collaborators) {
        this.collaborators = collaborators;
    }
    
    public void addCollaborator(Collaborator collaborator){
        this.collaborators.add(collaborator);
    }
    
    

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getBirth() {
        return birth;
    }

    public void setBirth(Calendar birth) {
        this.birth = birth;
    }

    public Coordinates getGeocoors() {
        return geocoors;
    }

    public void setGeocoors(Coordinates geocoors) {
        this.geocoors = geocoors;
    }
    
}
