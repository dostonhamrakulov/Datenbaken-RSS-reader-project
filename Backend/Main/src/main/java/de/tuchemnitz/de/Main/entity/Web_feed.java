package de.tuchemnitz.de.Main.entity;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@NamedQueries({
        @NamedQuery(name="Web_feed.findByLink", query = "SELECT w from Web_feed w WHERE w.link = ?1 AND w.providerid = ?2"),
        @NamedQuery(name = "Web_feed.findByProviderid", query = "SELECT w from Web_feed w where w.providerid = ?1"),
        @NamedQuery(name = "Web_feed.deleteByProviderid", query = "DELETE FROM Web_feed w where w.providerid = ?1"),
        @NamedQuery(name = "Web_feed.updateFeed", query = "UPDATE Web_feed w SET w.title = ?1, w.publisheddate = ?2 where w.link = ?3")
})

//@Table(name= "web_feed")
public class Web_feed {

//    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String link;
    private String description;
    private String publisheddate;
    private String importeddate;
    private int providerid;
    private String image;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisheddate() {
        return publisheddate;
    }

    public void setPublisheddate(String publisheddate) {
        this.publisheddate = publisheddate;
    }

    public String getImporteddate() {
        return importeddate;
    }

    public void setImporteddate(String importeddate) {
        this.importeddate = importeddate;
    }

    public int getProviderid() {
        return providerid;
    }

    public void setProviderid(int providerid) {
        this.providerid = providerid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Web_feed() {
    }

    public Web_feed(String title, String link, String description, String publisheddate, String importeddate, int providerid, String image) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.publisheddate = publisheddate;
        this.importeddate = importeddate;
        this.providerid = providerid;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Web_feed{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", publisheddate='" + publisheddate + '\'' +
                ", importeddate='" + importeddate + '\'' +
                ", providerid=" + providerid +
                ", image='" + image + '\'' +
                '}';
    }
}
