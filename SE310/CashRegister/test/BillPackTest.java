import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BillPackTest {
    @Test
    void ones() {
        BillPack bp = new BillPack();
        assertEquals(0, bp.ones());
    }

    @Test
    void fives() {
        BillPack bp = new BillPack();
        assertEquals(0, bp.fives());
    }

    @Test
    void tens() {
        BillPack bp = new BillPack();
        assertEquals(0, bp.tens());
    }

    @Test
    void twenties() {
        BillPack bp = new BillPack();
        assertEquals(0, bp.twenties());
    }

    @Test
    void fifties() {
        BillPack bp = new BillPack();
        assertEquals(0, bp.fifties());
    }

    @Test
    void hundreds() {
        BillPack bp = new BillPack();
        assertEquals(0, bp.hundreds());
    }

    @Test
    void ones1() {
        BillPack bp = new BillPack();
        bp.ones(2);
        assertEquals(2, bp.ones());
    }

    @Test
    void fives1() {
        BillPack bp = new BillPack();
        bp.fives(5);
        assertEquals(5, bp.fives());
    }

    @Test
    void tens1() {
        BillPack bp = new BillPack();
        bp.tens(5);
        assertEquals(5, bp.tens());
    }

    @Test
    void twenties1() {
        BillPack bp = new BillPack(3,2,3,4,5,5);
        bp.twenties(5);
        assertEquals(5, bp.twenties());
    }

    @Test
    void twentiesWithError() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            BillPack bp = new BillPack(-1,2,3,4,5,5);
        });
    }
    @Test
    void fifties1() {
        BillPack bp = new BillPack();
        bp.fifties(5);
        assertEquals(5, bp.fifties());
    }

    @Test
    void hundreds1() {
        BillPack bp = new BillPack();
        bp.hundreds(5);
        assertEquals(5, bp.hundreds());
    }

}