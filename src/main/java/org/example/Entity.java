/* CS 514 Assignment 7
 * My name is Qianru Wei
 * My Github is Qianru69
 *  */
package org.example;
import java.util.Date;
/**
 * Represent an entity that has a name and a unique ID.
 */
public class Entity {
    /**
     * The name of the entity
     * @param name of an entity.
     */
    protected String name;
    /**
     * The counter that count the entity ID.
     * @param entityCounter .
     */
    protected  static  int entityCounter = 0;
    /**
     * The unique ID of the entity.
     * @param entityID .
     */
    protected int entityID;
    /**
     * The Date this entity is created.
     * @param dateCreated .
     */
    protected  Date dateCreated;

    /**
     * @return the unique ID of this entity.
     */
    public int getEntityID() {
        return entityID;
    }
    /**
     * Set the unique ID based on the counts.
     * @param entityID
     */
    public void setEntityID(int entityID) {
        this.entityID = entityID;
    }
    /**
     * Create a new entity with unknown name.
     */
    public Entity() {
        this.name = "";
        entityCounter++;
        this.entityID = entityCounter;
        dateCreated = new Date();
    }
    /**
     * Create a new entity with known name.
     */
    public Entity(String name) {
        this.name = name;
        entityCounter++;
        this.entityID = entityCounter;
        dateCreated = new Date();
    }
    /**
     * Check if this entity is the same as another one.
     */
    public boolean equals(Entity other) {
        return this.entityID == other.entityID;
    }
    /**
     * @return the date that this entity is created.
     */
    public Date getDateCreated() {
        return dateCreated;
    }
    /**
     * Set the sate that this entity is created.
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
    /**
     * @return the name of this entity.
     */
    public String getName() {
        return name;
    }
    /**
     * Set the name of this entity.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return information of this entity as a string.
     */
    public String toString() {
        return this.name;
    }
    /**
     * @return information of this entity as HTML.
     */
    public String toHTML() {
        return "<b>" + this.name + "</b><i> " + this.entityID + "</i>";
    }
    /**
     * @return information of this entity as XML.
     */
    public String toXML() {
        return "<entity><name>" + this.name + "</name><ID> " + this.entityID + "</ID></entity>";
    }
    /**
     * @return information of this entity as a JSON object.
     */
    public String toJSON () {
        return ("{" +"\"id\": \"" + this.entityID + "\"," +"\"title\": \"" + this.getName() + "\"}");
    }
}
