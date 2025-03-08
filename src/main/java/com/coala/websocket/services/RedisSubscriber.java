package com.coala.websocket.services;

import com.coala.websocket.providers.DomainProvider;
import com.coala.websocket.providers.UrlProvider;
import com.coala.websocket.registries.SubscriptionRegistry;
import com.coala.websocket.utils.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Set;

@Service
public class RedisSubscriber implements MessageListener {
    private final WebSocketService webSocketService;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final UrlProvider urlProvider;

    private final SubscriptionRegistry subscriptionRegistry;

    @Autowired
    DomainProvider domainProvider;

    @Autowired
    public RedisSubscriber(WebSocketService webSocketService, SubscriptionRegistry subscriptionRegistry, UrlProvider urlProvider)
    {
        this.webSocketService = webSocketService;
        this.restTemplate = new RestTemplate();
        this.subscriptionRegistry = subscriptionRegistry;
        this.urlProvider = urlProvider;
    }


    @Override
    public void onMessage(Message message, byte[] pattern) {
        try
        {
            String json = message.toString();
            Map<String, Object> update = objectMapper.readValue(json, Map.class);

            String entityId = update.get("id").toString();
            String entityType = update.get("entity").toString();

            Set<String> activeDestinations = subscriptionRegistry.getActiveDestinations();

            for (String destination : activeDestinations)
            {

                String path = new String(destination);
                String type = new String(entityType);
                String url = urlProvider.getUrl(type, path);

                Set<String> sessionIds = subscriptionRegistry.getSessionsByDestination(destination);

                for (String sessionId : sessionIds)
                {
                    String token = subscriptionRegistry.getTokenForSession(sessionId);

                    if (url != null && token != null)
                    {
                        String freshData = urlProvider.addTokenInRequest(token, url);
                        System.out.println("Url: " + url + " Token: " + token);
                        webSocketService.sendUpdate(freshData, destination);
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private String getUrlDestination(String entirePath, String partOfPath)
    {
        return domainProvider.getDomain() + partOfPath + StringUtils.getStringAfterFirstOccurenceOfSubstring(entirePath, partOfPath);
    }
}
