package com.fouadev.notificationservice.web;



import com.fouadev.notificationservice.dto.NotificationDTO;
import com.fouadev.notificationservice.services.KafkaConsumerService;
import com.fouadev.notificationservice.services.NotificationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 Created by : Fouad SAIDI on 06/02/2025
 @author : Fouad SAIDI
 @date : 06/02/2025
 @project : bank-microservice-kafka
*/

@RestController
@RequestMapping("notifications")
public class NotificationRestController {

    private KafkaConsumerService kafkaConsumerService;
    private NotificationService notificationService;

    public NotificationRestController(
            KafkaConsumerService kafkaConsumerService, NotificationService notificationService) {
        this.kafkaConsumerService = kafkaConsumerService;
        this.notificationService = notificationService;
    }
    @GetMapping("")
    public List<NotificationDTO> notifications(){
        return notificationService.displayNotifications();
    }

    @GetMapping("temp")
    public String getNotifications(Model model) {
        model.addAttribute("notifications", kafkaConsumerService.getNotifications());
        return "notifications";
    }


}