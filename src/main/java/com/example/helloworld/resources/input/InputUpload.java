package com.example.helloworld.resources.input;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/input")
public class InputUpload {

    @Path("text")
    @Consumes(MediaType.TEXT_PLAIN)
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public String upload(String a_text) {
        System.out.println(a_text);
        return "OK";
    }
}
