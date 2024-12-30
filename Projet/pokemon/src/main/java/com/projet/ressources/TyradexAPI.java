package com.projet.ressources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.Map;

@Path("/api/v1/pokemon")
@RegisterRestClient(configKey = "tyradex-api")
@Produces(MediaType.APPLICATION_JSON)
public interface TyradexAPI {

    @GET
    @Path("/{id}")
    Map<String, Object> getPokemonById(@PathParam("id") int id);
}
