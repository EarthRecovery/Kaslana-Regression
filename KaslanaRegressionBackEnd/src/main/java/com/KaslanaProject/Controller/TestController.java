package com.KaslanaProject.Controller;

import com.KaslanaProject.Dao.TestDaoImpl;
import com.KaslanaProject.bean.TestBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestDaoImpl testDaoImpl;

    @GetMapping("/name")
    public String getName(){
        return "Kaslana";
    }

    @RequestMapping(value = "/test/GetById", method = RequestMethod.GET)
    public String GetById(Integer id){
        TestBean testBean = testDaoImpl.findById(id);
        return testBean.getName() + " " + testBean.getName2();
    }

    @RequestMapping(value = "/test/insert", method = RequestMethod.GET)
    public String insert(Integer id,String name, String name2) {
        TestBean testBean = new TestBean();
        testBean.setId(id);
        testBean.setName(name);
        testBean.setName2(name2);
        if (testDaoImpl.insert(testBean)) {
            return "success";
        } else {
            return "fail";
        }
    }
}
