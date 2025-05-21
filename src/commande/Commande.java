package commande;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import menu.ElementMenu;
import model.EtatTable;
import table.Table;

public class Commande {
    private int id;
    private List<ElementMenu> plats;
    private EtatCommande etat;
    private double montant;
    private Date date;
    private Table table;
    
    public Commande(int id, Table table) {
        this.id = id;
        this.plats = new ArrayList<>();
        this.etat = new Nouvelle();
        this.montant = 0;
        this.date = new Date();
        this.table = table;
        table.changerEtat(EtatTable.OCCUPEE);
    }
    
    public int getId() {
        return id;
    }
    
    public List<ElementMenu> getPlats() {
        return plats;
    }
    
    public EtatCommande getEtat() {
        return etat;
    }
    
    public void setEtat(EtatCommande etat) {
        this.etat = etat;
    }
    
    public double getMontant() {
        return montant;
    }
    
    public Date getDate() {
        return date;
    }
    
    public Table getTable() {
        return table;
    }
    
    public void ajouterPlat(ElementMenu plat) {
        plats.add(plat);
        montant += plat.getPrix();
    }
    
    public void modifierCommande(List<ElementMenu> nouveauxPlats) {
        if (this.etat instanceof Nouvelle) {
            this.plats = nouveauxPlats;
            recalculerMontant();
        } else {
            System.out.println("Impossible de modifier la commande dans l'état actuel.");
        }
    }
    
    public void annuler() {
        if (!(this.etat instanceof Payee)) {
            System.out.println("Commande #" + id + " annulée");
            table.changerEtat(EtatTable.LIBRE);
        } else {
            System.out.println("Impossible d'annuler une commande déjà payée");
        }
    }
    
    public double calculerPrix() {
        double total = 0;
        for (ElementMenu plat : plats) {
            total += plat.getPrix();
        }
        return total;
    }
    
    private void recalculerMontant() {
        this.montant = 0;
        for (ElementMenu plat : plats) {
            this.montant += plat.getPrix();
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Commande #" + id + " (" + etat.getClass().getSimpleName() + ")\n");
        sb.append("Table: ").append(table.getId()).append("\n");
        sb.append("Plats:\n");
        for (ElementMenu plat : plats) {
            sb.append("- ").append(plat.toString()).append("\n");
        }
        sb.append("Montant total: ").append(montant).append("€\n");
        sb.append("Date: ").append(date).append("\n");
        return sb.toString();
    }
}