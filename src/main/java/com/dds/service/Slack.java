package com.dds.service;

import allbegray.slack.SlackClientFactory;
import allbegray.slack.type.Channel;
import allbegray.slack.type.DirectMessageChannel;
import allbegray.slack.type.Message;
import allbegray.slack.webapi.SlackWebApiClient;
import java.util.List;
import java.util.function.Function;


public class Slack {
    
    public static String token = "xoxp-353216679731-353216680163-353357000178-494014014ff1c07e4eca75dd6d9768ca";
    public static void main(String[] args) {
        SlackWebApiClient webApiClient;
        webApiClient = SlackClientFactory.createWebApiClient(token);
        List<DirectMessageChannel> mensagens = webApiClient.getDirectMessageChannelList();
        
        for(DirectMessageChannel messageChannel : mensagens){
            for(Message message : webApiClient.getDirectMessageChannelHistory(messageChannel.getId()).getMessages()){
                System.out.println(message.getText());
            }
        }
    }
}
