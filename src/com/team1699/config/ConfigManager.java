package com.team1699.config;

import java.util.HashMap;
import java.util.Map;

public class ConfigManager {

    //This class manages configs

    private static ConfigManager instance;
    public static ConfigManager getInstance(){
        if(instance == null){
            instance = new ConfigManager();
        }
        return instance;
    }

    private Map<String, Config> configMap; //String is config name

    private ConfigManager(){
        configMap = new HashMap<>(); //Check is should be concurrent
    }

    //someString: "name"
    //someInt: 12345
    //someDouble: 0.0
    public void addConfig(final String input){
        //Parse name
        String[] splitInput = input.split(" ");
        String name = splitInput[0].replace(":", "");

        //Parse data
        //Check Type
        if(splitInput[1].contains("\"")){
            configMap.put(name, new Config(name, splitInput[1].replaceAll("\"", "")));
        }else if(splitInput[1].contains(".")){
            configMap.put(name, new Config(name, Double.parseDouble(splitInput[1]))); //TODO try catch
        }else{
            //TODO add int check and throw error if invalid
            configMap.put(name, new Config(name, Integer.parseInt(splitInput[1]))); //TODO try catch
        }
    }

    public Config getConfig(final String name){
        if(!configMap.containsKey(name)){
            throw new ConfigNotFoundException();
        }
        return configMap.get(name);
    }
}
