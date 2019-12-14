import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Sean Grimes on 9/19/17.
 * <p>Example main showing how to use the CashRegister.</p>
 */
public class Runner {
    private static BillPack bills = null;
    private static CoinPack coins = null;

    public static void main(String[] args){
        // The below shows how to instantiate a new CashRegister and pay for an item

        // Instantiate a new CashRegister with the following bills / coins
        // Ones: 100
        // Fives: 10
        // Tens: 10
        // Twenties: 10
        // Fifties: 10
        // Hundreds: 10
        // Pennies: 1,000
        // Nickles: 100
        // Dimes: 100
        // Quarters: 10
        CashRegister cr = null;

        if (args.length <=0) {
            System.out.println("Missing CashRegister Location. \nPlease pass one of the state abbreviations: NJ (for New Jersey), PA(for Pennsylvania) or NY(for New York) or DE (for Delaware) for tax calculation purposes. \nIf none passed then PA (Pennsylvania) will be used as default value for tax calculations.");
            System.out.println("---------");
            System.out.println("Using Pennsylvania for tax calculation purposes!!!");
            cr = new CashRegister(100,10,10,10,10,10,1000,100,100,10);
        }
        if (args.length >=1 ) {
            String state = args[0];
            if (state.equalsIgnoreCase("NJ")) {
                System.out.println("Using New Jersey for tax calculation purposes!!!");
                cr = new CashRegister(new BillPack(100,10,10,10,10,10),new CoinPack(1000,100,100,10),new NewJerseyTax());
            }
            else if (state.equalsIgnoreCase("NY")) {
                System.out.println("Using New York for tax calculation purposes!!!");
                cr = new CashRegister(new BillPack(100,10,10,10,10,10),new CoinPack(1000,100,100,10),new NewYorkTax());
            }
            else if (state.equalsIgnoreCase("DE")) {
                System.out.println("Using Delaware for tax calculation purposes!!!");
                cr = new CashRegister(new BillPack(100,10,10,10,10,10),new CoinPack(1000,100,100,10),new DelawareTax());
            }
            else {
                System.out.println("Using Pennsylvania for tax calculation purposes!!!");
                cr = new CashRegister(100,10,10,10,10,10,1000,100,100,10);
            }
        }

        // Instantiate a BillPack, an object to hold the number and type of bills the user is using
        // to pay for their purchase. This one holds the following:
        // Ones: 0
        // Fives: 0
        // Tens: 0
        // Twenties: 0
        // Fifties: 0
        // Hundreds: 1
        BillPack purchaseBills = new BillPack(0, 0, 0, 0, 0, 1);

        // Instantial a CoinPack, an object to hold the number and type of coins the user is using
        // to pay for their purchase. This one holds the following:
        // Pennies: 0
        // Nickles: 0
        // Dimes: 0
        // Quarters: 0
        CoinPack purchaseCoins = new CoinPack(0, 0, 0, 0);

        // Try to purchase the notebook using the above BillPack and CoinPack, the CashRegister will
        // return the change to the user if the purchase is successful
        double notebookPrice = 10.57;
        double change = cr.purchaseItem(notebookPrice, purchaseBills, purchaseCoins);

        //------------------------------------------------------------------------------------------

        // The below shows how to instantiate a new CashRegister, scan multiple items, and complete
        // the purchase of multiple items. Note that a cash register can also be instantiated
        // with the BillPack and CoinPack objects instead of individual values for each type of
        // coin and bill
        BillPack crBills = new BillPack(10, 10, 10, 10, 10, 10);
        CoinPack crCoins = new CoinPack(1000, 100, 100, 100);

        CashRegister multiItems = new CashRegister(crBills, crCoins);
        //CashRegister multiItems = new CashRegister();

        // Create a list of item prices and item names. The cash register will keep track of the
        // item name and it's price as you scan new items before finalizing the purchase.
        double totalPrice = buyItems(multiItems);
        //double totalPrice = 15.6;

        // The bills and coins the user will be using the purchase the above list of 3 items
         bills = new BillPack(3,0,0,0,0,0);
         coins = new CoinPack(4,1,3,0);



        // Finalize the purchase and gather the returned change if the purchase is successful
        try {
            //multiItems.purchaseItem(15.6,2,1,1,0,0,0,0,0,0,0);
            double multiChange = multiItems.finalizePurchase(bills, coins);//
        }
        catch(IllegalStateException e){
            if (e.getLocalizedMessage().contains("tendered to cover cost")) {
                Util.writeln("Due to Insufficient money tendered.");
                System.out.println("What to do just give more money?");
                long tendered = Drawer.centValueFromCoins(coins);
                tendered += Drawer.centValueFromBills(bills);
                double need = (double) totalPrice - tendered/100;
                double gave = (double)tendered/100;
                Util.writeln("You gave=" + gave);
                Util.writeln("You need=" + need);
                getEnoughTenderMoney(totalPrice);
                buyItems(multiItems);
                double multiChange = multiItems.finalizePurchase(bills, coins);
            }
            else if (e.getLocalizedMessage().contains("drawer to process change")) {
                    System.out.println("Insufficient funds in drawer to process change!!!!!!");
                }
            }
    }

