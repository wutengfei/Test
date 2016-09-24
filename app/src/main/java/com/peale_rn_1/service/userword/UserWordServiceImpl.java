package com.peale_rn_1.service.userword;


import com.peale_rn_1.dao.userword.UserWordDao;
import com.peale_rn_1.dao.userword.UserWordDaoImpl;
import com.peale_rn_1.model.UserWord;

import java.util.Date;
import java.util.List;

public class UserWordServiceImpl implements UserWordService {
    UserWordDaoImpl userWordDao = new UserWordDaoImpl();

    @Override
    public void add(String userId, String word, int topicLevel) {
        // TODO Auto-generated method stub
        List<UserWord> list = userWordDao.find(userId, word);
        UserWord uw;
        if (list == null || list.size() == 0)
            uw = new UserWord(userId, word, topicLevel, 1, 0, new Date());
        else
            uw = new UserWord(userId, word, topicLevel, list.size() + 1, 0, new Date());
        userWordDao.save(uw);
    }

    @Override
    public List<UserWord> find(String userId, String word) {
        // TODO Auto-generated method stub
        return userWordDao.find(userId, word);
    }

    @Override
    public int learnCount(String userId, String word) {
        // TODO Auto-generated method stub
        List<UserWord> list = userWordDao.find(userId, word);
        if (list != null && list.size() > 0)
            return list.size();
        return 0;
    }
}
