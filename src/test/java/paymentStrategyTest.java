import org.junit.jupiter.api.Test;

import paymentStrategy.CreditCard;
import paymentStrategy.PayPal;
import paymentStrategy.PaymentContext;
import paymentStrategy.PaymentReceipt;

import static org.junit.jupiter.api.Assertions.*;

public class StrategyPatternTest {

    @Test
    void shouldUseCreditCardPaymentStrategySuccessfully() {

        PaymentContext context =
                new PaymentContext(
                        new CreditCard(
                                "Salwa",
                                "123456789",
                                "123",
                                "12/27"
                        )
                );

        PaymentReceipt receipt =
                context.executePayment(200);

        assertTrue(receipt.isSuccessful());

        assertEquals(200, receipt.getAmount());

        assertEquals(
                "Credit Card",
                receipt.getPaymentMethod()
        );
    }

    @Test
    void shouldUsePayPalPaymentStrategySuccessfully() {

        PaymentContext context =
                new PaymentContext(
                        new PayPal(
                                "salwa@gmail.com",
                                "12345"
                        )
                );

        PaymentReceipt receipt =
                context.executePayment(150);

        assertTrue(receipt.isSuccessful());

        assertEquals(150, receipt.getAmount());

        assertEquals(
                "PayPal",
                receipt.getPaymentMethod()
        );
    }

    @Test
    void shouldRejectPaymentWhenAmountIsZero() {

        PaymentContext context =
                new PaymentContext(
                        new CreditCard(
                                "Salwa",
                                "123456789",
                                "123",
                                "12/27"
                        )
                );

        PaymentReceipt receipt =
                context.executePayment(0);

        assertFalse(receipt.isSuccessful());

        assertEquals(
                "Invalid amount",
                receipt.getMessage()
        );
    }

    @Test
    void shouldRejectCreditCardPaymentWhenCardNumberIsInvalid() {

        PaymentContext context =
                new PaymentContext(
                        new CreditCard(
                                "Salwa",
                                "123",
                                "123",
                                "12/27"
                        )
                );

        PaymentReceipt receipt =
                context.executePayment(200);

        assertFalse(receipt.isSuccessful());

        assertEquals(
                "Invalid card number",
                receipt.getMessage()
        );
    }

    @Test
    void shouldRejectPayPalPaymentWhenEmailIsInvalid() {

        PaymentContext context =
                new PaymentContext(
                        new PayPal(
                                "salwagmail.com",
                                "12345"
                        )
                );

        PaymentReceipt receipt =
                context.executePayment(100);

        assertFalse(receipt.isSuccessful());

        assertEquals(
                "Invalid email",
                receipt.getMessage()
        );
    }
}
