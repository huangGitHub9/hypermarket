package cn.xu.dygl.movieGrade.dao;
import cn.xu.dygl.movieGrade.entity.MovieGrade;

import java.util.List;

public interface MovieGradeDao {

    public void add(MovieGrade form);

    public void delete(String movieGradeId);

    public void update(MovieGrade form);

    public MovieGrade findObjectById(String movieGradeId);

    public List<MovieGrade> findAll();

    //查找该角色通过名字
    public int findMGByName(String name);

    //找该角色除过自己
    public int findMGAndExcludeSelf(MovieGrade form);
}
