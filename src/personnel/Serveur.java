package personnel;

import commande.Commande;

/**
 * Serveur responsable du service en salle.
 */
public class Serveur extends Personnel {
    public Serveur(int id, String nom) {
        super(id, nom, "Serveur");
    }
    
    public void servirCommande(Commande commande) {
        System.out.println(getNom() + " sert la commande #" + commande.getId() + " à la table #" + commande.getTable().getId());
    }
}