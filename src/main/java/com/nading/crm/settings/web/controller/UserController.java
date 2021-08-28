package com.nading.crm.settings.web.controller;

import com.nading.crm.settings.domain.User;
import com.nading.crm.settings.service.UserService;
import com.nading.crm.settings.service.impl.UserServiceImpl;
import com.nading.crm.utils.MD5Util;
import com.nading.crm.utils.PrintJson;
import com.nading.crm.utils.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入到用户控制器");
        String path = request.getServletPath();
        if("/settings/user/login.do".equals(path)){
            this.login(request,response);
        }else if("/settings/user/login.do".equals(path)){

        }
    }
    private void login(HttpServletRequest request, HttpServletResponse response){
//        response.setContentType("html/text;charset=utf-8");
        String loginAct = request.getParameter("loginAct");
        String loginPwd = request.getParameter("loginPwd");
        //将密码的明文形式转换为密文的md5形式
        //loginPwd = MD5Util.getMD5(loginAct);
        //接受ip地址
        String ip = request.getRemoteAddr();
        System.out.println("ip地址：" + ip);

        //UserService us = new UserServiceImpl();
        //未来业务层开发，统一使用代理类形态的接口对象
        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());

        try{
            User user = us.login(loginAct,loginPwd,ip);
            //System.out.println(us);

            request.getSession().setAttribute("user",user);
            /*
                程序执行到此处，说明业务层没有为controller抛出任何的异常，表示登录成功
                {"success":true}
             */
            PrintJson.printJsonFlag(response,true);
        }catch (Exception e){
            e.printStackTrace();
            /*
                一旦程序执行了catch快的信息，说明业务层为我们验证登录失败，为controller抛出了异常
                表示登录失败
                {"success":false,"msg":?}
             */
            String msg = e.getMessage();
            /*
                现在我们作为controller，需要为ajax提供更多信息，可以有两种手段来处理
                    1.将多项信息打包成map，将map解析为json串
                    2.创建一个Vo类

                如果对于展现的信息将来还会大量的使用，我们创建一个vo类，使用方便
                如果对于展现的信息只有在这个需求中能够使用，那么用map即可
             */
            Map<String,Object> map = new HashMap<>();
            map.put("success",false);
            map.put("msg",msg);
            PrintJson.printJsonObj(response,map);
        }


    }
}
