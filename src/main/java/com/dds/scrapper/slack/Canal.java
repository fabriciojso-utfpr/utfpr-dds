package com.dds.scrapper.slack;

import allbegray.slack.type.Message;
import com.dds.model.TypeChannel;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;


public class Canal {
    
    protected String id;
    protected String nome;
    protected HashMap<String, String> hosts = new HashMap<>();
    protected HashMap<String, Message> mensagens = new HashMap<>();
    protected TypeChannel tipoCanal;

    public Canal(TypeChannel tipoCanal) {
        this.tipoCanal = tipoCanal;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public HashMap<String, String> getHosts() {
        return hosts;
    }

    public void addHost(String id) {
        this.hosts.put(id, id);
    }

    public HashMap<String, Message> getMensagens() {
        return mensagens;
    }

    public void setMensagens(HashMap<String, Message> mensagens) {
        this.mensagens = mensagens;
    }


    public TypeChannel getTipoCanal() {
        return tipoCanal;
    }

    public void setTipoCanal(TypeChannel tipoCanal) {
        this.tipoCanal = tipoCanal;
    }

    
    
}
