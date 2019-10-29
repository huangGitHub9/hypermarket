package com.ahrtolia.controller;

import com.ahrtolia.entity.*;
import com.ahrtolia.service.UserService;
import com.ahrtolia.service.WeixinLoginService;
import com.ahrtolia.util.MessageUtils;
import com.ahrtolia.weixin.utils.DataJoinUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserHandler {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    @Autowired
    private WeixinLoginService weixinLoginService;

    ModelAndView modelAndView = new ModelAndView();

    @RequestMapping("/loginA.action")
    public String loginA() {
        return "login";
    }

    @RequestMapping("/registerA.action")
    public String registerA() {
        return "register";
    }

    @RequestMapping("/logout.action")
    public String logout(HttpSession session) {
        session.invalidate();
        return "main";
    }

    @RequestMapping("/toSettlement.action")
    public String toSettlement() {
        return "toSettlement";
    }

    /*回到首页*/
    @RequestMapping("/main.action")
    public String main() {
        return "main";
    }

    @RequestMapping("/userInfo.action")
    public String userInfo() {
        return "userInfo";
    }

    //手机号登陆页面
    @RequestMapping("/phoneLogin.action")
    public String phoneLogin() {
        return "phoneLogin";
    }

    //发送短信
    @ResponseBody
    @RequestMapping("/phoneLoginA.action")
    public String phoneLoginA(@RequestParam("mobile") String phone) {
        String[] phoneNumbers = {phone}; //保存手机号码
        int code = (int) ((Math.random() * 9 + 1) * 1000);//随机生成验证码
        String[] params = {code + ""};
        try {
            MessageUtils.sendMessage(phoneNumbers, params);//调用发送短信的方法
            request.getSession().setAttribute("phoneandcode", phone + code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    //手机登陆
    @ResponseBody
    @RequestMapping(value = "/doPhoneLogin.action", produces = {"application/text;charset=utf-8"})
    public String doPhoneLogin(@RequestParam("mobile") String phone, @RequestParam("code") String code,
                               HttpSession session) {
        String phonecode = phone + code;
        User user1 = userService.findPhone(phone);
        if (user1 != null) {
            if (request.getSession().getAttribute("phoneandcode").equals(phonecode)) { //验证通过
                session.setAttribute("user", user1);
                int money = knowMoney(user1.getId(),session);
                session.setAttribute("money",money);
                return "success";
            } else {
                return "error";
            }
        } else {
            return "error";
        }
    }


    public int knowMoney(int id,HttpSession session){
        List<Order> orderCar = userService.getMyProductCar(id);
        session.setAttribute("orders", orderCar);
        int money = 0;
        for (Order o : orderCar) {
            money += o.getCount() * o.getProduct().getPrice();
        }
        return money;
    }

    //注册
    @RequestMapping("/register.action")
    public String register(User user, HttpSession session) {
        String userName = user.getUsername();
        User user2 = userService.findUserName(userName);
        if (user2 == null) {
            userService.register(user);
            session.setAttribute("error", "注册成功！");
            return "login";
        } else {
            session.setAttribute("error1", "昵称重复！");
            return "register";
        }
    }

    //账号登录
    @RequestMapping("/login.action")
    public String login(User user, HttpSession session) {
        User user1 = userService.login(user);
        if (user1 != null) {
            session.setAttribute("user", user1);
            int money = knowMoney(user1.getId(),session);
            session.setAttribute("money",money);
            return "main";
        } else {
            session.setAttribute("error", "账号或密码不正确！");
            return "login";
        }
    }

    /*获取订单信息*/
    @RequestMapping("/getMyHasPayedOrder.action")
    public ModelAndView getMyHasPayedOrder(HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Order> payOrder = userService.getMyHasPayedOrder(user);
        session.setAttribute("payOrder",payOrder);
        modelAndView.setViewName("orderList");
        return modelAndView;
    }

    //获取收藏夹信息
    @RequestMapping("/getCollection.action")
    public ModelAndView getCollection(HttpSession session) {
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        List<Favorite> collection = userService.getCollection(userId);
        modelAndView.addObject("collection", collection);
        modelAndView.setViewName("collectionList");
        return modelAndView;
    }

    //收藏商品
    @RequestMapping("/doCollect.action")
    public ModelAndView doCollect(@RequestParam("productId") int productId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        Favorite favorite = userService.findFavorite(userId, productId);
        if (favorite == null) {
            userService.doCollect(userId, productId);
        }
        //更新收藏夹
        List<Favorite> collection = userService.getCollection(userId);
        modelAndView.addObject("collection", collection);
        modelAndView.setViewName("collectionList");
        return modelAndView;
    }

    //取消收藏品
    @RequestMapping("/remove.action")
    public ModelAndView remove(@RequestParam("favoriteId") int favoriteId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        if (favoriteId != 0) {
            userService.remove(favoriteId);
            //更新收藏夹
            List<Favorite> collection = userService.getCollection(userId);
            modelAndView.addObject("collection", collection);
        }
        modelAndView.setViewName("collectionList");
        return modelAndView;
    }

    //得到商品详情
    @RequestMapping("/getProductDetaile.action")
    public ModelAndView getProductDetaile(int productId, HttpSession session) {
        Product product = userService.getProductDetaile(productId);
        session.setAttribute("product", product);
        modelAndView.setViewName("productDeatil");
        return modelAndView;
    }

    //添加购物车
    @RequestMapping("/addToCar.action")
    public ModelAndView addToCar(int productId, int count, HttpSession session) {
        int userId = ((User) session.getAttribute("user")).getId();
        Order order = userService.getOrderCount(userId, productId);
        if (order != null) {
            int count0 = count + order.getCount();
            userService.addCarCount(userId, productId, count0);
        } else {
            userService.addToCar(userId, productId, count, 0);
        }
        //更新一下session中的购物车
        int money = knowMoney(userId,session);
        session.setAttribute("money",money);
        modelAndView.setViewName("productDeatil");
        return modelAndView;
    }

    //删除购物车商品
    @RequestMapping("/deleteCarProduct.action")
    public ModelAndView deleteCarProduct(int orderId, HttpSession session) {
        int userId = ((User) session.getAttribute("user")).getId();
        userService.deleteCarProduct(orderId);
        //更新一下session中的购物车
        int money = knowMoney(userId,session);
        session.setAttribute("money", money);
        modelAndView.setViewName("toSettlement");
        return modelAndView;
    }

    /*更新商品数量*/
    @ResponseBody
    @RequestMapping("/updateOrderCount.action")
    public void updateOrderCount(int orderId, int count) {
        userService.updateOrderCount(orderId, count);
    }

    /*未付款订单*/
    @RequestMapping("/getMyPayingOrder.action")
    public String getMyPayingOrder(HttpSession session) {
        int userId = ((User) session.getAttribute("user")).getId();
        //更新购物车订单
        List<Order> orderProductList = userService.getOrder(userId);
        UserAddress list = userService.getUserAddress(userId);
        int moneyOrder = 0;
        for (Order o : orderProductList) {
            moneyOrder += o.getCount() * o.getProduct().getPrice();
        }
        session.setAttribute("moneyOrder", moneyOrder);
        session.setAttribute("orderProductList", orderProductList);
        if (list != null) {
            session.setAttribute("addressId", list.getId());
            session.setAttribute("address", list.getAddress());
            session.setAttribute("aId","1");
        }else{
            session.setAttribute("aId","2");
        }
        return "settlement2";
    }

    /*确认结算*/
    @RequestMapping("/getUserAddress.action")
    public String getUserAddress(HttpSession session, String productIds) {
        int userId = ((User) session.getAttribute("user")).getId();
        userService.findProductIds(userId, productIds);
        //更新一下session中的购物车
        List<Order> orderCar = userService.getMyProductCar(userId);
        session.setAttribute("orders", orderCar);
        int money = 0;
        for (Order o : orderCar) {
            money += o.getCount() * o.getProduct().getPrice();
        }
        session.setAttribute("money", money);
        return "redirect:/user/getMyPayingOrder.action";
    }

    //删除未付款订单
    @RequestMapping("/deleteOrder.action")
    public String deleteOrder(HttpSession session, int orderId) {
        int userId = ((User) session.getAttribute("user")).getId();
        userService.deleteCarProduct(orderId);
        //更新购物车订单
        List<Order> orderProductList = userService.getOrder(userId);
        session.setAttribute("orderProductList", orderProductList);
        int moneyOrder = 0;
        for (Order o : orderProductList) {
            moneyOrder += o.getCount() * o.getProduct().getPrice();
        }
        session.setAttribute("moneyOrder", moneyOrder);
        return "settlement2";
    }

    @RequestMapping("/updateOrderAddress.action")
    public String updateOrder(@RequestParam("addressId") int addressId, HttpSession session,@RequestParam("zfmoney") int zfmoney) throws Exception {
        int userId = ((User) session.getAttribute("user")).getId();
        List<Order> orderCar = userService.getMyOrderCar(userId);
        int userOrderFirstId = orderCar.get(0).getId();
        /*String allMoney = Integer.toString(zfmoney*100);*/
        userService.updateOrder(addressId, userId);
        String serialNumber = userService.getSerialNumber(userOrderFirstId);
        String address = userService.getOrderAddress(userId);
        /*****************************生成支付二维码*******************************/
        DataJoinUtils dataJoinUtils = new DataJoinUtils();
        Map<String, String> result = dataJoinUtils.wxPay("http://localhost:80/main/getAll.action", serialNumber, "1", "127.0.0.1", "海玩商品");
        //将二维码地址封装至order对象中
        session.setAttribute("recommendCode", result.get("code_url"));
        session.setAttribute("newAddress", address);
        session.setAttribute("serialNumber", serialNumber);
        session.setAttribute("zfmoney", zfmoney);
        return "weixinPay/pay";
    }

    @ResponseBody
    @RequestMapping("/queryPayStatus.action")
    public String queryPayStatus(String orderNo) {
        String tip = "";
        try {
            int num = 0;
            DataJoinUtils dataJoinUtils = new DataJoinUtils();
            while (true) {
                //查询订单的支付状态
                Map<String, String> map = dataJoinUtils.wxOrderQuery(orderNo);
                if (map == null) {
                    tip = "支付发生错误";
                    break;
                }
                if (map.get("trade_state").equals("SUCCESS")) {
                    tip = "ok";
                    //支付成功，应该修改数据库中订单状态，改成已支付
                    userService.updateOrderPayState(orderNo);
                    break;
                }
                Thread.sleep(3000);
                num++;
                if (num >= 10) {
                    tip = "支付超时";
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tip;
    }

    //跳转至支付成功页面
    @RequestMapping("/paySuccess.action")
    public String paySuccess() {
        return "settlement3";
    }

    //跳转至支付失败页面
    @RequestMapping("/payfail.action")
    public String payfail() {
        return "payfail";
    }

    //添加新收货地址
    @RequestMapping("/addNewAddress.action")
    public String addAddresses(@RequestParam("addNewAddress") String address, HttpSession session) {
        int userId = ((User) session.getAttribute("user")).getId();
        userService.addNewAddress(userId, address);
        UserAddress address1 = userService.getUserAddress(userId);
        session.setAttribute("address", address1.getAddress());
        session.setAttribute("addressId", address1.getId());
        session.setAttribute("aId","1");
        return "settlement2";
    }

    //获取所有收货地址
    @RequestMapping("/address.action")
    public String address(HttpSession session) {
        int userId = ((User) session.getAttribute("user")).getId();
        //获取默认地址
        UserAddress address = userService.getUserAddress(userId);
        if (address != null) {
            session.setAttribute("address",address.getAddress());
            session.setAttribute("id",address.getId());
            session.setAttribute("delete","2");
        }else{
            session.setAttribute("delete","1");
        }
        String userName = ((User) session.getAttribute("user")).getUsername();
        session.setAttribute("userName",userName);
        //获取历史所有地址（不包括默认地址）
        List<UserAddress> addressList = userService.getAllAddress(userId);
        session.setAttribute("addressList",addressList);
        return "address";
    }

    //删除默认地址
    @RequestMapping("/deleteAddress.action")
    public String deleteAddress(@RequestParam("id") int id){
        userService.deleteAddress(id);
        return "redirect:/user/address.action";
    }

    //添加默认地址
    @RequestMapping("/addDeleteAddress.action")
    public String addDeleteAddress(@RequestParam("id")int id, HttpSession session){
        int userId = ((User) session.getAttribute("user")).getId();
        UserAddress address = userService.getUserAddress(userId);
        if (address != null) {
            userService.deleteAddress(address.getId());
        }
        userService.addDeleteAddress(id);
        return "redirect:/user/address.action";
    }

    /*修改用户头像*/
    @RequestMapping(value = "/modifyUserPhoto.action", method = RequestMethod.POST)
    public String modifyUserPhoto(User user, HttpSession session,
                                  @RequestParam(value = "previewImg", required = false) MultipartFile multipartFile,
                                  @RequestParam(value = "id", required = false)int id) {
        if (!multipartFile.isEmpty()){
            String newFileName = upload(multipartFile, session);
            user.setFileName(newFileName);
        }
        String fileName = user.getFileName();

        int i = userService.modifyUserPhoto(fileName,id);
        if (i > 0) {
            //当前用户应该及时更新用户头像
            String userName = ((User) session.getAttribute("user")).getUsername();
            User user1 = userService.findUserName(userName);
            session.setAttribute("user", user1);
            return "userInfo";
        } else {
            return "userInfo";
        }
    }

    // 实现文件上传
    public String upload(MultipartFile multipartFile, HttpSession session) {
        // 获取upload文件夹的绝对路径
        String savePath = session.getServletContext().getRealPath("/images");
        // 获取原文件名
        String oldFileName = multipartFile.getOriginalFilename();
        // 文件对象
        File file = new File(savePath + File.separator + oldFileName);
        // 将当前文件上传到TomCat某个位置
        try {
            multipartFile.transferTo(file);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return oldFileName;
    }

    //账户安全
    @RequestMapping("/safe.action")
    public String safe(){
        return "safe";
    }

    //发送邮件
    @ResponseBody
    @RequestMapping(value = "/active.action",produces={"allpication/text;charset=utf-8"})
    public String active(int id,HttpSession session){
        //判断是否绑定邮箱
        User user = userService.getEmail(id);
        if (user.getEmail() == null || user.getEmail().equals("")) {
            return "请您先绑定邮箱！";
        }else{
            try {
                String email = ((User) session.getAttribute("user")).getEmail();
                userService.AddActiveCode(id,email);
                return "请进入您的邮箱进行认证！";
            }catch (Exception e){
                e.printStackTrace();
                return "认证失败！";
            }
        }
    }

    //邮件认证
    @RequestMapping("/doActive.action")
    public String doActive(@RequestParam("activeCode") String activeCode,HttpSession session){
        if (activeCode != null) {
            int id = ((User) session.getAttribute("user")).getId();
            userService.updateActiveCode(id);
            User userByActiveCode = userService.getUserById(id);
            session.setAttribute("user",userByActiveCode);
        }else{
            session.setAttribute("codeError","验证异常，请重新验证！");
        }
        return "userInfo";
    }

    //微信扫码登录
    @RequestMapping("/weChatLogin.action")
    public String weChatLogin(@RequestParam("code") String code, HttpSession session){
        if (code != null) {
            String access_token=null;
            String openid=null;
            AccessToken access = weixinLoginService.getAccessToken(code);
            if (access != null) {
                // 把获取到的access_token和openId赋值给变量
                access_token=access.getAccess_token();
                openid=access.getOpenid();

                // 存在则把当前账号信息授权给扫码用户
                // 拿到openid获取微信用户的基本信息
                // 此处可以写业务逻辑

                WechatUserUnionID userUnionID = weixinLoginService.getUserUnionID(access_token,openid);
                User user = userService.findUserName(userUnionID.getNickname());
                if (user == null) {
                    userService.weChatAddUser(userUnionID);
                    User user1 = userService.findUserName(userUnionID.getNickname());
                    session.setAttribute("user",user1);
                    //int money = knowMoney(user1.getId(),session);
                    session.setAttribute("money", 0);
                }else{
                    session.setAttribute("user",user);
                    int money = knowMoney(user.getId(),session);
                    session.setAttribute("money", money);
                }
                /*session.setAttribute("headimgurl",userUnionID.getHeadimgurl());*/
                return "main";
            }
        }
        return "login";
    }

    //跳转到微信二维码
    @RequestMapping("/weChat.action")
    public String weChat(){
        return "weChatLogin";
    }

    //发送验证码至邮箱
    @ResponseBody
    @RequestMapping(value = "/addEmail.action",produces={"allpication/text;charset=utf-8"})
    public String addEmail(@RequestParam("email") String email, HttpSession session){
        int id = ((User) session.getAttribute("user")).getId();
        //判断是否绑定邮箱
        User user = userService.getEmail(id);
        if (user.getEmail() == null || user.getEmail().equals("")) {
            String result = userService.addEmail(id,email);
            return result;
        }else{
            return "已绑定邮箱，请解绑后进行操作！";
        }
    }

    //绑定邮箱
    @RequestMapping(value = "/doAddEmail.action")
    public String doAddEmail(@RequestParam("email") String email, @RequestParam("emailCode") String emailCode, HttpSession session){
        int id = ((User) session.getAttribute("user")).getId();
        //判断是否绑定邮箱
        User user = userService.getEmail(id);
        if (user.getEmail() == null || user.getEmail().equals("")) {
            String emailCodeandemail = emailCode + email;
            if(request.getSession().getAttribute("emailCodes").equals(emailCodeandemail)){
                //绑定邮箱
                userService.doAddEmail(id,email);
                //重新获取邮箱
                User user1 = userService.getUserById(id);
                session.setAttribute("user",user1);
                session.setAttribute("noNullemail","邮箱绑定成功！");
            }else {
                session.setAttribute("noNullemail","邮箱或者验证码不正确！");
            }
        }else{
            session.setAttribute("noNullemail","已绑定邮箱,请解绑后进行操作！");
        }
        return "safe";
    }
}