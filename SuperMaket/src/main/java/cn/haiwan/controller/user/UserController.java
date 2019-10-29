package cn.haiwan.controller.user;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.haiwan.util.MessageUtils;
import cn.haiwan.util.pager.PageModel;
import com.github.qcloudsms.httpclient.HTTPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSONArray;
import cn.haiwan.entity.Roler;
import cn.haiwan.entity.User;
import cn.haiwan.service.user.UserService;

@Controller
public class UserController {

    @Autowired
    private HttpServletRequest request;

    @Resource
    private UserService userService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }


    @RequestMapping("/dologin")
    public String doLogin(HttpSession session,
                          @RequestParam(value = "userCode") String usercode,
                          @RequestParam(value = "password") String password) {
        User user = userService.login(usercode, password);
        //System.out.println(user);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/sys/frame";
        } else {
            session.setAttribute("error", "用户名或者密码错误");
            return "redirect:/login";
        }
    }

    @RequestMapping("/noRegister")
    public String noRegister() {
        return "noRegister";
    }

    @ResponseBody
    @RequestMapping("/phoneLogin")
    public String phoneLogin(@RequestParam("phone") String phone) {

        String[] phoneNumbers = {phone}; //保存手机号码
        int code = (int) ((Math.random() * 9 + 1) * 1000);//随机生成验证码
        String[] params = {code + ""};
        try {
            MessageUtils.sendMessage(phoneNumbers, params);//调用发送短信的方法
            request.getSession().setAttribute("phoneandcode", phone + code);
            //System.out.println(request.getSession().getAttribute("phoneandcode"));
            //response.getWriter().print("success");
            //response.getOutputStream().write("success".getBytes());
            //response.reset();
        } catch (HTTPException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/dophoneLogin", produces = {"application/text;charset=utf-8"})
    public String dophoneLogin(@RequestParam("phone") String phone, @RequestParam("code") String code, HttpServletResponse response, HttpSession session) {
        //String password = super.request.getParameter("password");
        String phonecode = phone + code;
        //System.out.println(phonecode+"-----");
        User user = userService.findPhone(phone);
        //System.out.println(request.getSession().getAttribute("phoneandcode"));
        if (request.getSession().getAttribute("phoneandcode") != null) {
            if (user != null) {
                System.out.println(user.toString());
                if (request.getSession().getAttribute("phoneandcode").equals(phonecode)) { //验证通过
                    session.setAttribute("user", user);
                    return "success";
                } else { // 验证失败
                    return "error";
                }
            } else {
                return "error";
            }
        } else {
            return "error";
        }
    }

    @RequestMapping("/sys/frame")
    public String frame() {
        return "frame";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        session.removeAttribute("error");
        return "redirect:/login";
    }

    @RequestMapping("/sys/pwdmodify")
    public String pwdModify() {
        return "user/pwdmodify";
    }

    @RequestMapping("/sys/checkpwd")
    @ResponseBody
    public Object checkpwd(HttpSession session,
                           @RequestParam("oldpassword") String oldPwd) {
        User user = (User) session.getAttribute("user");
        HashMap<String, String> map = new HashMap<>();
        map.put("result", "true");
        if (oldPwd == "" || oldPwd == null) {
            map.put("result", "error");
        }
        if (user == null) {
            map.put("result", "sessionerror");
        }
        if (user.getUserPassword().equals(oldPwd)) {
            map.put("result", "true");
        } else {
            map.put("result", "false");
        }
        // String json = JSONArray.toJSONString(map);
        return map;
    }

    // 修改当前用户密码
    @RequestMapping("/sys/savepwdmodify")
    public String savepwdmodify(HttpServletRequest request, HttpSession session) {
        String newPassword = request.getParameter("newpassword");
        User user = (User) session.getAttribute("user");
        Integer id = user.getId();
        int i = userService.modifyPassword(newPassword, id);
        if (i > 0) {
            // System.out.println("修改成功");
            session.removeAttribute("user");
            session.setAttribute("error", "密码修改成功");
            return "redirect:/login";
        } else {
            return "user/pwdmodify";
        }
    }

    // 显示用户
    @RequestMapping("/sys/admin/user")
    public String userList(
            Model model,
            HttpSession session, PageModel pageModel,
            @RequestParam(value = "queryname", required = false) String userName,
            @RequestParam(value = "queryUserRole", required = false) Integer userRole) {
        // 角色下拉框显示
        List<Roler> rolelist = userService.findRoler();
        model.addAttribute("roleList", rolelist);
        // System.out.println(userName + "\t" + userRole);
        int recordCount = userService.findTotalCount(userName, userRole);
        pageModel.setRecordCount(recordCount);
        //pageIndex当前页面
        int pageIndex = pageModel.getPageIndex();
        //pageSize每页分多少条数据
        int pageSize = pageModel.getPageSize();
        List<User> userList = userService.findUser(userName, userRole, pageIndex, pageSize);

        model.addAttribute("userList", userList);
        session.setAttribute("userRole", userRole);
        session.setAttribute("userName", userName);
        return "user/userlist";
    }

    // 添加用户
    @RequestMapping("/sys/admin/useradd")
    public String addUser(HttpSession session) {
        return "user/useradd";
    }

    // 添加用户时角色下拉框的选择
    @RequestMapping("/sys/admin/getrolelist")
    @ResponseBody
    public String getRoleList() {
        List<Roler> roler = userService.findRoler();
        String json = JSONArray.toJSONString(roler);
        // System.out.println(json);
        return json;
    }

    // 验证当前添加的用户账号是否存在
    @RequestMapping("/sys/admin/usercode")
    @ResponseBody
    public Object checKUserCode(HttpServletRequest request) {
        HashMap<String, String> map = new HashMap<>();
        String userCode = request.getParameter("userCode");
        User user = userService.checkUserCode(userCode);
        if (user != null) {
            map.put("userCode", "exist");
        } else {
            map.put("userCode", "available");
        }
        return map;
    }

    // 真正的添加用户
    @RequestMapping(value = "/sys/admin/saveuser", method = RequestMethod.POST)
    public String saveUser(
            HttpSession session,
            @RequestParam String userCode, @RequestParam String userName,
            @RequestParam String userPassword, @RequestParam Integer gender,
            @RequestParam(value = "birthday") String birthdayStr, @RequestParam String phone,
            @RequestParam String address, @RequestParam Integer userRole) throws ParseException {
        /*@RequestParam(value = "userPhoto", required = false) MultipartFile multipartFile)*/
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = sdf.parse(birthdayStr);
        // 文件上传
        /*String oldFileName = upload(multipartFile, session);*/
        //上传默认头像
        String oldFileName = "head.png";
        Date creationDate = new Date();
        User user = new User(null, userCode, userName, userPassword, gender, birthday, phone, address, userRole, 1, creationDate, null, null, oldFileName);
        //System.out.println(user);
        int i = userService.addUser(user);
        if (i > 0) {
            return "redirect:/sys/admin/user";
        } else {
            return "redirect:/sys/admin/useradd";
        }
    }

    // 实现文件上传
    public String upload(
            @RequestParam(value = "userPhoto", required = false) MultipartFile multipartFile,
            HttpSession session) {
        // 获取upload文件夹的绝对路径
        String savePath = session.getServletContext().getRealPath("/statics/upload");
        // 获取原文件名
        String oldFileName = multipartFile.getOriginalFilename();
        // 文件对象
        File file = new File(savePath + File.separator + oldFileName);
        //判断上传文件类型，只有图片可以上传
        String fileName = file.getName();
        String typeName = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (typeName.equals("png") || typeName.equals("jpg") || typeName.equals("gif")) {
            // 将当前文件上传到TomCat某个位置
            try {
                multipartFile.transferTo(file);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return oldFileName;
        } else {
            User user = (User) session.getAttribute("user");
            oldFileName = user.getUserPhoto();
            return oldFileName;
        }

    }

    // 查看当前用户详情
    @RequestMapping("/sys/admin/viewUser")
    public String viewUser(HttpSession session,
                           @RequestParam(value = "uid", required = false) Integer uid) {
        User currentUser = userService.findUserById(uid);
        // System.out.println(currentUser.getRole().getRoleName());
        // System.out.println(currentUser);
        session.setAttribute("user1", currentUser);
        return "user/userview";
    }

    // 修改用户信息
    @RequestMapping("/sys/admin/modifyUser")
    public String mofifyUser(HttpSession session,
                             @RequestParam(value = "uid", required = false) Integer uid) {
        User currentUser = userService.findUserById(uid);
        session.setAttribute("user2", currentUser);
        return "user/usermodify";
    }

    @RequestMapping(value = "/sys/admin/modifyusersave", method = RequestMethod.POST)
    public String modifyUserSave(User user, HttpSession session,
                                 @RequestParam(value = "photo", required = false) MultipartFile multipartFile,
                                 @RequestParam(value = "userPhoto", required = false) String userPhoto) {
        if (!multipartFile.isEmpty()) {
            String newFileName = upload(multipartFile, session);
            //System.out.println(user);
            user.setUserPhoto(newFileName);
        } else {
            user.setUserPhoto(userPhoto);
        }
        //System.out.println(user);
        int i = userService.modifyUser(user);

        User currentUser = (User) session.getAttribute("user");
        if (i > 0) {
            //如果修改的用户为当前用户则应该及时更新用户头像
            if (currentUser.getId() == user.getId()) {
                User user1 = userService.showPhoto(user.getId());
                session.setAttribute("user", user1);
            }
            return "redirect:/sys/admin/user";
        } else {
            return "redirect:/sys/admin/modifyUser";
        }
    }

    // 删除用户
    @RequestMapping("/sys/admin/deleteUser")
    @ResponseBody
    public Object delUser(HttpServletRequest request, HttpSession session) {
        //获取当前用户的id
        User user = (User) session.getAttribute("user");
        Integer id = user.getId();
        //System.out.println(id);
        HashMap<String, String> map = new HashMap<>();
        String uidStr = request.getParameter("uid");
        Integer uid = Integer.parseInt(uidStr);
        //System.out.println("被删除用户的id为：" + uid);
        int i = 0;
        if (id != uid) {
            i = userService.delUser(uid);
            if (i > 0) {
                map.put("delResult", "true");
            } else {
                map.put("delResult", "false");
            }
        } else if (id == uid) {
            map.put("delResult", "error");
        }
        return map;
    }

    @RequestMapping("/sys/person")
    public String showPerson() {
        return "person/person";
    }

    /*修改用户头像*/
    @RequestMapping("/sys/userPhotoModify")
    public String modifyPhoto(HttpSession session,
                              @RequestParam(value = "userid", required = false) Integer uid,
                              @RequestParam(value = "photo", required = false) MultipartFile multipartFile) {
        int i = 0;
        if (!multipartFile.isEmpty()) {
            String newFileName = upload(multipartFile, session);
            i = userService.modifyPhoto(newFileName, uid);
        }

        if (i > 0) {
            //System.out.println("修改成功");
            User user = userService.showPhoto(uid);
            session.setAttribute("user", user);
        }
        return "redirect:/sys/person";
    }

    /*切换界面皮肤*/
    @RequestMapping("/sys/skin")
    public String changeSkin() {
        return "person/skin";
    }
}
