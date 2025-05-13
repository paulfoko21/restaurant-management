package model;

import java.util.ArrayList;
import java.util.List;

import menu.ElementMenu;

public class Menu {
    private List<ElementMenu> plats;
    
    public Menu() {
        this.plats = new ArrayList<>();
    }
    
    public void ajouterPlat(ElementMenu plat) {
        plats.add(plat);
    }
    
    public void supprimerPlat(ElementMenu plat) {
        plats.remove(plat);
    }
    
    public void modifierPlat(int index, ElementMenu nouveauPlat) {
        if (index >= 0 && index < plats.size()) {
            plats.set(index, nouveauPlat);
        }
    }
    
    public ElementMenu getPlat(int index) {
        if (index >= 0 && index < plats.size()) {
            return plats.get(index);
        }
        return null;
    }
    
    public List<ElementMenu> getPlats() {
        return new ArrayList<>(plats);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MENU\n");
        for (int i = 0; i < plats.size(); i++) {
            sb.append(i+1).append(". ").append(plats.get(i)).append("\n");
        }
        return sb.toString();
    }
}
