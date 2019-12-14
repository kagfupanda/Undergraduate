import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DrawerTest {
    @Test
    void drawerTotalInCents() {
        Drawer dr = new Drawer();
        assertEquals(0,dr.drawerTotalInCents());
    }

    @Test
    void penny() {
        Drawer dr = new Drawer();
        assertEquals(0,dr.penny());
    }

    @Test
    void nickle() {
        Drawer dr = new Drawer();
        assertEquals(0,dr.nickle());
    }

    @Test
    void dime() {
        Drawer dr = new Drawer();
        assertEquals(0,dr.dime());
    }

    @Test
    void quarter() {
        Drawer dr = new Drawer();
        assertEquals(0,dr.quarter());
    }

    @Test
    void one() {
        Drawer dr = new Drawer();
        assertEquals(0,dr.one());
    }

    @Test
    void five() {
        Drawer dr = new Drawer();
        assertEquals(0,dr.five());
    }

    @Test
    void ten() {
        Drawer dr = new Drawer();
        assertEquals(0,dr.ten());
    }

    @Test
    void twenty() {
        Drawer dr = new Drawer();
        assertEquals(0,dr.twenty());
    }

    @Test
    void fifty() {
        Drawer dr = new Drawer();
        assertEquals(0,dr.fifty());
    }

    @Test
    void hundred() {
        Drawer dr = new Drawer();
        assertEquals(0,dr.hundred());
    }

    @Test
    void coinPack() {
        CoinPack cp = new CoinPack(5L,6L,7L,8L);
        BillPack bp = new BillPack(1,2,3,4,5,6);
        Drawer dr = new Drawer(cp,bp);
        assertEquals(cp,dr.coinPack());
    }

    @Test
    void billPack() {
        CoinPack cp = new CoinPack(5L,6L,7L,8L);
        BillPack bp = new BillPack(1,2,3,4,5,6);
        Drawer dr = new Drawer(cp,bp);
        assertEquals(bp,dr.billPack());
    }

    @Test
    void depositChange() {
        CoinPack cp = new CoinPack(5L,6L,7L,8L);
        CoinPack cp1 = new CoinPack(5L,6L,7L,8L);
        BillPack bp = new BillPack(1,2,3,4,5,6);
        Drawer dr = new Drawer(cp,bp);
        dr.depositChange(cp1);
        assertEquals(97710,dr.drawerTotalInCents());
    }

    @Test
    void depositChange1() {
        CoinPack cp = new CoinPack(5L,6L,7L,8L);
        BillPack bp = new BillPack(1,2,3,4,5,6);
        Drawer dr = new Drawer(cp,bp);
        dr.depositChange(5,6,7,8);
        assertEquals(97710,dr.drawerTotalInCents());
    }


    @Test
    void depositChangeWithError() {
        CoinPack cp = new CoinPack(5L,6L,7L,8L);
        BillPack bp = new BillPack(1,2,3,4,5,6);
        Drawer dr = new Drawer(cp,bp);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            dr.depositChange(-1,6,7,8);
        });
    }
    @Test
    void depositBills() {
        CoinPack cp = new CoinPack(5L,6L,7L,8L);
        BillPack bp = new BillPack(1,2,3,4,5,6);
        Drawer dr = new Drawer(cp,bp);
        dr.depositBills(5,6,7,8,9,10);
        assertEquals(268905,dr.drawerTotalInCents());
    }

    @Test
    void depositBills1() {
        CoinPack cp = new CoinPack(5L,6L,7L,8L);
        BillPack bp1 = new BillPack(5L,6L,7L,8L,9L,10L);
        BillPack bp = new BillPack(1,2,3,4,5,6);
        Drawer dr = new Drawer(cp,bp);
        dr.depositBills(bp1);
        assertEquals(268905,dr.drawerTotalInCents());
    }

    @Test
    void depositBillsWithError() {
        CoinPack cp = new CoinPack(5L,6L,7L,8L);
        BillPack bp = new BillPack(1,2,3,4,5,6);
        Drawer dr = new Drawer(cp,bp);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            dr.depositBills(-1,6,7,8,9,10);
        });
    }
    @Test
    void removeChange() {
        CoinPack cp = new CoinPack(5L,6L,7L,8L);
        CoinPack cp1 = new CoinPack(5L,6L,7L,8L);
        BillPack bp = new BillPack(1,2,3,4,5,6);
        Drawer dr = new Drawer(cp,bp);
        dr.removeChange(cp1);
        assertEquals(97100,dr.drawerTotalInCents());
    }

    @Test
    void removeChange1() {
        CoinPack cp = new CoinPack(5L,6L,7L,8L);
        BillPack bp = new BillPack(1,2,3,4,5,6);
        Drawer dr = new Drawer(cp,bp);
        dr.removeChange(5,6,7,8);
        assertEquals(97100,dr.drawerTotalInCents());
    }
    @Test
    void removeChangeWithError() {
        CoinPack cp = new CoinPack(5L,6L,7L,8L);
        BillPack bp = new BillPack(1,2,3,4,5,6);
        Drawer dr = new Drawer(cp,bp);
        assertEquals(false,dr.removeChange(6,6,7,8));
    }
    @Test
    void removeBills() {
        CoinPack cp = new CoinPack(5L,6L,7L,8L);
        BillPack bp = new BillPack(1,2,3,4,5,6);
        Drawer dr = new Drawer(5,6,7,8,1,2,3,4,5,6);
        assertEquals(false,dr.removeBills(6,6,7,8,9,10));
    }

    @Test
    void removeBills1() {
        CoinPack cp = new CoinPack(5L,6L,7L,8L);
        BillPack bp = new BillPack(1,2,3,4,5,6);
        BillPack bp1 = new BillPack(1,0,1,0,0,0);
        Drawer dr = new Drawer(cp,bp);
        assertEquals(true,dr.removeBills(bp1));

    }

    @Test
    void centValueFromCoins() {
        CoinPack cp = new CoinPack(5L,6L,7L,8L);
        assertEquals(305,Drawer.centValueFromCoins(cp));
    }

    @Test
    void centValueFromCoins1() {
        assertEquals(305,Drawer.centValueFromCoins(5,6,7,8));
    }

    @Test
    void centValueFromBills() {
        BillPack bp = new BillPack(5,6,7,8,9,10);
        assertEquals(171500,Drawer.centValueFromBills(bp));
    }

    @Test
    void centValueFromBills1() {
        assertEquals(171500,Drawer.centValueFromBills(5,6,7,8,9,10));
    }

}