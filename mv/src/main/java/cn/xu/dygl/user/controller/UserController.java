package cn.xu.dygl.user.controller;


import cn.xu.core.utils.AuthUtils;
import cn.xu.core.utils.DateFormatUtils;
import cn.xu.core.utils.PageResult;
import cn.xu.core.utils.UUIDUtils;
import cn.xu.dygl.account.entity.Account;
import cn.xu.dygl.account.service.AccountService;
import cn.xu.dygl.history.entity.History;
import cn.xu.dygl.history.service.HistoryService;
import cn.xu.dygl.memPackage.entity.MemPackage;
import cn.xu.dygl.memPackage.service.MemPackageService;
import cn.xu.dygl.movie.entity.Movie;
import cn.xu.dygl.movie.service.MovieService;
import cn.xu.dygl.user.entity.Consume;
import cn.xu.dygl.user.entity.User;
import cn.xu.dygl.user.exception.UserException;
import cn.xu.dygl.user.service.UserService;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import sun.java2d.pipe.SpanIterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Resource(name ="userService")
    private UserService userService;

    @Resource(name = "movieService")
    private MovieService movieService;

    @Resource(name = "historyService")
    private HistoryService historyService;

    //套餐信息
    @Resource(name="memPackageService")
    private MemPackageService memPackageService;

    //账户
    @Resource(name = "accountService")
    private AccountService accountService;




    //处理日期的问题
    @InitBinder
    public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sm, true));
	}

    /**
     * 用户登录
     * @param user
     * @param captcha
     * @param request
     * @return
     */
	@RequestMapping(value = "/login.action")
	public String login(User user,String captcha,HttpServletRequest request){
        /*
        0.对验证码进行验证
        1.封装用户信息
        2.验证用户和密码是否正确
        3.完成登录
        4.保存用户信息到session中
        5.跳转到首页并改变导航栏内容
         */
        //0.session中的到验证码
        String session_captcha = (String)request.getSession().getAttribute("session_captcha");
        if(!session_captcha.equalsIgnoreCase(captcha) || captcha==null){
            //返回错误消息提示页面并显示错误信息
            request.setAttribute("errorMsg","验证码错误");
            return "errorMsgUI";
        }
        //2 3 4
        try {
            User _user = userService.login(user);
            request.getSession().setAttribute("session_user",_user);
            System.out.println("session_user2:"+request.getSession().getAttribute("session_user"));
        } catch (UserException e) {
            request.setAttribute("errorMsg",e.getMessage());
            return "errorMsgUI";
        }
        //保存成功信息到successMsg中
        request.setAttribute("successMsg","恭喜登录成功");
        request.setAttribute("jumpView","indexUI.action");
        return "successMsgUI";
    }

    /**
     * 用户注册
     * @param userName
     * @param userPassword
     * @param userBirthday
     * @param userGender
     * @param userAge
     * @param captcha
     * @param headImgFile
     * @param request
     * @return
     */
    @RequestMapping(value = "/regist.action")
    public String regist(
            String userName,
            String userPassword,
            Date userBirthday,
            String userGender,
            Integer userAge,
            String captcha,
            @RequestParam(value = "headImgFile")CommonsMultipartFile headImgFile,
            HttpServletRequest request){

        /*
            1.判断验证码是否正确
            2.设置用户的头像的名称、头像的路径，和用户的id
            3.设置文件的上传的路径
            4.用户名不能重复
            5.完成注册操作并保存成功信息到successsMsg页面
         */
        //1.session中的到验证码
        String session_captcha = (String)request.getSession().getAttribute("session_captcha");
        if(!session_captcha.equalsIgnoreCase(captcha) || captcha==null){
            //返回错误消息提示页面并显示错误信息
            request.setAttribute("errorMsg","验证码错误");
            return "errorMsgUI";
        }

        //2.javaBean的封装
        User user = getBean(userName,userPassword,userBirthday,userGender,userAge);
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
        //5.完成注册
        //默认用户的角色为 一般用户
        String[] roleIds = {"1"};
        try {
            userService.addUserAndRU(user,roleIds);
        } catch (UserException e) {
            request.setAttribute("errorMsg",e.getMessage());
            return "errorMsgUI";
        }

        //保存成功信息到successMsg中
        request.setAttribute("successMsg","恭喜你注册成功");
        request.setAttribute("jumpView","indexUI.action");
        return "successMsgUI";
    }

    /**
     * 用户信息修改
     * @param userName
     * @param userPassword
     * @param userBirthday
     * @param userGender
     * @param userAge
     * @param captcha
     * @param headImgFile
     * @param request
     * @return
     */
    @RequestMapping(value = "/edit.action")
    public String edit(
            String userName,
            String userPassword,
            Date userBirthday,
            String userGender,
            Integer userAge,
            String captcha,
            @RequestParam(value = "headImgFile")CommonsMultipartFile headImgFile,
            HttpServletRequest request){

        /*
            1.验证码的验证
            2.bean属性的封装
            3.处理文件上传的问题
                3.1 如果上传则上传旧文件重新上传新文件并设置头像名和头像路径
                3.2 如有没有上传则文件名为空，从session中获取该用户的头像名和头像路径并完成封装
            4.修改的用户名和密码不能重复
            5.修改成功则跳转到系统首页
         */

        //1.session中的到验证码
        String session_captcha = (String)request.getSession().getAttribute("session_captcha");
        if(!session_captcha.equalsIgnoreCase(captcha) || captcha==null){
            //返回错误消息提示页面并显示错误信息
            request.setAttribute("errorMsg","验证码错误");
            return "errorMsgUI";
        }

        //2.javaBean的封装
        User user = getBean(userName,userPassword,userBirthday,userGender,userAge);


         /*3
            1.没有上传图片
            2从sessio中获取该用户的信息并设置头像名和头型路径
            3.上传图片 删除该用户原来的图片然后重新上传
          */
        String headImgName = headImgFile.getOriginalFilename();
        User session_user  = (User)request.getSession().getAttribute("session_user");
        //设置userId
        user.setUserId(session_user.getUserId());
        //设置yoghurt的id

        if( headImgFile.getOriginalFilename()==null || headImgName.isEmpty()){
            //1.
 System.out.printf("该用户头像没有上传");
            user.setUserHeadImgName(session_user.getUserHeadImgName());
            user.setUserHeadImgPath(session_user.getUserHeadImgPath());
        }else{
 System.out.printf("该用户上传了头像");
            //2.
            String oldHeadImgPath =request.getServletContext().getRealPath("/"+session_user.getUserHeadImgPath());
            System.out.println(oldHeadImgPath);

            File oldFile = new File(oldHeadImgPath);
            oldFile.delete();

            //上传新的文件
            user.setUserHeadImgName(headImgName);
            String path = request.getServletContext().getRealPath("/upload/user");
            String newFileName = uploadImg(path,headImgName,headImgFile);
            user.setUserHeadImgPath(User.USER_HEADEIMGPATH+newFileName);
        }

        //修改是用户名和密码补不能呢重复
        try {
            userService.update(user);
        } catch (UserException e) {
            request.setAttribute("errorMsg",e.getMessage());
            return "errorMsgUI";
        }
        //删除该用户在session中的信息
        request.getSession().removeAttribute("session_user");
        request.setAttribute("successMsg","修改信息成功");
        request.setAttribute("jumpView","indexUI.action");
        return "successMsgUI";

    }


    /**
     * 用户退出
     * @param request
     * @return
     */
    @RequestMapping(value = "/quit.action")
    public String quit(HttpServletRequest request){
        //清除该用户的登录的信息 返回系统首页 改变系统和首页的页面的结构
       request.getSession().removeAttribute("session_user");
        //保存成功信息到successMsg中
       request.setAttribute("successMsg","退出成功");
       request.setAttribute("jumpView","indexUI.action");
       return "successMsgUI";

    }

    //上传文件 返回UUID的文件名
    private String uploadImg(String path,String headImgName,CommonsMultipartFile headImgFile){
        String newFileName = UUIDUtils.getUUID()+"_"+headImgName;
        File uploadFile = new File(path,newFileName);
        try {
            headImgFile.transferTo(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newFileName;
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





    @RequestMapping(value = "/loginUI.action")
    public String loginUI(){

        return "loginUI";
    }
    @RequestMapping(value = "/registUI.action")
    public String registUI(){
        return "registUI";
    }

    @RequestMapping(value = "/errorMsgUI.action")
    public String errorMsgUI(){
        return "errorMsgUI";
    }

    /*
     * 用户的个人信息
     *
     */
    @RequestMapping(value = "/userIndexUI.action")
    public String userIndexUI(  Integer pCur,
                                Integer handleT,
                                Model model,
                                HttpServletRequest request){
        /*
        处理选项卡的问题
        0.从sesioin中取出用户
        1.加载用户的收藏视频的信息
        2.加载用户的历史的的信息
        3.加载vip信息
         */
        //0
        User session_user = (User)request.getSession().getAttribute("session_user");
        String userId = session_user.getUserId();

        //1.加载用户收藏视频的信息
        List<Movie> collectMovies = movieService.findCMAndPaging(pCur,userId);
        Integer totalCounts = movieService.getCollectTC(userId);

        //必备分页四件套
        model.addAttribute("collectMovies",collectMovies);
        model.addAttribute("counts",totalCounts);
        model.addAttribute("pCur",(pCur==null)?1:pCur);
        model.addAttribute("pSize", PageResult.PAGERESULT_PSIZE);

        //加载用户的浏览历史记录
        List<History> historyMovies = historyService.findAllHByUid(userId);
        Map<String,List<History>> historyGBMovies = groupByHDate(historyMovies);
        model.addAttribute("historyGBMovies",historyGBMovies);

        //加载vip选项信息
        model.addAttribute("memPackages",memPackageService.findAll());
        //加载消费类型
        List<Map<String,Object>> userConsumeInfos = userService.findUserConsume(userId);
       // List<Consume> consumes = getConsume(userConsumeInfos);
        System.out.println(userConsumeInfos);
        model.addAttribute("consumes",userConsumeInfos);

        //设置你要显示的选项卡
        if(pCur==null){
            handleT=0;
        }else{
            if(handleT==null){
                handleT=2;
            }
        }
        model.addAttribute("showIndex",handleT);
        return "userIndexUI";
    }


    public List<Consume> getConsume(List<Map<String, Object>> userConsumeInfos) {
        List<Consume> consumes = new ArrayList<Consume>();
        for(Map<String,Object> map : userConsumeInfos){
            try {
                Consume consume = new Consume();
                BeanUtils.populate(consume,map);
                consumes.add(consume) ;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return consumes;
    }


    //删除用户的浏览历史记录
    @RequestMapping(value ="/delUHistoryRecord.action")
    public String delUHistoryRecord(String movieId,
                                    Integer handleT,
                                    Model model,
                                    HttpServletRequest request){
        Map<String,Object> params = new HashMap<String, Object>();
        User session_user = (User)request.getSession().getAttribute("session_user");
        String userId = session_user.getUserId();
        params.put("movieId",movieId);
        params.put("userId",userId);
        historyService.delHByUidAndMid(params);
        return userIndexUI(1,handleT,model,request);

    }

    //清空用户的浏览的历史记录
    @RequestMapping(value = "/clearUHistoryRecord.action")
    public String clearUHistoryRecord(Integer handleT,Model model,HttpServletRequest request){
        User session_user = (User)request.getSession().getAttribute("session_user");
        String userId = session_user.getUserId();
        historyService.clearHByUid(userId);
        return userIndexUI(1,handleT,model,request);
    }

    //多浏览的使用按日期进行分组
    private  Map<String,List<History>> groupByHDate(List<History> historys){
        Map<String,List<History>> mapsOrderBy = new TreeMap<String, List<History>>(new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        //将该日期放到set容器中
        Set<String> strDateSet = new HashSet<String>();
        for(History hs: historys){  //1 1 2 3 3 4 5
            //将该字符串解析为yyy-MM-dd
            String strDate =DateFormatUtils.getDateStr(hs.getHistoryDate());
            strDateSet.add(strDate);
        }
        //这是一个无序的容器
        for(String strDate : strDateSet){
            List<History> gbr =   getGroupByRes(historys,strDate);
            mapsOrderBy.put(strDate,gbr);
        }

        return mapsOrderBy;
    }




    //得到分组的结果
    private List<History> getGroupByRes(List<History> historys,String strDate){
        List<History> hts = new ArrayList<History>();
        for(History ht2 : historys){
            String strDate2 =DateFormatUtils.getDateStr(ht2.getHistoryDate());
            if(strDate.equals(strDate2)){
                hts.add(ht2);
            }
        }
        return hts;
    }

    /*
    用户收藏视频 收藏成功并做信息提示
     */
    @RequestMapping(value = "/collectMovie.action")
    @ResponseBody
    public Map<String,Object> collectMovie(String movieId, HttpServletRequest request){

        /*
            如果你第二次点击收藏 则认为你是取消收藏的
         */
        User session_user = (User)request.getSession().getAttribute("session_user");
        String userId = session_user.getUserId();
        String collectBoxId = UUIDUtils.getUUID();
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("collectBoxTime", DateFormatUtils.getFormatDate());
        params.put("collectBoxId",collectBoxId);
        params.put("userId",userId);
        params.put("movieId",movieId);

        Map<String,Object> msg = new HashMap<String,Object>();
        try {
            userService.collectMovie(params);
        } catch (UserException e) {
            //说明该视频你以及收藏过了 现在取消收藏
            userService.delCollectMByUIdAndMId(params);
            msg.put("msg","cancelCollect");
            return msg;
        }
        msg.put("msg","successCollect");
        return msg;
    }

    //删除用户收藏的的电影
    @RequestMapping(value = "/delCollectMovie.action")
    public String delCollectMovie(String movieId,Integer handleT,Model model,HttpServletRequest request){

        User session_user = (User)request.getSession().getAttribute("session_user");
        String userId = session_user.getUserId();
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("movieId",movieId);
        params.put("userId",userId);
        userService.delCollectMByUIdAndMId(params);

        //设置你要显示的选项卡
        model.addAttribute("showIndex",2);

        return userIndexUI(1,handleT,model,request);
    }

    /*
    异步来添加用户浏览记录
     */
    @RequestMapping(value = "/AjaxUHistoryRecord.action")
    public void AjaxUHistoryRecord(String movieId, HttpServletRequest request){
        /*
        1.判断session中的用户是否存在
            存在：则添加

            否则：不添加
         */
        User session_user = (User)request.getSession().getAttribute("session_user");
        if(session_user !=null){//说明该用户已经登录了
            //添加该用户的历史记录
            String userId =session_user.getUserId();
            History history = getBeanHistory(userId,movieId);
            historyService.addHistroryRecord(history);
        }
    }

    /*
    用户vip的充值
     */
    @RequestMapping(value = "/payVIP.action")
    public String payVIP(String memPackageId,Double payPrice,Model model,HttpServletRequest request){
        /*
        0.验证用户是否登录
        1.用户的会员开始时间的和结束时间的修改
        2..用户角色的修改
        3.用户表和套餐表的关系的添加
        4.钱支付到公司的账号上
        5.添加成功返回系统首页
         */
        User session_user = (User)request.getSession().getAttribute("session_user");
        if(session_user ==null){
            request.setAttribute("errorMsg","你没有登录，请登录后在付款");
            return "errorMsgUI";
        }

        MemPackage memPackage = memPackageService.findObjectById(memPackageId);
        //续费和首次充值一块处理
        Date userMemST = session_user.getUserMemStartTime();
        Date userMemET = session_user.getUserMemEndTime();

   /*     //对用户钱的判断
        if(payPrice < memPackage.getMemPackageResPrice() || StringUtils.isEmpty(payPrice)){
            //金钱额度不对
            request.setAttribute("errorMsg","金钱额度不对，请重新输入");
            return "errorMsgUI";
        }*/

        //1.
        if(userMemET == null){
            //说明是首次充值
            userMemST = DateFormatUtils.getFormatDate("yyyy-MM-dd HH:mm:ss");
            userMemET = DateFormatUtils.getEndMonthTime(userMemST,memPackage.getMemPackageMonth(),"yyyy-MM-dd HH:mm:ss");
        }else{
            //说明是续费
            userMemET = DateFormatUtils.getEndMonthTime(userMemET,memPackage.getMemPackageMonth(),"yyyy-MM-dd HH:mm:ss");
        }
        session_user.setUserMemStartTime(userMemST);
        session_user.setUserMemEndTime(userMemET);
        //2.
        String[] roleIds = {"1","2"};
        try {
            userService.updateUserAndRU(session_user,roleIds);
        } catch (UserException e) {
            request.setAttribute("errorMsg",e.getMessage());
            return "errorMsgUI";
        }
        //3.添加用户表和套餐表的关系
        Map<String,Object> params = getPayDetailPMap(session_user.getUserId(),memPackageId);
        //添加用户和套餐的关系
        userService.addUMP(params);
        //往账户上打钱

        Account account = new Account();
        account.setAccountId("1");
        account.setAccountBalance(payPrice);
        accountService.update(account);

        //保存成功信息到successMsg中
        request.setAttribute("successMsg","会员充值成功");
        request.setAttribute("jumpView","indexUI.action");
        return "successMsgUI";
    }

    @RequestMapping(value = "/GitLogin.action")
    public String gitLogin(String code,HttpServletRequest request) throws IOException {
        //github给你回调的url连接
        //http://127.0.0.1:8080/dyglxt/user/GitLogin.action?code=0eba98b4818189e27883&state=200
        //使用code再次访问
        /*
        https://github.com/login/oauth/access_token?client_id=xxx&client_secret=xxx&code=xxx&redirect_uri=xxxx
         */
        String redirect_uri = "http://127.0.0.1:8080/dyglxt/user/GitLogin.action";
        String url = "https://github.com/login/oauth/access_token?" +
                "client_id=" +AuthUtils.CLIENT_ID+
                "&client_secret=" + AuthUtils.CLIENT_SECRET+
                "&code=" +code+
                "&redirect_uri="+redirect_uri;
        String result = AuthUtils.doGetStr(url);
        System.out.println(result);

        //拿出https://api.github.com/user?access_token=xxx;
        String access_token =result.split("&")[0] ;
        String infoUrl = "https://api.github.com/user?"+access_token;
        JSONObject userInfo = AuthUtils.doGetJson(infoUrl);
        /*
        判断是需要登录还是注册
        1.查询该第三方的用户的id是否存在该数据库中
              1.1存在则保存用户已信息到首页
                1.2不存在则完成该用户的注册并保存该用户的信息到首页
         */
        System.out.println(userInfo.toString());
        String userId = userInfo.getString("id");
        String userName = userInfo.getString("name");
        String userHeandImgPath = userInfo.getString("avatar_url");

        User user = userService.findObjectById(userId);
        if(user ==null){
            //完成该用户的注册
            user =new User();
            user.setUserId(userId);
            user.setUserName(userName);
            user.setUserHeadImgPath(userHeandImgPath);
            String[] roleIds = {"1"};
            try {
                userService.addUserAndRU(user,roleIds);
            } catch (UserException e) {
                request.setAttribute("errorMsg",e.getMessage());
                return "errorMsgUI";
            }
            user = userService.findObjectById(userId);
        }

        request.getSession().setAttribute("session_user",user);
        request.getSession().setAttribute("session_test","ding");
        System.out.println("session_user:"+request.getSession().getAttribute("session_user"));
        //保存成功信息到successMsg中
        request.setAttribute("successMsg","登录成功");
        request.setAttribute("jumpView","indexUI.action");
        return "successMsgUI";
    }

    private Map<String,Object> getPayDetailPMap(String userId,String memPackageId){
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("userId",userId);
        params.put("memPackageId",memPackageId);
        params.put("accountId",1);//公司账号的id
        params.put("payDetailId",UUIDUtils.getUUID());
        params.put("payDetailPayTime", DateFormatUtils.getFormatDate("yyyy-MM-dd HH:mm:ss"));
        return params;
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
