package personnel;
import table.Table;

/**
 * Classe abstraite représentant un membre du personnel.
 */
public class Personnel {
    private int id;
    private String nom;
    private String role;
    private Table tableAssignee;
    
    public Personnel(int id, String nom, String role) {
        this.id = id;
        this.nom = nom;
        this.role = role;
    }
    
    public int getId() {
        return id;
    }
    
    public String getNom() {
        return nom;
    }
    
    public String getRole() {
        return role;
    }
    
    public Table getTableAssignee() {
        return tableAssignee;
    }
    
    public void affecterTable(Table table) {
        this.tableAssignee = table;
        System.out.println(nom + " a été affecté à la table #" + table.getId());
    }
    
    @Override
    public String toString() {
    	if(tableAssignee != null) {
            return "\t#" +id + " "+ nom + " (" + role + ")\t--->\tTable #"+ tableAssignee.getId();
    	}
        return "\t#" +id + " "+ nom + " (" + role + ")";
    }
}