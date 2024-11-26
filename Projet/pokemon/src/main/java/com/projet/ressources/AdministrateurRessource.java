package com.projet.ressources;

import com.projet.Administrateur;
import com.projet.services.AdministrateurService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/administrateurs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdministrateurRessource {

    @Inject
    AdministrateurService administrateurService;

    @GET
    public List<Administrateur> listerAdministrateurs() {
        return administrateurService.listerAdministrateurs();
    }

    @POST
    public Administrateur creerAdministrateur(Administrateur administrateur) {
        return administrateurService.creerAdministrateur(administrateur.getNom(), administrateur.getEmail());
    }

    @GET
    @Path("/{id}")
    public Administrateur trouverAdministrateur(@PathParam("id") Long id) {
        return administrateurService.trouverAdministrateur(id);
    }

    @DELETE
    @Path("/{id}")
    public void supprimerAdministrateur(@PathParam("id") Long id) {
        administrateurService.supprimerAdministrateur(id);
    }
}
