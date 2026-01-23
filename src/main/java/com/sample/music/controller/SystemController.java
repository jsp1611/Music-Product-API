package com.sample.music.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Request information on the system such as health and service connectivity.
 */
@RestController
@RequestMapping("/api/system")
public class SystemController implements ISystemController {

    @GetMapping("/ping")
    public String ping() {
        return "Pong";
    }

}
