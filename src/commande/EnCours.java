package commande;

/**
 * Représente une commande en cours de préparation.
 */
public class EnCours implements EtatCommande {
    @Override
    public void traiterCommande(Commande commande) {
        System.out.println("Commande #" + commande.getId() + " est prête à être servie");
        commande.setEtat(new Prete());
    }
    
    @Override
    public String toString() {
        return "En cours";
    }
}