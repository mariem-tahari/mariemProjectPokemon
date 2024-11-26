package com.projet.services;

import com.projet.Pokemon;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Random;

@ApplicationScoped
public class PokemonService {

    @Inject
    EntityManager em;


    @Transactional
    public Pokemon creerPokemon(String nom, String description, int valeurReelle) {
        Pokemon pokemon = new Pokemon(nom, description, valeurReelle);
        em.persist(pokemon);
        return pokemon;
    }

    public Pokemon trouverPokemon(Long id) {
        return em.find(Pokemon.class, id);
    }

    public List<Pokemon> listerPokemons() {
        return em.createQuery("SELECT p FROM Pokemon p", Pokemon.class).getResultList();
    }

    @Transactional
    public void supprimerPokemon(Long id) {
        Pokemon pokemon = em.find(Pokemon.class, id);
        if (pokemon != null) {
            em.remove(pokemon);
        }
    }


    private int calculerValeurReelle(Pokemon pokemon) {
        int valeurReelle = 0;
        for (Integer stat : pokemon.getStats().values()) {
            valeurReelle += stat;
        }
        return valeurReelle;
    }


}