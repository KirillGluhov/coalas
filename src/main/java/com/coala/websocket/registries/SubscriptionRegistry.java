package com.coala.websocket.registries;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SubscriptionRegistry {
    private final ConcurrentHashMap<String, Set<String>> subscriptions = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, String> sessionTokens = new ConcurrentHashMap<>();

    public void addSubscription(String destination, String sessionId, String token)
    {
        subscriptions.computeIfAbsent(destination, k -> ConcurrentHashMap.newKeySet()).add(sessionId);
        sessionTokens.put(sessionId, token);
    }

    public void removeSubscription(String destination, String sessionId)
    {
        subscriptions.getOrDefault(destination, ConcurrentHashMap.newKeySet()).remove(sessionId);
        sessionTokens.remove(sessionId);
    }

    public String getTokenForSession(String sessionId)
    {
        return sessionTokens.get(sessionId);
    }

    public Set<String> getSessionsByDestination(String destination)
    {
        return subscriptions.getOrDefault(destination, Collections.emptySet());
    }


    public Set<String> getActiveDestinations()
    {
        return subscriptions.keySet();
    }
}
