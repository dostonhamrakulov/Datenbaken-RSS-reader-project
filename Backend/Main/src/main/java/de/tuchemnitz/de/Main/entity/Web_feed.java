package de.tuchemnitz.de.Main.entity;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@NamedQuery(name="Web_feed.findByLink", query = "SELECT w from Web_feed w WHERE w.link = ?1")
//@Table(name= "web_feed")
public class Web_feed {

//    private static final long serialVersionUID = 1L;

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

    @Override
    public String toString() {
        return "Web_feed{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", published_date='" + published_date + '\'' +
                ", imported_date='" + imported_date + '\'' +
                ", provider_id=" + provider_id +
                ", image='" + image + '\'' +
                '}';
    }


    public Web_feed() {
    }

    public Web_feed(String title, String link, String description, String published_date, String imported_date, int provider_id, String image) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.published_date = published_date;
        this.imported_date = imported_date;
        this.provider_id = provider_id;
        this.image = image;
    }
}
