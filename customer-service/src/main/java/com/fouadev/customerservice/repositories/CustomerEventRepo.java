package com.fouadev.customerservice.repositories;

import com.fouadev.customerservice.event.CustomerEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerEventRepo extends JpaRepository<CustomerEvent, Long> {
    //List<CustomerEvent> findBySentFalse();
}
