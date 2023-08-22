package com.thuwsy.imperialcourt.servlet.module;

import com.thuwsy.imperialcourt.exception.LoginFailedException;
import com.thuwsy.imperialcourt.pojo.Emp;
import com.thuwsy.imperialcourt.service.api.EmpService;
import com.thuwsy.imperialcourt.service.impl.EmpServiceImpl;
import com.thuwsy.imperialcourt.servlet.base.ModelBaseServlet;
import com.thuwsy.imperialcourt.util.ImperialCourtConst;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * ClassName: AuthServlet
 * Package: com.thuwsy.imperialcourt.servlet.module
 * Description:
 *
 * @Author THU_wsy
 * @Create 2023/8/21 16:14
 * @Version 1.0
 */
@WebServlet("/auth")
public class AuthServlet extends ModelBaseServlet {
    private EmpService empService = new EmpServiceImpl();
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1、获取请求参数
            String loginAccount = request.getParameter("loginAccount");
            String loginPassword = request.getParameter("loginPassword");

            // 2、调用EmpService方法执行登录逻辑
            Emp emp = empService.getEmpByLoginAccount(loginAccount, loginPassword);

            // 3、通过request获取HttpSession对象
            HttpSession session = request.getSession();

            // 4、将查询到的Emp对象存入Session域
            session.setAttribute(ImperialCourtConst.LOGIN_EMP_ATTR_NAME, emp);

            // 5、前往指定页面视图
            response.sendRedirect(request.getContextPath() + "/work?method=showMemorialsDigestList");

        } catch (Exception e) {
            e.printStackTrace();
            // 6、判断此处捕获到的异常是否是登录失败的异常
            if (e instanceof LoginFailedException) {
                // 7、如果是登录失败异常，则跳转回登录页面
                // 7.1 将异常信息存入请求域
                request.setAttribute("message", e.getMessage());
                // 7.2 处理视图：index
                processTemplate("index", request, response);
            } else {
                // 8、如果不是登录异常，则封装为运行时异常继续抛出
                throw new RuntimeException(e);
            }
        }
    }
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1、通过request对象获取HttpSession对象
        HttpSession session = request.getSession();

        // 2、将HttpSession对象强制失效
        session.invalidate();

        // 3、回到首页
        String templateName = "index";
        processTemplate(templateName, request, response);
    }
}
