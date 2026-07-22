package com.api.utils;

import java.io.File;
import java.io.FileWriter;
import java.util.Properties;

public class AllureEnvWriterUtil {
    public static void createAllureEnvironmentProperties() {

        String folderPath = "target/allure-results";
        File file = new File(folderPath);
        file.mkdirs();

        Properties prop = new Properties();
        prop.setProperty("Project Name", "API Automation Framework");
        prop.setProperty("User Name", System.getProperty("user.name"));
        prop.setProperty("Env", ConfigManager.env);
        prop.setProperty("BASE_URI", ConfigManager.getProperty("BASE_URI"));
        prop.setProperty("Operating System", System.getProperty("os.name"));
        prop.setProperty("Os Version", System.getProperty("os.version"));
        prop.setProperty("Java Version", System.getProperty("java.version"));
        
        
        try {
            FileWriter writer = new FileWriter(folderPath + "/environment.properties");
            prop.store(writer, "Environment Properties");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
