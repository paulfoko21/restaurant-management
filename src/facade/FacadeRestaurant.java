package facade;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import commande.Commande;
import commande.GestionnaireCuisine;
import commande.Payee;
import menu.Boisson;
import menu.Dessert;
import menu.ElementMenu;
import menu.ElementMenuFactory;
import menu.PlatPrincipal;
import model.EtatTable;
import model.Ingredient;
import model.Restaurant;
import paiement.PaiementCB;
import paiement.PaiementEspece;
import paiement.PaiementStrategy;
import personnel.Cuisinier;
import personnel.Personnel;
import personnel.Serveur;
import reservation.Reservation;
import table.Table;

public class FacadeRestaurant {
    private Restaurant restaurant;
    private GestionnaireCuisine gestionnaireCuisine;
    private int idCommandeActuel = 1;
    private int idReservationActuel = 1;
    
    public FacadeRestaurant() {
        this.restaurant = Restaurant.getInstance();
        this.gestionnaireCuisine = GestionnaireCuisine.getInstance();
        
        // Inscrire le gestionnaire comme observateur du stock
        restaurant.getStock().ajouterObservateur(gestionnaireCuisine);
        
        // Initialiser avec des données de démo
        initialiserDonneesDemo();
    }
    
    private void initialiserDonneesDemo() {
        // Ajouter des tables
        for (int i = 1; i <= 10; i++) {
            restaurant.ajouterTable(new Table(i));
        }

        // Ajouter du personnel
        restaurant.ajouterPersonnel(new Serveur(1, "Jean"));
        restaurant.ajouterPersonnel(new Serveur(2, "Marie"));
        restaurant.ajouterPersonnel(new Cuisinier(3, "Pierre"));
        restaurant.ajouterPersonnel(new Cuisinier(4, "Sophie"));

        // Ajouter des plats au menu (avec ingrédients)
        PlatPrincipal steakFrites = (PlatPrincipal) ElementMenuFactory.creerPlatPrincipal("Steak Frites", 15.90, "Steak de bœuf avec frites maison");
        steakFrites.ajouterIngredient("Steak", 1);
        steakFrites.ajouterIngredient("Pommes de terre", 2);
        restaurant.getMenu().ajouterPlat(steakFrites);

        PlatPrincipal saumon = (PlatPrincipal) ElementMenuFactory.creerPlatPrincipal("Saumon Grillé", 18.50, "Filet de saumon grillé aux herbes");
        saumon.ajouterIngredient("Saumon", 1);
        restaurant.getMenu().ajouterPlat(saumon);

        Dessert tartePommes = (Dessert) ElementMenuFactory.creerDessert("Tarte aux pommes", 6.50, "Tarte aux pommes maison");
        tartePommes.ajouterIngredient("Pommes", 3);
        restaurant.getMenu().ajouterPlat(tartePommes);

        Dessert mousseChoco = (Dessert) ElementMenuFactory.creerDessert("Mousse au chocolat", 5.80, "Mousse au chocolat noir");
        mousseChoco.ajouterIngredient("Chocolat", 2);
        restaurant.getMenu().ajouterPlat(mousseChoco);

        Boisson eau = (Boisson) ElementMenuFactory.creerBoisson("Eau minérale", 3.00, "Bouteille d'eau minérale 50cl");
        eau.ajouterIngredient("Eau minérale", 1);
        restaurant.getMenu().ajouterPlat(eau);

        Boisson vin = (Boisson) ElementMenuFactory.creerBoisson("Vin rouge", 5.50, "Verre de vin rouge");
        vin.ajouterIngredient("Vin rouge", 1);
        restaurant.getMenu().ajouterPlat(vin);

        // Ajouter des produits au stock
        restaurant.getStock().ajouterProduit("Steak", 2);
        restaurant.getStock().ajouterProduit("Pommes de terre", 5);
        restaurant.getStock().ajouterProduit("Saumon", 1);
        restaurant.getStock().ajouterProduit("Pommes", 3);
        restaurant.getStock().ajouterProduit("Chocolat", 1);
        restaurant.getStock().ajouterProduit("Eau minérale", 4);
        restaurant.getStock().ajouterProduit("Vin rouge", 2);
    }

    
    // Méthodes pour le menu
    public void afficherMenu() {
        System.out.println(restaurant.getMenu());
    }
    
