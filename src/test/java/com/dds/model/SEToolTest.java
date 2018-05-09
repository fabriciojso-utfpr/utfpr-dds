package com.dds.model;

import com.dds.model.Project;
import com.dds.model.SETool;
import org.junit.Test;
import static org.junit.Assert.*;

public class SEToolTest {
  
    @Test
    public void testCriandoUmaFerramenta(){
        Project projeto = new Project("Sistema para alunos da UTFPR");
        SETool seTool = new SETool("Slack");
        assertEquals("Slack", seTool.getName());
       
    }
    
}
