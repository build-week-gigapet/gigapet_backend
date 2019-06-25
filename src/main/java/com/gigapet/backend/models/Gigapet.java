package com.gigapet.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "gigapet")
public class Gigapet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long gigapetid;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "childid")
    @JsonIgnoreProperties("Gigapet")
    private Child child;

    private int state;

    public Gigapet(String name, Child child, int state) {
        this.name = name;
        this.child = child;
        this.state = state;
    }

    public Gigapet() {
    }

    public long getGigapetid() {
        return gigapetid;
    }

    public void setGigapetid(long gigapetid) {
        this.gigapetid = gigapetid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