    public void afficherMenuDisponible() {
        System.out.println("MENU DISPONIBLE (stock suffisant uniquement) :");

        List<ElementMenu> platsDisponibles = new ArrayList<>();

        for (ElementMenu plat : restaurant.getMenu().getPlats()) {
            boolean disponible = true;

            for (Ingredient ingr : plat.getIngredients()) {
                int enStock = restaurant.getStock().getQuantite(ingr.getNom());
                if (enStock < ingr.getQuantite()) {
                    disponible = false;
                    break;
                }
            }

            if (disponible) {
                platsDisponibles.add(plat);
            }
        }

        if (platsDisponibles.isEmpty()) {
            System.out.println("Aucun plat disponible actuellement.");
        } else {
            for (ElementMenu plat : platsDisponibles) {
                plat.afficherMenu();
            }
        }
    }

    
    public void ajouterPlat(String type, String nom, double prix, String description) {
        ElementMenu nouveauPlat = null;
        
        switch (type.toLowerCase()) {
            case "plat":
                nouveauPlat = ElementMenuFactory.creerPlatPrincipal(nom, prix, description);
                break;
            case "dessert":
                nouveauPlat = ElementMenuFactory.creerDessert(nom, prix, description);
                break;
            case "boisson":
                nouveauPlat = ElementMenuFactory.creerBoisson(nom, prix, description);
                break;
            default:
                System.out.println("Type de plat non reconnu");
                return;
        }
        
        restaurant.getMenu().ajouterPlat(nouveauPlat);
        System.out.println("Plat ajouté: " + nouveauPlat);
    }
    
    public void supprimerPlat(int index) {
        List<ElementMenu> plats = restaurant.getMenu().getPlats();
        if (index >= 1 && index <= plats.size()) {
            ElementMenu plat = plats.get(index - 1);
            restaurant.getMenu().supprimerPlat(plat);
            System.out.println("Plat supprimé: " + plat);
        } else {
            System.out.println("Index invalide");
        }
    }
    
    // Méthodes pour les tables
    public void afficherTables() {
        System.out.println("\nTABLES DU RESTAURANT:");
        for (Table table : restaurant.getTables()) {
            System.out.println("\t" + table);
        }
    }
    
    public void afficherTables_vides() {
        System.out.println("\nTABLES DU RESTAURANT:");
        for (Table table : restaurant.getTables()) {
        	if(table.getEtatTable() == EtatTable.LIBRE)
            System.out.println("\t" + table);
        }
    }
    
    public void changerEtatTable(int id, String etat) {
        for (Table table : restaurant.getTables()) {
            if (table.getId() == id) {
                try {
                    EtatTable nouvelEtat = EtatTable.valueOf(etat.toUpperCase());
                    table.changerEtat(nouvelEtat);
                    System.out.println("État de la table #" + id + " changé à " + nouvelEtat);
                } catch (IllegalArgumentException e) {
                    System.out.println("État non valide. Utilisez: LIBRE, OCCUPEE, RESERVEE, EN_NETTOYAGE");
                }
                return;
            }
        }
        System.out.println("Table #" + id + " non trouvée");
    }
    
    // Méthodes pour les commandes
    public void creerCommande(int tableId) {
        Table table = null;
        for (Table t : restaurant.getTables()) {
            if (t.getId() == tableId) {
                table = t;
                break;
            }
        }
        
        if (table == null) {
            System.out.println("Table #" + tableId + " non trouvée");
            return;
        }
        
        if (!table.estLibre() && (table.getEtatTable()!= EtatTable.RESERVEE)) {
            System.out.println("Table #" + tableId + " n'est pas disponible");
            return;
        }
        
        Commande nouvelleCommande = new Commande(idCommandeActuel++, table);
        restaurant.ajouterCommande(nouvelleCommande);
        System.out.println("Nouvelle commande créée: " + nouvelleCommande.getId() + " pour la table #" + tableId);
    }
    
