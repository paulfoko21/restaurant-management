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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import java.util.*;


public class Main {
    private static FacadeRestaurant facade = new FacadeRestaurant();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("====================== SYSTÈME DE GESTION DE RESTAURANT ======================");
        System.out.println("Pour chaque choisir une option saisir le numero qui est avant !");
        
        boolean continuer = true;
        while (continuer) {
            afficherMenuPrincipal();
            int choix = saisirEntier("Votre choix: ");
            
            switch (choix) {
                case 1:
                    Menu();
                    break;
                case 2:
                    Tables();
                    break;
                case 3:
                    Commandes();
                    break;
                case 4:
                    Reservations();
                    break;
                case 5:
                    Personnel();
                    break;
                case 6:
                    Stock();
                    break;
                case 7:
                    Resume();
                    break;
                case 0:
                    continuer = false;
                    System.out.println("Vous etes quitte !");
                    break;
                default:
                    System.out.println("Choix non valide.");
            }
        }
        
        scanner.close();
    }
    
    private static void afficherMenuPrincipal() {
        System.out.println("\nMENU PRINCIPAL:");
        System.out.println("1. Gerer le menu");
        System.out.println("2. Gerer les tables");
        System.out.println("3. Gerer les commandes");
        System.out.println("4. Gerer les réservations");
        System.out.println("5. Gerer le personnel");
        System.out.println("6. Gerer le stock");
        System.out.println("7. Voir le Resume");
        System.out.println("0. Quitter");
    }
    
    private static void Menu() {
        boolean retour = false;
        while (!retour) {
            System.out.println("\nGESTION DU MENU:");
            System.out.println("1. Afficher le menu");
            System.out.println("2. Ajouter un plat");
            System.out.println("3. Supprimer un plat");
            System.out.println("0. Retour au menu principal");
            
            int choix = saisirEntier("Votre choix: ");
            
            switch (choix) {
                case 1:
                    facade.afficherMenu();
                    break;
                case 2:
                	System.out.println("Type de plat (plat, dessert, boisson): ");
                    String type = scanner.nextLine();
                    
                    System.out.println("Nom du plat: ");
                    String nom = scanner.nextLine();
                    
                    double prix = saisirDouble("Prix: ");
                    
                    System.out.println("Description: ");
                    String description = scanner.nextLine();
                    
                    facade.ajouterPlat(type, nom, prix, description);
                    break;
                case 3:
                    facade.afficherMenu();
                    int index = saisirEntier("Numéro du plat à supprimer: ");
                    facade.supprimerPlat(index);
                    break;
                case 0:
                    retour = true;
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }
    
    private static void Tables() {
        boolean retour = false;
        while (!retour) {
            System.out.println("\nGESTION DES TABLES:");
            System.out.println("1. Afficher les tables");
            System.out.println("2. Changer l'état d'une table");
            System.out.println("0. Retour au menu principal");
            
            int choix = saisirEntier("Votre choix: ");
            
            switch (choix) {
                case 1:
                    facade.afficherTables();
                    break;
                case 2:
                    int tableId = saisirEntier("Numéro de la table: ");
                    System.out.println("Nouvel état (LIBRE, OCCUPEE, RESERVEE, EN_NETTOYAGE): ");
                    String etat = scanner.nextLine();
                    facade.changerEtatTable(tableId, etat);
                    break;
                case 0:
                    retour = true;
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }
    
    private static void Commandes() {
        boolean retour = false;
        while (!retour) {
            System.out.println("\nGESTION DES COMMANDES:");
            System.out.println("1. Afficher les commandes");
            System.out.println("2. Créer une commande");
            System.out.println("3. Ajouter un plat à une commande");
            System.out.println("4. Traiter une commande");
            System.out.println("5. Payer une commande");
            System.out.println("0. Retour au menu principal");
            
            int choix = saisirEntier("Votre choix: ");
            
            switch (choix) {
                case 1:
                    facade.afficherCommandes();
                    break;
                case 2:
                	facade.afficherTables();
                    int tableId = saisirEntier("\n\nNuméro de la table: ");
                    facade.creerCommande(tableId);
                    break;
                case 3:
                    facade.afficherCommandes();
                    int commandeId = saisirEntier("Numéro de la commande: ");
                    facade.afficherMenuDisponible();
                    int platIndex = saisirEntier("Numéro du plat à ajouter: ");
                    facade.ajouterPlatCommande(commandeId, platIndex);
                    break;
                case 4:
                    facade.afficherCommandes();
                    commandeId = saisirEntier("Numéro de la commande à traiter: ");
                    facade.traiterCommande(commandeId);
                    break;
                case 5:
                    facade.afficherCommandes();
                    commandeId = saisirEntier("Numéro de la commande à payer: ");
                    System.out.println("Méthode de paiement (especes, cb): ");
                    String methode = scanner.nextLine();
                    facade.payerCommande(commandeId, methode);
                    break;
                case 0:
                    retour = true;
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }
    
    private static void Reservations() {
        boolean retour = false;
        while (!retour) {
            System.out.println("\nGESTION DES RÉSERVATIONS:");
            System.out.println("1. Afficher les réservations");
            System.out.println("2. Créer une réservation");
            System.out.println("3. Modifier une réservation");
            System.out.println("4. Annuler une réservation");
            System.out.println("0. Retour au menu principal");
            
            int choix = saisirEntier("Votre choix: ");
            int reservationId = 0;
            switch (choix) {
                case 1:
                    facade.afficherReservations();
                    break;
                case 2:
                	facade.afficherTables_vides();
                    int tableId = saisirEntier("Numéro de la table: ");
                    System.out.println("Nom du client: ");
                    String nomClient = scanner.nextLine();
                    System.out.println("Date (format YYYY-MM-DD HH:MM): ");
                    String dateStr = scanner.nextLine();
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        LocalDateTime dateHeure = LocalDateTime.parse(dateStr, formatter);
                        facade.creerReservation(tableId, nomClient, dateHeure);
                    } catch (Exception e) {
                        System.out.println("Format de date invalide. Utilisez YYYY-MM-DD HH:MM");
                    }
                    break;
                case 3:
                    facade.afficherReservations();
                    reservationId = saisirEntier("Numéro de la réservation: ");
                    System.out.println("Date (format YYYY-MM-DD HH:MM): ");
                    String newdate = scanner.nextLine();
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        LocalDateTime date = LocalDateTime.parse(newdate, formatter);
                        facade.modifierReservation(reservationId, date);
                    } catch (Exception e) {
                        System.out.println("Format de date invalide. Utilisez YYYY-MM-DD HH:MM");
                    }
                case 4:
                    facade.afficherReservations();
                    reservationId = saisirEntier("Numéro de la réservation: ");
                    facade.annulerReservation(reservationId);
                case 0:
                    retour = true;
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }
    
    private static void Personnel() {
        boolean retour = false;
        while (!retour) {
            System.out.println("\nGESTION DU PERSONNEL:");
            System.out.println("1. Afficher le personnel");
            System.out.println("2. Ajouter un membre du personnel");
            System.out.println("3. Renvoyer un membre du personnel");
            System.out.println("4. Affecter un serveur à une table");
            System.out.println("0. Retour au menu principal");
            
            int choix = saisirEntier("Votre choix: ");
            
            switch (choix) {
                case 1:
                    facade.afficherPersonnel();
                    break;
                case 2:
                    System.out.println("Nom: ");
                    String nom = scanner.nextLine();
                    System.out.println("Rôle (serveur, cuisinier): ");
                    String role = scanner.nextLine();
                    facade.creerPersonnel(nom, role);
                    break;
                case 3:
                    facade.afficherPersonnel();
                    int personnelIdToRemove = saisirEntier("\nSaisi l\'ID du personnel a affecter : ");
                    facade.afficherTables();
//                    facade.renvoyer(personnelId);
                    break;
                case 4:
                    facade.afficherServeur();
                    int personnelId = saisirEntier("\nSaisi l\'ID du personnel a affecter : ");
                    facade.afficherTables();
                    int tableId = saisirEntier("Numéro de la table: ");
                    facade.affecterPersonnelTable(personnelId, tableId);
                    break;
                case 0:
                    retour = true;
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }
    
    private static void Stock() {
        boolean retour = false;
        while (!retour) {
            System.out.println("\nGESTION DU STOCK:");
            System.out.println("1. Afficher le stock");
            System.out.println("2. Ajouter un produit au stock");
            System.out.println("0. Retour au menu principal");
            
            int choix = saisirEntier("Votre choix: ");
            
            switch (choix) {
                case 1:
                    facade.afficherStock();
                    break;
                case 2:
                    System.out.println("Nom du produit: ");
                    String nomProduit = scanner.nextLine();
                    int quantite = saisirEntier("Quantité: ");
                    facade.ajouterProduitStock(nomProduit, quantite);
                    break;
                case 0:
                    retour = true;
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }
    
    private static void Resume() {
        boolean retour = false;
        while (!retour) {
            System.out.println("\nRAPPORTS:");
            System.out.println("1. Rapport des ventes");
            System.out.println("0. Retour au menu principal");
            
            int choix = saisirEntier("Votre choix: ");
            
            switch (choix) {
                case 1:
                    facade.genererRapportVentes();
                    break;
                case 0:
                    retour = true;
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }
    
    private static int saisirEntier(String message) {
        System.out.print(message);
        try {
            int valeur = Integer.parseInt(scanner.nextLine());
            return valeur;
        } catch (NumberFormatException e) {
            System.out.println("Veuillez entrer un nombre entier valide.");
            return saisirEntier(message);
        }
    }
    
    private static double saisirDouble(String message) {
        System.out.print(message);
        try {
            double valeur = Double.parseDouble(scanner.nextLine());
            return valeur;
        } catch (NumberFormatException e) {
            System.out.println("Veuillez entrer un nombre décimal valide.");
            return saisirDouble(message);
        }
    }
}