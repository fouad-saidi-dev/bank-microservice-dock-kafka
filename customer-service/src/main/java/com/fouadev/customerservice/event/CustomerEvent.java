package com.fouadev.customerservice.event;

import java.util.Date;

public record CustomerEvent(String name, String email, Date date, long duration) {
}
