package com.gigapet.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "child")
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long childid;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "child",
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("child")
    private List<ParentChild> parentChild = new ArrayList<>();


    @OneToMany(mappedBy = "child",
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("child")
    private List<Gigapet> gigapets = new ArrayList<>();

    @OneToMany(mappedBy = "child",
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("child")
    private List<FoodEntry> foodEntries = new ArrayList<>();

    public Child(String name, List<ParentChild> parentChild, List<Gigapet> gigapets) {
        this.name = name;
        this.parentChild = parentChild;
        this.gigapets = gigapets;
    }

    public Child() {
    }

    public long getChildid() {
        return childid;
    }

    public void setChildid(long childid) {
        this.childid = childid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ParentChild> getParentChild() {
        return parentChild;
    }

    public void setParentChild(List<ParentChild> parentChild) {
        this.parentChild = parentChild;
    }

    public List<Gigapet> getGigapets() {
        return gigapets;
    }

    public void setGigapets(List<Gigapet> gigapets) {
        this.gigapets = gigapets;
    }
}
