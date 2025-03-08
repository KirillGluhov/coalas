package com.coala.websocket.listeners;

import com.coala.websocket.providers.DomainProvider;
import com.coala.websocket.providers.UrlProvider;
import com.coala.websocket.registries.SubscriptionRegistry;
import com.coala.websocket.services.WebSocketService;
import com.coala.websocket.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpMethod;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

@Component
public class WebSocketEventListener
{
    private final SubscriptionRegistry subscriptionRegistry;
    private final WebSocketService webSocketService;
    private final UrlProvider urlProvider;
    private final DomainProvider domainProvider;

    public WebSocketEventListener(SubscriptionRegistry subscriptionRegistry, WebSocketService webSocketService, UrlProvider urlProvider, DomainProvider domainProvider)
    {
        this.subscriptionRegistry = subscriptionRegistry;
        this.webSocketService = webSocketService;
        this.urlProvider = urlProvider;
        this.domainProvider = domainProvider;
    }

    @EventListener
    public void handleWebSocketSubscribeListener(SessionSubscribeEvent event)
    {


        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = headerAccessor.getSessionId();
        String destination = headerAccessor.getDestination();
        String token = (String) headerAccessor.getSessionAttributes().get("token");;

        System.out.println("Headers: " + headerAccessor.getMessageHeaders());



        if (destination != null && token != null)
        {
            System.out.println(destination + "___" + sessionId + "___" + token);
            subscriptionRegistry.addSubscription(destination, sessionId, token);

            String url = domainProvider.getDomain() + destination;
            String data = urlProvider.addTokenInRequest(token, url);

            webSocketService.sendUpdate(data, destination);

        }

    }
}
