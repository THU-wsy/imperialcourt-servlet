package com.thuwsy.imperialcourt.dao.impl;

import com.thuwsy.imperialcourt.dao.BaseDao;
import com.thuwsy.imperialcourt.dao.api.EmpDao;
import com.thuwsy.imperialcourt.pojo.Emp;

/**
 * ClassName: EmpDaoImpl
 * Package: com.thuwsy.imperialcourt.dao.impl
 * Description:
 *
 * @Author THU_wsy
 * @Create 2023/8/21 16:01
 * @Version 1.0
 */
public class EmpDaoImpl extends BaseDao<Emp> implements EmpDao {
    @Override
    public Emp selectEmpByLoginAccount(String loginAccount, String encodedLoginPassword) {
        // 1、编写SQL语句
        String sql = "select emp_id empId, " +
                "emp_name empName, emp_position empPosition, " +
                "login_account loginAccount, login_password loginPassword " +
                "from t_emp where login_account = ? and login_password = ?";

        // 2、调用父类方法查询单个对象
        return super.getSingleBean(sql, Emp.class, loginAccount, encodedLoginPassword);
    }
}
