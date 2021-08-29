package com.nading.crm.web.filter;

import com.nading.crm.settings.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("进入到验证有没有登陆过的过滤器");

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String path = request.getServletPath();
        if("/login.jsp".equals(path) || "/settings/user/login.do".equals(path) || "/test.jsp".equals(path)){
            filterChain.doFilter(req,resp);
        }else {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if(user != null){
                //user不为null说明登录过
                filterChain.doFilter(req,resp);
            }else {
            /*
                没有登录，重定向

                重定向和转发的区别：
                    转发：
                        使用的是一种特殊的绝对路径的使用方式，这种绝对路径前面不加/项目名，这种路径也称之为内部路径
                        /login.jsp

                    重定向：
                        使用的是传统绝对路径的写法，前面必须以/项目名开头，后面跟具体的资源路径
                        /crm/login.jsp

                为什么使用重定向？
                    转发之后，路径会停留在老路径上，而不是跳转之后最新的资源路径

             */
                //response.sendRedirect("/crm/login.jsp");
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        }


    }
}
