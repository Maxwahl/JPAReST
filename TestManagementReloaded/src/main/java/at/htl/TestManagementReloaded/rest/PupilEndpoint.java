package at.htl.TestManagementReloaded.rest;

import at.htl.TestManagementReloaded.busines.PupilFacade;
import at.htl.TestManagementReloaded.model.Pupil;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("pupils")
public class PupilEndpoint {
    @Inject
    PupilFacade pupilFacade;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(){
        List<Pupil> entities = pupilFacade.get();
        return Response.ok().entity(entities).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response get(@PathParam("id") long id){
        Pupil entity = pupilFacade.get(id);
        if(entity != null){
            return Response.ok().entity(entity).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") long id){
        Pupil entity = pupilFacade.get(id);
        if(entity != null){
            pupilFacade.remove(entity);
        }
        return Response.noContent().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(Pupil entity){
        try {
            entity = pupilFacade.save(entity);
        }catch(PersistenceException e){
            return Response.status(400).build();
        }
        return Response.ok().entity(entity).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response put(Pupil entity){
        entity = pupilFacade.update(entity);
        return Response.ok().entity(entity).build();
    }
}
