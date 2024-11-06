package com.aled;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.util.List;


@ApplicationScoped
public class Bibliotheque {
    @Inject
    EntityManager entityManager;


    @Transactional
    public void addLivre(String titre, String auteur, int nbExemplaires) {
        Livre livre = new Livre(titre, auteur, nbExemplaires);
        entityManager.persist(livre);
    }

    public Livre getLivre(String titre) {
        return entityManager.find(Livre.class, titre);
    }

    public List<Livre> getTousLesLivres() {
        return entityManager.createQuery("SELECT l FROM Livre l", Livre.class).getResultList();
    }

    @Transactional
    public void removeLivre(String titre) {
        entityManager.remove(getLivre(titre));
    }

    @Transactional
    public Response mettreAJourNbExemplaires(String titre, int nbExemplaires) {
        Livre livre = getLivre(titre);
        if (livre != null) {
            livre.setNbExemplaires(nbExemplaires);
            entityManager.persist(livre);
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Livre non trouvé").build();
        }
        return null;
    }
}
