package com.thuwsy.imperialcourt.service.impl;

import com.thuwsy.imperialcourt.dao.api.EmpDao;
import com.thuwsy.imperialcourt.dao.impl.EmpDaoImpl;
import com.thuwsy.imperialcourt.exception.LoginFailedException;
import com.thuwsy.imperialcourt.pojo.Emp;
import com.thuwsy.imperialcourt.service.api.EmpService;
import com.thuwsy.imperialcourt.util.ImperialCourtConst;
import com.thuwsy.imperialcourt.util.MD5Util;

/**
 * ClassName: EmpServiceImpl
 * Package: com.thuwsy.imperialcourt.service.impl
 * Description:
 *
 * @Author THU_wsy
 * @Create 2023/8/21 16:05
 * @Version 1.0
 */
public class EmpServiceImpl implements EmpService {
    private EmpDao empDao = new EmpDaoImpl();

    @Override
    public Emp getEmpByLoginAccount(String loginAccount, String loginPassword) {
        // 1、对密码执行加密
        String encodedLoginPassword = MD5Util.encode(loginPassword);

        // 2、根据账户和加密密码查询数据库
        Emp emp = empDao.selectEmpByLoginAccount(loginAccount, encodedLoginPassword);

        // 3、检查Emp对象是否为null
        if (emp != null) {
            // 不为null，返回Emp
            return emp;
        } else {
            // 为null，抛登录失败异常
            throw new LoginFailedException(ImperialCourtConst.LOGIN_FAILED_MESSAGE);
        }
    }
}
