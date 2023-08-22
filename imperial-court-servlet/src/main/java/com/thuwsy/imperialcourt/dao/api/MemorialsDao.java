package com.thuwsy.imperialcourt.dao.api;

import com.thuwsy.imperialcourt.pojo.Memorials;

import java.util.List;

/**
 * ClassName: MemorialsDao
 * Package: com.thuwsy.imperialcourt.dao.api
 * Description:
 *
 * @Author THU_wsy
 * @Create 2023/8/21 15:58
 * @Version 1.0
 */
public interface MemorialsDao {
    List<Memorials> selectAllMemorialsDigest();

    Memorials selectMemorialsById(String memorialsId);

    void updateMemorialsStatusToRead(String memorialsId);

    void updateMemorialsFeedBack(String memorialsId, String feedbackContent);
}
