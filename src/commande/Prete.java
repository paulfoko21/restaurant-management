package commande;

/**
 * Représente une commande prête à être servie.
 */
public class Prete implements EtatCommande {
    @Override
    public void traitementCommande(Commande commande) {
        System.out.println("Commande déjà prête. Aucun traitement supplémentaire.");
    }
}
