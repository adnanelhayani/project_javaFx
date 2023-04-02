package com.example.project_esalaf;




import java.sql.Date;

public class Credits {
    private int id;
    private float montant_total;
    private float montant_restant;
    private Date date_echeance;
    private String statut;
    private int id_commande;
    private Clients client;

    public Credits() {
        this.id = 0;
        this.montant_total = 0.0f;
        this.montant_restant = 0.0f;
        this.date_echeance = null;
        this.statut = null;
        this.id_commande = 0;
        this.client = null;
    }


    public Credits(int id, float montant_total, float montant_restant, Date date_echeance, String statut, int id_commande, Clients client) {
        this.id = id;
        this.montant_total = montant_total;
        this.montant_restant = montant_restant;
        this.date_echeance = date_echeance;
        this.statut = statut;
        this.id_commande = id_commande;
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getMontant_total() {
        return montant_total;
    }

    public void setMontant_total(float montant_total) {
        this.montant_total = montant_total;
    }

    public float getMontant_restant() {
        return montant_restant;
    }

    public void setMontant_restant(float montant_restant) {
        this.montant_restant = montant_restant;
    }

    public Date getDate_echeance() {
        return date_echeance;
    }

    public void setDate_echeance(Date date_echeance) {
        this.date_echeance = date_echeance;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public int getID_commande() {
        return id_commande;
    }

    public void setID_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    

    @Override
    public String toString() {
        return "Credits{" +
                "id=" + id +
                ", montant_total=" + montant_total +
                ", montant_restant=" + montant_restant +
                ", date_echeance='" + date_echeance + '\'' +
                ", statut='" + statut + '\'' +
                ", id_commande=" + id_commande +
                ", client=" + client +
                '}';
    }


    public void setCommandes(Commandes commandes) {
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client=client;
    }
}

