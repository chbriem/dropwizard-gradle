package com.example.helloworld.resources.output;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("result")
public class ResultDownload {

    @GET
    //@GZIP
    @Produces(MediaType.TEXT_PLAIN)
    public String getResult() {
        return "This is the Result: Everything OK";
    }
}
