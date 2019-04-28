package at.htl.TestManagementReloaded.rest;

import at.htl.TestManagementReloaded.busines.TestFacade;
import at.htl.TestManagementReloaded.model.Test;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("tests")
public class TestEndpoint {
    @Inject
    TestFacade testFacade;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(){
        List<Test> entities = testFacade.get();
        return Response.ok().entity(entities).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response get(@PathParam("id") long id){
        Test entity = testFacade.get(id);
        if(entity != null){
            return Response.ok().entity(entity).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") long id){
        Test entity = testFacade.get(id);
        if(entity != null){
            testFacade.remove(entity);
        }
        return Response.noContent().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(Test entity){
        try {
            entity = testFacade.save(entity);
        }catch(PersistenceException e){
            return Response.status(400).build();
        }
        return Response.ok().entity(entity).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response put(Test entity){
        entity = testFacade.update(entity);
        return Response.ok().entity(entity).build();
    }
}
