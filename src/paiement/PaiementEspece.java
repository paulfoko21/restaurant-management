package paiement;

/**
 * Paiement effectué en espèces.
 */
public class PaiementEspece implements PaiementStrategy {
    @Override
    public void payer(double montant) {
        System.out.println("Paiement de " + montant + "€ en espèces.");
    }
}
