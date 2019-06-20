package de.tuchemnitz.de.Main.entity;

import javax.persistence.*;

@Entity
//@Table(name = "web_feed_providers")
@NamedQueries({
        @NamedQuery(name="Web_feed.findByWUser_id", query = "select w from Web_feed_providers w WHERE w.userid = ?1"),
        @NamedQuery(name = "Web_feed_providers.deleteProvider", query = "delete from Web_feed_providers w where w.id = ?1 and w.userid = ?2"),
        @NamedQuery(name = "Web_feed_providers.updateProvider",
                query = "UPDATE Web_feed_providers w SET w.numfeeds = ?1, w.updateddate = ?2, w.lastattempt = ?3, w.latestrecorddate = ?4 WHERE w.id = ?5"),
        @NamedQuery(name = "Web_feed_providers.updateProviderOnly", query = "UPDATE Web_feed_providers w SET w.name = ?1, " +
                "w.link = ?2 WHERE w.id = ?3"),
        @NamedQuery(name = "Web_feed_providers.updateNumfeeds", query = "UPDATE Web_feed_providers w SET w.numfeeds = ?1 where w.id = ?2"),
        @NamedQuery(name = "Web_feed_providers.updateError", query = "UPDATE Web_feed_providers w SET w.error = ?1 WHERE w.id = ?2")
})


public class Web_feed_providers {

//    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
//    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    private String name;
    private String link;
    private String updateddate;
    private String latestrecorddate;
    private String lastattempt;
    private int numfeeds;
    private int error;
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

    public String getUpdateddate() {
        return updateddate;
    }

    public void setUpdateddate(String updateddate) {
        this.updateddate = updateddate;
    }

    public String getLatestrecorddate() {
        return latestrecorddate;
    }

    public void setLatestrecorddate(String latestrecorddate) {
        this.latestrecorddate = latestrecorddate;
    }

    public String getLastattempt() {
        return lastattempt;
    }

    public void setLastattempt(String lastattempt) {
        this.lastattempt = lastattempt;
    }

    public int getNumfeeds() {
        return numfeeds;
    }

    public void setNumfeeds(int numfeeds) {
        this.numfeeds = numfeeds;
    }

    public Web_feed_providers(String name, String link, String updateddate, String latestrecorddate, String lastattempt, int numfeeds, int error, int userid) {
        this.name = name;
        this.link = link;
        this.updateddate = updateddate;
        this.latestrecorddate = latestrecorddate;
        this.lastattempt = lastattempt;
        this.numfeeds = numfeeds;
        this.error = error;
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "Web_feed_providers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", updateddate='" + updateddate + '\'' +
                ", latestrecorddate='" + latestrecorddate + '\'' +
                ", lastattempt='" + lastattempt + '\'' +
                ", numfeeds=" + numfeeds +
                ", error=" + error +
                ", userid=" + userid +
                '}';
    }
}