    public void afficherCommandes() {
        System.out.println("\nCOMMANDES DU RESTAURANT:");
        for (Commande commande : restaurant.getCommandes()) {
            System.out.println(commande);
        }
    }
    
    public void ajouterPlatCommande(int commandeId, int platIndex) {
        Commande commande = trouverCommande(commandeId);
        if (commande == null) {
            System.out.println("Commande #" + commandeId + " non trouvée");
            return;
        }
        
        List<ElementMenu> plats = restaurant.getMenu().getPlats();
        if (platIndex < 1 || platIndex > plats.size()) {
            System.out.println("Index de plat invalide");
            return;
        }
        
        ElementMenu plat = plats.get(platIndex - 1);
        commande.ajouterPlat(plat);
        System.out.println("Plat ajouté à la commande #" + commandeId + ": \n\t" + plat);
    }
    
    public void traiterCommande(int commandeId) {
        Commande commande = trouverCommande(commandeId);
        if (commande == null) {
            System.out.println("Commande #" + commandeId + " non trouvée");
            return;
        }
        
        commande.getEtat().traiterCommande(commande);
    }
    
    public void payerCommande(int commandeId, String methode) {
        Commande commande = trouverCommande(commandeId);
        if (commande == null) {
            System.out.println("Commande #" + commandeId + " non trouvée");
            return;
        }
        
        PaiementStrategy strategie = null;
        switch (methode.toLowerCase()) {
            case "especes":
                strategie = new PaiementEspece();
                break;
            case "cb":
                strategie = new PaiementCB();
                break;
            default:
                System.out.println("Méthode de paiement non reconnue. Utilisez: especes, cb");
                return;
        }
        
        strategie.payer(commande.getMontant());
        commande.setEtat(new Payee());
        commande.getTable().changerEtat(EtatTable.LIBRE);
    }
    
    private Commande trouverCommande(int commandeId) {
        for (Commande commande : restaurant.getCommandes()) {
            if (commande.getId() == commandeId) {
                return commande;
            }
        }
        return null;
    }
    
    // Méthodes pour les réservations
    public void creerReservation(int tableId, String nomClient, LocalDateTime dateHeure) {
        Table table = null;
        for (Table t : restaurant.getTables()) {
            if (t.getId() == tableId) {
                table = t;
                break;
            }
        }
        
        if (table == null) {
            System.out.println("Table #" + tableId + " non trouvée");
            return;
        }
        
        if (!table.estLibre()) {
            System.out.println("Table #" + tableId + " n'est pas disponible");
            return;
        }
        
        Reservation nouvelleReservation = new Reservation(idReservationActuel, dateHeure, nomClient, table);
        restaurant.ajouterReservation(nouvelleReservation);
        System.out.println("Nouvelle réservation créée: \n\t" + nouvelleReservation);
    }
    
    public void afficherReservations() {
        System.out.println("\nRÉSERVATIONS DU RESTAURANT:");
        for (Reservation reservation : restaurant.getReservations()) {
            System.out.println(reservation);
        }
    }
    
    public void modifierReservation(int reservationId, LocalDateTime newDatetime) {
    	Reservation reservation = trouverReservation(reservationId);
    	reservation.modifierReservation(newDatetime);
    }
    
    public void annulerReservation(int reservationId) {
    	Reservation reservation = trouverReservation(reservationId);
    	reservation.annulerReservation();
    }
    
    private Reservation trouverReservation(int reservationId) {
        for (Reservation reservation : restaurant.getReservations()) {
            if (reservation.getId() == reservationId) {
                return reservation;
            }
        }
        return null;
    }
    
    // ici le code pour le stock
    public void afficherStock() {
        System.out.println("\nSTOCK DU RESTAURANT:");
        for (String produit : restaurant.getStock().getProduits().keySet()) {
            System.out.println("\t" + produit + ": " + restaurant.getStock().getQuantite(produit) + " unités");
        }
    }
    
    public void ajouterProduitStock(String nomProduit, int quantite) {
        restaurant.getStock().ajouterProduit(nomProduit, quantite);
        System.out.println("\t" + quantite + " unités de " + nomProduit + " ajoutées au stock");
    }
    
