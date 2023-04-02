package com.example.project_esalaf;
public class Produits {
    private int id ,quantite;
    private  Float prix;
    private  String nom,description;
    public Produits() {
        this.id = 0;
        this.quantite = 0;
        this.prix = null;
        this.nom = "";
        this.description = "";
    }
    public Produits(int id, int quantite, Float prix, String nom, String description) {
        this.id = id;
        this.quantite = quantite;
        this.prix = prix;
        this.nom = nom;
        this.description = description;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getQuantite() {
        return quantite;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    public Float getPrix() {
        return prix;
    }
    public void setPrix(Float prix) {
        this.prix = prix;
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
}
