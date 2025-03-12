package com.fouadev.reportservice.web;

import com.fouadev.reportservice.reports.ReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 Created by : Fouad SAIDI on 10/03/2025
 @author : Fouad SAIDI
 @date : 10/03/2025
 @project : bank-microservice-kafka
*/
@RestController
@RequestMapping("reports")
public class ReportRestController {
    private ReportService reportService;

    public ReportRestController(ReportService reportService) {
        this.reportService = reportService;
    }

//    @GetMapping("/customers")
//    public String generateFile(){
//        return reportService.generateCustomerReport();
//    }

    @GetMapping("")
    public String generateFile(@RequestParam("type") String type){
        return reportService.generateReport(type);
    }
}