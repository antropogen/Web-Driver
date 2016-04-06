package com.fox.automation.framework.helpers;

import java.io.*;
import java.util.Properties;

public class ConfigHelpers {
    public static String getBrowserName(){
        return getPropertyValue("browser");
    }

    public static String getBaseUrl(){
        return getPropertyValue("baseUrl");
    }

    private static String getPropertyValue(String key){
        try {
            File file = new File("src/test/resources/config/config.properties");
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInput);
            fileInput.close();
            return properties.getProperty(key);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}