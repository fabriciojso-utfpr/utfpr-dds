package com.dds.DAO;

import com.dds.model.CommunicationUnit;
import java.io.Serializable;


public class CommunicationUnitDAO extends GenericDao<CommunicationUnit, Serializable>{

    public CommunicationUnitDAO() {
        super(CommunicationUnit.class);
    }

    
}
