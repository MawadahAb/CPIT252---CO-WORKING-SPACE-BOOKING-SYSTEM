package paymentStrategy;

public class PaymentContext {

    private PaymentStrategy paymentStrategy;

    // Initialize the payment context with a specific payment strategy
    public PaymentContext(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    // Execute the payment process using the selected strategy
    public PaymentReceipt executePayment(double amount) {
        return paymentStrategy.pay(amount);
    }
}
