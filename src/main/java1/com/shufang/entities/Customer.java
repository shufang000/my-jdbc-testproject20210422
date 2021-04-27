package com.shufang.entities;


import java.sql.Date;

/**
 * | customers | CREATE TABLE `customers` (
 *   `id` int(10) NOT NULL AUTO_INCREMENT,
 *   `name` varchar(15) DEFAULT NULL,
 *   `email` varchar(20) DEFAULT NULL,
 *   `birth` date DEFAULT NULL,
 *   `photo` mediumblob,
 *   PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=gb2312 |
 */


public class Customer {

    private Integer id ;
    private String name ;
    private String email ;
    private Date birth ;
    private String photo ;

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                ", photo='" + photo + '\'' +
                '}';
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

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Customer(Integer id, String name, String email, Date birth, String photo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birth = birth;
        this.photo = photo;
    }

    public Customer() {
    }
}
