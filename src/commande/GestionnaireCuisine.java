package commande;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant le gestionnaire de cuisine qui observe les commandes.
 * Implémente le pattern Observer pour suivre les changements d'état.
 */
public class GestionnaireCuisine implements Observer {

    private List<Commande> suiviCommandes;

    public GestionnaireCuisine() {
        this.suiviCommandes = new ArrayList<>();
    }

    /**
     * Ajoute une commande au suivi de la cuisine.
     * @param commande la commande à suivre
     */
    public void ajouterCommande(Commande commande) {
        suiviCommandes.add(commande);
        System.out.println("Commande ID " + commande.getId() + " ajoutée au suivi de la cuisine.");
    }

    /**
     * Méthode appelée lorsqu'une commande change d'état.
     * @param commande la commande mise à jour
     */
    @Override
    public void update(Commande commande) {
        System.out.println("Mise à jour de la commande ID " + commande.getId() +
                " - État actuel : " + commande.getEtat().getClass().getSimpleName());
        // On pourrait ici déclencher des actions selon l’état (ex: notifier cuisinier, imprimer ticket, etc.)
    }

    public List<Commande> getSuiviCommandes() {
        return suiviCommandes;
    }
}
