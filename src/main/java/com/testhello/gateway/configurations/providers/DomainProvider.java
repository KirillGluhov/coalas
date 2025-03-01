package com.testhello.gateway.configurations.providers;

import com.testhello.gateway.configurations.filters.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class DomainProvider {

    private Environment env;

    public DomainProvider(Environment environment)
    {
        this.env = environment;
    }

    public String getUserDomain()
    {
        return getDomain(getPort("coalas.users"));
    }

    public String getCoreDomain()
    {
        return getDomain(getPort("coalas.core"));
    }

    public String getLoanDomain()
    {
        return getDomain(getPort("coalas.loans"));
    }


    private String getPort(String envValue)
    {
        return env.getProperty(envValue);
    }

    private String getDomain(String port)
    {
        String host = env.getProperty("server.address");
        return String.format("http://%s:%s", host, port);
    }

}

// расширить список
