package de.tuchemnitz.de.Main.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity // This tells Hibernate to make a table out of this class
public class Web_feed_providers {

//    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
//    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    private String name;
    private String link;
    private String updated_date;
    private int num_feeds;
    private int error;
    private int user_id;

    public int getId() {
//        Object id = entityManagerFactory.getPersistenceUnitUtil().getIdentifier(entity);
        return id;
    }

    public void setId(int id) {
        this.id = id;
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



    public Web_feed_providers() {
    }

    public Web_feed_providers(String name, String link, String updated_date, int num_feeds, int error, int user_id) {
        this.name = name;
        this.link = link;
        this.updated_date = updated_date;
        this.num_feeds = num_feeds;
        this.error = error;
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Web_feed_providers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", updated_date='" + updated_date + '\'' +
                ", num_feeds=" + num_feeds +
                ", error=" + error +
                ", user_id=" + user_id +
                '}';
    }
}
