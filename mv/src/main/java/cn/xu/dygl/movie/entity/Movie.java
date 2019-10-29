package cn.xu.dygl.movie.entity;

import cn.xu.dygl.movieGrade.entity.MovieGrade;
import cn.xu.dygl.movieType.entity.MovieType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Movie implements Serializable {
    private String movieId;
    private Date  movieTime;
    private Integer movieClickCount;
    private String movieDirector; //导演
    private String movieProtagonist;//主演
    private String movieIntro;//简介

    private String movieName;
    private String moviePath;//上传电影的路径
    private String movieImgName;
    private String movieImgPath;//电影的封面

    //电影和电影等级是多对一关系
    private MovieGrade movieGrade;


    //提供分页帮助帮助属性
    private String movieTimeText;
    private String  movieTypeName;
    private String  movieTypeId;
    private String movieGradeId;

    public String getMovieGradeId() {
        return movieGradeId;
    }

    public void setMovieGradeId(String movieGradeId) {
        this.movieGradeId = movieGradeId;
    }

    public MovieGrade getMovieGrade() {
        return movieGrade;
    }

    public void setMovieGrade(MovieGrade movieGrade) {
        this.movieGrade = movieGrade;
    }

    public String getMovieTypeId() {
        return movieTypeId;
    }

    public void setMovieTypeId(String movieTypeId) {
        this.movieTypeId = movieTypeId;
    }

    private List<MovieType> movieTypes = new ArrayList<MovieType>();

    public String getMovieTimeText() {
        return movieTimeText;
    }

    public void setMovieTimeText(String movieTimeText) {
        this.movieTimeText = movieTimeText;
    }

    public String getMovieTypeName() {
        return movieTypeName;
    }

    public void setMovieTypeName(String movieTypeName) {
        this.movieTypeName = movieTypeName;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public Date getMovieTime() {
        return movieTime;
    }

    public void setMovieTime(Date movieTime) {
        this.movieTime = movieTime;
    }

    public Integer getMovieClickCount() {
        return movieClickCount;
    }

    public void setMovieClickCount(Integer movieClickCount) {
        this.movieClickCount = movieClickCount;
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    public String getMovieProtagonist() {
        return movieProtagonist;
    }

    public void setMovieProtagonist(String movieProtagonist) {
        this.movieProtagonist = movieProtagonist;
    }

    public String getMovieIntro() {
        return movieIntro;
    }

    public void setMovieIntro(String movieIntro) {
        this.movieIntro = movieIntro;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMoviePath() {
        return moviePath;
    }

    public void setMoviePath(String moviePath) {
        this.moviePath = moviePath;
    }

    public String getMovieImgName() {
        return movieImgName;
    }

    public void setMovieImgName(String movieImgName) {
        this.movieImgName = movieImgName;
    }

    public String getMovieImgPath() {
        return movieImgPath;
    }

    public void setMovieImgPath(String movieImgPath) {
        this.movieImgPath = movieImgPath;
    }

    public List<MovieType> getMovieTypes() {
        return movieTypes;
    }

    public void setMovieTypes(List<MovieType> movieTypes) {
        this.movieTypes = movieTypes;
    }

    public Movie(String movieId, Date movieTime, Integer movieClickCount, String movieDirector, String movieProtagonist, String movieIntro, String movieName, String moviePath, String movieImgName, String movieImgPath, List<MovieType> movieTypes) {
        this.movieId = movieId;
        this.movieTime = movieTime;
        this.movieClickCount = movieClickCount;
        this.movieDirector = movieDirector;
        this.movieProtagonist = movieProtagonist;
        this.movieIntro = movieIntro;
        this.movieName = movieName;
        this.moviePath = moviePath;
        this.movieImgName = movieImgName;
        this.movieImgPath = movieImgPath;
        this.movieTypes = movieTypes;
    }

    public Movie() {
        super();
    }


    @Override
    public String toString() {
        return "Movie{" +
                "movieId='" + movieId + '\'' +
                ", movieTime=" + movieTime +
                ", movieClickCount=" + movieClickCount +
                ", movieDirector='" + movieDirector + '\'' +
                ", movieProtagonist='" + movieProtagonist + '\'' +
                ", movieIntro='" + movieIntro + '\'' +
                ", movieName='" + movieName + '\'' +
                ", moviePath='" + moviePath + '\'' +
                ", movieImgName='" + movieImgName + '\'' +
                ", movieImgPath='" + movieImgPath + '\'' +
                ", movieTypes=" + movieTypes +
                '}';
    }
}
