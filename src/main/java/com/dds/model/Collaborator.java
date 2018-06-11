package com.dds.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


@Entity
public class Collaborator {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private String name;
    private String email;
   
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<SETool> tools = new ArrayList<>();
    
    public Collaborator() { }

    public Collaborator(String name, String email) {
        this.name = name;
        this.email = email;
    }    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<SETool> getTools() {
        return tools;
    }

    public void setTools(List<SETool> tools) {
        this.tools = tools;
    }
    
    public void addTool(SETool tool) {
        this.tools.add(tool);
    }
    
    
    
}
