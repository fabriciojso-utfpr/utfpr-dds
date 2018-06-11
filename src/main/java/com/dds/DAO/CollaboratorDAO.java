package com.dds.DAO;

import com.dds.model.Collaborator;
import java.io.Serializable;


public class CollaboratorDAO extends GenericDao<Collaborator, Serializable>{

    public CollaboratorDAO() {
        super(Collaborator.class);
    }

    
}
