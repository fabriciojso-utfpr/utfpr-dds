package com.dds.model;

import com.dds.model.CommunicationUnit;
import java.util.ArrayList;

public class Project {
    
    private Long id;
    private String description;
    private ArrayList<Site> sites;
    private ArrayList<SETool> seTools;
    private ArrayList<CommunicationUnit> communicationUnits;

    public Project(String description) {
        this.sites = new ArrayList<>();
        this.seTools = new ArrayList<>();
        this.communicationUnits = new ArrayList<>();
        
        this.description = description;
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
    
    public void addSite(Site site){
        this.sites.add(site);
    }

    public ArrayList<Site> getSites() {
        return sites;
    }

    public void setSites(ArrayList<Site> sites) {
        this.sites = sites;
    }

    public ArrayList<SETool> getSETools() {
        return seTools;
    }

    public void setSETools(ArrayList<SETool> seTools) {
        this.seTools = seTools;
    }
    
    public void addSETool(SETool seTool){
        this.seTools.add(seTool);
    }

    public ArrayList<SETool> getSeTools() {
        return seTools;
    }

    public void setSeTools(ArrayList<SETool> seTools) {
        this.seTools = seTools;
    }

    public ArrayList<CommunicationUnit> getCommunicationUnits() {
        return communicationUnits;
    }

    public void setCommunicationUnits(ArrayList<CommunicationUnit> communicationUnits) {
        this.communicationUnits = communicationUnits;
    }
    
    public void addCommunicationUnit(CommunicationUnit communicationUnit) {
        this.communicationUnits.add(communicationUnit);
    }

}
