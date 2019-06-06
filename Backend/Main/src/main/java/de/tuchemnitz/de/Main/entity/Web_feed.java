package de.tuchemnitz.de.Main.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Web_feed {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String title;
    private String link;
    private String description;
    private String published_date;
    private String imported_date;
    private int provider_id;
    private String image;



    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getPublished_date() {
        return published_date;
    }

    public String getImported_date() {
        return imported_date;
    }

    public int getProvider_id() {
        return provider_id;
    }

    public String getImage() {
        return image;
    }
}
