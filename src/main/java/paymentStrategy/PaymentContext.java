package paymentStrategy;

public class PaymentContext {

    private PaymentStrategy paymentStrategy;
    public PaymentContext(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public PaymentReceipt executePayment(double amount) {
        return paymentStrategy.pay(amount);
    }
}
