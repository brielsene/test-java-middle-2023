package br.com.testjavamiddle2023.domain.parent;

import org.springframework.boot.autoconfigure.domain.EntityScan;


public class Parent {
    private Long id;
    private String name;

    public Parent(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Parent(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
