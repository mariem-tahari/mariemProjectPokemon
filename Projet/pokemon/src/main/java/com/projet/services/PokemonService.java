package com.projet.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.jboss.logging.Logger;


import com.projet.Pokemon;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import java.util.Random;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class PokemonService {

    private static final Logger LOGGER = Logger.getLogger(PokemonService.class);

    @Inject
    EntityManager em;

    @Inject
    CacheService cacheService;  // Injection du service de cache


    //private Map<Long, Pokemon> cachePokemon = new HashMap<>();
    //private List<Pokemon> pokemonsAVendre = new ArrayList<>();
    private static final String API_URL = "https://tyradex.vercel.app/api/v1/pokemon/";

    private static final int COST_PER_PERCENT = 250;

    //private static final int MIN_POKEMONS = 5;


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

    private Pokemon recupererPokemonDepuisAPI(Long id) throws IOException {
        URL url = new URL(API_URL + id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(connection.getInputStream());

            // Extraire les informations du Pokémon à partir de la réponse JSON
            String nom = rootNode.get("name").get("fr").asText();
            String description = "Description non disponible"; // À ajuster si disponible dans l'API

            // Extraire les types du Pokémon
            List<String> types = new ArrayList<>();
            JsonNode typesNode = rootNode.get("types");
            if (typesNode.isArray()) {
                for (JsonNode typeNode : typesNode) {
                    types.add(typeNode.get("name").asText());
                }
            }

            // Extraire les statistiques du Pokémon et les stocker dans une map
            Map<String, Integer> stats = new HashMap<>();
            stats.put("hp", rootNode.get("stats").get("hp").asInt());
            stats.put("atk", rootNode.get("stats").get("atk").asInt());
            stats.put("def", rootNode.get("stats").get("def").asInt());
            stats.put("speAtk", rootNode.get("stats").get("spe_atk").asInt());
            stats.put("speDef", rootNode.get("stats").get("spe_def").asInt());
            stats.put("vit", rootNode.get("stats").get("vit").asInt());

            // Calculer la valeur réelle du Pokémon
            int hp = stats.get("hp");
            int atk = stats.get("atk");
            int def = stats.get("def");
            int speAtk = stats.get("speAtk");
            int speDef = stats.get("speDef");
            int vit = stats.get("vit");

            // Calcul de la valeur réelle du Pokémon
            double valeurReelle = (hp * 1.5) + (atk * 1.2) + (def * 1.1) + (speAtk * 1.1) + (speDef * 1.1) + (vit * 1.0);

            // Créer un objet Pokemon avec la valeur réelle calculée
            Pokemon pokemon = new Pokemon(nom, description, (int) valeurReelle);
            pokemon.setTypes(types);  // Associer les types
            pokemon.setStats(stats);  // Associer les statistiques

            return pokemon;
        } else {
            throw new IOException("Erreur lors de la récupération du Pokémon");
        }
    }


    public Pokemon recupererPokemon(Long id) {
        // Vérifier si le Pokémon est dans le cache
        Pokemon pokemonCache = (Pokemon) cacheService.getFromCache(id);
        if (pokemonCache != null) {
            return pokemonCache; // Si oui, retourne-le depuis le cache
        }

        // Vérifier si le Pokémon est dans la base de données
        Pokemon pokemonDb = em.find(Pokemon.class, id);
        if (pokemonDb != null) {
            // Si oui, met-le dans le cache pour une prochaine fois
            cacheService.addToCache(id, pokemonDb);
            return pokemonDb;
        }

        // Si le Pokémon n'est pas dans le cache ni la base de données, récupère-le depuis l'API
        try {
            Pokemon pokemonApi = recupererPokemonDepuisAPI(id);
            // Sauvegarde le Pokémon dans la base de données
            em.persist(pokemonApi);
            em.flush(); // Assure-toi que l'objet est bien persistant
            // Mets le Pokémon dans le cache
            cacheService.addToCache(id, pokemonApi);
            return pokemonApi;
        } catch (IOException e) {
            // Gère les erreurs d'API ici
            e.printStackTrace();
        }
        return null;
    }

    // Méthode pour générer un Pokémon aléatoire
    @Transactional
    public Pokemon genererPokemonAleatoire() {
        Random random = new Random();
        Long randomId = (long) (random.nextInt(718) + 1); // Tirage entre 1 et 718
        return recupererPokemon(randomId);
    }


/*
    // Méthode pour générer les Pokémon au démarrage du serveur
    @Transactional
    public void genererPokemonsInitials() {
        // Vérifier si le nombre de Pokémon dans la base est inférieur à 5
        long count = em.createQuery("SELECT COUNT(p) FROM Pokemon p", Long.class).getSingleResult();
        LOGGER.info("Nombre de Pokémon dans la base : " + count);

        // Si moins de 5 Pokémon, générer des Pokémon supplémentaires
        if (count < 5) {
            for (int i = 0; i < (5 - count); i++) {
                Pokemon pokemon = genererPokemonAleatoire();
                if (pokemon != null) {
                    em.persist(pokemon);
                    LOGGER.info("Un Pokémon a été généré : " + pokemon.getNom());

                }
            }
        }
    }

*/

}