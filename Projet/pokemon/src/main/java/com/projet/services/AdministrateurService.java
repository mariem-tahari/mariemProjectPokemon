package com.projet.services;

import com.projet.Administrateur;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class AdministrateurService {

    @Inject
    EntityManager em;

    @Transactional
    public Administrateur creerAdministrateur(String nom, String email) {
        Administrateur administrateur = new Administrateur(nom, email);
        em.persist(administrateur);
        return administrateur;
    }

    public Administrateur trouverAdministrateur(Long id) {
        return em.find(Administrateur.class, id);
    }

    public List<Administrateur> listerAdministrateurs() {
        return em.createQuery("SELECT a FROM Administrateur a", Administrateur.class).getResultList();
    }

    @Transactional
    public void supprimerAdministrateur(Long id) {
        Administrateur administrateur = em.find(Administrateur.class, id);
        if (administrateur != null) {
            em.remove(administrateur);
        }
    }
}
