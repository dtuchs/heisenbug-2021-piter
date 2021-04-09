package ru.heisenbug.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.heisenbug.domain.SayResponse;

@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${another.service.address}")
    private String anotherServiceUrl;

    @GetMapping("/say")
    public SayResponse say(@RequestParam(name = "name") String name) {
        SayResponse response = SayResponse.builder().name(name).build();
        if (!name.startsWith("D"))
            restTemplate.postForEntity(
                    anotherServiceUrl + "/upload",
                    response,
                    String.class
            );
        return response;
    }
}


