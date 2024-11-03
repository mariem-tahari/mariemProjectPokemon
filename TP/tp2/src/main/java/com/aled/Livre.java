package com.aled;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


public class Livre {

    private String titre;
    private String auteur;
    private int nbExemplaires;


    public Livre(String titre, String auteur, int nbExemplaires){
        this.titre = titre;
        this.auteur = auteur;
        this.nbExemplaires = nbExemplaires;
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
