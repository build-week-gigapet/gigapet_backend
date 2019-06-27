package com.gigapet.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "foodentry")
public class FoodEntry {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long foodentryid;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "childid")
    @JsonIgnore
    private Child child;

    @Column(name = "category")
    private int category;

    @Column(name = "date_added")
    private long dateAdded;

    @Column(name = "date_changed")
    private long dateChanged;

    @Column(name = "is_used")
    private boolean isUsed;

    public FoodEntry(Child child, int category, long dateAdded, long dateChanged, boolean isUsed) {
        this.child = child;
        this.category = category;
        this.dateAdded = dateAdded;
        this.dateChanged = dateChanged;
        this.isUsed = isUsed;
    }

    public FoodEntry(Child child, int category, boolean isUsed) {
        this.child = child;
        this.category = category;
        this.dateAdded = System.currentTimeMillis();
        this.dateChanged = System.currentTimeMillis();
        this.isUsed = isUsed;
    }

    public FoodEntry() {
    }

    public long getFoodentryid() {
        return foodentryid;
    }

    public void setFoodentryid(long foodentryid) {
        this.foodentryid = foodentryid;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public long getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(long dateAdded) {
        this.dateAdded = dateAdded;
    }

    public long getDateChanged() {
        return dateChanged;
    }

    public void setDateChanged(long dateChanged) {
        this.dateChanged = dateChanged;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }
}
