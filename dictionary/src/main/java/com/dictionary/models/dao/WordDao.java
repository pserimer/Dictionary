package com.dictionary.models.dao;

import com.dictionary.base.BaseDao;
import com.dictionary.models.Word;

public interface WordDao extends BaseDao<Word> {

    void deleteByUuid(String uuid);

}
