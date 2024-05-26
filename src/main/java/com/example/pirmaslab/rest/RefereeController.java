package com.example.pirmaslab.rest;

import com.example.pirmaslab.entities.Referee;
import com.example.pirmaslab.persistence.RefereeDAO;
import com.example.pirmaslab.rest.DTO.RefereeDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
@Path("/referees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RefereeController {
    @Inject
    private RefereeDAO refereeDAO;

    @GET
    @Transactional
    public Response loadAll() {
        List<Referee> referees = refereeDAO.getAll();

        return Response.ok(referees.stream().map(RefereeDTO::toRefereeDTO).collect(Collectors.toList())).build();
    }

    @POST
    @Transactional
    public Response create(RefereeDTO refereeDTO) {
        refereeDAO.persist(RefereeDTO.toReferee(refereeDTO));

        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        Referee existingReferee = refereeDAO.findOne(id);

        if (existingReferee == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        refereeDAO.delete(id);
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, RefereeDTO refereeDTO) {
        Referee existingReferee = refereeDAO.findOne(id);

        if (existingReferee != null) {
            existingReferee.setName(refereeDTO.getName());
            existingReferee.setSurname(refereeDTO.getSurname());
            existingReferee.setEmail(refereeDTO.getEmail());

            try {
                refereeDAO.update(existingReferee);
            } catch (OptimisticLockException ole) {
                return Response.status(Response.Status.CONFLICT).build();
            }

            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
