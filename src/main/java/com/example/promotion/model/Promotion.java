package com.example.promotion.model;


import javax.persistence.*;

@Entity
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Type Type;
    private String description;

    public Promotion(Long id, com.example.promotion.model.Type type, String description) {
        this.id = id;
        Type = type;
        this.description = description;
    }

    public Promotion() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public com.example.promotion.model.Type getType() {
        return Type;
    }

    public void setType(com.example.promotion.model.Type type) {
        Type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}