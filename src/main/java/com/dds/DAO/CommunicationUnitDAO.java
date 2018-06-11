package com.dds.DAO;

import com.dds.model.CommunicationUnit;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

public class CommunicationUnitDAO extends GenericDao<CommunicationUnit, Serializable> {

    public CommunicationUnitDAO() {
        super(CommunicationUnit.class);
    }

    public void truncateTables() {
        List<String> tables = new ArrayList<>();
          tables.add("MessageItem");
        tables.add("Content");
      
        tables.add("CommunicationUnit");

        
     
        
        for (String table : tables) {
            entityManager.getTransaction().begin();
            String hql = String.format("delete from %s", table);
            entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS=0").executeUpdate();
            Query query = entityManager.createQuery(hql);
            query.executeUpdate();
              entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS=1").executeUpdate();
            entityManager.getTransaction().commit();
        }

    }

}
