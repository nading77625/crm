package com.nading.crm.workbench.web.controller;

import com.nading.crm.VO.PaginationVO;
import com.nading.crm.settings.domain.User;
import com.nading.crm.settings.service.UserService;
import com.nading.crm.settings.service.impl.UserServiceImpl;
import com.nading.crm.utils.DateTimeUtil;
import com.nading.crm.utils.PrintJson;
import com.nading.crm.utils.ServiceFactory;
import com.nading.crm.utils.UUIDUtil;
import com.nading.crm.workbench.domain.Activity;
import com.nading.crm.workbench.domain.ActivityRemark;
import com.nading.crm.workbench.service.ActivityService;
import com.nading.crm.workbench.service.ClueService;
import com.nading.crm.workbench.service.impl.ActivityServiceImpl;
import com.nading.crm.workbench.service.impl.ClueServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClueController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入到线索控制器");
        String path = request.getServletPath();
        if("/workbench/clue/getUserList.do".equals(path)){
            this.getUserList(request,response);
        }else if("/workbench/clue/xxx.do".equals(path)){
            //this.(request,response);
        }
    }

    private void getUserList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("获得用户信息");
        UserService cs = (UserService) ServiceFactory.getService(new UserServiceImpl());
        List<User> list = cs.getUserList();
        PrintJson.printJsonObj(response,list);
    }

}

