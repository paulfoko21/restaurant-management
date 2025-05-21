package reservation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import model.EtatTable;
import table.Table;

public class Reservation {
    private int id;
    private LocalDateTime dateHeure;
    private String nomClient;
    private Table table;
    
    public Reservation(int id, LocalDateTime dateHeure, String nomClient, Table table) {
        this.id = id;
        this.dateHeure = dateHeure;
        this.nomClient = nomClient;
        this.table = table;
        table.changerEtat(EtatTable.RESERVEE);
    }
    
    public int getId() {
		return id;
	}

	public LocalDateTime getDateHeure() {
        return dateHeure;
    }
    
    public String getNomClient() {
        return nomClient;
    }
    
    public Table getTable() {
        return table;
    }
    
    public void annulerReservation() {
        table.changerEtat(EtatTable.LIBRE);
        System.out.println("Réservation annulée pour " + nomClient);
    }
    
    public void modifierReservation(LocalDateTime nouvelleDateHeure) {
        this.dateHeure = nouvelleDateHeure;
        System.out.println("Réservation modifiée pour " + nomClient + " à " + 
                           dateHeure.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
    }
    
    @Override
    public String toString() {
        return "Réservation pour " + nomClient + " le " + 
               dateHeure.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + 
               " à la table " + table.getId();
    }
}
