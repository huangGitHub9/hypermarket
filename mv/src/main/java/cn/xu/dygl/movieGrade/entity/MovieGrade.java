package cn.xu.dygl.movieGrade.entity;

import java.io.Serializable;

public class MovieGrade implements Serializable {

    private String movieGradeId;
    private String movieGradeName;
    private String movieGradeImgPath;

    public MovieGrade() {
        super();
    }

    public String getMovieGradeId() {
        return movieGradeId;
    }

    public void setMovieGradeId(String movieGradeId) {
        this.movieGradeId = movieGradeId;
    }

    public String getMovieGradeName() {
        return movieGradeName;
    }

    public void setMovieGradeName(String movieGradeName) {
        this.movieGradeName = movieGradeName;
    }

    public String getMovieGradeImgPath() {
        return movieGradeImgPath;
    }

    public void setMovieGradeImgPath(String movieGradeImgPath) {
        this.movieGradeImgPath = movieGradeImgPath;
    }

    @Override
    public String toString() {
        return "MovieGrade{" +
                "movieGradeId='" + movieGradeId + '\'' +
                ", movieGradeName='" + movieGradeName + '\'' +
                '}';
    }
}
