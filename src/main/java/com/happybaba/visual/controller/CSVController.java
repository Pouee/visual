package com.happybaba.visual.controller;

import com.happybaba.visual.csv.CSVColumn;
import com.happybaba.visual.csv.CSVData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
public class CSVController {
    @ResponseBody
    @RequestMapping("/statistic")
    public Object statistic(@RequestParam("file") MultipartFile file) throws IOException {
        try (InputStream in = file.getInputStream()){
            CSVData origin = new CSVData(in,"success");
            CSVData csvData = new CSVData();
            csvData.setInvalidateCount(origin.getInvalidateCount());
            csvData.addColumn(origin.findByName("frame"));
            csvData.addColumn(origin.findByName("pose_Tx"));
            csvData.addColumn(origin.findByName("pose_Ty"));
            csvData.addColumn(origin.findByName("pose_Tz"));
            csvData.addColumn(origin.findByName("pose_Rx"));
            csvData.addColumn(origin.findByName("pose_Ry"));
            csvData.addColumn(origin.findByName("pose_Rz"));
            csvData.addColumn(origin.findByName("AU12_c"));
            csvData.addColumn(origin.findByName("AU14_c"));
            csvData.addColumn(origin.findByName("AU15_c"));
            //csvData.addColumn(origin.findByName("AU24_c"));
            csvData.addColumn(CSVColumn.difference(origin.findByName("pose_Tx")));
            csvData.addColumn(CSVColumn.difference(origin.findByName("pose_Ty")));
            csvData.addColumn(CSVColumn.difference(origin.findByName("pose_Tz")));
            csvData.addColumn(CSVColumn.difference(origin.findByName("pose_Rx")));
            csvData.addColumn(CSVColumn.difference(origin.findByName("pose_Ry")));
            csvData.addColumn(CSVColumn.difference(origin.findByName("pose_Rz")));
            return csvData ;
        }
    }
}
