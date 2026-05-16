package paymentStrategy;

import java.util.UUID;

public class CreditCard implements PaymentStrategy {

    private String cardHolderName;
    private String cardNumber;
    private String cvv;
    private String expirationDate;

    public CreditCard(String cardHolderName, String cardNumber, String cvv, String expirationDate) {
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
    }


    // Validate credit card details and process the payment
    @Override
    public PaymentReceipt pay(double amount) {

        if (amount <= 0) {
            return new PaymentReceipt(false, null, amount, "Credit Card", "Invalid amount");
        }
        if (cardHolderName == null || cardHolderName.trim().isEmpty()) {
            return new PaymentReceipt(false, null, amount, "Credit Card", "Invalid card holder name");
        }

        if (cardNumber == null || cardNumber.length() < 8) {
            return new PaymentReceipt(false, null, amount, "Credit Card", "Invalid card number");
        }

        if (cvv == null || cvv.length() != 3) {
            return new PaymentReceipt(false, null, amount, "Credit Card", "Invalid CVV");
        }

        if (expirationDate == null || expirationDate.trim().isEmpty()) {
            return new PaymentReceipt(false, null, amount, "Credit Card", "Invalid expiration date");
        }

        // Generate a unique transaction ID for successful payments
        String transactionId = UUID.randomUUID().toString();

        return new PaymentReceipt(true, transactionId, amount,
                "Credit Card", "Payment successful");
    }
}
