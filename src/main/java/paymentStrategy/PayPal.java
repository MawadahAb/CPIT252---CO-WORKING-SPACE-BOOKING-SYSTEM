package paymentStrategy;

import java.util.UUID;

public class PayPal implements PaymentStrategy {

    private String email;
    private String password;

    public PayPal(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public PaymentReceipt pay(double amount) {

        if (amount <= 0) {
            return new PaymentReceipt(false, null, amount, "PayPal", "Invalid amount");
        }

        if (email == null || !email.contains("@")) {
            return new PaymentReceipt(false, null, amount, "PayPal", "Invalid email");
        }

        if (password == null || password.trim().isEmpty()) {
            return new PaymentReceipt(false, null, amount, "PayPal", "Invalid password");
        }

        String transactionId = UUID.randomUUID().toString();

        return new PaymentReceipt(true, transactionId, amount, "PayPal", "Payment successful");
    }
}