package at.htl.TestManagement.rest;

import at.htl.TestManagement.business.SubjectDAO;
import at.htl.TestManagement.business.TestDAO;
import at.htl.TestManagement.model.Subject;
import at.htl.TestManagement.model.Test;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("subjects")
public class SubjectEndpoint {
    @Inject
    SubjectDAO subjectDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(){
        List<Subject> entities = subjectDAO.get();
        return Response.ok().entity(entities).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") long id){
        Subject entity = subjectDAO.get(id);
        if(entity != null){
            return Response.ok().entity(entity).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") long id){
        Subject entity = subjectDAO.get(id);
        if(entity != null){
            subjectDAO.remove(entity);
        }
        return Response.noContent().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(Subject entity){
        try {
            entity = subjectDAO.save(entity);
        }catch(PersistenceException e){
            return Response.status(400).build();
        }
        return Response.ok().entity(entity).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public Response put(Subject entity){
        entity = subjectDAO.update(entity);
        return Response.ok().entity(entity).build();
    }
}