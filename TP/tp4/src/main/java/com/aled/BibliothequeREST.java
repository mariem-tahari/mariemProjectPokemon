package com.aled;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;


@Path("/bibliotheque")
public class BibliothequeREST {
    @Inject
    Bibliotheque bibliotheque;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Livre> getLivres() {
        return bibliotheque.getTousLesLivres();
    }


    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addLivre(@FormParam("titre") String titre,
                             @FormParam("auteur") String auteur,
                             @FormParam("nbExemplaires") int nbExemplaires) {
        bibliotheque.addLivre(titre, auteur, nbExemplaires);
        return Response.status(Response.Status.FOUND).location(URI.create("/bibliotheque")).build();
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateNbExemplaires(@FormParam("titre") String titre,
                                        @FormParam("nbExemplaires") int nbExemplaires) {
        bibliotheque.mettreAJourNbExemplaires(titre, nbExemplaires);
        return Response.status(Response.Status.FOUND).location(URI.create("/bibliotheque")).build();
    }

}