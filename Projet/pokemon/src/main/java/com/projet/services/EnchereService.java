package com.projet.services;

import com.projet.Enchere;
import com.projet.Pokemon;
import com.projet.Utilisateur;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class EnchereService {

    @Inject
    EntityManager em;


    @Transactional
    public Enchere creerEnchere(Utilisateur utilisateur, Pokemon pokemon, int montant, LocalDateTime expiration) {
        Enchere enchere = new Enchere(utilisateur, pokemon, montant, expiration);
        em.persist(enchere);
        return enchere;
    }

    public Enchere trouverEnchere(Long id) {
        return em.find(Enchere.class, id);
    }

    public List<Enchere> listerEncheres() {
        return em.createQuery("SELECT e FROM Enchere e", Enchere.class).getResultList();
    }

    @Transactional
    public void supprimerEnchere(Long id) {
        Enchere enchere = em.find(Enchere.class, id);
        if (enchere != null) {
            em.remove(enchere);
        }
    }

}
