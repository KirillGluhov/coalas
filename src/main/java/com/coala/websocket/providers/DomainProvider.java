package com.coala.websocket.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class DomainProvider {
    private final Environment env;

    @Autowired
    public DomainProvider(Environment environment)
    {
        this.env = environment;
    }

    public String getDomain()
    {
        String port = env.getProperty("coalas.gateway");
        String host = env.getProperty("server.address");
        System.out.println(String.format("http://%s:%s", host, port) + "/");

        return String.format("http://%s:%s", host, port);
    }
}
