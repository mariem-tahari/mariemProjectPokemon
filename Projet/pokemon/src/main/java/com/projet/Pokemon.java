package com.projet;


import jakarta.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;
    private int miseAPrix;
    private int valeurReelle;

    @ElementCollection
    @CollectionTable(name = "pokemon_encheres", joinColumns = @JoinColumn(name = "pokemon_id"))
    @MapKeyColumn(name = "utilisateur_id")
    @Column(name = "montant_enchere")
    private Map<Long, Integer> historique_encheres = new HashMap<>(); // On stocke id_utilisateur et montant

    public Pokemon() {}

    public Pokemon(String nom, String description, int valeurReelle) {
        this.nom = nom;
        this.description = description;
        this.valeurReelle = valeurReelle;
        this.miseAPrix = (int) (valeurReelle * (0.6 + Math.random() * 0.8));
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMiseAPrix() {
        return miseAPrix;
    }

    public void setMiseAPrix(int miseAPrix) {
        this.miseAPrix = miseAPrix;
    }

    public int getValeurReelle() {
        return valeurReelle;
    }

    public void setValeurReelle(int valeurReelle) {
        this.valeurReelle = valeurReelle;
    }

    public Map<Long, Integer> getHistorique_encheres() {
        return historique_encheres;
    }

    public void ajouterEnchere(Long utilisateurId, int montant) {
        historique_encheres.put(utilisateurId, montant);
    }

    public void supprimerEnchere(Long utilisateurId) {
        historique_encheres.remove(utilisateurId);
    }

    public Integer getMontantEnchere(Long utilisateurId) {
        return historique_encheres.get(utilisateurId);
    }
}