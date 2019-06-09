package de.tuchemnitz.de.Main.entity;

import javax.persistence.*;

@Entity
//@Table(name = "web_feed_providers")
@NamedQuery(name="Web_feed.findByWUser_id", query = "select w from Web_feed_providers w WHERE userid = :userid")
public class Web_feed_providers {

//    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
//    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "link")
    private String link;
    @Column(name = "updated_date")
    private String updated_date;

    @Column(name = "num_feeds")
    private int num_feeds;
    @Column(name = "error")
    private int error;

    @Column(name = "userid")
    private int userid;

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

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Web_feed_providers() {
    }

    public Web_feed_providers(String name, String link, String updated_date, int num_feeds, int error, int userid) {
        this.name = name;
        this.link = link;
        this.updated_date = updated_date;
        this.num_feeds = num_feeds;
        this.error = error;
        this.userid = userid;
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
                ", userid=" + userid +
                '}';
    }
}
