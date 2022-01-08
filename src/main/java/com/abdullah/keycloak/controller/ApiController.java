package com.abdullah.keycloak.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    @PreAuthorize("permitAll()")
    @GetMapping("/not-secured")
    public String getNonSecuredMessage() {
        return "Server return NON-SECURED message";
    }


    @PreAuthorize("hasAnyRole('Member', 'Librarian')")
    @GetMapping("/secured")
    public String getSecuredMessage() {
        return "Server return SECURED message";
    }

}
