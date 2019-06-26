package com.team1699.server;

import java.util.HashMap;
import java.util.Map;

public class MessageManager {

    private static MessageManager instance;

    public static MessageManager getInstance() {
        if(instance == null){
            instance = new MessageManager();
        }
        return instance;
    }

    private final Map<String, Class<? extends ReceivedMessage>> msgMap;

    private MessageManager(){
        msgMap = new HashMap<>();

        //TODO Add msg classes
    }

    public ReceivedMessage getClassFromMessage(String input){
        //TODO Split string get class and add message
        return null;
    }
}
