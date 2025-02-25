package com.testhello.gateway;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GatewayController
{
    private final GreetingService greeting;
    @GetMapping
    public String greeting(@RequestParam(required = false) String data)
    {
        return greeting.print(data);
    }
}
