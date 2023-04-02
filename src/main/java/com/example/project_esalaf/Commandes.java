package com.example.project_esalaf;


import java.sql.Date;

public class Commandes {
    private int id;
    private Date date_commande;
    private  Clients client;
    private  Produits produit;
    private int id_produit;
    private float montant_total;
    private String statut;

    public Commandes() {
        this.id = 0;
        this.date_commande = null;
        this.client = null;
        this.montant_total = 0;
        this.statut = "";
        this.produit = null;
    }

    public Commandes(int id, int id_produit,Date date_commande, Clients client, Produits produit,float montant_total, String statut) {
        this.id = id;
        this.id_produit = id_produit;
        this.date_commande = date_commande;
        this.client = client;
        this.produit = produit;
        this.montant_total = montant_total;
        this.statut = statut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(Date date_commande) {
        this.date_commande = date_commande;
    }

    public  Clients getClient() {

        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    public float getMontant_total() {
        return montant_total;
    }

    public void setMontant_total(float montant_total) {
        this.montant_total = montant_total;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public  Produits getProduit() {
        return produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public void setProduit(Produits produit) {
        this.produit = produit;
    }

    @Override
    public String toString() {
        return "Commandes{" +
                "id=" + id +
                ", date_commande='" + date_commande + '\'' +
                ", client=" + client +
                ", montant_total=" + montant_total +
                ", statut='" + statut + '\'' +
                '}';
    }

    public String getId_client() {
        return this.getId_client();
    }

    public void setId_client(int parseInt) {
    }

    public void setId_commande(int anInt) {
    }

    public int getId_commande() {
        return this.getId_commande();
    }


    public int getId_produit() {
        return  this.getId_produit();
    }
}
