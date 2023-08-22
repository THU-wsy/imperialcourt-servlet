package com.thuwsy.imperialcourt.servlet.module;

import com.thuwsy.imperialcourt.servlet.base.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: PortalServlet
 * Package: com.thuwsy.imperialcourt.servlet.module
 * Description:
 *
 * @Author THU_wsy
 * @Create 2023/8/21 16:22
 * @Version 1.0
 */
@WebServlet("/")
public class PortalServlet extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 声明要访问的首页的逻辑视图
        String templateName = "index";

        // 调用父类的方法根据逻辑视图名称渲染视图
        processTemplate(templateName, req, resp);

    }
}
