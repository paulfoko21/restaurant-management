package commande;

import model.EtatTable;

/**
 * Représente une commande nouvellement créée.
 */
public class Nouvelle implements EtatCommande {
    @Override
    public void traiterCommande(Commande commande) {
        System.out.println("Commande #" + commande.getId() + " créée et envoyée en cuisine");
        commande.setEtat(new EnCours());
        commande.getTable().changerEtat(EtatTable.OCCUPEE);
    }
    
    @Override
    public String toString() {
        return "Nouvelle";
    }
}