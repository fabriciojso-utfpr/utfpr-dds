/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dds.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import org.hibernate.annotations.Type;

@Entity
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Type(type = "text")
    private String content;

    @Enumerated(EnumType.ORDINAL)
    private TypeContent type;

    public Content() {
    }

    public Content(String content, TypeContent type) {
        this.content = content;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TypeContent getType() {
        return type;
    }

    public void setType(TypeContent type) {
        this.type = type;
    }
}
