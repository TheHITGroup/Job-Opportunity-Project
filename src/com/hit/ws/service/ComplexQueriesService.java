package com.hit.ws.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.hit.controller.ComplexQueryController;
import com.hit.controller.LocationController;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/complexQueries")
public class ComplexQueriesService
{
    /*@GET
    @Path("/twoTechsByCityState")
    @Produces("text/plain")
    public String getTwoTechsByCityState(@QueryParam("techOne") String techOne, @QueryParam("techTwo") String techTwo, 
    		@QueryParam("city") String city, @QueryParam("state") String state) throws Exception {
        return ComplexQueryController.getPopOfTwoTechsByCityState(techOne, techTwo, city, state);
    }*/
    
    
    @GET
    @Path("/twoTechsByCityState")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTwoTechsByCityState(@QueryParam("techOne") String techOne, @QueryParam("techTwo") String techTwo, 
    		@QueryParam("city") String city, @QueryParam("state") String state) throws Exception {
        return Response.ok(ComplexQueryController.getPopOfTwoTechsByCityState(techOne, techTwo, city, state))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET")
                .build();

    }
    
    
    
    @GET
    @Path("/twoTechsByZip")
    @Produces("text/plain")
    public String getTwoTechsByZip(@QueryParam("techOne") String techOne, @QueryParam("techTwo") String techTwo, 
    		@QueryParam("city") String zip) throws Exception {
        return ComplexQueryController.getPopOfTwoTechsByZip(techOne, techTwo, zip);
    }
    
    @GET
    @Path("/twoCityStatesForTech")
    @Produces("text/plain")
    public String gettwoCityStatesForTech(@QueryParam("tech") String tech, 
    		@QueryParam("cityOne") String cityOne, @QueryParam("stateOne") String stateOne, 
    		@QueryParam("cityTwo") String cityTwo, @QueryParam("stateTwo") String stateTwo) throws Exception {
        return ComplexQueryController.getPopOfTwoCityStatesForTech(tech, cityOne,  stateOne,  cityTwo, stateTwo);
    }
    
    @GET
    @Path("/fwForLangInCityState")
    @Produces("text/plain")
    public String getPopFWForLangInCityState(@QueryParam("language") String language, 
    		@QueryParam("city") String city, @QueryParam("state") String state) throws Exception {
        return ComplexQueryController.getPopFWForLangInCityState(language,  city,  state);
    }
    
    @GET
    @Path("/cityStateNJobsForTech")
    @Produces("text/plain")
    public String getcityStateNJobsForTech(@QueryParam("state") String state, 
    		@QueryParam("tech") String tech, 
    		@QueryParam("numJobs") String numJobsRequest) throws Exception {
        return ComplexQueryController.getCityInStateAtLeastNJobsForTech(state,  tech,  numJobsRequest);
    }
}