    // Méthodes pour le personnel
    public void afficherPersonnel() {
        System.out.println("\nPERSONNEL DU RESTAURANT:");
        for (Personnel p : restaurant.getPersonnel()) {
            System.out.println(p);
        }
    }
    
    public void afficherServeur() {
        System.out.println("\nPERSONNEL(Serveur) DU RESTAURANT:");
        for (Personnel p : restaurant.getPersonnel()) {
        	if (p.getRole() == "Serveur") {
                System.out.println(p);
			}
        }
    }
    
    public void creerPersonnel(String nom, String role) {
        Personnel nouveauPersonnel = null;
        int id = restaurant.getPersonnel().size() + 1;
        
        switch (role.toLowerCase()) {
            case "serveur":
                nouveauPersonnel = new Serveur(id, nom);
                break;
            case "cuisinier":
                nouveauPersonnel = new Cuisinier(id, nom);
                break;
            default:
                nouveauPersonnel = new Personnel(id, nom, role);
                break;
        }
        
        restaurant.ajouterPersonnel(nouveauPersonnel);
        System.out.println("Nouveau personnel ajouté: \n\t" + nouveauPersonnel);
    }
    
    public void affecterPersonnelTable(int personnelId, int tableId) {
        Personnel personnel = null;
        for (Personnel p : restaurant.getPersonnel()) {
            if (p.getId() == personnelId) {
                personnel = p;
                break;
            }
        }
        
        if (personnel == null) {
            System.out.println("Personnel #" + personnelId + " non trouvé");
            return;
        }
        
        Table table = null;
        for (Table t : restaurant.getTables()) {
            if (t.getId() == tableId) {
                table = t;
                break;
            }
        }
        
        if (table == null) {
            System.out.println("Table #" + tableId + " non trouvée");
            return;
        }
        
        personnel.affecterTable(table);
    }
    
    public void genererRapportVentes() {
        System.out.println("\n========= RAPPORT DE VENTES =========");
        
        double totalVentes = 0;
        Map<String, Integer> compteurPlats = new HashMap<>();
        Map<String, Integer> consommationStock = new HashMap<>();

        // Traitement des commandes payées
        for (Commande commande : restaurant.getCommandes()) {
            if (commande.getEtat() instanceof Payee) {
                totalVentes += commande.getMontant();

                commande.getPlats().forEach(plat -> {
                    // Comptage des plats
                    compteurPlats.merge(plat.getNom(), 1, Integer::sum);

                    // Simulation de la consommation de stock (ex : 1 plat = 1 unité/ingrédient)
                    // À ajuster si besoin selon recettes réelles
                    if (plat.getNom().toLowerCase().contains("steak")) {
                        consommationStock.merge("Steak", 1, Integer::sum);
                        consommationStock.merge("Pommes de terre", 1, Integer::sum);
                    } else if (plat.getNom().toLowerCase().contains("saumon")) {
                        consommationStock.merge("Saumon", 1, Integer::sum);
                    } else if (plat.getNom().toLowerCase().contains("tarte")) {
                        consommationStock.merge("Pommes", 1, Integer::sum);
                    } else if (plat.getNom().toLowerCase().contains("mousse")) {
                        consommationStock.merge("Chocolat", 1, Integer::sum);
                    } else if (plat.getNom().toLowerCase().contains("eau")) {
                        consommationStock.merge("Eau minérale", 1, Integer::sum);
                    } else if (plat.getNom().toLowerCase().contains("vin")) {
                        consommationStock.merge("Vin rouge", 1, Integer::sum);
                    }
                });
            }
        }

        // Affichage du total des ventes
        System.out.printf("Total des ventes : %.2f €%n", totalVentes);

        // Classement des plats les plus vendus
        System.out.println("\n\t Plats les plus vendus :");
        compteurPlats.entrySet().stream()
            .sorted((a, b) -> b.getValue() - a.getValue())
            .forEach(entry -> System.out.println("- " + entry.getKey() + " : " + entry.getValue() + " ventes"));

        // Flux de stock utilisé
        System.out.println("\n\t Flux de consommation du stock :");
        consommationStock.forEach((produit, quantite) ->
            System.out.println("- " + produit + " consommé : " + quantite + " unités"));
    }

}
