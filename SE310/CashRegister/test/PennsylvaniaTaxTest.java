import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PennsylvaniaTaxTest {
    @Test
    void calculateTax() {
        PennsylvaniaTax ny = new PennsylvaniaTax();
        assertEquals(90,ny.calculateTax(1500));
    }

    @Test
    void applyTaxToPurchase() {
        PennsylvaniaTax ny = new PennsylvaniaTax();
        assertEquals(1590,ny.applyTaxToPurchase(1500));
    }

}