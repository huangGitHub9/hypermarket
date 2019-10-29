package cn.xu.dygl.movie.controller;

import cn.xu.core.utils.DateFormatUtils;
import cn.xu.core.utils.UUIDUtils;
import cn.xu.dygl.history.entity.History;
import cn.xu.dygl.history.service.HistoryService;
import cn.xu.dygl.memPackage.service.MemPackageService;
import cn.xu.dygl.movie.entity.Movie;
import cn.xu.dygl.movie.service.MovieService;
import cn.xu.dygl.movieType.entity.MovieType;
import cn.xu.dygl.movieType.service.MovieTypeService;
import cn.xu.dygl.user.entity.User;
import cn.xu.dygl.user.service.UserService;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/movie")
public class MovieContorller {


    @Resource(name = "movieService")
    private MovieService movieService;

    @Resource(name = "movieTypeService")
    private MovieTypeService movieTypeService;

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "historyService")
    private HistoryService historyService;

    @Resource(name = "memPackageService")
    private MemPackageService memPackageService;


    @RequestMapping(value = "/movieDetailUI.action")
    public String movieDetailUI(String movieId,Model model,HttpServletRequest reqeust){
        /*
        1.加载电影信息
        2.更新视频的点击量

         */
       //1
        Movie movie = movieService.findObjectById(movieId);
        //根据电影的id 来查询所属的分类
        List<MovieType> movieTypes = movieTypeService.findMovieTypeByMovieId(movieId);
        //2.
        movieService.updateClickCounts(movieId);
        movie.setMovieTypes(movieTypes);
        model.addAttribute("movie",movie);

        return "movieDetailUI";
    }



    @RequestMapping(value = "/downloadMovie.action")
    public ResponseEntity<byte[]> downloadMovie(String movieId,
                                        HttpServletRequest reqeust)throws Exception{
        Movie movie = movieService.findObjectById(movieId);
        //得到下载的电影的名称和电影的下载路径
        String movieName = movie.getMovieName()+".mp4";
        //对下载的文件名进行编码 以防止下载是文件名乱码
        movieName = new String(movieName.getBytes("utf-8"),"iso8859-1");
        String downloadPath = reqeust.getServletContext().getRealPath("/"+movie.getMoviePath());

        File downloadFile = new File(downloadPath);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", movieName);
        //使用所有的媒体类型
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(downloadFile), headers, HttpStatus.CREATED);

    }

    @RequestMapping(value = "/moviePlayUI.action")
    public String moviePlayUI(String movieId,Model model,HttpServletRequest request){
        /*
        1.加载该视频的播放的信息
        2.根据用户的收藏电影来判断 是页面收藏的状态
        3.添加用户的浏览的历史记录
         */

        Movie movie = movieService.findObjectById(movieId);
        model.addAttribute("movie",movie);
        //加载vip套餐详情
        //加载vip选项信息
        model.addAttribute("memPackages",memPackageService.findAll());
        //找到就设置 找不到就不设置
        User session_user =(User)request.getSession().getAttribute("session_user");
        if(session_user !=null){
            //开始数据库中找该视频是否被当前用户收藏
            Map<String,Object> params = new HashMap<String,Object>();
            String userId = session_user.getUserId();
            params.put("userId",userId);
            params.put("movieId",movieId);
            int count = userService.findCollectMByUIdAndMId(params);
            if(count>0){
                model.addAttribute("exist","exist");
            }
            //添加用户的浏览记录
            addHistoryRecord(userId,movieId);
        }

        return "moviePlayUI";
    }

    /*
   添加用户浏览记录
    */
    public void addHistoryRecord(String userId,String movieId){
        /*
        1.判断session中的用户是否存在
            存在：则添加
            否则：不添加
         */
        //添加该用户的历史记录
        History history = getBeanHistory(userId,movieId);
        historyService.addHistroryRecord(history);

    }

    //封装Historybean
    private History getBeanHistory(String userId,String movieId){
        History history = new History();
        history.setHistoryId(UUIDUtils.getUUID());
        history.setHistoryDate(DateFormatUtils.getFormatDate());
        User user = new User();
        user.setUserId(userId);
        Movie movie = new Movie();
        movie.setMovieId(movieId);

        history.setUser(user);
        history.setMovie(movie);
        return history;
    }


}
