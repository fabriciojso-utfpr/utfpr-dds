package com.dds.DAO;

import com.dds.model.Collaborator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

public class CollaboratorDAO extends GenericDao<Collaborator, Serializable> {

    public CollaboratorDAO() {
        super(Collaborator.class);
    }

   

}
