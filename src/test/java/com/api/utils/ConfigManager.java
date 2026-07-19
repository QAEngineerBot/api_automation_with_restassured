package com.api.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static Properties prop = new Properties();
    private static String filePath = "config/config.properties";
    private static String env;

    static {

        env = System.getProperty("env", "default").toLowerCase().trim();

        switch (env) {

            case "dev" ->
                filePath = "config/config_dev.properties";

            case "qa" ->
                filePath = "config/config_qa.properties";

            case "uat" ->
                filePath = "config/config_uat.properties";

            default ->
                filePath = "config/config.properties";
        }

        InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);

        if (input == null) {
            throw new RuntimeException("Can not find file at " + filePath);
        }

        try {
            prop.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private ConfigManager() {
        // private constructor to restrict the object creation
    }

    public static String getProperty(String key) {

        return prop.getProperty(key);
    }
}
