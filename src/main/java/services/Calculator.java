package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * Created by andreykazakov on 14.03.16.
 */
@Path("calculator")
public class Calculator {
    @GET
    @Path("squareRoot")
    //@Produces(MediaType.APPLICATION_JSON)
    public Result squareRoot(@QueryParam("input") double input){
        Result result = new Result("Square Root");
        result.setInput(input);
        result.setOutput(Math.sqrt(result.getInput()));
        return result;
    }

    @GET
    @Path("square")
    //@Produces(MediaType.APPLICATION_JSON)
    public Result square(@QueryParam("input") double input){
        Result result = new Result("Square");
        result.setInput(input);
        result.setOutput(result.getInput()*result.getInput());
        return result;
    }

    static class Result{
        double input;
        double output;
        String action;

        public Result(){}

        public Result(String action) {
            this.action = action;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public double getInput() {
            return input;
        }

        public void setInput(double input) {
            this.input = input;
        }

        public double getOutput() {
            return output;
        }

        public void setOutput(double output) {
            this.output = output;
        }
    }
}