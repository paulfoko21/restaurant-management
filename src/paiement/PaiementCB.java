package paiement;

/**
 * Paiement effectué par carte bancaire.
 */
public class PaiementCB implements PaiementStrategy {
    @Override
    public void payer(double montant) {
        System.out.println("Paiement de " + montant + "€ par carte bancaire.");
    }
}
