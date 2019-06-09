package de.tuchemnitz.de.Main.entity;

//package hello;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@NamedQueries({
        @NamedQuery(name="User.updateByFeedage", query = "UPDATE User u SET u.feedage = ?1 WHERE u.id = ?2"),
        @NamedQuery(name = "User.findByUpdateperiod", query = "SELECT u.updateperiod from User u where u.id= :id")
})
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;

    private String email;
    private int status;
    private int feedage;
    private int updateperiod;

    public User() {
    }

    public User(String name, String email, int status, int feedage, int updateperiod) {
        this.name = name;
        this.email = email;
        this.status = status;
        this.feedage = feedage;
        this.updateperiod = updateperiod;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getFeedage() {
        return feedage;
    }

    public void setFeedage(int feedage) {
        this.feedage = feedage;
    }

    public int getUpdateperiod() {
        return updateperiod;
    }

    public void setUpdateperiod(int updateperiod) {
        this.updateperiod = updateperiod;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", feedage=" + feedage +
                ", updateperiod=" + updateperiod +
                '}';
    }
}
