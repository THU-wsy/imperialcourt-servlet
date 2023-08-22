package com.thuwsy.imperialcourt.dao;

import com.thuwsy.imperialcourt.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * ClassName: BaseDao
 * Package: com.thuwsy.imperialcourt.dao
 * Description: BaseDao类是所有Dao实现类的基类，泛型<T>表示实体类的类型
 *
 * @Author THU_wsy
 * @Create 2023/8/21 15:59
 * @Version 1.0
 */
public class BaseDao<T> {
    // DBUtils 工具包提供的数据库操作对象
    private QueryRunner runner = new QueryRunner();

    // 通用的增删改方法
    public int update(String sql, Object ... parameters) {
        try {
            Connection conn = JDBCUtils.getConnection();
            return runner.update(conn, sql, parameters);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    // 查询单个对象
    public T getSingleBean(String sql, Class<T> entityClass, Object ... parameters) {
        try {
            Connection conn = JDBCUtils.getConnection();
            return runner.query(conn, sql, new BeanHandler<>(entityClass), parameters);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    // 查询多个对象
    public List<T> getBeanList(String sql, Class<T> entityClass, Object ... parameters) {
        try {
            Connection conn = JDBCUtils.getConnection();
            return runner.query(conn, sql, new BeanListHandler<>(entityClass), parameters);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
