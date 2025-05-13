package personnel;

/**
 * Cuisinier responsable de la préparation des plats.
 */
public class Cuisinier extends Personnel {

    public Cuisinier(int id, String nom) {
        super(id, nom, "Cuisinier");
    }

    @Override
    public void affecterTable(int numeroTable) {
        System.out.println("Cuisinier " + nom + " n'est pas affecté aux tables.");
    }
}
