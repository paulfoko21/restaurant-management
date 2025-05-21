package menu;

import java.util.List;

import model.Ingredient;

public interface ElementMenu {
    String getNom();
    double getPrix();
    String getDescription();
    void afficherMenu();
    void ajouterIngredient(String nom, int quantite);
    List<Ingredient> getIngredients();
}
