package com.KaslanaProject.Controller;

import com.KaslanaProject.Bean.Line;
import com.KaslanaProject.Bean.Point;
import com.KaslanaProject.Dao.TestDaoImpl;
import com.KaslanaProject.Service.LinearRegressionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class LinearRegressionController {

    @Autowired
    private LinearRegressionServices linearRegressionServices = new LinearRegressionServices();

    @CrossOrigin
    @PostMapping("api/model/linearModel/add")
    @ResponseBody
    public void linearModel_Add(@RequestBody Point point){
        System.out.println("success!");
        linearRegressionServices.addPoint(point);
    }

    @CrossOrigin
    @PostMapping("api/model/linearModel/generate")
    @ResponseBody
    public Line linearModel_Generate(){
        Line line = linearRegressionServices.returnline();
        return line;
    }

    @CrossOrigin
    @PostMapping("api/model/linearModel/clear")
    @ResponseBody
    public void linearModel_Clear(){
        linearRegressionServices.clearPoints();
    }
}
