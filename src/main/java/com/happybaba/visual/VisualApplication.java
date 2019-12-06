package com.happybaba.visual;

import com.happybaba.visual.csv.CSVData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;

@SpringBootApplication
public class VisualApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(VisualApplication.class, args);
//        CSVData csvData = new CSVData(new FileInputStream("D:\\20191127_203651.csv"));
//        System.setOut(new PrintStream("D:\\1.txxx"));
//        csvData.save(new FileOutputStream("D:\\2.csv"));
//        System.out.println(csvData);
    }

}
