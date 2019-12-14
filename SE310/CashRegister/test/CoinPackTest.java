import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CoinPackTest {
    @Test
    void pennies() {
        CoinPack cp = new CoinPack();
        assertEquals(0, cp.pennies());
    }

    @Test
    void nickles() {
        CoinPack cp = new CoinPack();
        assertEquals(0, cp.nickles());
    }

    @Test
    void dimes() {
        CoinPack cp = new CoinPack();
        assertEquals(0, cp.dimes());
    }

    @Test
    void quarters() {
        CoinPack cp = new CoinPack();
        assertEquals(0, cp.quarters());
    }

    @Test
    void pennies1() {
        CoinPack cp = new CoinPack();
        cp.pennies(5);
        assertEquals(5, cp.pennies());
    }

    @Test
    void nickles1() {
        CoinPack cp = new CoinPack();
        cp.nickles(5);
        assertEquals(5, cp.nickles());
    }

    @Test
    void dimes1() {
        CoinPack cp = new CoinPack();
        cp.dimes(5);
        assertEquals(5, cp.dimes());
    }

    @Test
    void quarters1() {
        CoinPack cp = new CoinPack(1,2,3,5);
        assertEquals(5, cp.quarters());
    }
    @Test
    void pennies2() {
        CoinPack cp = new CoinPack();
        assertEquals(false, cp.pennies(-1));
    }

    @Test
    void quartersWithError() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CoinPack cp = new CoinPack(2,2,3,-1);
        });
    }
    @Test
    void nickles2() {
        CoinPack cp = new CoinPack();
        assertEquals(false, cp.nickles(-1));
    }

    @Test
    void dimes2() {
        CoinPack cp = new CoinPack();
        assertEquals(false, cp.dimes(-1));
    }

    @Test
    void quarters2() {
        CoinPack cp = new CoinPack();
        assertEquals(false, cp.quarters(-5));
    }

}