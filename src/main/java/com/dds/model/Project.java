package com.dds.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Project {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String description;
    
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Site> sites  = new ArrayList<>();
    
    public Project() { }
    
    public Project(String description) {
        this.description = description;
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
    
    public void addSite(Site site){
        this.sites.add(site);
    }

    public List<Site> getSites() {
        return sites;
    }

    public void setSites(List<Site> sites) {
        this.sites = sites;
    }
}
