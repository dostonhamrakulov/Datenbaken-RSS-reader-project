package de.tuchemnitz.de.Main.entity;

//package hello;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@NamedQueries({
        @NamedQuery(name = "User.updateByFeedage", query = "UPDATE User u SET u.feedage = ?1 WHERE u.id = ?2"),
        @NamedQuery(name = "User.updateByUpdateperiod", query = "UPDATE User u SET u.updateperiod = ?1 WHERE u.id = ?2"),
        @NamedQuery(name = "User.getUser", query = "SELECT u FROM User u WHERE u.email = ?1 AND u.password = ?2")
})
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;



    private String email;
    private String name;
    private String password;
    private int status;
    private int feedage;
    private int updateperiod;

    public User(String email, String name, String password, int status, int feedage, int updateperiod) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.status = status;
        this.feedage = feedage;
        this.updateperiod = updateperiod;
    }

    public User() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
