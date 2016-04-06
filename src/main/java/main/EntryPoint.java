package main;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by andreykazakov on 14.03.16.
 */


@Path("/entry-point")
public class EntryPoint {

    @GET
    @Path("test1")
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return "Test";
    }
    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public String test1() {
        return "PEdik - AAAAAЧ";
    }
}
