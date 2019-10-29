package cn.xu.dygl.movieType.dao;

import cn.xu.dygl.movieType.entity.MovieType;

import java.util.List;
import java.util.Map;

public interface MovieTypeDao {

    public void add(MovieType movieType);

    public void delete(String id);

    public void update(MovieType movieType);

    public List<MovieType> findAll();

    public MovieType findObjectById(String id);

    //查处该电影类型但是不包括自己
    public int findMovieTypeExcludeSelf(Map<String, Object> params);

    public List<MovieType> findAllByPaging(Map<String, Object> params);

    public Integer getTotalCounts(Map<String, Object> params);

    public List<MovieType> findMovieTypeByMovieId(String movieId);

    //查询视频类型的视频的点击的总次数
    public List<Map<String,Object>> movieTypeClickCounts();


}
