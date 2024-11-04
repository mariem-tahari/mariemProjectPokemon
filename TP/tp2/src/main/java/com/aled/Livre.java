package com.aled;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "livres")
public class Livre {

    @Id
    private String titre;
    private String auteur;
    private int nbExemplaires;


    public Livre(String titre, String auteur, int nbExemplaires){
        this.titre = titre;
        this.auteur = auteur;
        this.nbExemplaires = nbExemplaires;
    }


    public Livre() {
    }


    public String getTitre(){
        return titre;
    }

    public String getAuteur(){
        return auteur;
    }

    public int getNbExemplaires(){
        return nbExemplaires;
    }


    public void setNbExemplaires(int nbExemplaires) {
        this.nbExemplaires = nbExemplaires;
    }
}
