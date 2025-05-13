
package commande;

import menu.ElementMenu;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant une commande dans le restaurant.
 */
public class Commande {
    private int id;
    private List<ElementMenu> plats = new ArrayList<>();
    private EtatCommande etat;
    private double montant;

    public Commande(int id) {
        this.id = id;
        this.etat = new Nouvelle();
    }

    public void ajouterPlat(ElementMenu plat) {
        plats.add(plat);
        montant += plat.getPrix();
    }

    public void supprimerPlat(ElementMenu plat) {
        plats.remove(plat);
        montant -= plat.getPrix();
    }

    public void traitement() {
        etat.traitementCommande(this);
    }

    public void annuler() {
        plats.clear();
        montant = 0;
        etat = new Nouvelle();
    }

    public double calculerPrix() {
        return montant;
    }

    // Getters & Setters

    public int getId() {
        return id;
    }

    public List<ElementMenu> getPlats() {
        return plats;
    }

    public double getMontant() {
        return montant;
    }

    public EtatCommande getEtat() {
        return etat;
    }

    public void setEtat(EtatCommande etat) {
        this.etat = etat;
    }
}
