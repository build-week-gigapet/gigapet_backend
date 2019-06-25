package com.gigapet.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "parent")
public class Parent extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long parentid;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid",
                nullable = false)
    @JsonIgnoreProperties({"parents", "hibernateLazyInitializer"})
    private User user;

    @OneToMany(mappedBy = "parent",
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("parent")
    private List<ParentChild> parentChild = new ArrayList<>();

    public Parent()
    {
    }

    public Parent(String name, User user)
    {
        this.name = name;
        this.user = user;
    }

    public long getParentid() {
        return parentid;
    }

    public void setParentid(long parentid) {
        this.parentid = parentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

}