    private static void getEnoughTenderMoney(double totalPrice) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            getBills(totalPrice, scan);
            getCoins(totalPrice, scan);
            long tendered = Drawer.centValueFromCoins(coins);
            tendered += Drawer.centValueFromBills(bills);
            double gave = (double) tendered / 100;
            double need = (double) totalPrice - tendered / 100;
            if (need <= gave) {
                System.out.println("Good job, you gave enough to cover the purchase! You gave=" + gave);
                break;
            }
            else {
                System.out.println("Not enough money tenderd, please give more money!");
            }
        }
    }

    private static void getCoins(double totalPrice, Scanner scan) {
        while (true) {
            System.out.println("\n" +
                    "------------\n" +
                    "enter 4 (comma separated) amounts\n" +
                    "(respectively in this order:1s,5s,10s,25s)\n" +
                    " you want to give/tender in coins:[ex: 1,0,0,0]");
            String option = scan.nextLine();
            option = option.trim();
            String[] parseStr = option.split(",");
            if (parseStr.length < 4)
                continue;
            boolean flag = invalidInput(parseStr);
            if (flag)
                continue;
            CoinPack cp = new CoinPack(coins.pennies(), coins.nickles(), coins.dimes(), coins.quarters());
            coins = new CoinPack(cp.pennies() + Long.parseLong(parseStr[0]),
                    cp.nickles() + Long.parseLong(parseStr[1]),
                    cp.dimes() + Long.parseLong(parseStr[2]),
                    cp.quarters() + Long.parseLong(parseStr[3]));
            if (!flag)
                break;
        }
    }

    private static void getBills(double totalPrice, Scanner scan) {
        while (true) {
            System.out.println("\n" +
                    "-------------------\n" +
                    "enter 6 (comma separated) amounts\n" +
                    "(respectively in this order: 1s,5s,10s,20s,50s,100s)\n" +
                    "you want to give/tender in bills:[ex: 1,2,0,2,0,2]");
            String option = scan.nextLine();
            option = option.trim();
            String[] parseStr = option.split(",");
            if (parseStr.length < 6)
                continue;
            boolean flag = invalidInput(parseStr);
            if (flag)
                continue;
            BillPack bp = new BillPack(bills.ones(),
                    bills.fives(),bills.tens(),bills.twenties(),
                    bills.fifties(),bills.hundreds());
            bills = new BillPack(bp.ones() + Long.parseLong(parseStr[0]),
                    bp.fives() + Long.parseLong(parseStr[1]),
                    bp.tens() + Long.parseLong(parseStr[2]),
                    bp.twenties() + Long.parseLong(parseStr[3]),
                    bp.fifties() + Long.parseLong(parseStr[4]),
                    bp.hundreds() + Long.parseLong(parseStr[5]));
            if (!flag)
                break;
        }

    }

    private static boolean invalidInput(String[] parseStr) {
        boolean flag = false;
        for (int i = 0; i<parseStr.length; i++) {
            try {
                int num = Integer.parseInt(parseStr[i]);
                if (num<0)
                    return true;
            }
            catch(NumberFormatException e){
                return true;
            }
        }
        return flag;
    }

    private static double buyItems(CashRegister multiItems) {
        List<Double> itemPrices = new ArrayList<>();
        List<String> itemNames = new ArrayList<>();

        itemPrices.add(10.00);
        itemNames.add("Expensive Soda");
        itemPrices.add(24.99);
        itemNames.add("Very Good Chocolate");
        itemPrices.add(0.89);
        itemNames.add("Very Bad Chocolate");

        // Run through the list of items, scan them into the cash register using their price and
        // name
        double totalPrice=0;
        for(int i = 0; i < itemPrices.size(); ++i) {
            multiItems.scanItem(itemPrices.get(i), itemNames.get(i));
            totalPrice += itemPrices.get(i);
        }
        return totalPrice;
    }
}