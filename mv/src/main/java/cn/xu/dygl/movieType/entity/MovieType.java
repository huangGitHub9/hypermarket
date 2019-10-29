package cn.xu.dygl.movieType.entity;

import cn.xu.dygl.movie.entity.Movie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MovieType implements Serializable {

    private String movieTypeId;
    private String movieTypeName;

    List<Movie> movies = new ArrayList<Movie>();

    public MovieType() {
        super();
    }

    public MovieType(String movieTypeId, String movieTypeName) {
        this.movieTypeId = movieTypeId;
        this.movieTypeName = movieTypeName;
    }

    public String getMovieTypeId() {

        return movieTypeId;
    }

    public void setMovieTypeId(String movieTypeId) {
        this.movieTypeId = movieTypeId;
    }

    public String getMovieTypeName() {
        return movieTypeName;
    }

    public void setMovieTypeName(String movieTypeName) {
        this.movieTypeName = movieTypeName;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "MovieType{" +
                "movieTypeId='" + movieTypeId + '\'' +
                ", movieTypeName='" + movieTypeName + '\'' +
                ", movies=" + movies +
                '}';
    }
}
