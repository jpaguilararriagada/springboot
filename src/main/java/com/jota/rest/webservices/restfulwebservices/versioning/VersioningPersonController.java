package com.jota.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {


    @GetMapping("/v1/person")
    public PersonV1 getFirstVersionOfPerson(){
        return new PersonV1("Bob Charlie");
    }

    @GetMapping("/v2/person")
    public PersonV2 getSecondVersionOfPerson(){
        return new PersonV2(new Name("Juanito", "Perez"));
    }

    @GetMapping(value = "/person", params = "version=1")
    public PersonV1 getFirstVersionOfPersonParameter(){
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(value = "/person", params = "version=2")
    public PersonV2 getSecondVersionOfPersonParameter(){
        return new PersonV2(new Name("Juanito", "Perez"));
    }

    @GetMapping(value = "/person", headers = "X-API-VERSION=1")
    public PersonV1 getFirstVersionOfPersonRequestHeader(){
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(value = "/person", headers = "X-API-VERSION=2")
    public PersonV2 getSecondVersionOfPersonHeader(){
        return new PersonV2(new Name("Juanito", "Perez"));
    }

    @GetMapping(value = "/person", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getFirstVersionOfPersonRequestAcceptHeader(){
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(value = "/person", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getSecondVersionOfPersonRequestAcceptHeader(){
        return new PersonV2(new Name("Juanito", "Perez"));
    }

}
