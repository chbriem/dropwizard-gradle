package com.example.helloworld.security;

import java.security.Principal;

import io.dropwizard.auth.Authorizer;

public class ExampleAuthorizer implements Authorizer<Principal> {
    @Override
    public boolean authorize(Principal user, String role) {
        return user.getName().equals("good-guy") && role.equals("ADMIN");
    }
}