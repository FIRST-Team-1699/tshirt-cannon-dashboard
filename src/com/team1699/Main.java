package com.team1699;

import com.team1699.server.Server;

public class Main {

    public static void main(String[] args){
        Dashboard.getInstance().start();
        //Server.getInstance(); //TODO Move to dashboard state
    }
}
