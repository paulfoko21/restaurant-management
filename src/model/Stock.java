package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import observer.Observer;

public class Stock {
    private Map<String, Integer> produit;
    
    public Stock() {
        this.produit = new HashMap<>();
    }
    
    
    
    public Map<String, Integer> getProduits() {
		return produit;
	}


	public void ajouterProduit(String nomProduit, int quantite) {
        if (produit.containsKey(nomProduit)) {
            produit.put(nomProduit, produit.get(nomProduit) + quantite);
        } else {
            produit.put(nomProduit, quantite);
        }
        notifierObservateurs(nomProduit);
    }
    
    public boolean retirerProduit(String nomProduit, int quantite) {
        if (produit.containsKey(nomProduit) && produit.get(nomProduit) >= quantite) {
            produit.put(nomProduit, produit.get(nomProduit) - quantite);
            notifierObservateurs(nomProduit);
            return true;
        }
        return false;
    }
    
    public int getQuantite(String nomProduit) {
        return produit.getOrDefault(nomProduit, 0);
    }
    
    public void mettreAJour() {
        System.out.println("Stock mis à jour");
        // Logique pour mettre à jour le stock
    }
    
    // Observer pattern
    private List<Observer> observateurs = new ArrayList<>();
    
    public void ajouterObservateur(Observer observateur) {
        observateurs.add(observateur);
    }
    
    public void retirerObservateur(Observer observateur) {
        observateurs.remove(observateur);
    }
    
    private void notifierObservateurs(String produit) {
        for (Observer obs : observateurs) {
            obs.notifier("Le stock du produit " + produit + " a été modifié. Nouvelle quantité: " + getQuantite(produit));
        }
    }
}