package commande;

/**
 * Représente une commande prête à être servie.
 */
public class Prete implements EtatCommande {
    @Override
    public void traiterCommande(Commande commande) {
        System.out.println("Commande #" + commande.getId() + " a été servie");
        commande.setEtat(new Payee());
    }
    
    @Override
    public String toString() {
        return "Prête";
    }
}