import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CashRegisterTest {
    private CashRegister cr = null;
    private CoinPack cp = null;
    private BillPack bp = null;

    @BeforeEach
    void setUp() {
        this.cp = new CoinPack();
        this.bp = new BillPack();
        this.cr = new CashRegister(this.bp, this.cp);
    }
    @Test
    void drawerValue() {
        this.cr = new CashRegister();
        assertEquals(0, this.cr.drawerValue());
    }

    @Test
    void coinsInDrawer() {
        assertEquals(this.cp, this.cr.coinsInDrawer());
    }

    @Test
    void billsInDrawer() {
        assertEquals(this.bp, this.cr.billsInDrawer());
    }

    @Test
    void purchaseItemWithNJStateTax() {
        this.cr = new CashRegister(5,5,5,4,5,2,3,4,5,6, new NewJerseyTax());
        CoinPack cp1 = new CoinPack(1,2,3,4);
        BillPack bp1 = new BillPack(0,0,2,0,0,0);
        assertEquals(5.35, this.cr.purchaseItem(15.6, bp1, cp1));
    }
    @Test
    void purchaseItem() {
        this.cr = new CashRegister(5,5,5,4,5,2,3,4,5,6);
        CoinPack cp1 = new CoinPack(1,2,3,4);
        BillPack bp1 = new BillPack(0,0,2,0,0,0);
        assertEquals(4.88, this.cr.purchaseItem(15.6, bp1, cp1));
    }
    @Test
    void purchaseItemWithError() {
        this.cr = new CashRegister(5,5,5,4,5,2,3,4,5,6);
        CoinPack cp1 = new CoinPack(1,2,3,4);
        BillPack bp1 = new BillPack(0,0,0,0,0,0);
        Assertions.assertThrows(IllegalStateException.class, () -> {
            this.cr.purchaseItem(15.6, bp1, cp1);
        });
    }
    @Test
    void purchaseItemWithExactChange() {
        this.cr = new CashRegister(5,5,5,4,5,2,3,4,5,6);
        CoinPack cp1 = new CoinPack(3,0,0,2);
        BillPack bp1 = new BillPack(1,1,1,0,0,0);
        assertEquals(0,this.cr.purchaseItem(15.6, bp1, cp1));
    }
    @Test
    void purchaseItemWithHundredsInChange() {
        this.cr = new CashRegister(5,5,5,4,5,2,3,4,5,6);
        CoinPack cp1 = new CoinPack(0,1,3,8);
        BillPack bp1 = new BillPack(0,2,2,0,0,1);
        assertEquals(115.82,this.cr.purchaseItem(15.6, bp1, cp1));
    }
    @Test
    void purchaseItemWithFiftiesInChange() {
        this.cr = new CashRegister(5,5,5,4,5,2,3,4,5,6);
        CoinPack cp1 = new CoinPack(0,1,3,3);
        BillPack bp1 = new BillPack(0,0,2,0,1,0);
        assertEquals(54.57,this.cr.purchaseItem(15.6, bp1, cp1));
    }
    @Test
    void purchaseItemWithTwentiesInChange() {
        this.cr = new CashRegister(5,5,5,4,5,2,3,4,5,6);
        CoinPack cp1 = new CoinPack(0,1,3,3);
        BillPack bp1 = new BillPack(0,0,2,1,0,0);
        assertEquals(24.57,this.cr.purchaseItem(15.6, bp1, cp1));
    }
    @Test
    void purchaseItemWithTensInChange() {
        this.cr = new CashRegister(5,5,5,4,5,2,3,4,5,6);
        CoinPack cp1 = new CoinPack(0,1,3,3);
        BillPack bp1 = new BillPack(0,0,2,0,0,0);
        assertEquals(4.57,this.cr.purchaseItem(15.6, bp1, cp1));
    }
    @Test
    void purchaseItemWithLessMoneyInDrawer() {
        this.cr = new CashRegister();
        CoinPack cp1 = new CoinPack(0,1,3,3);
        BillPack bp1 = new BillPack(0,0,2,0,0,0);
        Assertions.assertThrows(IllegalStateException.class, () -> {
            this.cr.purchaseItem(15.6, bp1, cp1);
        });
    }

    @Test
    void purchaseItem1() {
        this.cp = new CoinPack(5,5,5,4);
        this.bp = new BillPack(5,2,3,4,5,6);
        this.cr = new CashRegister(this.bp, this.cp);
        CoinPack cp1 = new CoinPack(1,2,3,4);
        BillPack bp1 = new BillPack(0,0,2,0,0,0);
        assertEquals(4.88, this.cr.purchaseItem(15.6, 0,0,2,0,0,0,1,2,3,4));
    }

    @Test
    void scanItem() {
        this.cp = new CoinPack(5,5,5,4);
        this.bp = new BillPack(5,2,3,4,5,6);
        Drawer dr = new Drawer(this.cp, this.bp);
        this.cr = new CashRegister(dr);
        assertEquals(15.600000381469727, this.cr.scanItem(15.6, "Soda"));
    }

    @Test
    void finalizePurchase() {
        this.cp = new CoinPack(5,5,5,4);
        this.bp = new BillPack(5,2,3,4,5,6);
        this.cr = new CashRegister(this.bp, this.cp);
        CoinPack cp1 = new CoinPack(1,2,3,4);
        BillPack bp1 = new BillPack(0,0,2,0,0,0);
        this.cr.scanItem(15.6, "Soda");
        assertEquals(4.88, this.cr.finalizePurchase(bp1,cp1));
    }

    @Test
    void finalizePurchaseWithLessMoneyInDrawer() {
        this.cr = new CashRegister();
        CoinPack cp1 = new CoinPack(0,1,3,3);
        BillPack bp1 = new BillPack(0,0,2,0,0,0);
        this.cr.scanItem(15.6, "Soda");
        Assertions.assertThrows(IllegalStateException.class, () -> {
            this.cr.finalizePurchase(bp1, cp1);
        });
    }
    @Test
    void finalizePurchaseWithLessMoneyTendered() {
        this.cr = new CashRegister(5,5,5,4,5,2,3,4,5,6);
        CoinPack cp1 = new CoinPack(1,2,3,4);
        BillPack bp1 = new BillPack(0,0,0,0,0,0);
        this.cr.scanItem(15.6, "Soda");
        Assertions.assertThrows(IllegalStateException.class, () -> {
            this.cr.finalizePurchase(bp1, cp1);
        });
    }

}