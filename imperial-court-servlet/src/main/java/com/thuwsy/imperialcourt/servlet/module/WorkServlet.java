package com.thuwsy.imperialcourt.servlet.module;

import com.thuwsy.imperialcourt.pojo.Memorials;
import com.thuwsy.imperialcourt.service.api.MemorialsService;
import com.thuwsy.imperialcourt.service.impl.MemorialsServiceImpl;
import com.thuwsy.imperialcourt.servlet.base.ModelBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * ClassName: WorkServlet
 * Package: com.thuwsy.imperialcourt.servlet.module
 * Description:
 *
 * @Author THU_wsy
 * @Create 2023/8/21 16:08
 * @Version 1.0
 */
@WebServlet("/work")
public class WorkServlet extends ModelBaseServlet {
    private MemorialsService memorialsService = new MemorialsServiceImpl();

    protected void showMemorialsDigestList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1、调用Service方法查询数据
        List<Memorials> memorialsList = memorialsService.getAllMemorialsDigest();

        // 2、将查询得到的数据存入请求域
        request.setAttribute("memorialsList", memorialsList);

        // 3、渲染视图
        String templateName = "memorials-list";
        processTemplate(templateName, request, response);
    }

    protected void showMemorialsDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1、从请求参数读取memorialsId
        String memorialsId = request.getParameter("memorialsId");

        // 2、根据memorialsId从Service中查询Memorials对象
        Memorials memorials = memorialsService.getMemorialDetailById(memorialsId);

        // 补充功能
        // 获取当前奏折对象的状态
        Integer memorialsStatus = memorials.getMemorialsStatus();
        // 判断奏折状态，如果是未读，则更新为已读
        if (memorialsStatus == 0) {
            // 数据库中更新状态为已读
            memorialsService.updateMemorialsStatusToRead(memorialsId);
            // 当前对象更新状态为已读
            memorials.setMemorialsStatus(1);
        }

        // 3、将Memorials对象存入请求域
        request.setAttribute("memorials", memorials);

        // 4、解析渲染视图
        String templateName = "memorials_detail";
        processTemplate(templateName, request, response);
    }

    protected void feedBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取表单提交的请求参数
        String memorialsId = request.getParameter("memorialsId");
        String feedbackContent = request.getParameter("feedbackContent");

        // 执行更新
        memorialsService.updateMemorialsFeedBack(memorialsId, feedbackContent);

        // 重定向回显示奏折列表页面
        response.sendRedirect(request.getContextPath() + "/work?method=showMemorialsDigestList");
    }
}
