package cn.xu.dygl.history.entity;

import cn.xu.dygl.movie.entity.Movie;
import cn.xu.dygl.user.entity.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class History implements Serializable {

    private String historyId ;
    private Date historyDate;
    private User user ;
    private Movie movie;


    public History() {
        super();
    }

    public String getHistoryId() {
        return historyId;
    }

    public void setHistoryId(String historyId) {
        this.historyId = historyId;
    }

    public Date getHistoryDate() {
        return historyDate;
    }

    public void setHistoryDate(Date historyDate) {
        this.historyDate = historyDate;
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
        return "History{" +
                "historyId='" + historyId + '\'' +
                ", historyDate=" + historyDate +
                ", user=" + user +
                ", movie=" + movie +
                '}';
    }
}
