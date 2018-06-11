package com.dds.DAO;

import com.dds.model.Project;
import com.dds.util.JPAUtil;
import java.io.Serializable;

public class ProjectDAO extends GenericDao<Project, Serializable>{
    
    
    public ProjectDAO(){
       super(Project.class);
     }  
}
