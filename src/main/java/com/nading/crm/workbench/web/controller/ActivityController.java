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
import com.nading.crm.workbench.service.ActivityService;
import com.nading.crm.workbench.service.impl.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Service;
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
        }else if("/workbench/activity/pageList.do".equals(path)){
            this.pageList(request,response);
        }else if("/workbench/activity/delete.do".equals(path)){
            this.delete(request,response);
        }else if("/workbench/activity/getUserListAndActivity.do".equals(path)){
            this.getUserListAndActivity(request,response);
        }else if("/workbench/activity/update.do".equals(path)){
            this.update(request,response);
        }else if("/workbench/activity/detail.do".equals(path)){
            this.detail(request,response);
        }
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("跳转到详细页面");
        String id = request.getParameter("id");
        ActivityService act = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        Activity a = act.detail(id);
        request.setAttribute("a",a);
        try {
            request.getRequestDispatcher("detail.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("修改市场活动信息");
        String name = request.getParameter("name");
        String id = request.getParameter("id");
        String owner = request.getParameter("owner");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String cost = request.getParameter("cost");
        String description = request.getParameter("description");
        String editTime = DateTimeUtil.getSysTime();
        String editteBy = ((User) request.getSession().getAttribute("user")).getName();
        ActivityService act = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        Activity a = new Activity();
        a.setId(id);
        a.setOwner(owner);
        a.setName(name);
        a.setEndDate(endDate);
        a.setStartDate(startDate);
        a.setCost(cost);
        a.setDescription(description);
        a.setEditTime(editTime);
        a.setEditBy(editteBy);
        boolean flag = act.update(a);
        PrintJson.printJsonFlag(response,flag);

    }

    private void getUserListAndActivity(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入修改市场活动信息操作");
        String id = request.getParameter("id");
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        /*
            UList
            市场活动对象
         */
        Map<String,Object> map = as.getUserListAndActivity(id);

        PrintJson.printJsonObj(response,map);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到删除市场活动信息列表操作");
        String[] ids = request.getParameterValues("id");
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        boolean flag = as.delete(ids);
        PrintJson.printJsonFlag(response,flag);
    }


    private void pageList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到市场活动信息列表的操作，结合条件查询+分页查询");
        String name = request.getParameter("name");
        String owner = request.getParameter("owner");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String pageNoStr = request.getParameter("pageNo");
        String pageSizeStr = request.getParameter("pageSize");
        int pageNo = Integer.valueOf(pageNoStr);
        //每页展现的记录数
        int pageSize = Integer.valueOf(pageSizeStr);

        //计算出略过的记录数
        int skipCount = (pageNo - 1) * pageSize;

        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("owner" , owner);
        map.put("startDate" , startDate);
        map.put("endDate" , endDate);
        map.put("pageNo" , skipCount);
        map.put("pageSize" , pageSize);

        //ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        ActivityService as = new ActivityServiceImpl();
        /*
            前端需要：市场活动信息列表
                    查询的总条数

                    业务层拿到了以上两条信息之后，如果做返回
                    map
                        map.put("dataList",dataList);
                        map.put("total",total);
                        PrintJson map ---->Json
                        {"dataList":[{1},{2},{3}],""total":100}

                    vo:
                        PaginationVO<T>
                            private int total;
                            private List<T> dataList;
         */

        PaginationVO<Activity> vo = as.pageList(map);
        PrintJson.printJsonObj(response,vo);
        //p80 5:53

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
        //System.out.println(name);

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

