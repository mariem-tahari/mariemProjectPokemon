package com.aled;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;
import java.util.Map;


@Path("/bibliotheque")
public class BibliothequeREST {
    private Bibliotheque bibliotheque = Bibliotheque.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Livre> getLivres() {
        return bibliotheque.getTousLesLivres();
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getLivresHtml() {
        List<Livre> livres = bibliotheque.getTousLesLivres();
        StringBuilder html = new StringBuilder();
        html.append("<html><body><h1>Liste des Livres</h1><ul>");
        for (Livre livre : livres) {
            html.append("<li>")
                    .append(livre.getTitre())
                    .append(" - ")
                    .append(livre.getAuteur())
                    .append(" (")
                    .append(livre.getNbExemplaires())
                    .append(" exemplaires)")
                    .append("</li>");
        }
        html.append("</ul></body></html>");
        return html.toString();
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