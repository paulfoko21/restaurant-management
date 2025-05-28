package commande;

import java.util.ArrayList;
import java.util.List;

import observer.Observer;

/**
 * Classe représentant le gestionnaire de cuisine qui observe les commandes.
 * Implémente le pattern Observer pour suivre les changements d'état.
 */
public class GestionnaireCuisine implements Observer {
    private static GestionnaireCuisine instance;
    
    private GestionnaireCuisine() {}
    
    public static GestionnaireCuisine getInstance() {
        if (instance == null) {
            instance = new GestionnaireCuisine();
        }
        return instance;
    }
    
    public void suivreCommande(Commande commande) {
        System.out.println("Cuisine: Suivi de la commande -> #" + commande.getId());
    }
    
    public void mettreAJour() {
        System.out.println("Cuisine: Mise à jour des informations");
    }
    
    @Override
    public void notifier(String message) {
        System.out.println("Notification cuisine: " + message);
    }
}
