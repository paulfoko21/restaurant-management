package commande;

/**
 * Représente une commande payée.
 */
public class Payee implements EtatCommande {
    @Override
    public void traitementCommande(Commande commande) {
        commande.setEtat(new Prete());
        System.out.println("Commande passée à l'état : Prête");
    }
}
