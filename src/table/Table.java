package table;

import model.EtatTable;

public class Table {
    private int id;
    private EtatTable etatTable;
    
    public Table(int id) {
        this.id = id;
        this.etatTable = EtatTable.LIBRE;
    }
    
    public int getId() {
        return id;
    }
    
    public EtatTable getEtatTable() {
        return etatTable;
    }
    
    public void changerEtat(EtatTable nouvelEtat) {
        this.etatTable = nouvelEtat;
    }
    
    public boolean estLibre() {
        return etatTable == EtatTable.LIBRE;
    }
    
    @Override
    public String toString() {
        return "Table #" + id + " (" + etatTable + ")";
    }
}
