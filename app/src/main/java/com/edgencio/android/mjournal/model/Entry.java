package com.edgencio.android.mjournal.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by edgencio on 6/30/18.
 */

@Entity(tableName = "entries")
public class Entry {
    @PrimaryKey (autoGenerate = true)
    private int id;
    private String topic;
    private String description;
    private Date createdAt;
    private Date updatedAt;
    private Date scheduledTo;
    private int isArquived;

    public Entry() {
    }

    public Entry(String topic, String description, Date createdAt, Date updatedAt, Date scheduledTo) {
        this.topic = topic;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.scheduledTo = scheduledTo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsArquived() {
        return isArquived;
    }

    public void setIsArquived(int isArquived) {
        this.isArquived = isArquived;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getScheduledTo() {
        return scheduledTo;
    }

    public void setScheduledTo(Date scheduledTo) {
        this.scheduledTo = scheduledTo;
    }
}
