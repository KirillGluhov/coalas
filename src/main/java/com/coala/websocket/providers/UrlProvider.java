package com.coala.websocket.providers;

import com.coala.websocket.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UrlProvider {
    @Autowired
    DomainProvider domainProvider;

    RestTemplate restTemplate;

    @Autowired
    public UrlProvider()
    {
        this.restTemplate = new RestTemplate();
    }

    public String getUrl(String type, String path)
    {
        String url = null;
        if (type.contains("loan"))
        {
            if (path.contains("/loans/clients"))
            {
                url = getDestination(path, "/loans/clients");
            }
            else if(path.contains("/loans/my"))
            {
                url = getDestination(path, "/loans/my");
            }
        }
        else if(type.contains("account"))
        {
            if (path.contains("/accounts/clients"))
            {
                url = getDestination(path, "/accounts/clients");
            }
            else if(path.contains("/accounts/my"))
            {
                url = getDestination(path, "/accounts/my");
            }
        }
        else if(type.contains("operation"))
        {
            if (path.contains("/operations/my/accounts/"))
            {
                url = getDestination(path, "/operations/my/accounts/");
            }
            else if(path.contains("/operations/my/loans/"))
            {
                url = getDestination(path, "/operations/my/loans/");
            }
            else if(path.contains("/operations/loans/"))
            {
                url = getDestination(path, "/operations/loans/");
            }
            else if (path.contains("/operations/accounts/"))
            {
                url = getDestination(path, "/operations/accounts/");
            }
        }

        return url;
    }

    public String addTokenInRequest(String token, String url)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
    }
    private String getDestination(String entirePath, String partOfPath)
    {
        return domainProvider.getDomain() + partOfPath + StringUtils.getStringAfterFirstOccurenceOfSubstring(entirePath, partOfPath);
    }
}
