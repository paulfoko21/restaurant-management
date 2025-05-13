package commande;

/**
 * Représente une commande en cours de préparation.
 */
public class EnCours implements EtatCommande {
    @Override
    public void traitementCommande(Commande commande) {
        commande.setEtat(new Payee());
        System.out.println("Commande passée à l'état : Payée");
    }
}
