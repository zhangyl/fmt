package com.example.jib;

import org.springframework.stereotype.Component;

@Component
public class HelloJibFromApiComponent {

    public String getHelloFromApi() {
        return "Hello from API component.";
    }
}
