package menu;

import java.util.ArrayList;
import java.util.List;

import model.Ingredient;

public class PlatPrincipal implements ElementMenu {
    private String nom;
    private double prix;
    private String description;
    private List<Ingredient> ingredients = new ArrayList<>();
    
    public PlatPrincipal(String nom, double prix, String description) {
        this.nom = nom;
        this.prix = prix;
        this.description = description;
    }
    
    @Override
    public void ajouterIngredient(String nom, int quantite) {
        ingredients.add(new Ingredient(nom, quantite));
    }

    @Override
    public List<Ingredient> getIngredients() {
        return ingredients;
    }
    
    
    @Override
    public String getNom() {
        return nom;
    }
    
    @Override
    public double getPrix() {
        return prix;
    }
    
    @Override
    public String getDescription() {
        return description;
    }
    
    @Override
    public void afficherMenu() {
        System.out.println("Plat principal: " + nom + " - " + prix + "€");
        System.out.println("Description: " + description);
    }
    
    @Override
    public String toString() {
        return nom + " - " + prix + "€ (" + description + ")";
    }
}
