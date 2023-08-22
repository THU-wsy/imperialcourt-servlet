package com.thuwsy.test;

import com.thuwsy.imperialcourt.dao.BaseDao;
import com.thuwsy.imperialcourt.pojo.Emp;
import com.thuwsy.imperialcourt.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

/**
 * ClassName: ImperialCourtTest
 * Package: com.thuwsy.test
 * Description:
 *
 * @Author THU_wsy
 * @Create 2023/8/21 16:24
 * @Version 1.0
 */
public class ImperialCourtTest {
    private BaseDao<Emp> baseDao = new BaseDao<>();
    @Test
    public void testGetSingleBean() {
        String sql = "select emp_id empId, emp_name empName, emp_position empPosition, login_account loginAccount, login_password loginPassword from t_emp where emp_id = ?";
        Emp emp = baseDao.getSingleBean(sql, Emp.class, 1);
        System.out.println("emp = " + emp);
    }
    @Test
    public void testGetBeanList() {
        String sql = "select emp_id empId, emp_name empName, emp_position empPosition, login_account loginAccount, login_password loginPassword from t_emp";
        List<Emp> empList = baseDao.getBeanList(sql, Emp.class);
        empList.forEach(System.out::println);
    }
    @Test
    public void testUpdate() {
        String sql = "update t_emp set emp_position = ? where emp_id = ?";
        int affectRowNumber = baseDao.update(sql, "minister", 3);
        System.out.println("affectRowNumber = " + affectRowNumber);
    }
    @Test
    public void testGetConnection() {
        Connection conn = JDBCUtils.getConnection();
        System.out.println("connection = " + conn);
        JDBCUtils.releaseConnection(conn);
    }

}