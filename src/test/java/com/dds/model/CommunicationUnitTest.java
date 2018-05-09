package com.dds.model;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import static org.junit.Assert.*;
import org.junit.*;

public class CommunicationUnitTest {

    private Project projeto;
    private SETool slack;
    private Site equipe1;
    private Collaborator fabricio;
    private Collaborator tiago;

    @Before
    public void iniciaProjetoBase() {

        this.criaCollaborators();
        this.criaSite();
        this.criaFerramentas();
        this.criaProjetos();
    }

    private void criaProjetos() {
        this.projeto = new Project("Projeto de teste2");
        this.projeto.addSite(this.equipe1);
        this.projeto.addSETool(this.slack);
    }

    private void criaFerramentas() {
        this.slack = new SETool("Slack");
    }

    private void criaSite() {
        this.equipe1 = new Site(
                "Equipe 01",
                Calendar.getInstance(),
                new Coordinates(-23.1869224, -50.659025)
        );
        this.equipe1.addCollaborator(this.fabricio);
        this.equipe1.addCollaborator(this.tiago);
    }

    private void criaCollaborators() {
        this.fabricio = new Collaborator("Fabricio Oliveira", "fabricio.jhonata@gmail.com");
        this.tiago = new Collaborator("Tiago Pagotto", "pagotto@alunos.utfpr.edu.br");
    }

    @Test
    public void testCriaUnidadeDeComunicacao() throws IOException {
        CommunicationUnit communicationUnit = new CommunicationUnit(TypeChannel.DIRECT);
        communicationUnit.addHost(tiago);
        communicationUnit.addHost(fabricio);
        this.projeto.addCommunicationUnit(communicationUnit);

        communicationUnit.addMessage(new Message(
                this.slack,
                this.fabricio,
                new Content("Bom dia", TypeContent.TEXT),
                Calendar.getInstance()
        ));

        communicationUnit.addMessage(new Message(
                this.slack,
                this.fabricio,
                new Content("Tudo bem com você?", TypeContent.TEXT),
                Calendar.getInstance()
        ));

        communicationUnit.addMessage(new Message(
                this.slack,
                this.tiago,
                new Content("Opa! Bom dia", TypeContent.TEXT),
                Calendar.getInstance()
        ));
        
        assertEquals(3, this.projeto.getCommunicationUnits().get(0).getMessages().size());
        assertEquals(this.slack, this.projeto.getCommunicationUnits().get(0).getMessages().get(1).getSeTool());
        assertEquals(this.fabricio, this.projeto.getCommunicationUnits().get(0).getMessages().get(1).getCollaborator());
        assertEquals("Tudo bem com você?", this.projeto.getCommunicationUnits().get(0).getMessages().get(1).getContent().getContent());

        
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(this.projeto, Project.class);
        
        FileWriter fileWriter = new FileWriter("project.json");
        fileWriter.write(json);
        fileWriter.close();
    }
}
