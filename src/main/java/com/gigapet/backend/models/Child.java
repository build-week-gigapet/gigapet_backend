package com.gigapet.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private String favorites;
    private String allergies;

    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnore
    private User user;


    @OneToMany(mappedBy = "child",
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("child")
    private List<Gigapet> gigapets = new ArrayList<>();

    @OneToMany(mappedBy = "child",
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("child")
    private List<FoodEntry> foodEntries = new ArrayList<>();

    public Child(String name, String favorites, String allergies, User user, List<Gigapet> gigapets, List<FoodEntry> foodEntries) {
        this.name = name;
        this.favorites = favorites;
        this.allergies = allergies;
        this.user = user;
        this.gigapets = gigapets;
        this.foodEntries = foodEntries;
    }

    public Child(String name, User user, List<Gigapet> gigapets, List<FoodEntry> foodEntries) {
        this.name = name;
        this.user = user;
        this.gigapets = gigapets;
        this.foodEntries = foodEntries;
    }

    public Child(String name, List<Gigapet> gigapets, List<FoodEntry> foodEntries) {
        this.name = name;
        this.gigapets = gigapets;
        this.foodEntries = foodEntries;
    }

    public Child(String name) {
        this.name = name;
        gigapets = new ArrayList<>();
        foodEntries = new ArrayList<>();
    }


    public Child(String name, User user, String allergies, String favorites) {
        this.name = name;
        this.user = user;
        this.favorites = favorites;
        this.allergies = allergies;
        gigapets = new ArrayList<>();
        foodEntries = new ArrayList<>();
    }


    public Child() {
    }

    public List<FoodEntry> getFoodEntries() {
        return foodEntries;
    }

    public void setFoodEntries(List<FoodEntry> foodEntries) {
        this.foodEntries = foodEntries;
    }

    public User getUser() {
        return user;
    }

    public String getFavorites() {
        return favorites;
    }

    public void setFavorites(String favorites) {
        this.favorites = favorites;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public void setUser(User user) {
        this.user = user;
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

    public List<Gigapet> getGigapets() {
        return gigapets;
    }

    public void setGigapets(List<Gigapet> gigapets) {
        this.gigapets = gigapets;
    }
}
