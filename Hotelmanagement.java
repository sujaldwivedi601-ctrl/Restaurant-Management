import java.util.ArrayList;
import java.util.Scanner;

// input helpers
class SystemTool {

    public static Scanner sc = new Scanner(System.in);

    // Returns null when there is no more input (EOF), instead of throwing
    public static String readLine() {
        if (sc.hasNextLine()) {
            return sc.nextLine();
        }
        return null;
    }

    // Returns 1..max for a valid number, 0 otherwise. Never reads input.
    public static int isInteger(String str, int max) {
        if (str == null) {
            return 0;
        }
        String s = str.trim();
        if (s.isEmpty()) {
            return 0;
        }
        try {
            int temp = Integer.parseInt(s);
            if (temp > 0 && temp <= max) {
                return temp;
            }
            return 0;
        } catch (NumberFormatException e) {
            return 0; // letters, decimals, oversized numbers, etc.
        }
    }

}

// item = key for name, price = key for price
class Item {
    String name;
    int price;

    Item(String name, int price) {
        this.name = name;
        this.price = price;
    }
}

class Hotal {

    private Item[] menu = {
            new Item("Pizza", 150),
            new Item("Burger", 80),
            new Item("Sandwich", 60),
            new Item("Cold Coffee", 50),
            new Item("Samosha", 20),
            new Item("Jaledi", 30)
    };
    private ArrayList<Item> userOrder = new ArrayList<>();

    void manue() {
        System.out.println("----------- MENU -----------");
        for (int i = 0; i < menu.length; i++) {
            System.out.println((i + 1) + ". " + menu[i].name + "  - Rs. " + menu[i].price);
        }
        System.out.println("----------------------------- \n");
    }

    // by using this method use can order food
    void Order() {

        System.out.print("What do you like to order : ");
        String ord = SystemTool.readLine();
        if (ord == null) {
            return;
        }
        ord = ord.trim();
        int numOrd = SystemTool.isInteger(ord, menu.length); // 0 when it is a name

        // Search by number OR by name
        for (int i = 0; i < menu.length; i++) {
            Item it = menu[i];
            if (numOrd == i + 1 || ord.equalsIgnoreCase(it.name)) {
                userOrder.add(it);
                System.out.println(it.name + " added to your order. (Rs. " + it.price + ") \n");
                return;
            }
        }
        System.out.println("Sorry, we don't have that item. Try again. \n");
    }

    // Change yor order
    void Change() {
        if (userOrder.isEmpty()) {
            System.out.println("You haven't ordered anything yet.");
            return;
        }
        System.out.println("========= Your Order ========");
        for (int i = 0; i < userOrder.size(); i++) {
            Item it = userOrder.get(i);
            System.out.println((i + 1) + ". " + it.name + " - Rs. " + it.price);
        }
        System.out.println("============================");

        System.out.print("Enter Order number to Remove : ");
        String input = SystemTool.readLine();
        if (input == null) {
            return;
        }
        int orderNumber = SystemTool.isInteger(input, userOrder.size());

        if (orderNumber >= 1) {
            Item removed = userOrder.remove(orderNumber - 1); // remove() shifts later items left
            System.out.println(removed.name + " removed from your order.");
        } else {
            System.out.println("Invalid order number.");
        }
    }

    // it gives bill to the user
    void Bill() {
        if (userOrder.isEmpty()) {
            System.out.println("You haven't ordered anything yet.");
            return;
        }
        int total = 0;
        System.out.println("========= Your bill ========");
        for (int i = 0; i < userOrder.size(); i++) {
            Item it = userOrder.get(i);
            System.out.println((i + 1) + ". " + it.name + " - Rs. " + it.price);
            total += it.price;
        }
        System.out.println("============================");
        System.out.println("Total: Rs. " + total + "\n");
    }

}

public class Hotelmanagement {
    public static void main(String[] args) {
        Hotal vater = new Hotal();
        String chose = "";

        System.out.println("==================================");
        System.out.println("WELCOME TO OUR HOTEL");
        System.out.println("==================================");

        do {
            System.out.println("1. Manue");
            System.out.println("2. Order");
            System.out.println("3. Bill");
            System.out.println("4. change Order");
            System.out.println("5. Exit");
            System.out.print("\nEnter your chose : ");
            chose = SystemTool.readLine();
            if (chose == null) {
                break; // input ended
            }
            chose = chose.trim();
            int numvalue = SystemTool.isInteger(chose, 5);

            if (numvalue == 1 || chose.equalsIgnoreCase("Manue")) {
                vater.manue();
            } else if (numvalue == 2 || chose.equalsIgnoreCase("Order")) {
                vater.Order();
            } else if (numvalue == 3 || chose.equalsIgnoreCase("Bill")) {
                vater.Bill();
            } else if (numvalue == 4 || chose.equalsIgnoreCase("Change")) {
                vater.Change();
            } else if (numvalue == 5 || chose.equals("0") || chose.equalsIgnoreCase("Exit")) {
                chose = "0"; // exit
            } else {
                System.out.println("Wrong Input. \n");
            }

        } while (!chose.equals("0"));

        System.out.println("Thank you for visiting !");
    }

}
