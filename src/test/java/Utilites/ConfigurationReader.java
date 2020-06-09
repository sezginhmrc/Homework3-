package Utilites;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationReader {
    // we will create static block when we call this class static block will be executed
    // and we will craete get method to get properties from properties file
    // We basically will be able to read this file anywhere in the project
    // ConigrationRead.getProperty("Key pair") -> it will give value pair

    private static Properties configFile ;

    static {
        try {
            String path = System.getProperty("user.dir")+"/configuration.properties";
            ///Users/sezgin/IdeaProjects/Fall2019Selenium/configuration.properties
            FileInputStream input = new FileInputStream(path);
            // it will read the data from file
            // it is checke exeption had to handle it with try catch
            configFile = new Properties();
            configFile.load(input);
            input.close();
        } catch (Exception e ){
            e.printStackTrace();
            throw new RuntimeException("Failed to load properties file ! ");
        }
    }
    // so far we loaded out properties file here now we will create a method to get it inside of this class

    public static String getProperty (String keyPair){
        return configFile.getProperty(keyPair);
        // since we loadded our properties file into configfile
        // we are able to get it our value from properties file along wit keypair (it will give value)
    }
}
