package commande;

/**
 * Interface représentant l'état d'une commande (Pattern State).
 */
public interface EtatCommande {
    void traiterCommande(Commande commande);
}
