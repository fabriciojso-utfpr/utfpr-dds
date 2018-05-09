package com.dds.real;

import com.dds.model.Collaborator;
import com.dds.model.Coordinates;
import com.dds.model.Project;
import com.dds.model.Site;
import com.dds.model.Slack;
import com.dds.scrapper.slack.SlackScrapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import org.junit.*;

public class ComunicacaoRealTest {
    
    @Test
    public void testGrupo01() throws IOException{
        Project fabrica = new Project("Fabrica de software distribuida");
        
        Site grupo1 = new Site("Equipe 01", Calendar.getInstance(), new Coordinates(-1000.20, -20000.00));
        fabrica.addSite(grupo1);
        
        Collaborator fabricio, amilton, renato, vinicius, alexandre;
        
        fabricio = new Collaborator("Fabricio Oliveira", "fabricio.jhonata@gmail.com");
        fabricio.addTool(new Slack("UAD6CL04T", "xoxp-353216679731-353216680163-353357000178-494014014ff1c07e4eca75dd6d9768ca"));
        
        amilton = new Collaborator("Amilton Junior", "amilton@alunos.utfpr.edu.br");
        amilton.addTool(new Slack("UAECVL9DM", "xoxp-353216679731-354437689463-353389889234-b357415f0c837c63373bd469fcfc1d98"));
        
        renato = new Collaborator("Renato Candido", "renatinhu_barbosa@hotmail.com");
        renato.addTool(new Slack("UAECW859V", "xoxp-353216679731-354438277335-353164673716-7c26e4fffc3d3cd9a9b77badacacb62d"));
        
        vinicius = new Collaborator("Vinicius Soares", "vsoares@alunos.utfpr.edu.br");
        vinicius.addTool(new Slack("UAD7P0ECT", "xoxp-353216679731-353261014435-353159024612-c6b61501d05f58208dd98c578d2b9100"));
        
        alexandre = new Collaborator("Alexandre Lerario", "alerario@gmail.com");
        alexandre.addTool(new Slack("UAD8B843V", "xoxp-353216679731-353283276131-353283491331-ac6cd002fc1375610eabf41c1d91a3d2"));
        
        grupo1.addCollaborator(amilton);
        grupo1.addCollaborator(renato);
        grupo1.addCollaborator(vinicius);
        grupo1.addCollaborator(alexandre);
        grupo1.addCollaborator(fabricio);
        
        
        SlackScrapper scrapper = new SlackScrapper(fabrica);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(scrapper.build());
        
        FileWriter fileWriter = new FileWriter("canal.json");
        fileWriter.write(json);
        fileWriter.close();
    }
}
