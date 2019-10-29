package cn.xu.dygl.movie.service;

import cn.xu.dygl.movie.entity.Movie;
import cn.xu.dygl.movie.exception.MovieException;
import cn.xu.dygl.movieType.entity.MovieType;

import java.util.List;
import java.util.Map;

public interface MovieService {

    public void add(Movie movie) throws MovieException;

    public void delete(String id);

    public void update(Movie movie, List<MovieType> oldMovieTypes) throws MovieException;

    public List<Movie> findAll();

    public Movie findObjectById(String id);

    public void delMMvoieTypeByMovieId(String movieId);

    //用户分页查询
    public List<Movie> findAllByPaging(Integer pCur, Movie form);

    //得到该分页查询的总记录数
    public Integer getTotalCounts(Movie form);

    public void updateClickCounts(String movieId);

    //查找收藏电影根据用户的Id
    public List<Movie> findCollectMsByUId(String userId);


    //查找收藏电影并分页
    public List<Movie> findCMAndPaging(Integer pCur, String userId);

    //得到收藏的总记录
    public Integer getCollectTC(String userId);

}
