package cn.xu.dygl.movie.dao;

import cn.xu.dygl.movie.entity.Movie;

import java.util.List;
import java.util.Map;

public interface MovieDao {

    public void add(Movie movie);

    public void delete(String id);

    public void update(Movie movie);

    public List<Movie> findAll();

    public Movie findObjectById(String id);



    //根据名字找电影
    public int findMovieByName(String movieName);

    //添加电影类型
    public void addMMoveType(Map<String, Object> params);

    //查找电影但是不包含自己
    public int findMoiveExcludeSelf(Map<String, Object> params);

    //上传电影和电影类型关系的数据
    public void delMMvoieTypeByMovieId(String movieId);

    //更新电影的浏览次数
    public void updateClickCounts(String movieId);

    //分页查询
    public List<Movie> findAllByPaging(Map<String, Object> params);

    //查找收藏电影根据用户的Id
    public List<Movie> findCollectMsByUId(String userId);

    //根据分页的条件的总记录数
    public Integer getTotalCounts(Map<String, Object> params);

    //查找收藏电影并分页
    public List<Movie> findCMAndPaging(Map<String, Object> params);

    //得到收藏的总记录
    public Integer getCollectTC(Map<String, Object> params);









}
