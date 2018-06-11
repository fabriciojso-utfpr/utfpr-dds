package com.dds.DAO;

import com.dds.model.Site;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JList;

public class SiteDAO extends GenericDao<Site, Serializable> {

    public SiteDAO() {
        super(Site.class);
    }

    

}
