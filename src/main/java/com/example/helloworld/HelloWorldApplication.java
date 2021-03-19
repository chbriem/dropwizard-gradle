package com.example.helloworld;

import java.security.Principal;

import org.eclipse.jetty.server.Authentication;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import com.example.helloworld.health.TemplateHealthCheck;
import com.example.helloworld.resources.HelloWorldResource;
import com.example.helloworld.resources.input.InputUpload;
import com.example.helloworld.resources.output.ResultDownload;
import com.example.helloworld.security.ExampleAuthorizer;
import com.example.helloworld.security.ExampleOAuthAuthenticator;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
  public static void main(String[] args) throws Exception {
    new HelloWorldApplication().run(args);
  }

  @Override
  public String getName() {
    return "hello-world";
  }

  @Override
  public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
    // nothing to do yet
  }

  @Override
  public void run(HelloWorldConfiguration configuration,
                  Environment environment) {
    final HelloWorldResource resource = new HelloWorldResource(
        configuration.getTemplate(),
        configuration.getDefaultName()
    );
    final TemplateHealthCheck healthCheck =
        new TemplateHealthCheck(configuration.getTemplate());
    environment.healthChecks().register("template", healthCheck);
    environment.jersey().register(resource);
    environment.jersey().register(new InputUpload());
    environment.jersey().register(new ResultDownload());

    environment.jersey().register(new AuthDynamicFeature(new OAuthCredentialAuthFilter.Builder<>()
                    .setAuthenticator(new ExampleOAuthAuthenticator())
                    .setAuthorizer(new ExampleAuthorizer())
                    .setPrefix("Bearer")
                    .buildAuthFilter()));
    environment.jersey().register(RolesAllowedDynamicFeature.class);
    //If you want to use @Auth to inject a custom Principal type into your resource
    environment.jersey().register(new AuthValueFactoryProvider.Binder<>(Principal.class));
  }


}