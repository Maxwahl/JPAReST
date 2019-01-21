package at.htl.TestManagement.rest;

import at.htl.TestManagement.business.StudentDAO;
import at.htl.TestManagement.model.Student;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("students")
public class StudentEndpoint {
    @Inject
    StudentDAO studentDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(){
        List<Student> entities = studentDAO.get();
        return Response.ok().entity(entities).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response get(@PathParam("id") long id){
        Student entity = studentDAO.get(id);
        if(entity != null){
            return Response.ok().entity(entity).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") long id){
        Student entity = studentDAO.get(id);
        if(entity != null){
            studentDAO.remove(entity);
        }
        return Response.noContent().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(Student entity){
        try {
            entity = studentDAO.save(entity);
        }catch(PersistenceException e){
            return Response.status(400).build();
        }
        return Response.ok().entity(entity).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response put(Student entity){
        entity = studentDAO.update(entity);
        return Response.ok().entity(entity).build();
    }
}
