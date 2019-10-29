package cn.xu.dygl.collectBox.entity;

import cn.xu.dygl.movie.entity.Movie;
import cn.xu.dygl.user.entity.User;

import java.io.Serializable;
import java.util.Date;

public class CollectBox implements Serializable {

    private String collectBoxId;
    private Date collectBoxTime;
    private User user;
    private Movie movie;

    public CollectBox() {
        super();
    }

    public CollectBox(String collectBoxId, Date collectBoxTime, User user, Movie movie) {
        this.collectBoxId = collectBoxId;
        this.collectBoxTime = collectBoxTime;
        this.user = user;
        this.movie = movie;
    }

    public String getCollectBoxId() {
        return collectBoxId;
    }

    public void setCollectBoxId(String collectBoxId) {
        this.collectBoxId = collectBoxId;
    }

    public Date getCollectBoxTime() {
        return collectBoxTime;
    }

    public void setCollectBoxTime(Date collectBoxTime) {
        this.collectBoxTime = collectBoxTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "CollectBox{" +
                "collectBoxId='" + collectBoxId + '\'' +
                ", collectBoxTime=" + collectBoxTime +
                ", user=" + user +
                ", movie=" + movie +
                '}';
    }
}
