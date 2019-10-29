package cn.xu.dygl.admin.controller;

import cn.xu.core.utils.DateFormatUtils;
import cn.xu.core.utils.PageResult;
import cn.xu.core.utils.UUIDUtils;
import cn.xu.dygl.admin.entity.Admin;
import cn.xu.dygl.admin.exception.AdminException;
import cn.xu.dygl.admin.service.AdminService;
import cn.xu.dygl.memPackage.entity.MemPackage;
import cn.xu.dygl.memPackage.exception.MemPackageException;
import cn.xu.dygl.memPackage.service.MemPackageService;
import cn.xu.dygl.movie.entity.Movie;
import cn.xu.dygl.movie.exception.MovieException;
import cn.xu.dygl.movie.service.MovieService;
import cn.xu.dygl.movieGrade.entity.MovieGrade;
import cn.xu.dygl.movieGrade.exception.MovieGradeException;
import cn.xu.dygl.movieGrade.service.MovieGradeService;
import cn.xu.dygl.movieType.entity.MovieType;
import cn.xu.dygl.movieType.exception.MovieTypeException;
import cn.xu.dygl.movieType.service.MovieTypeService;
import cn.xu.dygl.privilege.entity.Privilege;
import cn.xu.dygl.privilege.exception.PrivilegeException;
import cn.xu.dygl.privilege.service.PrivilegeService;
import cn.xu.dygl.role.entity.Role;
import cn.xu.dygl.role.exception.RoleException;
import cn.xu.dygl.role.service.RoleService;
import cn.xu.dygl.user.entity.User;
import cn.xu.dygl.user.exception.UserException;
import cn.xu.dygl.user.service.UserService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "movieTypeService")
    private MovieTypeService movieTypeService;

    @Resource(name = "movieService")
    private MovieService movieService;

    @Resource(name = "privilegeService")
    private PrivilegeService privilegeService;

    @Resource(name = "roleService")
    private RoleService roleService;

    @Resource(name = "movieGradeService")
    private MovieGradeService movieGradeService;

    @Resource(name = "memPackageService")
    private MemPackageService memPackageService;

    @Resource(name = "adminService")
    private AdminService adminService;

    //处理日期的问题
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sm, true));
    }


    @RequestMapping(value = "/adminLogin.action")
    public String adminLogin(Admin admin, String captcha,HttpServletRequest request){

        /*
        1.验证码的验证
        2.bean的封装
        3.用户和密码的验证
        4.登录成功跳转到邓沟成功提示界面
        */
        //1
        String session_captcha = (String)request.getSession().getAttribute("session_captcha");
        if(!session_captcha.equalsIgnoreCase(captcha) || captcha==null){
            //返回错误消息提示页面并显示错误信息
            request.setAttribute("errorMsg","验证码错误");
            return "errorMsgUI";
        }

        try {
            Admin _admin = adminService.findAdminByNameAndPassword(admin);
            request.getSession().setAttribute("session_admin",_admin);
        } catch (AdminException e) {
            request.setAttribute("errorMsg",e.getMessage());
            return "errorMsgUI";
        }

        request.setAttribute("adminSuccessMsg","恭喜老大登录成功");

        return "adminSuccessMsgUI";
    }


    /**
     * 管路员退出
     * @param request
     * @return
     */
    @RequestMapping(value = "/quit.action")
    public String quit(HttpServletRequest request){
        request.getSession().removeAttribute("session_admin");
        //推到后台登录界面
        return "adminLoginUI";
    }

    /*
   后台首页
    */
    @RequestMapping(value = "/adminIndexUI.action")
    public String adminIndexUI(){


        return "adminIndexUI";
    }
    @RequestMapping(value = "/adminLoginUI.action")
    public String adminLoginUI(){

        return "adminLoginUI";
    }
    @RequestMapping(value = "/adminDescUI.action")
    public String adminDescUI(){

        return "adminDescUI";
    }

    @RequestMapping(value = "/welcomeAUI.action")
    public String welcomeAUI(){
        return "welcomeAUI";
    }

    //账户信息
    @RequestMapping(value = "/accountInfoUI.action")
    public String accountInfoUI(Model model){
        System.out.println(adminService.findAccountInfo());
        model.addAttribute("accountInfo",adminService.findAccountInfo());
        return "accountInfoUI";
    }


    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////用户操作////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
    private static void USER_HANDLE(){}


    /**
     * 加载用户添加页面
     */
    @RequestMapping(value = "/userAddAUI.action")
    public String userAddAUI(Model model){
        //加载角色
        model.addAttribute("roleList",roleService.findAll());
        //加载会员套餐
        model.addAttribute("memPackageList",memPackageService.findAll());
        return "userAddAUI";
    }

    /**
     * 用户添加
     */
    @RequestMapping(value = "/userAddA.action")
    public String userAddA( String userName,
                            String userPassword,
                            Date userBirthday,
                            String userGender,
                            Integer userAge,
                            String captcha,
                            String roleIds[],
                            String memPackageId,
                            @RequestParam(value = "headImgFile")CommonsMultipartFile headImgFile,
                            HttpServletRequest request,
                            Model model){

        System.out.println(headImgFile);


        //1.session中的到验证码
        String session_captcha = (String)request.getSession().getAttribute("session_captcha");
        if(!session_captcha.equalsIgnoreCase(captcha) || captcha==null){
            //返回错误消息提示页面并显示错误信息
            request.setAttribute("errorMsg","验证码错误");
            return "errorMsgUI";
        }

        //对会员套餐的的判定
        memPackageId = ("".equals(memPackageId))?null:memPackageId;
        String roleIdStrs = Arrays.toString(roleIds);

        if(roleIdStrs.contains("2")){
            //判断它是否套餐
            if(memPackageId == null){
                request.setAttribute("errorMsg","是会员就要选会员套餐！");
                return "errorMsgUI";
            }
        }else{
            if(memPackageId != null){
                request.setAttribute("errorMsg","你不是会员不能选择会员套餐！");
                return "errorMsgUI";
            }
        }


        //2.javaBean的封装
        User user = getBean(userName,userPassword,userBirthday,userGender,userAge);

        //添加用户的时候也要添加 用户和套餐之间的的关系


        //根据你设置的套餐来设置该会员的开始和到期时间
        setUserMPAndMemTime(user,memPackageId);

        //设置userId
        user.setUserId(UUIDUtils.getUUID());

        //3.对上传图片的处理 //如果图片为空则给一张默认的图片来显示
        String headImgName = User.USER_HEADIMGNAME;

        user.setUserHeadImgPath(User.USER_HEADEIMGPATH+User.USER_HEADIMGNAME);
        if(!headImgFile.isEmpty()){
            headImgName = headImgFile.getOriginalFilename();
        }
        user.setUserHeadImgName(headImgName);
        //执行上传图片操作
        String path = request.getServletContext().getRealPath("/upload/user");
        if(!headImgFile.isEmpty()){
            String newFileName = uploadImg(path,headImgName,headImgFile);
            user.setUserHeadImgPath(User.USER_HEADEIMGPATH+newFileName);
        }
        //4.用户不能重复
        //5.完成注册并添加用户和角色之间的依赖关系
        try {
            userService.addUserAndRU(user,roleIds);
        } catch (UserException e) {
            request.setAttribute("errorMsg",e.getMessage());
            return "errorMsgUI";
        }
        request.setAttribute("users",userService.findAll());
        return userListAUI(null,new User(),model);

    }

    //设置用户的会员套餐和会员的开始时间和结束时间
    private void setUserMPAndMemTime(User user,String memPackageId) {

        if(memPackageId !=null){
            //设置用户会员的开始和截止时间
            MemPackage mp = memPackageService.findObjectById(memPackageId);
            int addMonth = mp.getMemPackageMonth();
            Date memStartTime = DateFormatUtils.getFormatDate("yyyy-MM-dd HH:mm:ss");
            Date memEndTime = DateFormatUtils.getEndMonthTime(memStartTime,addMonth,"yyyy-MM-dd HH:mm:ss");
            //封装属性
            user.setUserMemStartTime(memStartTime);
            user.setUserMemEndTime(memEndTime);
        }
    }

    /**
     * 加载用户修改页面
     */
    @RequestMapping(value = "/userEditAUI.action")
    public String userEditAUI(String userId,Model model){
        User user = userService.findObjectById(userId);
        //加载该用户的角色
        model.addAttribute("user",user);
        //加载会员套餐
        model.addAttribute("memPackageList",memPackageService.findAll());
        model.addAttribute("roleList",roleService.findAll());
        return "userEditAUI";
    }

    /**
     * 用户修改
     */
    @RequestMapping(value = "/userEditA.action")
    public String userEditA(
                            String userId,
                            String userName,
                            String userPassword,
                            Date userBirthday,
                            String userGender,
                            Integer userAge,
                            String captcha,
                            String roleIds[],
                            String memPackageId,
                            @RequestParam(value = "headImgFile")CommonsMultipartFile headImgFile,
                            HttpServletRequest request,
                            Model model){

        //1.session中的到验证码
        String session_captcha = (String)request.getSession().getAttribute("session_captcha");
        if(!session_captcha.equalsIgnoreCase(captcha) || captcha==null){
            //返回错误消息提示页面并显示错误信息
            request.setAttribute("errorMsg","验证码错误");
            return "errorMsgUI";
        }

        //对会员套餐的的判定
        memPackageId = ("".equals(memPackageId))?null:memPackageId;
        String roleIdStrs = Arrays.toString(roleIds);

        if(roleIdStrs.contains("2")){
            //判断它是否套餐
            if(memPackageId == null){
                request.setAttribute("errorMsg","是会员就要选会员套餐！");
                return "errorMsgUI";
            }
        }else{
            if(memPackageId != null){
                request.setAttribute("errorMsg","你不是会员不能选择会员套餐！");
                return "errorMsgUI";
            }
        }

        //2.javaBean的封装
        User user = getBean(userName,userPassword,userBirthday,userGender,userAge);
        String headImgName = headImgFile.getOriginalFilename();
        User _user  = userService.findObjectById(userId);
        //设置userId
        user.setUserId(userId);
        if( headImgFile.getOriginalFilename()==null || headImgName.isEmpty()){
            //1.
            user.setUserHeadImgName(_user.getUserHeadImgName());
            user.setUserHeadImgPath(_user.getUserHeadImgPath());
        }else{
            //2.
            String oldHeadImgPath =request.getServletContext().getRealPath("/"+_user.getUserHeadImgPath());
            System.out.println(oldHeadImgPath);

            File oldFile = new File(oldHeadImgPath);
            oldFile.delete();

            //上传新的文件
            user.setUserHeadImgName(headImgName);
            String path = request.getServletContext().getRealPath("/upload/user");
            String newFileName = uploadImg(path,headImgName,headImgFile);
            user.setUserHeadImgPath(User.USER_HEADEIMGPATH+newFileName);
        }

        //判断该用户的套餐是否修改s

        /*
        2. 本身不是会员 修改成会员了
        3  本身是会员   但是修改套餐

        1. 本身不是会员 也没有修改
        4  本身是会员   套餐没有给修改
        1
         */
        MemPackage oldMP = _user.getMemPackage(); //知道你有没有会员套餐
        if(oldMP != null && !oldMP.getMemPackageId().equals(memPackageId)){
            //3
            setUserMPAndMemTime(user,memPackageId);
        }else if(oldMP ==null && memPackageId !=null){
            //2
            setUserMPAndMemTime(user,memPackageId);
        }else{
            //1 4
            user.setMemPackage(oldMP);
        }

        try {
            userService.updateUserAndRU(user,roleIds);
        } catch (UserException e) {
            request.setAttribute("errorMsg",e.getMessage());
            return "errorMsgUI";
        }
        return userListAUI(null,new User(),model);
    }


    /**
     * 用户删除假象的删除
     */
    @RequestMapping(value = "/userDeleteA.action")
    public String userDeleteA(String userId,Model model){
        /*
         delState更新为1 1：代表没有删除
        1.t_user
         */
        userService.updateUDelState(userId);
       // userService.delete(userId);

        return userListAUI(null,new User(),model);
    }

    /**
     * 加载用户详情页面
     */
    @RequestMapping(value = "/userDetailAUI.action")
    public String userDetailAUI(String userId,Model model){
        User user = userService.findObjectById(userId);
        model.addAttribute("roleList",roleService.findAll());
        model.addAttribute("user",user);
        return "userDetailAUI";
    }

    /**
     * 加载用户列表页面
     */
    @RequestMapping(value = "/userListAUI.action")
    public String userListAUI(Integer pCur,User userForm,Model model){

        //处理用户空串的问题
        handleUsreEmpty(userForm);
        List<User> users = userService.findAllByPaging(pCur,userForm);
        Integer totalCounts = userService.getTotalCounts(userForm);

        model.addAttribute("users",users);
        model.addAttribute("counts",totalCounts);
        model.addAttribute("pCur",(pCur==null)?1:pCur);
        model.addAttribute("pSize",PageResult.PAGERESULT_PSIZE);
        model.addAttribute("roles",roleService.findAll());
        model.addAttribute("userForm",userForm);
        return "userListAUI";
    }




    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////电影类型操作////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
    private static void MOVIE_TYPE_HANDLE(){}

    /**
     * 加载电影类型添加页面
     */
    @RequestMapping(value = "/movieTypeAddAUI.action")
    public String movieTypeAddAUI(Model model){
        List<MovieType> movieTypes = movieTypeService.findAll();
        model.addAttribute("movieTypeNames",getMovieTypeNames(movieTypes));
        return "movieTypeAddAUI";
    }



    /**
     * 电影类型添加
     */
    @RequestMapping(value = "/movieTypeAddA.action")
    public String movieTypeAddA(MovieType form, Model model){
        /*
        1.设置javabean
        2.判断是否添加重复
        3.添加成功跳转到电影类型列表
         */
        form.setMovieTypeId(UUIDUtils.getUUID());
        try {
            movieTypeService.add(form);
        } catch (MovieTypeException e) {
            model.addAttribute("errorMsg",e.getMessage());
            return "errorMsgUI";
        }

        return movieTypeListAUI(null,new MovieType(),model);
    }

    /**
     * 加载电影类型详情页面
     */
    @RequestMapping(value = "/movieTypeDetailAUI.action")
    public String movieTypeDetailAUI(String movieTypeId,Model model){

        MovieType movieType =  movieTypeService.findObjectById(movieTypeId);
        model.addAttribute("movieType",movieType);

        return "movieTypeDetailAUI";
    }

    /**
     * 加载电影类型修改页面
     */
    @RequestMapping(value = "/movieTypeEditAUI.action")
    public String movieTypeEditAUI(String movieTypeId,Model model){
        MovieType movieType =  movieTypeService.findObjectById(movieTypeId);
        List<MovieType> movieTypes = movieTypeService.findAll();

        model.addAttribute("movieTypeNames",getMovieTypeNames(movieTypes));
        model.addAttribute("movieType",movieType);
        return "movieTypeEditAUI";
    }

    /**
     *  修改电影类型
     */
    @RequestMapping(value = "/movieTypeEditA.action")
    public String movieTypeEditA(MovieType form,Model model){
        //修改的类型的名不能重复
        try {
            movieTypeService.update(form);
        } catch (MovieTypeException e) {
            model.addAttribute("errorMsg",e.getMessage());
            return "errorMsgUI";
        }

        return movieTypeListAUI(null,new MovieType(),model);
    }

    /**
     *  删除电影类型
     */
    @RequestMapping(value = "/movieTypeDeleteA.action")
    public String movieTypeDeleteA(String movieTypeId,Model model){
        movieTypeService.delete(movieTypeId);
        return movieTypeListAUI(null,new MovieType(),model);
    }

    /**
     *  电影类型列表
     */
    @RequestMapping(value = "/movieTypeListAUI.action")
    public String movieTypeListAUI(Integer pCur,MovieType movieTypeForm,Model model){
        handleMovieTypeEmpty(movieTypeForm);
        List<MovieType> movieTypes = movieTypeService.findAllByPaging(pCur,movieTypeForm);
        Integer totalCounts = movieTypeService.getTotalCounts(movieTypeForm);

        model.addAttribute("movieTypes",movieTypes);
        model.addAttribute("counts",totalCounts);
        model.addAttribute("pCur",(pCur==null)?1:pCur);
        model.addAttribute("pSize",PageResult.PAGERESULT_PSIZE);
        model.addAttribute("movieTypeForm",movieTypeForm);
        return "movieTypeListAUI";
    }



    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////电影操作////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
    private static void MOVIE_HANDLE(){};

    /**
     * 加载添加电影页面
     */
    @RequestMapping(value = "/movieAddAUI.action")
    public String movieAddAUI(Model model){
        //查询所有的视频的类型
        List<MovieType> movieTypes = movieTypeService.findAll();
        model.addAttribute("movieGradeList",movieGradeService.findAll());
        model.addAttribute("movieTypes",movieTypes);
        return "movieAddAUI";
    }

    /**
     * 加载电影详情页面
     */
    @RequestMapping(value = "/movieDetailAUI.action")
    public String movieDetailAUI(String movieId,Model model){
        Movie movie  = movieService.findObjectById(movieId);
        //根据电影的id 来查询所属的分类
        List<MovieType> movieTypes = movieTypeService.findMovieTypeByMovieId(movieId);
        movie.setMovieTypes(movieTypes);
        model.addAttribute("movie",movie);
        return "movieDetailAUI";
    }

    /**
     * 电影异步上传
     */
    @RequestMapping(value = "/movieFileAjaxUpload.action")
    public @ResponseBody Map<String,Object> movieFileAjaxUpload(
            @RequestParam(value = "movieFile")CommonsMultipartFile movieFile,
            HttpServletRequest request){
        Map<String,Object> params = new HashMap<String, Object>();

        /*
            1. 要限制文件的上传的大小 限制在1GB = 1024*1024*1024=109951162776
                1.出错：保存错误信息
            2.设置上传路径和上传的文件名
            3.保存错误或成功信息以及上传的路径名
            4.最终以json的格式返回
            5.前台获取数据并生成隐藏域最终提交给后台
         */
        //1

        String movieName = movieFile.getOriginalFilename();
        String newMovieName = UUIDUtils.getUUID()+"_"+movieName;
        String moviePath ="upload/movie/video/"+newMovieName;

        String path = request.getServletContext().getRealPath("/upload/movie/video");
        System.out.println(path);
        File _movieFile = new File(path,newMovieName);
        try {
            movieFile.transferTo(_movieFile);
            params.put("moviePath",moviePath);
            params.put("msg","上传成功");
        } catch (IOException e) {
           // e.printStackTrace();
           //保存错误信息
            params.put("msg","你上传的文件超过1GB了,请重新上传");
        }
        return params;
    }


    /**
     * 电影添加页面
     */
    @RequestMapping(value ="/movieAddA.action")
    public String movieAddA(
            String movieName,
            String movieDirector,
            Date movieTime,
            String[] movieTypeName,
            @RequestParam(value = "movieImgFile")CommonsMultipartFile movieImgFile,
            String movieProtagonist,
            String movieIntro,
            String moviePath,
            String movieGradeId,
            Model model,
            HttpServletRequest request){
        /*
        1.判断上传视频路径和上传视频封面 和是否为空
            空则：提示错误信息
        2.处理视频封面的上传
        3.封装bean
        4.开始上传
            判断视频的名字是否重复 并给出提示
         */
        String movieImgFileName = movieImgFile.getOriginalFilename();
        if(movieImgFileName.isEmpty()){
            model.addAttribute("errorMsg","视频封面图片必须上传！");
            return "errorMsgUI";
        }
        if(moviePath.isEmpty()){
            model.addAttribute("errorMsg","视频必须上传！");
            return "errorMsgUI";
        }
        String path =request.getServletContext().getRealPath("/upload/movie/img");

        //完成上传 并返回一个唯一的名字
        String newFileName = uploadImg(path,movieImgFileName,movieImgFile);
        String movieImgPath = "upload/movie/img/"+newFileName;
        Movie movie = getMovieBean(movieName,movieDirector,movieTime,
                            movieProtagonist,movieIntro,moviePath,movieImgFileName,
                movieImgPath, movieTypeName,movieGradeId);
        try {
            movieService.add(movie);
        } catch (MovieException e) {
            model.addAttribute("errorMsg",e.getMessage());
            return "errorMsgUI";
        }
        return movieListAUI(null,new Movie(),model);
    }



    /**
     * 加载电影修改页面
     */
    @RequestMapping(value = "/movieEditAUI.action")
    public String movieEditAUI(String movieId,Model model){
        Movie movie = movieService.findObjectById(movieId);
        List<MovieType> mTs = movieTypeService.findAll();
        List<MovieType> movieTypes = movieTypeService.findMovieTypeByMovieId(movieId);
        movie.setMovieTypes(movieTypes);
        model.addAttribute("movieGradeList",movieGradeService.findAll());
        model.addAttribute("movie",movie);
        model.addAttribute("movieTypes",mTs);
        return "movieEditAUI";
    }

    /**
     * 电影修改
     */
    @RequestMapping(value = "/movieEditA.action")
    public String movieEditA( String movieId,
                              String movieName,
                              String movieDirector,
                              Date movieTime,
                              String[] movieTypeName,
                              @RequestParam(value = "movieImgFile")CommonsMultipartFile movieImgFile,
                              String movieProtagonist,
                              String movieIntro,
                              String moviePath,
                              String movieGradeId,
                              Model model,
                              HttpServletRequest request){
        /*
            修改的前提是你已经上传过了 视频和视频封面了
            1.如果上传视频和上传视频封面为空则说明你没有修改
            2.如果两者不为空则删除原来重新上传(视频就不用了)
            3.修改的电影名字不能有一样
         */
        //1.
        moviePath = editMoviePath(moviePath,movieId,request);

        String movieImgName = movieService.findObjectById(movieId).getMovieImgName();
        String movieImgPath = movieService.findObjectById(movieId).getMovieImgPath();

        String newMovieImgName = movieImgFile.getOriginalFilename();
        if(!newMovieImgName.isEmpty()){
            //删除原来的然后重新上传
            String oldPath = request.getServletContext().getRealPath("/"+movieImgPath);
            File oldFile = new File(oldPath);
            oldFile.delete();
            //然后重新上传
            movieImgName = newMovieImgName;
            String path = request.getServletContext().getRealPath("/upload/movie/img");
            String newFileName = uploadImg(path,movieImgName,movieImgFile);
            movieImgPath = "upload/movie/img/"+newFileName;
        }
        Movie movie = getMovieBean(movieName,movieDirector,movieTime,
                movieProtagonist,movieIntro,moviePath,movieImgName,movieImgPath,
                movieTypeName,movieGradeId);

        //在重新设置uuid
        movie.setMovieId(movieId);
        List<MovieType> oldmovieTypes = movieTypeService.findMovieTypeByMovieId(movieId);
        try {
            movieService.update(movie,oldmovieTypes);

        } catch (MovieException e) {
            model.addAttribute("errorMsg",e.getMessage());
            return "errorMsgUI";
        }


        return movieListAUI(null,new Movie(),model);
    }


    /**
     *
     * @param movieId
     * @param model
     * @return
     */
    @RequestMapping(value = "/movieDeleteA.action")
    public String mvoieDeleteA(String movieId,Model model){
        movieService.delete(movieId);
        return movieListAUI(null,new Movie(),model);
    }


    /**
     *
     * @param pCur 当前的页数
     * @param movieForm 页面的查询条件
     * @param model
     * @return
     */
    @RequestMapping(value = "/movieListAUI.action")
    public String movieListAUI(Integer pCur,
                               Movie movieForm,
                               Model model){

        handleMovieEmpty(movieForm);
        List<Movie> movies = movieService.findAllByPaging(pCur,movieForm);
        List<MovieType> movieTypes = movieTypeService.findAll();
        int totalCounts = movieService.getTotalCounts(movieForm);

        model.addAttribute("movieGradeList",movieGradeService.findAll());
        System.out.println("====================================");
        System.out.println("==================="+totalCounts);
        System.out.println("====================================");
        model.addAttribute("movies",movies);
        model.addAttribute("movieTypes",movieTypes);
        model.addAttribute("counts",totalCounts);
        model.addAttribute("pCur",(pCur==null)?1:pCur);
        model.addAttribute("pSize",PageResult.PAGERESULT_PSIZE);
        model.addAttribute("movieForm",movieForm);
        return "movieListAUI";
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////角色操作////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
    private static void ROLE_HANDLE(){};

    @RequestMapping(value = "/roleAddAUI.action")
    public String roleAddUI(Model model){
        /*
        添加角色时并为角色分配对应的权限
         */
        model.addAttribute("roles",roleService.findAll());
        model.addAttribute("privileges",privilegeService.findAll());
        return "roleAddAUI";
    }

    @RequestMapping(value = "/roleAddA.action")
    public String roleAddA(Role form,String privilegeIds[] ,Model model){


        form.setRoleId(UUIDUtils.getUUID());
        try {
            roleService.add(form,privilegeIds);
        } catch (RoleException e) {
            model.addAttribute("errorMsg",e.getMessage());
            return "errorMsgUI";
        }
        return roleListAUI(model);
    }

    @RequestMapping(value = "/roleEditAUI.action")
    public String roleEditAUI(String roleId,Model model){
        //加载该角色和该角色的对应的权限
        model.addAttribute("role",roleService.findObjectById(roleId));
        model.addAttribute("roles",roleService.findAll());
        model.addAttribute("privileges",privilegeService.findAll());
        return "roleEditAUI";
    }

    @RequestMapping(value = "/roleEditA.action")
    public String roleEditA(Role form,String privilegeIds[],Model model){

        try {
            roleService.update(form,privilegeIds);
        } catch (RoleException e) {
            model.addAttribute("errorMsg",e.getMessage());
            return "errorMsgUI";
        }
        return roleListAUI(model);
    }

    @RequestMapping(value = "/roleDelA.action")
    public String roleDelA(String roleId,Model model){
        roleService.delete(roleId);
        return  roleListAUI(model);
    }

    @RequestMapping(value = "/roleDetailAUI.action")
    public String roleDetailAUI(String roleId,Model model){

        model.addAttribute("role",roleService.findObjectById(roleId));
        model.addAttribute("privileges",privilegeService.findAll());

        return "roleDetailAUI";
    }

    @RequestMapping(value = "/roleListAUI.action")
    public String roleListAUI(Model model){

        model.addAttribute("roles",roleService.findAll());
        return "roleListAUI";
    }



    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////权限操作////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
    private static void PRIVILEGE_HANDLE(){};

    @RequestMapping(value = "/privilegeAddAUI.action")
    public String privilegeAddUI(Model model){
        //加载数据中已经有的权限
        List<Privilege> privileges = privilegeService.findAll();
        model.addAttribute("privileges",privileges);
        return "privilegeAddAUI";
    }

    @RequestMapping(value = "/privilegeAddA.action")
    public String privilegeAddA(Privilege form,Model model){

        form.setPrivilegeId(UUIDUtils.getUUID());
        try {
            privilegeService.add(form);
        } catch (PrivilegeException e) {
            model.addAttribute("errorMsg",e.getMessage());
            return "errorMsgUI";
        }
        return privilegeListAUI(model);
    }

    @RequestMapping(value = "/privilegeEditAUI.action")
    public String privilegeEditAUI(String privilegeId,Model model){

        model.addAttribute("privilege",privilegeService.findObjectById(privilegeId));
        model.addAttribute("privileges",privilegeService.findAll());
        return "privilegeEditAUI";
    }

    @RequestMapping(value = "/privilegeEditA.action")
    public String privilegeEditA(Privilege form,Model model){
        try {
            privilegeService.update(form);
        } catch (PrivilegeException e) {
            model.addAttribute("errorMsg",e.getMessage());
            return "errorMsgUI";
        }
        return privilegeListAUI(model);
    }

    @RequestMapping(value = "/privilegeDelA.action")
    public String privilegeDelA(String privilegeId){
        privilegeService.delete(privilegeId);
        return "privilegeListAUI";
    }

    @RequestMapping(value = "/privilegeDetailAUI.action")
    public String privilegeDetailAUI(String privilegeId,Model model){
        model.addAttribute("privilege",privilegeService.findObjectById(privilegeId));
        return "privilegeDetailAUI";
    }

    @RequestMapping(value = "/privilegeListAUI.action")
    public String privilegeListAUI(Model model){
        model.addAttribute("privileges",privilegeService.findAll());
        return "privilegeListAUI";
    }


    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////电影级别操作////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
    private static void MOVIEGRADE_HANDLE(){};

    @RequestMapping(value = "/movieGradeAddAUI.action")
    public String movieGradeAddUI(Model model){

        model.addAttribute("movieGrades",movieGradeService.findAll());
        return "movieGradeAddAUI";
    }

    @RequestMapping(value = "/movieGradeAddA.action")
    public String movieGradeAddA(String movieGradeName,
                                @RequestParam(value = "movieGradeImgFile")CommonsMultipartFile movieGradeImgFile,
                                Model model,
                                 HttpServletRequest request){

        if(movieGradeImgFile==null ||  movieGradeImgFile.getOriginalFilename().isEmpty()){
            model.addAttribute("errorMsg","电影等级必须上传！");
            return "errorMsgUI";
        }

        //获取上传的文件名
        String uploadMGImgName = movieGradeImgFile.getOriginalFilename();
        String uploadPath = request.getServletContext().getRealPath("/upload/movieGrade/img");
        String newUploadFileName = uploadImg(uploadPath,uploadMGImgName,movieGradeImgFile);
        String newUploadFilePath = "upload/movieGrade/img/"+newUploadFileName;

        MovieGrade form = new MovieGrade();
        form.setMovieGradeId(UUIDUtils.getUUID());
        form.setMovieGradeName(movieGradeName);
        form.setMovieGradeImgPath(newUploadFilePath);

        try {
            movieGradeService.add(form);
        } catch (MovieGradeException e) {
            model.addAttribute("errorMsg",e.getMessage());
            return "errorMsgUI";
        }
        return movieGradeListAUI(model);
    }

    @RequestMapping(value = "/movieGradeEditAUI.action")
    public String movieGradeEditAUI(String movieGradeId,Model model){
        model.addAttribute("movieGrade",movieGradeService.findObjectById(movieGradeId));
        model.addAttribute("movieGrades",movieGradeService.findAll());
        return "movieGradeEditAUI";
    }

    @RequestMapping(value = "/movieGradeEditA.action")
    public String movieGradeEditA(String movieGradeId,
                                  String movieGradeName,
                                  @RequestParam(value = "movieGradeImgFile")CommonsMultipartFile movieGradeImgFile,
                                  Model model,
                                  HttpServletRequest request){
        MovieGrade oldMG = movieGradeService.findObjectById(movieGradeId);
        MovieGrade newMG = new MovieGrade();
        String uploadMGImgName = movieGradeImgFile.getOriginalFilename();

        newMG.setMovieGradeId(movieGradeId);
        newMG.setMovieGradeName(movieGradeName);
        //说明你的真的没有修改等级图片
        if(movieGradeImgFile == null || uploadMGImgName.isEmpty()){
            newMG.setMovieGradeImgPath(oldMG.getMovieGradeImgPath());
        }else{
            //说明你修改了等级图片
            String oldMGImgPath =request.getServletContext().getRealPath("/"+oldMG.getMovieGradeImgPath());
            File oldFile = new File(oldMGImgPath);
            oldFile.delete();

            //重新上传新的等级图片
            String uploadPath = request.getServletContext().getRealPath("/upload/movieGrade/img");
            String newUploadFileName = uploadImg(uploadPath,uploadMGImgName,movieGradeImgFile);
            String newUploadFilePath = "upload/movieGrade/img/"+newUploadFileName;
            newMG.setMovieGradeImgPath(newUploadFilePath);
        }
        try {
            movieGradeService.update(newMG);
        } catch (MovieGradeException e) {
            model.addAttribute("errorMsg",e.getMessage());
            return "errorMsgUI";
        }
        return movieGradeListAUI(model);
    }

    @RequestMapping(value = "/movieGradeDelA.action")
    public String movieGradeDelA(String movieGradeId,Model model){
        movieGradeService.delete(movieGradeId);
        return movieGradeListAUI(model);
    }

    @RequestMapping(value = "/movieGradeDetailAUI.action")
    public String movieGradeDetailAUI(String movieGradeId,Model model){
        model.addAttribute("movieGrade",movieGradeService.findObjectById(movieGradeId));
        return "movieGradeDetailAUI";
    }

    @RequestMapping(value = "/movieGradeListAUI.action")
    public String movieGradeListAUI(Model model){
        model.addAttribute("movieGrades",movieGradeService.findAll());
        return "movieGradeListAUI";
    }


    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////会员套餐操作////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
    private static void MEMPACKAGE_HANDLE(){};

    @RequestMapping(value = "/memPackageAddAUI.action")
    public String memPackageAddUI(Model model){
        //增加12个月份
        model.addAttribute("monthList",getMonths());
        return "memPackageAddAUI";
    }

    private List<Integer> getMonths(){
        List<Integer> months = new ArrayList<Integer>();
        for(int i=1;i<=12;i++){
            months.add(i);
        }
        return months;
    }

    //得到你的打折后的价格
    private Double getDiscountAfterPrice(MemPackage form){
        Double discount = form.getMemPackageDiscount();
        if(discount == null || StringUtils.isEmpty(discount) || discount ==0 ){
            //不打折
           return form.getMemPackagePrice();
        }else{
            //打折
            return form.getMemPackagePrice()*discount;
        }
    }

    @RequestMapping(value = "/memPackageAddA.action")
    public String memPackageAddA(MemPackage form, Model model){
        form.setMemPackageId(form.getMemPackageMonth()+"");
        //计算套餐的真实的价格
        form.setMemPackageResPrice(getDiscountAfterPrice(form));
        try {
            memPackageService.add(form);
        } catch (MemPackageException e) {
            model.addAttribute("errorMsg",e.getMessage());
            return "errorMsgUI";
        }
        return memPackageListAUI(model);
    }

    @RequestMapping(value = "/memPackageEditAUI.action")
    public String memPackageEditAUI(String memPackageId,Model model){
        model.addAttribute("monthList",getMonths());
        model.addAttribute("memPackage",memPackageService.findObjectById(memPackageId));
        return "memPackageEditAUI";
    }

    @RequestMapping(value = "/memPackageEditA.action")
    public String memPackageEditA(MemPackage form, Model model){
        //计算套餐的真实的价格
        form.setMemPackageResPrice(getDiscountAfterPrice(form));
        try {
            memPackageService.update(form);
        } catch (MemPackageException e) {
            model.addAttribute("errorMsg",e.getMessage());
            return "errorMsgUI";
        }
        return memPackageListAUI(model);
    }

    @RequestMapping(value = "/memPackageDelA.action")
    public String memPackageDelA(String memPacakgeId,Model model){
        memPackageService.delete(memPacakgeId);
        return memPackageListAUI(model);
    }

    @RequestMapping(value = "/memPackageDetailAUI.action")
    public String memPackageDetailAUI(String memPackageId,Model model){
        model.addAttribute("memPackage",memPackageService.findObjectById(memPackageId));
        return "memPackageDetailAUI";
    }

    @RequestMapping(value = "/memPackageListAUI.action")
    public String memPackageListAUI(Model model){
        model.addAttribute("memPackageList",memPackageService.findAll());
        return "memPackageListAUI";
    }

    //视频类型的点击量
    @RequestMapping(value = "/mTClickCountsUI.action")
    public String mTClickCountsUI(){

        return "mTClickCountsUI";
    }


    //获取所有电影类型的名字，然后同逗号分隔开
    private String getMovieTypeNames(List<MovieType> movieTypes){
        String movieTypeNames = "";
        for(int i=0;i< movieTypes.size();i++){
            movieTypeNames +=movieTypes.get(i).getMovieTypeName();
            if(i != movieTypes.size()-1){
                movieTypeNames+=",";
            }
        }
        return movieTypeNames;
    }

    //修改电影路径
    private String  editMoviePath(String moviePath,String movieId,HttpServletRequest request){
        if(moviePath.isEmpty()){
            //说明你没哟修改
            moviePath = movieService.findObjectById(movieId).getMoviePath();
        }else{
            //说明你修改了视频文件已经异步上传了  只需要删除原来的
            String oldMoviePath = movieService.findObjectById(movieId).getMoviePath();
            String oldPath = request.getServletContext().getRealPath("/"+oldMoviePath);
            File oldFile = new File(oldPath);
            oldFile.delete();
        }
        return moviePath;
    }

    private List<MovieType> getMovieTypes(String[] movieTypeNames){

        List<MovieType> movieTypes = new ArrayList<MovieType>();
        for(int i=0;i<movieTypeNames.length;i++){
            movieTypes.add(new MovieType(movieTypeNames[i],""));
        }
        return movieTypes;
    }


    //处理电影参数空串的问题
    private void handleMovieEmpty(Movie movieForm){
        String movieName = movieForm.getMovieName();
        String movieDirector = movieForm.getMovieDirector();
        String movieProtagonist =  movieForm.getMovieProtagonist();
        String movieTypeId = movieForm.getMovieTypeId();
        String movieTimeText = movieForm.getMovieTimeText();
        String movieGradeId = movieForm.getMovieGradeId();

        movieForm.setMovieName((movieName=="")?null:movieName);
        movieForm.setMovieTimeText((movieTimeText=="")?null:movieTimeText);
        movieForm.setMovieDirector((movieDirector=="")?null:movieDirector);
        movieForm.setMovieProtagonist((movieProtagonist=="")?null:movieProtagonist);
        movieForm.setMovieGradeId((movieGradeId =="")?null:movieGradeId);

        List<MovieType> movieTypes = new ArrayList<MovieType>();
        movieTypes.add(new MovieType((movieTypeId=="" )?null:movieTypeId,""));
        movieForm.setMovieTypes(movieTypes);
    }


    //处理电影类型空串的问题
    private void handleMovieTypeEmpty(MovieType form){
        String movieTypeName = form.getMovieTypeName();
        movieTypeName = (movieTypeName =="")?null: movieTypeName;
        form.setMovieTypeName(movieTypeName);
    }



    //处理用户空串的问题
    private void handleUsreEmpty(User userForm){
        String userName = userForm.getUserName();
        String userGender = userForm.getUserGender();
        String roleId = userForm.getRoleId();
        userName = (userName=="")?null:userName;
        userGender = (userGender=="")?null:userGender;
        roleId = (roleId=="")?null:roleId;

        userForm.setUserName(userName);
        userForm.setUserGender(userGender);
        userForm.setRoleId(roleId);
    }


    /**
     *
     * @param path 上传的路径
     * @param fileName 上传的文件名
     * @param file  表单的文件项
     * @return
     */
    private String uploadImg(String path,String fileName,CommonsMultipartFile file){
        String newFileName = UUIDUtils.getUUID()+"_"+fileName;
        File uploadFile = new File(path,newFileName);
        try {
            file.transferTo(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newFileName;
    }

    public Movie getMovieBean(
                    String movieName,
                    String movieDirector,
                    Date movieTime,
                    String movieProtagonist,
                    String movieIntro,
                    String moviePath,
                    String movieImgName,
                    String movieImgPath,
                    String[] movieTypeName,
                    String movieGradeId){
        Movie movie = new Movie();
        movie.setMovieId(UUIDUtils.getUUID());
        movie.setMovieName(movieName);
        movie.setMovieDirector(movieDirector);
        movie.setMovieTime(movieTime);
        movie.setMovieProtagonist(movieProtagonist);
        movie.setMovieIntro(movieIntro);
        movie.setMoviePath(moviePath);
        movie.setMovieImgName(movieImgName);
        movie.setMovieImgPath(movieImgPath);
        movie.setMovieTypes(getMovieTypes(movieTypeName));
        MovieGrade mg = new MovieGrade();
        mg.setMovieGradeId(movieGradeId);
        movie.setMovieGrade(mg);
        return  movie;
    }

    //javabean的简单的封装
    private User getBean( String userName, String userPassword, Date userBirthday,
                          String userGender, Integer userAge){
        User user = new User();
        user.setUserName(userName);
        user.setUserPassword(userPassword);
        user.setUserAge(userAge);
        user.setUserGender(userGender);
        user.setUserBirthday(userBirthday);
        return user;
    }
}