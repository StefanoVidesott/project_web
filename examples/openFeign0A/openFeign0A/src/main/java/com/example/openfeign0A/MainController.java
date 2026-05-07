package com.example.openfeign0A;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
public class MainController {

    private final DateTimeService dateTimeService;

    public MainController(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    @GetMapping("/date")
    String getDate() {
        return dateTimeService.getDate();
    }

    @GetMapping("/time")
    String getTime() {
        return dateTimeService.getTime();
    }
}