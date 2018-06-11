package com.dds.real;

import allbegray.slack.exception.SlackResponseErrorException;
import allbegray.slack.type.Message;
import com.dds.DAO.CommunicationUnitDAO;
import com.dds.DAO.ProjectDAO;
import com.dds.model.*;
import com.dds.scrapper.slack.Canal;
import com.dds.scrapper.slack.ChannelScrapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import jdk.nashorn.internal.objects.Global;
import org.junit.*;

public class ComunicacaoRealTest {

    private HashMap<String, Collaborator> collaboratorsWithSlackId = new HashMap<>();
    private HashMap<String, Slack> slackWithId = new HashMap<>();

    @Test
    public void testGrupo01() {
        ProjectDAO dao = new ProjectDAO();
        Project project = dao.find(6);

        for (Site site : project.getSites()) {
            for (Collaborator collaborator : site.getCollaborators()) {
                for (SETool tool : collaborator.getTools()) {
                    if (tool instanceof Slack) {
                        Slack slack = (Slack) tool;
                        collaboratorsWithSlackId.put(slack.getSlackID(), collaborator);
                        slackWithId.put(slack.getSlackID(), slack);

                        try {
                            ChannelScrapper api = new ChannelScrapper(slack.getToken(), slack.getSlackID());
                        } catch (SlackResponseErrorException e) {
                            System.out.println("error: " + collaborator.getName());
                        }

                    }
                }
            }

        }
       
        List<CommunicationUnit> coms = this.create();
        
        for(CommunicationUnit c : coms){
            new CommunicationUnitDAO().save(c);
        }
    }

    private List<CommunicationUnit> create() {
        List<CommunicationUnit> coms = new ArrayList<>();
        for (Map.Entry<String, Canal> itemCanal : ChannelScrapper.getChannels().entrySet()) {
            Canal canal = itemCanal.getValue();

            CommunicationUnit communicationUnit = new CommunicationUnit(canal.getTipoCanal());

            for (Map.Entry<String, Message> itemMessage : canal.getMensagens().entrySet()) {
                Message message = itemMessage.getValue();
                Calendar calendar = Calendar.getInstance();
                
                for(Map.Entry<String, String> hostsItem : canal.getHosts().entrySet()){
                    communicationUnit.addHost(collaboratorsWithSlackId.get(hostsItem.getValue()));
                }
                
                
  
                
                communicationUnit
                        .addMessage(new MessageItem(
                                collaboratorsWithSlackId.get(message.getUser()) != null ? collaboratorsWithSlackId.get(message.getUser()).getTools().get(0) : null,
                                collaboratorsWithSlackId.get(message.getUser()),
                                new Content(message.getText(), message.getType().equals("message") ? TypeContent.TEXT : TypeContent.FILE),
                                timestampToCalendar(message.getTs())
                        ));
                
            }

            coms.add(communicationUnit);
        }

        return coms;
    }


    public static Calendar timestampToCalendar(String ts) {
        Double dou = Double.parseDouble(ts);
        Date date = new Date((long) (dou * 1000L));
        SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        jdf.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }


}
