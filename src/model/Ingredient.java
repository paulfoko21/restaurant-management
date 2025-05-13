package model;

public class Ingredient {
    private String nom;
    private int quantite;
    
    public Ingredient
(String nom, int quantite) {
        this.nom = nom;
        this.quantite = quantite;
    }
    
    public String getNom() {
        return nom;
    }
    
    public int getQuantite() {
        return quantite;
    }
    
    public void ajouter(int quantite) {
        this.quantite += quantite;
    }
    
    public boolean retirer(int quantite) {
        if (this.quantite >= quantite) {
            this.quantite -= quantite;
            return true;
        }
        return false;
    }
}