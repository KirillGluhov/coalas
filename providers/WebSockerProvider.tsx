import { Client } from "@stomp/stompjs";
import SockJS from "sockjs-client";
import React, { useContext } from "react";
import { createContext, ReactNode, useEffect, useState } from "react";

interface WebSocketContextType
{
    updates: any[]
}

const WebSocketContext = createContext<WebSocketContextType | null>(null);

export const WebSocketProvider = ({children} : {children: ReactNode}) => {
    const [stompClient, setStompClient] = useState<Client | null>(null);
    const [updates, setUpdates] = useState<any[]>([]);

    useEffect(() => {
        const client = new Client({
            brokerURL: "wss://tomcat.sonya.jij.li/websocket/?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJPbmxpbmUgSldUIEJ1aWxkZXIiLCJpYXQiOjE3NDEzNzY1MzIsImV4cCI6MTc3MjkxMjUzMiwiYXVkIjoid3d3LmV4YW1wbGUuY29tIiwic3ViIjoianJvY2tldEBleGFtcGxlLmNvbSIsInJvbGUiOiJjbGllbnQifQ.6wIgRNeh5WwEo7NSAMyVYeBhjmKP4CxnWQuSRWsVfqE",
            reconnectDelay: 5000,
            onConnect: () => {
                console.log("Connected to WebSocket");

                client.subscribe("/accounts/my", (message) => {
                    try
                    {
                        console.log(message);
                        const data = JSON.parse(message.body);
                        setUpdates(data)
                    }
                    catch (error)
                    {
                        console.error("can't parse");
                    }
                    
                });
            },
            onStompError: (frame) => {
                console.error("WebSocket Error: ", frame);
            }
        });

        client.activate();
        setStompClient(client);
    },[])

    return <WebSocketContext.Provider value={{updates}}>
        {children}
    </WebSocketContext.Provider>
}

export const useWebSocket = () => {
    const context = useContext(WebSocketContext);
    if (!context)
    {
        throw Error("Context doesn't exist")
    }
    return context;
}