package com.philip.demo;

import java.io.File;
import java.io.FileNotFoundException;

import org.ho.yaml.Yaml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    //JYaml
    public void testJYaml()
    {
        File file = new File("/Users/philip/eclipse-workspace/yaml4Java/src/test/java/com/philip/demo/config.yaml");
        Configer conf;
        try {
            conf = Yaml.loadType(file, Configer.class);
            //失败了，此库不推荐使用
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(conf);
            System.out.println(json);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    //Jackson yaml
    public void testJacksonYaml()
    {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            Configer conf = mapper.readValue(new File("/Users/philip/eclipse-workspace/yaml4Java/src/test/java/com/philip/demo/config.yaml"), Configer.class);
            //成功~
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(conf);
            System.out.println(json);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
