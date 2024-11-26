package com.projet;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/utilisateurs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class UtilisateurRessource {

    @Inject
    UtilisateurService utilisateurService;

    @GET
    public List<Utilisateur> listerUtilisateurs() {
        return utilisateurService.listerUtilisateurs();
    }

    @POST
    public Utilisateur creerUtilisateur(Utilisateur utilisateur) {
        return utilisateurService.creerUtilisateur(utilisateur.getNom());
    }

    @GET
    @Path("/{id}")
    public Utilisateur trouverUtilisateur(@PathParam("id") Long id) {
        return utilisateurService.trouverUtilisateur(id);
    }

    @DELETE
    @Path("/{id}")
    public void supprimerUtilisateur(@PathParam("id") Long id) {
        utilisateurService.supprimerUtilisateur(id);
    }
}
