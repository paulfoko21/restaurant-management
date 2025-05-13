package commande;

import model.EtatTable;

/**
 * Représente une commande payée.
 */
public class Payee implements EtatCommande {
    @Override
    public void traiterCommande(Commande commande) {
        System.out.println("Commande #" + commande.getId() + " est déjà payée. Fin du processus.");
        commande.getTable().changerEtat(EtatTable.LIBRE);
    }
    
    @Override
    public String toString() {
        return "Payée";
    }
}