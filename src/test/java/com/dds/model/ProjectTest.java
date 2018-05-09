package com.dds.model;

import com.dds.model.Project;
import com.dds.model.SETool;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProjectTest {
  
    public static Project getProject(){
         Project projeto = new Project("Sistema para alunos da UTFPR");
         SETool seTool = new SETool("Slack", projeto);
         
         return projeto;
    }
    
    @Test
    public void testCriandoUmProjeto(){
        Project projeto = new Project("Sistema para alunos da UTFPR");
        
        assertEquals("Sistema para alunos da UTFPR", projeto.getDescription());
    }
    
}
