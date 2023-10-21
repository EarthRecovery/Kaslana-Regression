package com.KaslanaProject.Service;

import com.KaslanaProject.Bean.Line;
import com.KaslanaProject.Bean.Point;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class LinearRegressionServices {

    private ArrayList<Point> points;

    private double slope;
    private double yIntercept;

    private double mse;

    public LinearRegressionServices(){
        this.points = new ArrayList<Point>();
    }

    public void addPoint(float x, float y){
        this.points.add(new Point(x, y));
    }

    public void addPoint(Point point){
        this.points.add(point);
    }

    public Line returnline(){
        double number1, number2;
        // 创建一个工作簿对象
        Workbook workbook = new HSSFWorkbook(); // 使用HSSFWorkbook来创建XLS文件

        // 创建一个工作表
        Sheet sheet = workbook.createSheet("Points");

        // 创建标题行
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("X");
        headerRow.createCell(1).setCellValue("Y");

        // 将points列表中的数据写入工作表
        int rowNum = 1;
        for (Point point : this.points) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(point.getX());
            row.createCell(1).setCellValue(point.getY());
        }

        // 保存工作簿到文件
        try {
            FileOutputStream fileOut = new FileOutputStream("points.xls");
            workbook.write(fileOut);
            fileOut.close();
//            System.out.println("Excel文件已创建成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //调用python脚本
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

            String[] parts = output.toString().split("\n");

            this.slope = Double.parseDouble(parts[0]);
            this.yIntercept = Double.parseDouble(parts[1]);
            this.mse = Double.parseDouble(parts[2]);

            System.out.println("斜率：" + this.slope);
            System.out.println("截距：" + this.yIntercept);

        } catch (IOException e) {
            System.out.println("Error executing python file");
        }
        //返回线性回归的结果

        return new Line(this.slope, this.yIntercept, this.mse);
    }

    public void clearPoints(){
        this.points.clear();
    }
}
