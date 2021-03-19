package com.example.helloworld.security;

import java.security.Principal;
import java.util.Optional;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;

public class ExampleOAuthAuthenticator implements Authenticator<String, Principal> {
    @Override
    public Optional<Principal> authenticate(String credentials)
            throws AuthenticationException {
        // TODO: here we would have to parse the token, with some library (not included)
        return Optional.empty();
    }
}
