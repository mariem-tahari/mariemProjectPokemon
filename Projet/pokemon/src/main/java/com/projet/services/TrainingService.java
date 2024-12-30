package com.projet.resources;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Map;

public class TrainingService {

    @PersistenceContext
    private EntityManager em;

    // Méthode d'entraînement pour un Pokémon
    @Transactional
    public void entrainerPokemon(Long pokemonId, Long userId, double pourcentage) {
        // Vérifier si le pourcentage est valide (ne dépasse pas 10%)
        if (pourcentage > 10) {
            throw new IllegalArgumentException("Un Pokémon ne peut gagner plus de 10% par session.");
        }

        // Récupérer le Pokémon et l'utilisateur
        Pokemon pokemon = em.find(Pokemon.class, pokemonId);
        User user = em.find(User.class, userId);

        if (pokemon == null || user == null) {
            throw new IllegalArgumentException("Pokémon ou utilisateur non trouvé.");
        }

        // Vérifier si l'utilisateur a assez de Limcoins pour l'entraînement
        int coutEnLimcoins = calculerCoutEnLimcoins(pourcentage);
        if (user.getLimcoins() < coutEnLimcoins) {
            throw new IllegalArgumentException("L'utilisateur n'a pas assez de Limcoins.");
        }

        // Calculer le temps nécessaire pour l'entraînement
        int nombreTranches = (int) (pourcentage / 1); // chaque tranche de 1% = 5 minutes
        long tempsNecessaire = nombreTranches * 5L; // Temps nécessaire en minutes

        // Appliquer l'entraînement
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.plusMinutes(tempsNecessaire);

        // Simuler l'attente du temps d'entraînement (en pratique, on pourrait gérer cette attente différemment)
        try {
            Thread.sleep(tempsNecessaire * 60 * 1000); // Attend le temps nécessaire en millisecondes
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Appliquer l'augmentation des stats et de la valeur réelle
        pokemon.setStats(entraînerPokemon(pokemon, pourcentage));
        pokemon.setValeurReelle(pokemon.getValeurReelle() * (1 + pourcentage / 100));

        // Débiter l'utilisateur du coût de l'entraînement
        user.setLimcoins(user.getLimcoins() - coutEnLimcoins);

        // Enregistrer les changements
        em.merge(pokemon);
        em.merge(user);

        // Log de l'entraînement
        System.out.println("Entraînement de Pokémon " + pokemon.getName() + " terminé, stats et valeur augmentées de " + pourcentage + "%.");
        System.out.println("L'utilisateur " + user.getUsername() + " a été débité de " + coutEnLimcoins + " Limcoins.");
    }

    // Calculer le coût en Limcoins en fonction du pourcentage
    private int calculerCoutEnLimcoins(double pourcentage) {
        // Chaque pourcentage coûte 250 Limcoins
        return (int) (pourcentage / 1 * 250);
    }

    // Méthode d'entraînement : augmente les stats du Pokémon par pourcentage
    private Map<String, Integer> entraînerPokemon(Pokemon pokemon, double pourcentage) {
        Map<String, Integer> newStats = pokemon.getStats();

        // Appliquer l'augmentation des stats
        for (Map.Entry<String, Integer> entry : newStats.entrySet()) {
            int newValue = (int) (entry.getValue() * (1 + pourcentage / 100.0));
            newStats.put(entry.getKey(), newValue);
        }

        return newStats;
    }
}
