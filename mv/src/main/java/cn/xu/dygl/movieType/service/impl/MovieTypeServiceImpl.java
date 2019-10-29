package cn.xu.dygl.movieType.service.impl;

import cn.xu.core.utils.PageResult;
import cn.xu.dygl.movie.service.MovieService;
import cn.xu.dygl.movieType.dao.MovieTypeDao;
import cn.xu.dygl.movieType.entity.MovieType;
import cn.xu.dygl.movieType.exception.MovieTypeException;
import cn.xu.dygl.movieType.service.MovieTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "movieTypeService")
public class MovieTypeServiceImpl implements MovieTypeService {

    @Resource
    private MovieTypeDao movieTypeDao;

    @Transactional
    public void add(MovieType form)throws MovieTypeException {
        List<MovieType> movieTypes = movieTypeDao.findAll();
        //判断你不能添加重复电影类型
        if(isContainMovieType(movieTypes,form.getMovieTypeName())){
            throw new MovieTypeException("电影类型名字已存在，请重新添加！");
        }
        movieTypeDao.add(form);
    }

    @Transactional
    public void delete(String id) {
        movieTypeDao.delete(id);
    }

    @Transactional
    public void update(MovieType form)throws MovieTypeException {

        //判断你不能添加重复电影类型
        if(findMovieTypeExcludeSelf(form)>0){
            throw new MovieTypeException("电影类型名字已存在，请重新修改！");
        }
        movieTypeDao.update(form);
    }

    @Override
    public List<MovieType> findAll() {
        return movieTypeDao.findAll();
    }

    @Override
    public MovieType findObjectById(String id) {
        return movieTypeDao.findObjectById(id);
    }

    @Override
    public int findMovieTypeExcludeSelf(MovieType form) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("movieTypeId",form.getMovieTypeId());
        params.put("movieTypeName",form.getMovieTypeName());

        return movieTypeDao.findMovieTypeExcludeSelf(params);
    }

    @Override
    public List<MovieType> findAllByPaging(Integer pCur, MovieType form) {
        Map<String,Object> params = new HashMap<String,Object>();
        PageResult pr = new PageResult();
        pr.setpCur(pCur);
        params.put("pCur",pr.getpCur());
        params.put("pSize",PageResult.PAGERESULT_PSIZE);
        setMoiveTypeMapParams(params,form);
        return movieTypeDao.findAllByPaging(params);
    }

    @Override
    public Integer getTotalCounts(MovieType form) {
        Map<String,Object> params = new HashMap<String,Object>();
        setMoiveTypeMapParams(params,form);
        return movieTypeDao.getTotalCounts(params);
    }

    @Override
    public List<MovieType> findMovieTypeByMovieId(String movieId) {

        return movieTypeDao.findMovieTypeByMovieId(movieId);
    }

    @Override
    public List<Map<String,Object>> movieTypeClickCounts() {
        return movieTypeDao.movieTypeClickCounts();
    }

    private Boolean isContainMovieType(List<MovieType> movieTypes, String movieTypeName){
        Boolean flag = false; //不包含
        for(MovieType movieType : movieTypes){
            if(movieType.getMovieTypeName().equals(movieTypeName)){
                flag = true;//包含
            }
        }
        return flag;
    }

    private void setMoiveTypeMapParams(Map<String,Object> params,
                                       MovieType form){
        String movieTypeName = form.getMovieTypeName();
        params.put("movieTypeName",(movieTypeName !=null)? "%"+movieTypeName+"%":movieTypeName);
    }
}
