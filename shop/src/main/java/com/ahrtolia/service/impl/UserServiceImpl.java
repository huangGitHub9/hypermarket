package com.ahrtolia.service.impl;

import com.ahrtolia.dao.UserMapper;
import com.ahrtolia.entity.*;
import com.ahrtolia.service.UserService;
import com.sun.mail.smtp.SMTPMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HttpServletRequest request;

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    @Override
    public void register(User user) {
        String name = user.getName();
        String userName = user.getUsername();
        String password = user.getPassword();
        String sex = user.getSex();
        String identityCode = user.getIdentityCode();
        String email = user.getEmail();
        String mobile = user.getMobile();
        String fileName = user.setFileName("head.png");
        userMapper.register(name, userName, password, sex, identityCode, email, mobile, fileName);
    }

    @Override
    public List<Order> getMyHasPayedOrder(User user, int page, int limit) {
        int begin = (page - 1) * limit;
        return userMapper.getMyHasPayedOrder(user.getId(), begin, limit);
    }

    @Override
    public int getMyHasPayedOrderCount(User user, int limit) {
        int count = userMapper.getMyHasPayedOrderCount(user.getId());
        int pageCount = count / limit;
        if (count % limit != 0) {
            pageCount += 1;
        }
        return pageCount;
    }

    @Override
    public Product getProductDetaile(int productId) {
        return userMapper.getProductDetaile(productId);
    }

    @Override
    public void addToCar(int userId, int productId, int count, int state) {
        userMapper.addToCar(userId, productId, count, state);
    }

    @Override
    public List<Favorite> getCollection(int userId) {
        List<Favorite> list = userMapper.getCollection(userId);
        return list;
    }

    @Override
    public void remove(int favoriteId) {
        userMapper.remove(favoriteId);
    }

    @Override
    public int doCollect(int userId, int productId) {

        return userMapper.doCollect(userId, productId);
    }


    @Override
    public User findPhone(String mobile) {
        return userMapper.findPhone(mobile);
    }

    @Override
    public User findUserName(String userName) {
        return userMapper.findUserName(userName);
    }

    @Override
    public Favorite findFavorite(int userId, int productId) {
        return userMapper.findFavorite(userId, productId);
    }

    @Override
    public Order getOrderCount(int userId, int productId) {
        return userMapper.getOrderCount(userId, productId);
    }

    @Override
    public int addCarCount(int userId, int productId, int count) {
        int i = userMapper.addCarCount(userId, productId, count);
        return i;
    }

    @Override
    public void findProductIds(int userId, String productIds) {
        try {
            String[] pids = productIds.split(",");
            for (int i = 0; i < pids.length; i++) {
                userMapper.findProductIds(userId, pids[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Order> getOrder(int userId) {
        return userMapper.getOrder(userId);
    }

    @Override
    public List<Order> getMyOrderCar(int userId) {
        return userMapper.getMyOrderCar(userId);
    }

    @Override
    public void updateOrderPayState(String orderNo) {
        userMapper.updateOrderPayState(orderNo);
    }

    @Override
    public void addNewAddress(int userId, String address) {
        userMapper.addNewAddress(userId, address);
    }

    @Override
    public int modifyUserPhoto(String fileName, int id) {
        int i = userMapper.modifyUserPhoto(fileName, id);
        return i;
    }

    @Override
    public List<UserAddress> getAllAddress(int userId) {
        return userMapper.getAllAddress(userId);
    }

    @Override
    public void deleteAddress(int id) {
        userMapper.deleteAddress(id);
    }

    @Override
    public void addDeleteAddress(int id) {
        userMapper.addDeleteAddress(id);
    }

    //邮箱认证
    @Override
    public void AddActiveCode(int id, String email) {
        try {
            // 生成激活码
            /*String activeCode = UUID.randomUUID().toString();*/
            String activeCode = "2";
            userMapper.saveActiveCode(id, activeCode);

            // 开始发送邮件给用户
            // 创建Properties对象，用来封装邮件服务器相关信息
            Properties props = new Properties();
            // 设置邮件服务器的地址
            props.setProperty("mail.smtp.host", "smtp.qq.com");
            // 邮件服务器需要权限，指定用户必须登录邮件服务器才能发送邮件
            props.setProperty("mail.smtp.auth", "true");
            //阿里云服务器禁用25端口，所以服务器上改为465端口
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.socketFactory.port", "465");

            // 创建Authenticator的实例，实现账户、密码的鉴权。
            Authenticator auth = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("2629301367@qq.com",
                            "fryjcxwxjlufdjaf");
                }
            };

            // 通过Session与服务器建立连接
            Session session = Session.getInstance(props, auth);

            // 创建发送邮件对象，该对象主要用于封装邮件相关信息，比如 主题 发件人 邮件内容等
            SMTPMessage message = new SMTPMessage(session);

            // 设置邮件的主题
            message.setSubject("用户认证邮件，请勿回复，按照指引进行认证操作！");
            // 设置邮件的内容
            message.setContent(
                    "<a href='http://106.15.196.6:80/user/doActive.action?activeCode="
                            + activeCode
                            + "' target='_blank'>海玩科技真心感谢您的支持，请点击该链接进行邮箱认证，无需回复！</a>",
                    "text/html;charset=utf-8");

            // 设置发件人
            message.setFrom(new InternetAddress("2629301367@qq.com"));

            // 设置收件人 接收者类型由：TO(收件人)、CC(抄送)、BCC(密送)
            message.setRecipient(RecipientType.TO,
                    new InternetAddress(email));

            // 发送邮件
            Transport.send(message);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }


    @Override
    public void updateActiveCode(int id) {
        userMapper.updateActiveCode(id);
    }

    @Override
    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }

    @Override
    public void weChatAddUser(WechatUserUnionID userUnionID) {
        String userName = userUnionID.getNickname();
        String name = userUnionID.getNickname();
        String sex = "";
        //随机生成密码
        String password = UUID.randomUUID().toString();
        if(userUnionID.getSex() == 1){
           sex = "男";
        }else{
           sex = "女";
        }
        String fileName = "head.png";
        userMapper.weChatAddUser(userName,name,sex,password,fileName);
    }

    @Override
    public User getEmail(int id) {
        return userMapper.getEmail(id);
    }

    @Override
    public String addEmail(int id, String email) {
        int emailCode = (int) ((Math.random() * 9 + 1) * 100000);//随机生成验证码
        request.getSession().setAttribute("emailCodes", emailCode + email);
        try {
            // 开始发送邮件给用户
            // 创建Properties对象，用来封装邮件服务器相关信息
            Properties props = new Properties();
            // 设置邮件服务器的地址
            props.setProperty("mail.smtp.host", "smtp.qq.com");
            // 邮件服务器需要权限，指定用户必须登录邮件服务器才能发送邮件
            props.setProperty("mail.smtp.auth", "true");
            //阿里云服务器禁用25端口，所以服务器上改为465端口
	        props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.socketFactory.port", "465");

            // 创建Authenticator的实例，实现账户、密码的鉴权。
            Authenticator auth = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("2629301367@qq.com",
                            "fryjcxwxjlufdjaf");
                }
            };

            // 通过Session与服务器建立连接
            Session session = Session.getInstance(props, auth);

            // 创建发送邮件对象，该对象主要用于封装邮件相关信息，比如 主题 发件人 邮件内容等
            SMTPMessage message = new SMTPMessage(session);

            // 设置邮件的主题
            message.setSubject("邮件验证码，请勿回复，按照指引进行认证操作！");
            // 设置邮件的内容
            message.setContent(
                    "<p>海玩科技真心感谢您的支持，验证码如下：</p>"+emailCode,
                    "text/html;charset=utf-8");

            // 设置发件人
            message.setFrom(new InternetAddress("2629301367@qq.com"));

            // 设置收件人 接收者类型由：TO(收件人)、CC(抄送)、BCC(密送)
            message.setRecipient(RecipientType.TO,
                    new InternetAddress(email));

            // 发送邮件
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "验证码已发送到您的邮箱，请注意查收！";
    }

    @Override
    public void doAddEmail(int id, String email) {
        userMapper.doAddEmail(id,email);
    }


    @Override
    public List<Order> getMyProductCar(int userId) {
        List<Order> list = userMapper.getMyProductCar(userId);
        return list;
    }

    @Override
    public void deleteCarProduct(int orderId) {
        userMapper.deleteCarProduct(orderId);
    }

    @Override
    public void updateOrderCount(int orderId, int count) {
        userMapper.updateOrderCount(orderId, count);
    }

    @Override
    public UserAddress getUserAddress(int userId) {
        return userMapper.getUserAddress(userId);
    }

    @Override
    public void updateOrder(int addressId, int userId) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = simpleDateFormat.format(date);
        userMapper.updateOrder(createTime, getSerialNumber(), addressId, userId);
    }


    @Override
    public String getSerialNumber(int orderId) {
        return userMapper.getSerialNumber(orderId);
    }

    @Override
    public String getOrderAddress(int userId) {
        return userMapper.getOrderAddress(userId);
    }


    public String getSerialNumber() {
        String str = "A1B2C3D4E5F6G7HI8G3K23L9MN0O214PQ2R5ST6UV32W3XY2Z";
        StringBuffer ddh = new StringBuffer();
        int num = 0;
        for (int i = 0; i < 10; i++) {
            num = (int) (Math.random() * str.length());
            ddh.append(str.charAt(num));
        }
        return ddh.toString();
    }
}
