package com.dds.scrapper.slack;

import allbegray.slack.SlackClientFactory;
import allbegray.slack.type.Message;
import allbegray.slack.webapi.SlackWebApiClient;
import com.dds.model.TypeChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;

public class ChannelScrapper {

    protected static HashMap<String, Canal> canais;
    protected SlackWebApiClient api;
    private String id;

    public ChannelScrapper(String token, String id) {
        this.api = SlackClientFactory.createWebApiClient(token);
        this.id = id;
        if(canais == null){
            canais = new HashMap<>();
        }

        this.setChannelChannels();
        this.setDirectChannels();
        this.setGroupChannels();
    }

    public static HashMap<String, Canal> getChannels() {
        return canais;
    }

    private void setGroupChannels() {
        api.getGroupList().forEach((group) -> {
            this.addCanal(TypeChannel.GROUP, group.getId(), group.getPurpose().getValue(),
                    api.getGroupHistory(group.getId()).getMessages(),
                    group.getMembers()
            );
        });
    }

    private void setDirectChannels() {
        api.getDirectMessageChannelList().forEach((direct) -> {
            this.addCanal(TypeChannel.DIRECT, direct.getId(), "direct_" + direct.getId(),
                    api.getDirectMessageChannelHistory(direct.getId()).getMessages(),
                    direct.getUser()
            );
        });
    }

    private void setChannelChannels() {
        api.getChannelList().forEach((channel) -> {
            this.addCanal(TypeChannel.CHANNEL, channel.getId(), channel.getName(),
                    api.getChannelHistory(channel.getId()).getMessages(),
                    channel.getMembers()
            );
        });
    }

    private void addCanal(TypeChannel tc, String id, String nome, List<Message> messages, String member) {
        ArrayList<String> members = new ArrayList<String>();
        members.add(member);
        this.addCanal(tc, id, nome, messages, members);
    }

    private void addCanal(TypeChannel tc, String id, String nome, List<Message> messages, List<String> members) {
        Canal canal = new Canal(tc);
        canal.setId(id);
        canal.setNome(nome);
        canal.setMensagens(
                this.extractMessages(messages)
        );
        members.forEach(member -> {
            canal.addHost(member);
        });

        canais.put(id, canal);
    }

    private HashMap<String, Message> extractMessages(List<Message> messages) {
        HashMap<String, Message> ms = new HashMap<>();

        for (Message m : messages) {
             ms.put(DigestUtils.md5Hex(m.getText() + m.getTs()), m);
        }

        return ms;
    }
}
