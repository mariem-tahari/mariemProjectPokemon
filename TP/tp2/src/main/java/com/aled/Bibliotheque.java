package com.aled;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bibliotheque {
    private static Bibliotheque instance = null;
    private Map<String, Livre> livres;

    private Bibliotheque() {
        livres = new HashMap<>();
    }

    public static Bibliotheque getInstance() {
        if (instance == null) {
            instance = new Bibliotheque();
        }
        return instance;
    }

    public void addLivre(String titre, String auteur, int nbExemplaires) {
        Livre livre = new Livre(titre, auteur, nbExemplaires);
        livres.put(titre, livre);
    }

    public Livre getLivre(String titre) {
        return livres.get(titre);
    }

    public List<Livre> getTousLesLivres() {
        return new ArrayList<>(livres.values());
    }

    public void removeLivre(String titre) {
        livres.remove(titre);
    }

    public void mettreAJourNbExemplaires(String titre, int nbExemplaires) {
        Livre livre = livres.get(titre);
        if (livre != null) {
            livre.setNbExemplaires(nbExemplaires);
        }
    }
}
