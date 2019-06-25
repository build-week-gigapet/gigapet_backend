package com.gigapet.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "parentchild")
public class ParentChild extends Auditable implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "parentid")
    @JsonIgnoreProperties("parentchild")
    private Parent parent;

    @Id
    @ManyToOne
    @JoinColumn(name = "childid")
    @JsonIgnoreProperties("parentchild")
    private Child child;

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getChild(), getParent());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof ParentChild))
        {
            return false;
        }

        ParentChild parentChild = (ParentChild) o;

        return getChild().equals(parentChild.getChild()) && getParent().equals(parentChild.getParent());
    }
}
