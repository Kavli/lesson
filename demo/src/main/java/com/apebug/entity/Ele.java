package com.apebug.entity;

import java.util.ArrayList;
import java.util.List;

public class Ele {
    private String name;
    private Long id;
    private List<Ele> fromEleList;

    public Ele(Long id) {
        this.id = id;
        this.name = "结点-" + id;
        this.fromEleList = new ArrayList<Ele>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Ele> getFromEleList() {
        return fromEleList;
    }

    public void setFromEleList(List<Ele> fromEleList) {
        this.fromEleList = fromEleList;
    }
}
