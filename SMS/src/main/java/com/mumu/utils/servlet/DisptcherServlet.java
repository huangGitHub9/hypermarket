package com.mumu.utils.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author Huangxulin
 * @date 2019/9/4 - 15:21
 */
/*@SuppressWarnings("serial")*/
public class DisptcherServlet extends HttpServlet {
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.request = req;
        this.response = resp;
        String status = req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/") + 1);
        if (status != null) {
            try {
                Method met = this.getClass().getMethod(status);
                met.invoke(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
