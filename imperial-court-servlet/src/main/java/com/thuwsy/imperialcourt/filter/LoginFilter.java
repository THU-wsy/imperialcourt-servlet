package com.thuwsy.imperialcourt.filter;

import com.thuwsy.imperialcourt.util.ImperialCourtConst;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * ClassName: LoginFilter
 * Package: com.thuwsy.imperialcourt.filter
 * Description:
 *
 * @Author THU_wsy
 * @Create 2023/8/21 15:51
 * @Version 1.0
 */
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 1、获取HttpSession对象
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();

        // 2、尝试从Session域获取已登录的对象
        Object loginEmp = session.getAttribute(ImperialCourtConst.LOGIN_EMP_ATTR_NAME);

        // 3、判断loginEmp是否为空
        if (loginEmp != null) {
            // 4、若不为空则说明当前请求已登录，直接放行
            filterChain.doFilter(request, servletResponse);
            return;
        }

        // 5、若为空，说明尚未登录，则回到登录页面
        request.setAttribute("systemMessage", ImperialCourtConst.ACCESS_DENIED_MESSAGE);
        request.getRequestDispatcher("/").forward(request, servletResponse);
    }

    @Override
    public void destroy() {

    }
}