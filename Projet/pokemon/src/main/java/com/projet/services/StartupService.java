package com.projet.services;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class StartupService {

    @Inject
    PokemonService pokemonService;

    @PostConstruct
    public void onStartup() {
        System.out.println("Démarrage : Vérification des Pokémon initiaux...");
        pokemonService.genererPokemonsInitials();
    }
}
