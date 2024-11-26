package com.projet;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class UtilisateurService {

    @Inject
    EntityManager em;

    @Transactional
    public Utilisateur creerUtilisateur(String nom) {
        Utilisateur utilisateur = new Utilisateur(nom);
        em.persist(utilisateur);
        return utilisateur;
    }

    public Utilisateur trouverUtilisateur(Long id) {
        return em.find(Utilisateur.class, id);
    }

    public List<Utilisateur> listerUtilisateurs() {
        return em.createQuery("SELECT u FROM Utilisateur u", Utilisateur.class).getResultList();
    }

    @Transactional
    public void supprimerUtilisateur(Long id) {
        Utilisateur utilisateur = em.find(Utilisateur.class, id);
        if (utilisateur != null) {
            em.remove(utilisateur);
        }
    }
}
