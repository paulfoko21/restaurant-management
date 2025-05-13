package personnel;

/**
 * Classe abstraite représentant un membre du personnel.
 */
public abstract class Personnel {
    protected int id;
    protected String nom;
    protected String role;

    public Personnel(int id, String nom, String role) {
        this.id = id;
        this.nom = nom;
        this.role = role;
    }

    public abstract void affecterTable(int numeroTable);

    // Getters & Setters

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getRole() {
        return role;
    }
}
