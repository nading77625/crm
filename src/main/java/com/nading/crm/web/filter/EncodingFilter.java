package com.nading.crm.web.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("进入到过滤字符编码的过滤器");
        //post请求中文的乱码
        req.setCharacterEncoding("utf-8");
        //过滤响应流中中文乱码
        resp.setContentType("text/html;charset=utf-8");
        //请求放行：
        filterChain.doFilter(req,resp);
    }
}
