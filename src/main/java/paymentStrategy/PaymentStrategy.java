package paymentStrategy;

public interface PaymentStrategy {
    // Process the payment and return a payment receipt
    PaymentReceipt pay (double amount);
}
