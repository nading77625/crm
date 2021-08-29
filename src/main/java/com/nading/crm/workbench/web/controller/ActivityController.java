package com.nading.crm.workbench.web.controller;

import com.nading.crm.settings.domain.User;
import com.nading.crm.settings.service.UserService;
import com.nading.crm.settings.service.impl.UserServiceImpl;
import com.nading.crm.utils.DateTimeUtil;
import com.nading.crm.utils.PrintJson;
import com.nading.crm.utils.ServiceFactory;
import com.nading.crm.utils.UUIDUtil;
import com.nading.crm.workbench.domain.Activity;
import com.nading.crm.workbench.service.ActivityService;
import com.nading.crm.workbench.service.impl.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.acl.Owner;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入到市场活动控制器");
        String path = request.getServletPath();
        if("/workbench/activity/getUserList.do".equals(path)){
            this.getUserList(request,response);
        }else if("/workbench/activity/save.do".equals(path)){
            this.save(request,response);
        }
    }

    private void save(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行市场活动添加操作");
        String id = UUIDUtil.getUUID();
        String owner = request.getParameter("owner");
        String name = request.getParameter("name");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String cost = request.getParameter("cost");
        String description = request.getParameter("description");
        String createTime = DateTimeUtil.getSysTime();
        String createBy = ((User) request.getSession().getAttribute("user")).getName();
        ActivityService act = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        Activity a = new Activity();
        a.setId(id);
        a.setOwner(owner);
        a.setName(name);
        a.setEndDate(endDate);
        a.setStartDate(startDate);
        a.setCost(cost);
        a.setDescription(description);
        a.setCreateTime(createTime);
        a.setCreateBy(createBy);
        boolean flag = act.sava(a);
        PrintJson.printJsonFlag(response,flag);

    }

    private void getUserList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("取得用户信息列表");
        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());
        List<User> uList = us.getUserList();
        PrintJson.printJsonObj(response,uList);
    }


}

