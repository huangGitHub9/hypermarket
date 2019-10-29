package cn.xu.dygl.movieGrade.service;

import cn.xu.dygl.movieGrade.entity.MovieGrade;
import cn.xu.dygl.movieGrade.exception.MovieGradeException;

import java.util.List;

public interface MovieGradeService {

    public void add(MovieGrade form) throws MovieGradeException;

    public void delete(String movieGradeId);

    public void update(MovieGrade form) throws MovieGradeException;

    public MovieGrade findObjectById(String movieGradeId);

    public List<MovieGrade> findAll();
}
