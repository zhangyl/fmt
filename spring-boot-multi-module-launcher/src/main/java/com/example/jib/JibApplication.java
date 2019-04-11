package com.example.jib;

import java.net.URL;
import java.net.URLClassLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jib.external.api.DemoFacade;

@SpringBootApplication
@RestController
public class JibApplication {

    @Autowired
    public HelloJibFromApiComponent helloJibFromApiComponent;
    @Autowired
    public DemoFacade demoFacade;

    public static void main(String[] args) {
        SpringApplication.run(JibApplication.class, args);
    }

    @RequestMapping("/")
    public String helloJib() {
        return "Hello Jib";
    }

    @RequestMapping("/classpath")
    public URL[] getClasspath() {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        URL[] urls = ((URLClassLoader)cl).getURLs();

        return urls;
    }

    @RequestMapping("/api-component")
    public String getHelloFromApi() {
        return helloJibFromApiComponent.getHelloFromApi();
    }
    
    @RequestMapping("/external-api")
    public String getHelloFromExternalApi() {
    	return demoFacade.say("world！");
    }
    
    
}
