package com.thuwsy.imperialcourt.service.impl;

import com.thuwsy.imperialcourt.dao.api.MemorialsDao;
import com.thuwsy.imperialcourt.dao.impl.MemorialsDaoImpl;
import com.thuwsy.imperialcourt.pojo.Memorials;
import com.thuwsy.imperialcourt.service.api.MemorialsService;

import java.util.List;

/**
 * ClassName: MemorialsServiceImpl
 * Package: com.thuwsy.imperialcourt.service.impl
 * Description:
 *
 * @Author THU_wsy
 * @Create 2023/8/21 16:06
 * @Version 1.0
 */
public class MemorialsServiceImpl implements MemorialsService {
    private MemorialsDao memorialsDao = new MemorialsDaoImpl();

    @Override
    public List<Memorials> getAllMemorialsDigest() {
        return memorialsDao.selectAllMemorialsDigest();
    }

    @Override
    public Memorials getMemorialDetailById(String memorialsId) {
        return memorialsDao.selectMemorialsById(memorialsId);
    }

    @Override
    public void updateMemorialsStatusToRead(String memorialsId) {
        memorialsDao.updateMemorialsStatusToRead(memorialsId);
    }

    @Override
    public void updateMemorialsFeedBack(String memorialsId, String feedbackContent) {
        memorialsDao.updateMemorialsFeedBack(memorialsId, feedbackContent);
    }
}
