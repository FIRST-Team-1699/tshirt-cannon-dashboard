package com.team1699.server;

public interface ReceivedMessage {

    String handleMessage(String input);
    String addMessage(String input);
    String getName(); //To be overridden with name of class
}
