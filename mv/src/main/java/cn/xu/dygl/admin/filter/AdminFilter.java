package cn.xu.dygl.admin.filter;

import cn.xu.dygl.admin.entity.Admin;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String reqRUL = request.getRequestURI();///dyglxt/admin/adminIndexUI.action
        Admin session_admin = (Admin) request.getSession().getAttribute("session_admin");
        if(session_admin != null){
            chain.doFilter(req, resp);
        }else{
            //除了这两个其他的我都拦截
            if( !reqRUL.contains("adminLoginUI") && !reqRUL.contains("adminLogin") ){
                //让它跳到登录界面
                String projoPath = request.getServletContext().getContextPath();
                System.out.println(projoPath);
                response.sendRedirect(projoPath+"/admin/adminLoginUI.action");
            }else{
                //其他则放行
                chain.doFilter(req, resp);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
