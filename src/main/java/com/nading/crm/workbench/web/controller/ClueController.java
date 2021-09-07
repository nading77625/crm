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
import com.nading.crm.workbench.domain.Clue;
import com.nading.crm.workbench.service.ActivityService;
import com.nading.crm.workbench.service.ClueService;
import com.nading.crm.workbench.service.impl.ActivityServiceImpl;
import com.nading.crm.workbench.service.impl.ClueServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        }else if("/workbench/clue/save.do".equals(path)){
            this.save(request,response);
        }
        else if("/workbench/clue/detail.do".equals(path)){
            this.detail(request,response);
        }
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入到详细信息页");
        String id = request.getParameter("id");
        ClueService cs = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
        Clue c = cs.detail(id);
//        HttpSession hs  = request.getSession();
//        hs.setAttribute("c",c);
        request.setAttribute("c",c);
        request.getRequestDispatcher("detail.jsp").forward(request,response);

    }

    private void save(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("保存线索信息");
        String id = UUIDUtil.getUUID();
        String fullname = request.getParameter("fullname");
        String appellation = request.getParameter("appellation");
        String owner = request.getParameter("owner");
        String company = request.getParameter("company");
        String job = request.getParameter("job");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String website = request.getParameter("website");
        String mphone = request.getParameter("mphone");
        String state = request.getParameter("state");
        String source = request.getParameter("source");
        String description = request.getParameter("description");
        String contactSummary = request.getParameter("contactSummary");
        String nextContactTime = request.getParameter("nextContactTime");
        String address = request.getParameter("address");
        String createTime = DateTimeUtil.getSysTime();
        String createBy = ((User)request.getSession().getAttribute("user")).getName();
        Clue c = new Clue();
        c.setFullname(fullname);
        c.setAppellation(appellation);
        c.setOwner(owner);
        c.setCompany(company);
        c.setJob(job);
        c.setEmail(email);
        c.setPhone(phone);
        c.setWebsite(website);
        c.setMphone(mphone);
        c.setState(state);
        c.setSource(source);
        c.setDescription(description);
        c.setContactSummary(contactSummary);
        c.setNextContactTime(nextContactTime);
        c.setAddress(address);
        c.setCreateTime(createTime);
        c.setCreateBy(createBy);
        c.setId(id);

        ClueService cs = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
        boolean flag = cs.save(c);
        PrintJson.printJsonFlag(response,flag);



    }

    private void getUserList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("获得用户信息");
        UserService cs = (UserService) ServiceFactory.getService(new UserServiceImpl());
        List<User> list = cs.getUserList();
        PrintJson.printJsonObj(response,list);//114
    }

}

