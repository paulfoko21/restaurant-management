package commande;

/**
 * Représente une commande nouvellement créée.
 */
public class Nouvelle implements EtatCommande {
    @Override
    public void traitementCommande(Commande commande) {
        commande.setEtat(new EnCours());
        System.out.println("Commande passée à l'état : En cours");
    }
}