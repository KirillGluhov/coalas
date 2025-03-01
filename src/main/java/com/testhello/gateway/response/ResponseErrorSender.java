package com.testhello.gateway.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ResponseErrorSender
{
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Mono<Void> sendErrorResponse(ServerWebExchange exchange, HttpStatus status, String message) {
        try {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", status.value());
            errorResponse.put("message", message);

            String json = objectMapper.writeValueAsString(errorResponse);

            exchange.getResponse().setStatusCode(status);
            exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

            DataBuffer  buffer = exchange.getResponse().bufferFactory().wrap(json.getBytes(StandardCharsets.UTF_8));

            return exchange.getResponse().writeWith(Mono.just(buffer));
        } catch (JsonProcessingException e) {
            return Mono.error(e);
        }


    }

}
