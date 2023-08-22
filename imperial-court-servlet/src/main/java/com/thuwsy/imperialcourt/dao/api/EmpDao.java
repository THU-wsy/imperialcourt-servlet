package com.thuwsy.imperialcourt.dao.api;

import com.thuwsy.imperialcourt.pojo.Emp;

/**
 * ClassName: EmpDao
 * Package: com.thuwsy.imperialcourt.dao.api
 * Description:
 *
 * @Author THU_wsy
 * @Create 2023/8/21 15:58
 * @Version 1.0
 */
public interface EmpDao {
    Emp selectEmpByLoginAccount(String loginAccount, String encodedLoginPassword);
}
