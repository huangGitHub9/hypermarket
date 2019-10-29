package cn.xu.core.utils;

import cn.xu.dygl.movieType.service.MovieTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/shape")
public class ShapeControll {


    @Resource(name = "movieTypeService")
    MovieTypeService movieTypeService;

    //视频类型的下的点击量
    @RequestMapping(value = "/movieTypeClickCounts.action")
    public @ResponseBody List<Map<String,Object>> movieTypeClickCounts(){
        List<Map<String,Object>> result =movieTypeService.movieTypeClickCounts();
        System.out.println(result);
        return result;
    }
}
