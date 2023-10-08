package com.KaslanaProject.Controller;

import com.KaslanaProject.Dao.TestDaoImpl;
import com.KaslanaProject.Bean.TestBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

    @RequestMapping(value = "/model/linearModel/randomTest", method = RequestMethod.GET)
    public Object linearModel_RandomTest(){
        try{
            String Python_File = "D:\\projects\\project_KP\\python\\main.py";
            String Anaconda_Path = "D:\\anaconda\\envs\\ML\\python.exe";
            ProcessBuilder processBuilder = new ProcessBuilder(Anaconda_Path, Python_File);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder output = new StringBuilder();

            // 打印输出
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            return output.toString();

        } catch (IOException e) {
            return("error");
        }
    }
}
