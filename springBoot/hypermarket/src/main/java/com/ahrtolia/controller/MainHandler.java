package com.ahrtolia.controller;

import com.ahrtolia.entity.News;
import com.ahrtolia.entity.Product;
import com.ahrtolia.entity.Type1;
import com.ahrtolia.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/main")
public class MainHandler {
    @Autowired
    private MainService mainService;

    @RequestMapping("/getAll.action")
    //mybatis默认把数据存储在request中  若要存储到session中则可以获取原生session对象存储
    public String getMainAllShow(HttpServletRequest request){
        //防止注销登陆时session销毁  导致页面没有数据所以存在application中
       ServletContext application = request.getServletContext();
        List<Type1> type1List = mainService.getAllType1();
        application.setAttribute("type1List", type1List);
        List<News> newsList = mainService.getAllNews();
        application.setAttribute("newsList",newsList);
        return "main";
    }

    @RequestMapping("/searchProduct.action")
    public String searchProducts(@RequestParam("keyWord") String keyWord,HttpSession session){
        if (keyWord != null) {
            //模糊查询
            List<Product> type1Product = mainService.getProductByKeyWord(keyWord);
            session.setAttribute("type1Product",type1Product);
            session.setAttribute("keyWord",keyWord);
        }else{
            List<Product> type1Product = mainService.getAllProduct();
            session.setAttribute("type1Product",type1Product);
        }
        return "goodsList";
    }

    @RequestMapping("/getProductByType1.action")
    public String getProductByType1(@RequestParam("type1Id") Integer type1Id, HttpSession session){
        List<Product> type1Product = mainService.getProductByType1(type1Id);
        session.setAttribute("type1Product",type1Product);
        session.setAttribute("type1Id",type1Id);
        //设置一个标志  用于前端分页区别不同的请求
        session.setAttribute("st","s");
        return "goodsList";
    }

    @RequestMapping("/getProductByType2.action")
    public String getProductByType2(@RequestParam("type2Id") Integer type2Id, HttpSession session){
        List<Product> type1Product = mainService.getProductByType2(type2Id);
        session.setAttribute("type1Product",type1Product);
        session.setAttribute("type2Id",type2Id);
        //设置一个标志  用于前端分页区别不同的请求
        session.setAttribute("st","s2");
        return "goodsList";
    }

    @RequestMapping("/getProductByType3.action")
    public String getProductByType3(@RequestParam("type3Id") Integer type3Id, HttpSession session){
        List<Product> type1Product = mainService.getProductByType3(type3Id);
        session.setAttribute("type1Product",type1Product);
        session.setAttribute("type3Id",type3Id);
        //设置一个标志  用于前端分页区别不同的请求
        session.setAttribute("st","s3");
        return "goodsList";
    }

    @RequestMapping("/getAllNews.action")
    public String getAllNews(HttpSession session){
        List<News> newsList = mainService.getAllNews();
        session.setAttribute("newList",newsList);
        return "newsList";
    }
}