import facade.FacadeRestaurant;
import menu.Boisson;
import menu.Dessert;
import menu.ElementMenu;
import menu.PlatPrincipal;
import model.Restaurant;
import personnel.Cuisinier;
import personnel.Serveur;
import reservation.Reservation;
import table.Table;
import commande.Commande;
import paiement.PaiementCB;
import paiement.PaiementEspece;

import java.util.*;

/**
 * Classe principale de simulation interactive du système de gestion de restaurant.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final FacadeRestaurant facade = new FacadeRestaurant();
    private static final Restaurant restaurant = Restaurant.getInstance();

    public static void main(String[] args) {
        System.out.println("=== SYSTÈME DE GESTION DE RESTAURANT ===");

        initialiserDonnees();

        boolean continuer = true;
        while (continuer) {
            afficherMenuPrincipal();
            System.out.print("Votre choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consomme le retour chariot

            switch (choix) {
                case 1 -> afficherMenuRestaurant();
                case 2 -> effectuerReservation();
                case 3 -> passerCommande();
                case 4 -> effectuerPaiement();
                case 5 -> {
                    System.out.println("Fermeture du système...");
                    continuer = false;
                }
                default -> System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
        System.out.println("=== FIN ===");
    }

    /** Initialise les données par défaut du restaurant */
    private static void initialiserDonnees() {
        restaurant.setNom("Le Gourmet Java");

        // Personnel
        facade.gererPersonnel(new Serveur(1, "Alice", "serveur"));
        facade.gererPersonnel(new Cuisinier(2, "Bob", "cuisinier"));

        // Stock
        facade.gererStock("Tomate", 50);
        facade.gererStock("Poulet", 30);

        // Menu
        restaurant.getMenu().ajouterPlat(new PlatPrincipal("Poulet Basquaise", "Poulet avec poivrons", 12.5));
        restaurant.getMenu().ajouterPlat(new Dessert("Tiramisu", "Dessert italien", 5.0));
        restaurant.getMenu().ajouterPlat(new Boisson("Jus de mangue", "Boisson fruitée", 3.0));

        // Tables
        restaurant.getTables().add(new Table(1));
        restaurant.getTables().add(new Table(2));
    }

    /** Affiche le menu principal */
    private static void afficherMenuPrincipal() {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("1. Afficher le menu du restaurant");
        System.out.println("2. Réserver une table");
        System.out.println("3. Passer une commande");
        System.out.println("4. Payer une commande");
        System.out.println("5. Quitter");
    }

    /** Affiche les plats du menu */
    private static void afficherMenuRestaurant() {
        System.out.println("\n--- MENU ---");
        System.out.println(restaurant.getMenu());
    }

    /** Simule une réservation d'une table */
    private static void effectuerReservation() {
        System.out.print("Nom du client : ");
        String nom = scanner.nextLine();

        Table tableDispo = restaurant.getTables().stream()
                .filter(t -> !t.estOccupee())
                .findFirst()
                .orElse(null);

        if (tableDispo == null) {
            System.out.println("Aucune table disponible !");
            return;
        }

        Reservation res = new Reservation(new Date(), nom, tableDispo);
        restaurant.getReservation().add(res);
        tableDispo.setOccupee(true);

        System.out.println("Réservation effectuée pour " + nom + " à la table " + tableDispo.getNumero());
    }

    /** Permet de passer une commande */
    private static void passerCommande() {
        System.out.print("Numéro de table : ");
        int num = scanner.nextInt();
        scanner.nextLine();

        Table table = restaurant.getTableParNumero(num);
        if (table == null || !table.estOccupee()) {
            System.out.println("Table non réservée ou inexistante !");
            return;
        }

        ArrayList<ElementMenu> platsChoisis = new ArrayList<>();
        boolean ajouter = true;

        while (ajouter) {
            System.out.println("Sélectionnez un plat (nom exact) ou tapez 'fin' pour terminer :");
            afficherMenuRestaurant();
            String nomPlat = scanner.nextLine();
            if (nomPlat.equalsIgnoreCase("fin")) break;

            ElementMenu plat = restaurant.getMenu().trouverParNom(nomPlat);
            if (plat != null) {
                platsChoisis.add(plat);
                System.out.println(plat.getNom() + " ajouté à la commande.");
            } else {
                System.out.println("Plat introuvable.");
            }
        }

        if (!platsChoisis.isEmpty()) {
            Commande commande = facade.creerCommande(table, platsChoisis);
            facade.creerSuiviCommande(commande);
            System.out.println("Commande enregistrée :\n" + commande);
        } else {
            System.out.println("Aucune commande enregistrée.");
        }
    }

    /** Permet d’effectuer un paiement */
    private static void effectuerPaiement() {
        System.out.print("Numéro de table : ");
        int num = scanner.nextInt();
        scanner.nextLine();

        Commande commande = restaurant.getCommandeParTable(num);
        if (commande == null) {
            System.out.println("Aucune commande trouvée pour cette table.");
            return;
        }

        System.out.printf("Montant à payer : %.2f €\n", commande.calculerPrix());
        System.out.print("Méthode de paiement (1. CB | 2. Espèces) : ");
        int mode = scanner.nextInt();
        scanner.nextLine();

        if (mode == 1) {
            new PaiementCB().payer(commande.calculerPrix());
        } else if (mode == 2) {
            new PaiementEspece().payer(commande.calculerPrix());
        } else {
            System.out.println("Méthode invalide.");
        }

        commande.setPayee(true);
        System.out.println("Commande payée. Merci !");
    }
}
