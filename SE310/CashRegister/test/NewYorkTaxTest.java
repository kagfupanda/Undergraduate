import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class NewYorkTaxTest {
    @Test
    void calculateTax() {
        NewYorkTax ny = new NewYorkTax();
        assertEquals(180,ny.calculateTax(1500));
    }

    @Test
    void applyTaxToPurchase() {
        NewYorkTax ny = new NewYorkTax();
        assertEquals(1680,ny.applyTaxToPurchase(1500));
    }

}