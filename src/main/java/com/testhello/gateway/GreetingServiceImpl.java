package com.testhello.gateway;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GreetingServiceImpl implements GreetingService {
    @Override
    public String print(String data) {
        return (data != null) ? data : "Hello World";
    }
}
