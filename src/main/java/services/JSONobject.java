package services;

import org.json.simple.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
/**
 * Created by andreykazakov on 15.03.16.
 */
@Path("/users")
public class JSONobject {

    @Path("/all")
    @GET
    @Produces("application/json")
    public String getAll(){
        JSONObject json = new JSONObject();
        json.put("em","andrkaz96");
        return json.toString();
    }

}
