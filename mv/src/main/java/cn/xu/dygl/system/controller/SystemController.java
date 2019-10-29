package cn.xu.dygl.system.controller;

import cn.xu.core.utils.PageResult;
import cn.xu.dygl.movie.entity.Movie;
import cn.xu.dygl.movie.service.MovieService;
import cn.xu.dygl.movieType.entity.MovieType;
import cn.xu.dygl.movieType.service.MovieTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/sys")
public class SystemController {


    @Resource(name = "movieTypeService")
    private MovieTypeService movieTypeService;

    @Resource(name = "movieService")
    private MovieService movieService;

    @RequestMapping(value = "/indexUI.action")
    public String indexUI(Integer pCur,
                         Movie movieForm,
                          Model model){
        /*
        加载首页要做哪些事情
        1.电影类型的加载
        2.电影的加载
         */
        handleMovieEmpty(movieForm);
        List<Movie> movies = movieService.findAllByPaging(pCur,movieForm);
        int totalCounts = movieService.getTotalCounts(movieForm);

        List<MovieType> movieTypes = movieTypeService.findAll();

        model.addAttribute("movieTypes",movieTypes);
        model.addAttribute("movies",movies);

        model.addAttribute("counts",totalCounts);
        model.addAttribute("pCur",(pCur==null)?1:pCur);
        model.addAttribute("pSize", PageResult.PAGERESULT_PSIZE);
        model.addAttribute("movieForm",movieForm);

        return "indexUI";
    }

    private void handleMovieEmpty(Movie movieForm){
        String movieName = movieForm.getMovieName();
        String movieTypeId = movieForm.getMovieTypeId();

        movieForm.setMovieName((movieName=="")?null:movieName);

        List<MovieType> movieTypes = new ArrayList<MovieType>();
        movieTypes.add(new MovieType((movieTypeId=="" || "SearchAll".equals(movieTypeId) )?null:movieTypeId,""));
        movieForm.setMovieTypes(movieTypes);


    }
}
