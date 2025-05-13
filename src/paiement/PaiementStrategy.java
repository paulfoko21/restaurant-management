package paiement;

/**
 * Interface du pattern Strategy pour les méthodes de paiement.
 */
public interface PaiementStrategy {
    void payer(double montant);
}
