package com.thuwsy.imperialcourt.filter;

import com.thuwsy.imperialcourt.util.JDBCUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * ClassName: TransactionFilter
 * Package: com.thuwsy.imperialcourt.filter
 * Description:
 *
 * @Author THU_wsy
 * @Create 2023/8/21 15:49
 * @Version 1.0
 */
public class TransactionFilter implements Filter {

    // 声明集合保存静态资源扩展名
    private static Set<String> staticResourceExtNameSet;
    static {
        staticResourceExtNameSet = new HashSet<>();
        staticResourceExtNameSet.add(".png");
        staticResourceExtNameSet.add(".jpg");
        staticResourceExtNameSet.add(".css");
        staticResourceExtNameSet.add(".js");

    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 前置操作：排除静态资源
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String servletPath = request.getServletPath();
        if (servletPath.contains(".")) {
            String extName = servletPath.substring(servletPath.lastIndexOf("."));
            if (staticResourceExtNameSet.contains(extName)) {
                // 如果检测到当前请求确实是静态资源，则直接放行，不做事务操作
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }


        Connection conn = null;
        try {
            // 1、获取数据库连接
            conn = JDBCUtils.getConnection();
            // 重要操作：关闭自动提交功能
            conn.setAutoCommit(false);

            // 2、核心操作
            filterChain.doFilter(servletRequest, servletResponse);

            // 3、提交事务
            conn.commit();

        } catch (Exception e) {
            try {
                // 4、回滚事务
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            // 页面显示：将这里捕获到的异常发送到指定页面显示
            String message = e.getMessage();
            // 将异常信息存入请求域
            request.setAttribute("systemMessage", message);
            // 将请求转发到指定页面
            request.getRequestDispatcher("/").forward(request, servletResponse);

        } finally {
            // 5、释放数据库连接
            JDBCUtils.releaseConnection(conn);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void destroy() {

    }
}
