package com.hypeflame.rabbitmqdemo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class Api {

    private final Processor processor;

    @GetMapping("v1/processed")
    @ResponseStatus(HttpStatus.OK)
    public Integer get() {
        return processor.getProcessed();
    }

    @PostMapping("v1/processed")
    @ResponseStatus(HttpStatus.CREATED)
    public void post(@RequestBody String body) throws Exception {
        if (processor.isAvailable()) {
            processor.process(body);
        } else {
            throw new Exception("Unavailable");
        }
    }

    @DeleteMapping("v1/processed")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete() {
        processor.clear();
    }

    @GetMapping("v1/available")
    public Boolean isEnabled() {
        return processor.isAvailable();
    }

    @PostMapping("v1/available")
    public Boolean setEnabled(@RequestBody Boolean enabled) {
        return processor.setEnabled(enabled);
    }
}
