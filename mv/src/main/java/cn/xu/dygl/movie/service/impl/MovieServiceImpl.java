package cn.xu.dygl.movie.service.impl;

import cn.xu.core.utils.PageResult;
import cn.xu.core.utils.SubStringTimeUtils;
import cn.xu.core.utils.UUIDUtils;
import cn.xu.dygl.movie.dao.MovieDao;
import cn.xu.dygl.movie.entity.Movie;
import cn.xu.dygl.movie.exception.MovieException;
import cn.xu.dygl.movie.service.MovieService;
import cn.xu.dygl.movieType.entity.MovieType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "movieService")
public class MovieServiceImpl implements MovieService {

    @Resource
    private MovieDao movieDao;

    @Transactional
    public void add(Movie movie)throws MovieException {
        int counts = movieDao.findMovieByName(movie.getMovieName());
        if(counts>0){
            throw new MovieException("电影名不能重复！");
        }


        movieDao.add(movie);
        //还要添加到电影和电影类型的关联的表
        //包含电影id和电影类型的Id 和该表的自身的id
        String movieId = movie.getMovieId();
        for(MovieType movieType : movie.getMovieTypes()){
            String movieTypeId = movieType.getMovieTypeId();
            String mmovieTypeId = UUIDUtils.getUUID();
            Map<String,Object> params = new HashMap<String,Object>();
            params.put("mmovieTypeId",mmovieTypeId);
            params.put("movieId",movieId);
            params.put("movieTypeId",movieTypeId);
            movieDao.addMMoveType(params);
        }
    }

    @Transactional
    public void delete(String id) {
        movieDao.delete(id);
    }

    @Transactional
    public void update(Movie movie,List<MovieType> oldMovieTypes)throws MovieException {

        Map<String,Object> params2 =  new HashMap<String, Object>();
        params2.put("movieId",movie.getMovieId());
        params2.put("movieName",movie.getMovieName());


        int counts = movieDao.findMoiveExcludeSelf(params2);
        if(counts>0){
            throw new MovieException("电影名不能重复！");
        }
        movieDao.update(movie);

        //true:一样 false:不一样 说明修改了
        if(!isEditMovieType(movie,oldMovieTypes)){
            //删除t_mmType表的关于movie_id 的信息 然后在重新添加
            delMMvoieTypeByMovieId(movie.getMovieId());
            String movieId = movie.getMovieId();
            for(MovieType movieType : movie.getMovieTypes()){
                String movieTypeId = movieType.getMovieTypeId();
                String mmovieTypeId = UUIDUtils.getUUID();
                Map<String,Object> params = new HashMap<String,Object>();
                params.put("mmovieTypeId",mmovieTypeId);
                params.put("movieId",movieId);
                params.put("movieTypeId",movieTypeId);
                movieDao.addMMoveType(params);
            }
        }

    }

    @Override
    public List<Movie> findAllByPaging(Integer pCur, Movie form) {
        Map<String,Object> params = new HashMap<String,Object>();
        PageResult pr = new PageResult();
        pr.setpStart(pCur);
        params.put("pStart",pr.getpStart());
        params.put("pSize",PageResult.PAGERESULT_PSIZE);
        setMovieMapParams(params,form);
        return movieDao.findAllByPaging(params);
    }

    @Override
    public Integer getTotalCounts(Movie form) {
        Map<String,Object> params = new HashMap<String,Object>();
        setMovieMapParams(params,form);
        return movieDao.getTotalCounts(params);
    }

    //判断你的视频类型是否真的修改了
    public boolean isEditMovieType(Movie movie,List<MovieType> oldMovieTypes){
        boolean flag = false;
        /*
        1.得到最新的视频类型
        2.得到之前的视频类型和旧的做比较
         */
        List<MovieType> newMovieTypes = movie.getMovieTypes();
        for(MovieType newMt : newMovieTypes){
            String newMvieTypenName = newMt.getMovieTypeName();
            flag = false;
            for(MovieType oldMt : oldMovieTypes){
                String oldMvieTypenName = oldMt.getMovieTypeName();
                if(newMvieTypenName.equals(oldMvieTypenName)){
                    flag =true;
                }
            }

            if(!flag){
                return flag;//不一样
            }
        }



        return flag;
    }

    @Override
    public void delMMvoieTypeByMovieId(String movieId) {
        movieDao.delMMvoieTypeByMovieId(movieId);
    }

    @Override
    public List<Movie> findAll() {
        return movieDao.findAll();
    }

    @Override
    public Movie findObjectById(String id) {

        return movieDao.findObjectById(id);
    }

    @Override
    public void updateClickCounts(String movieId) {
        movieDao.updateClickCounts(movieId);
    }

    @Override
    public List<Movie> findCollectMsByUId(String userId) {
        return movieDao.findCollectMsByUId(userId);
    }


    @Override
    public List<Movie> findCMAndPaging(Integer pCur, String userId) {
        Map<String,Object> params = new HashMap<String,Object>();
        PageResult pr = new PageResult();
        pr.setpStart(pCur);
        params.put("pStart",pr.getpStart());
        params.put("pSize",PageResult.PAGERESULT_PSIZE);
        params.put("userId",userId);
        return movieDao.findCMAndPaging(params);
    }

    @Override
    public Integer getCollectTC(String userId) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("userId",userId);
        return movieDao.getCollectTC(params);
    }

    //设置电影查询的参数
    private void setMovieMapParams(Map<String,Object> params,Movie form ){
        params.put("startTime", SubStringTimeUtils.getStatTime(form.getMovieTimeText()));
        params.put("endTime", SubStringTimeUtils.getEndTime(form.getMovieTimeText()));
        params.put("movieName", (form.getMovieName() != null)? "%"+form.getMovieName()+"%": form.getMovieName());
        params.put("movieDirector", (form.getMovieDirector() != null)? "%"+form.getMovieDirector()+"%": form.getMovieDirector());
        params.put("movieProtagonist", (form.getMovieProtagonist() != null)? "%"+form.getMovieProtagonist()+"%": form.getMovieProtagonist());
        //如果为空则将它的movieTypeId设我null 否则原值
        params.put("movieTypeId", form.getMovieTypes().get(0).getMovieTypeId());
        params.put("movieGradeId",form.getMovieGradeId());
    }

}


