import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class NewJerseyTaxTest {
    @Test
    void calculateTax() {
        NewJerseyTax nj = new NewJerseyTax();
        assertEquals(45,nj.calculateTax(1500));
    }

    @Test
    void applyTaxToPurchase() {
        NewJerseyTax nj = new NewJerseyTax();
        assertEquals(1545,nj.applyTaxToPurchase(1500));
    }

}