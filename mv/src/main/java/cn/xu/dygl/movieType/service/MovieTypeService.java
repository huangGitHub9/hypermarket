package cn.xu.dygl.movieType.service;

import cn.xu.dygl.movieType.entity.MovieType;
import cn.xu.dygl.movieType.exception.MovieTypeException;

import java.util.List;
import java.util.Map;

public interface MovieTypeService {

    public void add(MovieType form) throws MovieTypeException;

    public void delete(String id);

    public void update(MovieType form) throws MovieTypeException;

    public List<MovieType> findAll();

    public MovieType findObjectById(String id);

    //查处该电影类型但是不包括自己
    public int findMovieTypeExcludeSelf(MovieType form);

    //用户分页查询
    public List<MovieType> findAllByPaging(Integer pCur, MovieType form);

    //得到该分页查询的总记录数
    public Integer getTotalCounts(MovieType form);

    public List<MovieType> findMovieTypeByMovieId(String movieId);

    public List<Map<String,Object>> movieTypeClickCounts();



}
