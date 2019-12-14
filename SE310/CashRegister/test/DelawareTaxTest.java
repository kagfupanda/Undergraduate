import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DelawareTaxTest {
    @Test
    void calculateTax() {
        DelawareTax ny = new DelawareTax();
        assertEquals(30,ny.calculateTax(1500));
    }

    @Test
    void applyTaxToPurchase() {
        DelawareTax ny = new DelawareTax();
        assertEquals(1530,ny.applyTaxToPurchase(1500));
    }

}