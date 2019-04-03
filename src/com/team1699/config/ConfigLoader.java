package com.team1699.config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigLoader {

    //This class loads a config file
    public static void loadConfigFile(final String path){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));

            String line = reader.readLine();
            while(line != null){
                ConfigManager.getInstance().addConfig(line);
                line = reader.readLine();
            }

            reader.close();
        } catch (FileNotFoundException e) {
            System.err.println("----------Configs not loaded correctly----------");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
