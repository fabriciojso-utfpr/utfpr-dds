package com.dds.model;

import com.dds.model.Site;
import com.dds.model.Coordinates;
import com.dds.model.ProjectTest;
import java.util.Calendar;
import org.junit.Test;


public class SiteTest {
    
    @Test
    public void testCriandoSite(){
        Site site = new Site(
            "Grupo 01",
            Calendar.getInstance(), 
            new Coordinates(-23.1869224, -50.659025)
        );
        
    }
}
