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

        String transactionId = UUID.randomUUID().toString();

        return new PaymentReceipt(true, transactionId, amount,
                "Credit Card", "Payment successful");
    }
}
