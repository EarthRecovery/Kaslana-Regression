package com.KaslanaProject.Dao;

import com.KaslanaProject.bean.TestBean;

public interface TestDao {
    TestBean findById(int id);
    boolean insert(TestBean testBean);
}
