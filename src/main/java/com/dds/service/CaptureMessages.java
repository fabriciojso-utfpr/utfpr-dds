package com.dds.service;

import allbegray.slack.exception.SlackResponseErrorException;
import allbegray.slack.type.Message;
import com.dds.model.Collaborator;
import com.dds.model.CommunicationUnit;
import com.dds.model.Content;
import com.dds.model.MessageItem;
import com.dds.model.Project;
import com.dds.model.SETool;
import com.dds.model.Site;
import com.dds.model.Slack;
import com.dds.model.TypeContent;
import com.dds.scrapper.slack.Canal;
import com.dds.scrapper.slack.ChannelScrapper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import javax.persistence.Query;

public class CaptureMessages{

    private HashMap<String, Collaborator> collaboratorsWithSlackId = new HashMap<>();
    private HashMap<String, Slack> slackWithId = new HashMap<>();
    private Project project;

    public CaptureMessages(Project project) {
        this.project = project;
    }
    
    
    
    public List<CommunicationUnit> capture() {

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
        
        return this.create();
    }

    private List<CommunicationUnit> create() {
        List<CommunicationUnit> coms = new ArrayList<>();
        for (Map.Entry<String, Canal> itemCanal : ChannelScrapper.getChannels().entrySet()) {
            Canal canal = itemCanal.getValue();

            CommunicationUnit communicationUnit = new CommunicationUnit(canal.getTipoCanal());

            for (Map.Entry<String, Message> itemMessage : canal.getMensagens().entrySet()) {
                Message message = itemMessage.getValue();
                Calendar calendar = Calendar.getInstance();

                for (Map.Entry<String, String> hostsItem : canal.getHosts().entrySet()) {
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
