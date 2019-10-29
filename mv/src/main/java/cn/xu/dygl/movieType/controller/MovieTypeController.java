package cn.xu.dygl.movieType.controller;

import cn.xu.dygl.movieType.entity.MovieType;
import cn.xu.dygl.movieType.service.MovieTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/movieType")
public class MovieTypeController {

    @Resource(name = "movieTypeService")
    private MovieTypeService movieTypeService;



}
