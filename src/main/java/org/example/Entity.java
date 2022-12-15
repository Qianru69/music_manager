/* CS 514 Assignment 7
 * My name is Qianru Wei
 * My Github is Qianru69
 *  */
package org.example;
import java.util.Date;

public class Entity {
    protected String name;
    protected  static  int entityCounter = 0;
    protected int entityID;
    protected  Date dateCreated;


    public int getEntityID() {
        return entityID;
    }

    public void setEntityID(int entityID) {
        this.entityID = entityID;
    }
    public Entity() {
        this.name = "";
        entityCounter++;
        this.entityID = entityCounter;
        dateCreated = new Date();
    }

    public Entity(String name) {
        this.name = name;
        entityCounter++;
        this.entityID = entityCounter;
        dateCreated = new Date();
    }

    public boolean equals(Entity other) {
        return this.entityID == other.entityID;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
    public String toHTML() {
        return "<b>" + this.name + "</b><i> " + this.entityID + "</i>";
    }
    public String toXML() {
        return "<entity><name>" + this.name + "</name><ID> " + this.entityID + "</ID></entity>";
    }
    public String toJSON () {
        return ("{" +"\"id\": \"" + this.entityID + "\"," +"\"title\": \"" + this.getName() + "\"}");
    }
}
