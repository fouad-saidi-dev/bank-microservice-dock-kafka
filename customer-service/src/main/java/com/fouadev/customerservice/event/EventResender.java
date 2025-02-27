package com.fouadev.customerservice.event;

import com.fouadev.customerservice.repositories.CustomerEventRepo;
import com.netflix.discovery.DiscoveryClient;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Component
public class EventResender {
    private final CustomerEventRepo customerEventRepository;
    private final StreamBridge streamBridge;

    public EventResender(CustomerEventRepo customerEventRepository, StreamBridge streamBridge) {
        this.customerEventRepository = customerEventRepository;
        this.streamBridge = streamBridge;
    }

    private boolean isNotificationServiceAvailable() {
        String url = "http://localhost:8083/actuator/health"; // Remplace avec l'URL correcte
        try {
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(url, String.class);
            return response.contains("\"status\":\"UP\"");
        } catch (Exception e) {
            return false;
        }
    }

    @Scheduled(fixedDelay = 30000)
    public void resendFailedEvents() {
        if (!isNotificationServiceAvailable()) {
            System.out.println("Notification service is down. Retrying later...");
            return;
        }
        System.out.println("Resending failed events");
        List<CustomerEvent> failedEvents = customerEventRepository.findBySentFalse();
        for (CustomerEvent event : failedEvents) {
            CustomerEvent customerEvent = CustomerEvent.builder()
                    .eventId(event.getEventId())
                    .name(event.getName())
                    .email(event.getEmail())
                    .type(event.getType())
                    .date(event.getDate())
                    .duration(event.getDuration())
                    .build();

            boolean sent = streamBridge.send("customer-topic", customerEvent);

            if (sent) {
                event.setSent(true);
                customerEventRepository.save(event);
            }
        }
    }
}