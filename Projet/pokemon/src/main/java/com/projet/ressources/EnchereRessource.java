package com.projet.ressources;

import com.projet.Enchere;
import com.projet.Pokemon;
import com.projet.Utilisateur;
import com.projet.services.EnchereService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.util.List;

@Path("/encheres")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EnchereRessource {

    @Inject
    EnchereService enchereService;

    @GET
    public List<Enchere> listerEncheres() {
        return enchereService.listerEncheres();
    }

    @POST
    public Enchere creerEnchere(EnchereRequest request) {
        Utilisateur utilisateur = request.getUtilisateur();
        Pokemon pokemon = request.getPokemon();
        int montant = request.getMontant();
        LocalDateTime expiration = request.getExpiration();
        return enchereService.creerEnchere(utilisateur, pokemon, montant, expiration);
    }

    @GET
    @Path("/{id}")
    public Enchere trouverEnchere(@PathParam("id") Long id) {
        return enchereService.trouverEnchere(id);
    }

    @DELETE
    @Path("/{id}")
    public void supprimerEnchere(@PathParam("id") Long id) {
        enchereService.supprimerEnchere(id);
    }

    // Classe interne pour la requête de création d'enchère
    public static class EnchereRequest {
        private Utilisateur utilisateur;
        private Pokemon pokemon;
        private int montant;
        private LocalDateTime expiration;

        public Utilisateur getUtilisateur() {
            return utilisateur;
        }

        public void setUtilisateur(Utilisateur utilisateur) {
            this.utilisateur = utilisateur;
        }

        public Pokemon getPokemon() {
            return pokemon;
        }

        public void setPokemon(Pokemon pokemon) {
            this.pokemon = pokemon;
        }

        public int getMontant() {
            return montant;
        }

        public void setMontant(int montant) {
            this.montant = montant;
        }

        public LocalDateTime getExpiration() {
            return expiration;
        }

        public void setExpiration(LocalDateTime expiration) {
            this.expiration = expiration;
        }
    }
}
