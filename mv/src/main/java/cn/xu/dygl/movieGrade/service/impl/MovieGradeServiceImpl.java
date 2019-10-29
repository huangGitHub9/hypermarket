package cn.xu.dygl.movieGrade.service.impl;

import cn.xu.dygl.movieGrade.dao.MovieGradeDao;
import cn.xu.dygl.movieGrade.entity.MovieGrade;
import cn.xu.dygl.movieGrade.exception.MovieGradeException;
import cn.xu.dygl.movieGrade.service.MovieGradeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "movieGradeService")
public class MovieGradeServiceImpl implements MovieGradeService {

    @Resource
    private MovieGradeDao movieGradeDao;

    @Transactional
    @Override
    public void add(MovieGrade form)throws MovieGradeException {
        int counts = movieGradeDao.findMGByName(form.getMovieGradeName());
        if(counts>0){
            throw new MovieGradeException("电影级等级已存在！ 请重新添加");
        }

        movieGradeDao.add(form);
    }

    @Transactional
    @Override
    public void delete(String movieGradeId) {
        movieGradeDao.delete(movieGradeId);
    }

    @Transactional
    @Override
    public void update(MovieGrade form)throws MovieGradeException {
        int counts = movieGradeDao.findMGAndExcludeSelf(form);
        if(counts >0){
            throw new MovieGradeException("电影等级已存在！请重新修改");
        }
        movieGradeDao.update(form);
    }

    @Override
    public MovieGrade findObjectById(String movieGradeId) {
        return movieGradeDao.findObjectById(movieGradeId);
    }

    @Override
    public List<MovieGrade> findAll() {
        return movieGradeDao.findAll();
    }
}
