package personnel;

import menu.ElementMenu;

/**
 * Cuisinier responsable de la préparation des plats.
 */
public class Cuisinier extends Personnel {
    public Cuisinier(int id, String nom) {
        super(id, nom, "Cuisinier");
    }
    
    public void preparerPlat(ElementMenu plat) {
        System.out.println(getNom() + " prépare: " + plat.getNom());
    }
}