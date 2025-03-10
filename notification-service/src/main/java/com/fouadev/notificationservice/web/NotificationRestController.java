package com.fouadev.notificationservice.web;



import com.fouadev.notificationservice.services.KafkaConsumerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
 Created by : Fouad SAIDI on 06/02/2025
 @author : Fouad SAIDI
 @date : 06/02/2025
 @project : bank-microservice-kafka
*/
@Controller
@RequestMapping("notifications")
public class NotificationRestController {

    private KafkaConsumerService kafkaConsumerService;

    public NotificationRestController(
            KafkaConsumerService kafkaConsumerService) {
        this.kafkaConsumerService = kafkaConsumerService;
    }

    @GetMapping("")
    public String getNotifications(Model model) {
        model.addAttribute("notifications", kafkaConsumerService.getNotifications());
        return "notifications";
    }

}