package commande;

import menu.ElementMenu;
import model.Ingredient;
import model.Restaurant;
import model.Stock;

/**
 * Représente une commande en cours de préparation.
 */
public class EnCours implements EtatCommande {

    @Override
    public void traiterCommande(Commande commande) {
        System.out.println("Commande #" + commande.getId() + " est prête à être servie");

        // Décrémente le stock pour chaque plat commandé
        Restaurant restaurant = Restaurant.getInstance();
        Stock stock = restaurant.getStock();

        for (ElementMenu plat : commande.getPlats()) {
            if (plat instanceof menu.PlatPrincipal || plat instanceof menu.Dessert || plat instanceof menu.Boisson) {
                for (Ingredient ingredient : plat.getIngredients()) {
                    boolean ok = stock.retirerProduit(ingredient.getNom(), ingredient.getQuantite());
                    if (!ok) {
                        System.out.println("Le stock est insuffisant pour " + ingredient.getNom() +
                                           " (besoin: " + ingredient.getQuantite() +
                                           ", disponible: " + stock.getQuantite(ingredient.getNom()) + ")");
                    }
                }
            }
        }

        // Passage à l'état "Prête"
        commande.setEtat(new Prete());
    }

    @Override
    public String toString() {
        return "En cours";
    }
}
