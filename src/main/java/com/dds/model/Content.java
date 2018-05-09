package com.dds.model;


class Content {
    
    private Long id;
    private String content;
    private TypeContent type;

    public Content(String content, TypeContent type) {
        this.content = content;
        this.type = type;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
