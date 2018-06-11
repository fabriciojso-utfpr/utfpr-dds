package com.dds.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Site {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private String description;
    private Calendar birth;
    
    @Embedded
    private Coordinates geocoors;
    
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Collaborator> collaborators = new ArrayList<>();
    

    public Site() { }
    
    public Site(String description, Calendar birth, Coordinates geocoors) {
        this.description = description;
        this.birth = birth;
        this.geocoors = geocoors;
    }

    public List<Collaborator> getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(List<Collaborator> collaborators) {
        this.collaborators = collaborators;
    }
    
    public void addCollaborator(Collaborator collaborator){
        this.collaborators.add(collaborator);
    }
     
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
