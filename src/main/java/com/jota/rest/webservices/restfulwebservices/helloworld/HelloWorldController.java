package com.jota.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {

    @GetMapping(path = "hello-world")
    public String helloWorld(){
        return "Hello world";
    }

    @GetMapping(path = "hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("hola jp");
    }

    @GetMapping(path = "hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name){
        return new HelloWorldBean(String.format("hola %s", name));
    }
}
