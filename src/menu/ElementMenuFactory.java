package menu;
public class ElementMenuFactory {
    public static ElementMenu creerPlatPrincipal(String nom, double prix, String description) {
        return new PlatPrincipal(nom, prix, description);
    }
    
    public static ElementMenu creerDessert(String nom, double prix, String description) {
        return new Dessert(nom, prix, description);
    }
    
    public static ElementMenu creerBoisson(String nom, double prix, String description) {
        return new Boisson(nom, prix, description);
    }
}