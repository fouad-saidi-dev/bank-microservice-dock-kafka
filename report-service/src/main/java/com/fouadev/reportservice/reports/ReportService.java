package com.fouadev.reportservice.reports;


import com.fouadev.reportservice.model.AccountDetail;
import com.fouadev.reportservice.model.Customer;
import com.fouadev.reportservice.openfeign.AccountClient;
import com.fouadev.reportservice.openfeign.CustomerClient;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.List;

/*
 Created by : Fouad SAIDI on 10/03/2025
 @author : Fouad SAIDI
 @date : 10/03/2025
 @project : bank-microservice-kafka
*/
@Service
@Slf4j
public class ReportService {

    private CustomerClient customerClient;
    private AccountClient accountClient;
    @Value("${report.directory}")
    private String reportDirectory;



    public ReportService(CustomerClient customerClient, AccountClient accountClient) {
        this.customerClient = customerClient;
        this.accountClient = accountClient;
    }


//    public String generateCustomerReport(){
//        try {
//
//            File file = new ClassPathResource("customers.jrxml").getFile();
//            JasperReport report = JasperCompileManager.compileReport(file.getAbsolutePath());
//
//            List<Customer> customers = customerClient.findAllCustomers();
//            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(customers);
//
//            JasperPrint jasperPrint = JasperFillManager.fillReport(report, new HashMap<>(),dataSource);
//
//            String outputPath = "customer_report_service.pdf";
//            JasperExportManager.exportReportToPdfStream(jasperPrint,new FileOutputStream(outputPath));
//
//            return "Report generated : " +outputPath;
//
//        } catch (JRException | IOException e) {
//            System.out.println(" Jasper Error ==>> "+e.getMessage());
//            return "Error generating file";
//        }
//    }

//    public String generateCustomerReport(){
//        try {
//
//            List<Customer> customers = customerClient.findAllCustomers();
//            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(customers);
//
//            InputStream inputStream = getClass().getResourceAsStream("/reports/customers.jrxml");
//
//            if (inputStream == null) {
//                throw new FileNotFoundException("Le fichier JRXML est introuvable dans le classpath.");
//            }
//
//            JasperReport report = JasperCompileManager.compileReport(inputStream);
//
//            JasperPrint jasperPrint = JasperFillManager.fillReport(report, new HashMap<>(),dataSource);
//
//            File reportFile = new File(reportDirectory);
//            if (!reportFile.getParentFile().exists()) {
//                reportFile.mkdirs();
//            }
//
//            String outputPath = reportDirectory+"/customer_report.pdf";
//            File file = new File(outputPath);
//
//            // increment the file name if it already exists
//            if (file.exists()) {
//                int i = 1;
//                while (file.exists()) {
//                    String fileName = file.getName();
//                    String extension = fileName.substring(fileName.lastIndexOf('.'));
//                    String baseName = fileName.substring(0, fileName.lastIndexOf('.'));
//                    String newName = baseName + "_" + i + extension;
//                    file = new File(reportDirectory, newName);
//                    i++;
//                }
//            }
//
//            log.info("File name: {}", file.getName());
//            log.info("File path: {}", file.getAbsolutePath());
//            log.info("Output path: {}", outputPath);
//
//            JasperExportManager.exportReportToPdfStream(jasperPrint,new FileOutputStream(file));
//
//            return "Report generated : " +outputPath;
//
//        } catch (JRException | IOException e) {
//            System.out.println(" Jasper Error ==>> "+e.getMessage());
//            return "Error generating file : "+e.getMessage();
//        }
//    }

    public String generateReport(String type) {
        try {

            List<AccountDetail> accountDetails = accountClient.findAllAccounts();
            List<Customer> customers = customerClient.findAllCustomers();

            JRBeanCollectionDataSource dataSource = switch (type) {
                case "customers" -> new JRBeanCollectionDataSource(customers);
                case "accounts" -> new JRBeanCollectionDataSource(accountDetails);
                default -> null;
            };


            InputStream inputStream = getClass().getResourceAsStream("/reports/"+type+".jrxml");

            if (inputStream == null) {
                throw new FileNotFoundException("Le fichier JRXML est introuvable dans le classpath.");
            }

            JasperReport report = JasperCompileManager.compileReport(inputStream);

            JasperPrint jasperPrint = JasperFillManager.fillReport(report, new HashMap<>(),dataSource);

            File reportFile = new File(reportDirectory);
            if (!reportFile.getParentFile().exists()) {
                reportFile.mkdirs();
            }

            String outputPath = reportDirectory+"/"+type+"_report.pdf";
            File file = new File(outputPath);


            if (file.exists()) {
                int i = 1;
                while (file.exists()) {
                    String fileName = file.getName();
                    String extension = fileName.substring(fileName.lastIndexOf('.'));
                    String baseName = fileName.substring(0, fileName.lastIndexOf('.'));
                    String newName = baseName + "_" + i + extension;
                    file = new File(reportDirectory, newName);
                    i++;
                }
            }

            log.info("File name: {}", file.getName());
            log.info("File path: {}", file.getAbsolutePath());
            log.info("Output path: {}", outputPath);

            JasperExportManager.exportReportToPdfStream(jasperPrint,new FileOutputStream(file));

            return "Report generated : " +outputPath;

        } catch (JRException | IOException e) {
            System.out.println(" Jasper Error ==>> "+e.getMessage());
            return "Error generating file : "+e.getMessage();
        }
    }
}