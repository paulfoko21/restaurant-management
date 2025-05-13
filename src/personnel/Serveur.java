package personnel;

/**
 * Serveur responsable du service en salle.
 */
public class Serveur extends Personnel {

    public Serveur(int id, String nom) {
        super(id, nom, "Serveur");
    }

    @Override
    public void affecterTable(int numeroTable) {
        System.out.println("Serveur " + nom + " affecté à la table " + numeroTable);
    }
}
