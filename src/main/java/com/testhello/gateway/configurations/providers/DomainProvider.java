package com.testhello.gateway.configurations.providers;

import com.testhello.gateway.configurations.filters.JWTAuthorizationFilter;
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

    public String getUserDomain()
    {
        return getHTTPDomain(getPort("coalas.users"));
    }

    public String getCoreDomain()
    {
        return getHTTPDomain(getPort("coalas.core"));
    }

    public String getLoanDomain()
    {
        return getHTTPDomain(getPort("coalas.loans"));
    }

    private String getPort(String envValue)
    {
        return env.getProperty(envValue);
    }

    private String getHTTPDomain(String port)
    {
        String host = env.getProperty("server.address");
        return String.format("https://%s/%s", host, port);
    }
}
