package com.KaslanaProject.Dao;

import com.KaslanaProject.Bean.TestBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service("UserDaoImpl")
public class TestDaoImpl implements TestDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public TestBean findById(int id) {
        String sql = "select * from test1 where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            TestBean testBean = new TestBean();
            testBean.setId(rs.getInt("id"));
            testBean.setName(rs.getString("name"));
            testBean.setName2(rs.getString("name2"));
            return testBean;
        });
    }

    @Override
    public boolean insert(TestBean testBean) {
        String sql = "insert into test1(id, name, name2) values(?, ?, ?)";
        Object[] params = new Object[]{testBean.getId(), testBean.getName(), testBean.getName2()};
        return jdbcTemplate.update(sql, params)>0;
    }
}
