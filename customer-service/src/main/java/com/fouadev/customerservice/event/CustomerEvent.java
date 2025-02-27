package com.fouadev.customerservice.event;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private UUID eventId;
    private String name;
    private String email;
    @Enumerated(EnumType.STRING)
    private EventType type;
    private Date date;
    private long duration;
    private boolean sent = false;
}
