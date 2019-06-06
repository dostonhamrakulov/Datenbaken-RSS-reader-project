package de.tuchemnitz.de.Main.entity;

import org.hibernate.annotations.Table;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Web_feed_providers {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)

    private int provider_id;
    private String name;
    private String link;
    private String updated_date;
    private int num_feeds;
    private int error;

    public int getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(int provider_id) {
        this.provider_id = provider_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(String updated_date) {
        this.updated_date = updated_date;
    }

    public int getNum_feeds() {
        return num_feeds;
    }

    public void setNum_feeds(int num_feeds) {
        this.num_feeds = num_feeds;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }
}
