package model;

import java.util.ArrayList;
import java.util.List;

import commande.Commande;
import personnel.Personnel;
import reservation.Reservation;
import table.Table;

public class Restaurant {
    private String nom;
    private Menu menu;
    private List<Table> tables;
    private List<Personnel> personnel;
    private List<Commande> commandes;
    private List<Reservation> reservations;
    private Stock stock;
    
    // Singleton
    private static Restaurant instance;
    
    private Restaurant(String nom) {
        this.nom = nom;
        this.menu = new Menu();
        this.tables = new ArrayList<>();
        this.personnel = new ArrayList<>();
        this.commandes = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.stock = new Stock();
    }
    
    public static Restaurant getInstance() {
        if (instance == null) {
            instance = new Restaurant("Restaurant (Singleton)");
        }
        return instance;
    }
    
    public String getNom() {
        return nom;
    }
    
    public Menu getMenu() {
        return menu;
    }
    
    public List<Table> getTables() {
        return tables;
    }
    
    public List<Personnel> getPersonnel() {
        return personnel;
    }
    
    public List<Commande> getCommandes() {
        return commandes;
    }
    
    public List<Reservation> getReservations() {
        return reservations;
    }
    
    public Stock getStock() {
        return stock;
    }
    
    public void ajouterTable(Table table) {
        tables.add(table);
    }
    
    public void ajouterPersonnel(Personnel personnel) {
        this.personnel.add(personnel);
    }
    
    public void ajouterCommande(Commande commande) {
        this.commandes.add(commande);
    }
    
    public void ajouterReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }
    
    public String toString() {
        return nom;
    }
}
