package com.nading.crm.web.listener;


import com.nading.crm.settings.domain.DicValue;
import com.nading.crm.settings.service.DicService;
import com.nading.crm.utils.ServiceFactory;
import com.nading.crm.workbench.service.ClueService;
import com.nading.crm.workbench.service.impl.ClueServiceImpl;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SysInitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /*
            该方法用来监听application作用域对象的方法，当服务器启动的时候，上下文作用域对象创建，对象创建完毕之后，马上执行该方法

            sec：能够取得监听的对象，监听的是什么对象，就可以通过该参数取得什么对象
            例如我们现在监听得是application作用域对象，通过该参数就可以取得该对象。
         */
        System.out.println("服务器处理缓存字典开始");
        ServletContext application = sce.getServletContext();
        DicService ds = (DicService) ServiceFactory.getService(new ClueServiceImpl());
        Map<String, List<DicValue>> map = ds.getAll();
        Set<String> set = map.keySet();
        for(String key:set){
            application.setAttribute(key,map.get(key));
        }
        System.out.println("服务器处理缓存字典结束");//108
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
