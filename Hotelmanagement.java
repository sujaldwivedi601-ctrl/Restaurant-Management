import java.util.ArrayList;
import java.util.Scanner;

// input helpers
class SystemTool {
    // global scanner , so don't have make a new scanner avery time when we need
    // user input
    public static Scanner sc = new Scanner(System.in);

    // Returns 1..max for a valid number, 0 otherwise. Never reads input.
    public static int isInteger(String str, int max) {
        int num = 0;
        int flg = 0;
        if (str.equals("null") || str.equals("")) {
            System.out.println("Empty");
            return 0;
        }
        str = str.trim();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0' || str.charAt(i) == '1' || str.charAt(i) == '2' || str.charAt(i) == '3'
                    || str.charAt(i) == '4' || str.charAt(i) == '5' || str.charAt(i) == '6' || str.charAt(i) == '7'
                    || str.charAt(i) == '8' || str.charAt(i) == '9') {
                flg = 1;
            } else {
                flg = 0;
                break;
                
            }
        }

        if (flg == 1) {
            num = Integer.parseInt(str.trim());
            if (num <= max)
                return num;
            else
                return 0;
        } else
            return 0;
    }

    public static void Remove(Item[] arr, int arrlen, int deletOrder) {

        for (int i = deletOrder; i < arrlen - 1; i++) {
            Item temp = arr[i];
            arr[i] = arr[i + 1];
            arr[i + 1] = temp;
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

    // private ArrayList<Item> userOrder = new ArrayList<>();
    private Item[] userOrder = new Item[10];
    private int orderCount = -1;

    void manue() {
        System.out.println("----------- MENU -----------");
        for (int i = 0; i < menu.length; i++) {
            System.out.println((i + 1) + ". " + menu[i].name + "  - Rs. " + menu[i].price);
        }
        System.out.println("----------------------------- \n");
    }

    // by using this method use can order food
    void Order() {

        // max order check
        if (orderCount >= userOrder.length) {
            System.out.println("Order is full.");
            return;
        }
        System.out.print("What do you like to order : ");
        String ord = SystemTool.sc.nextLine();
        if (ord.equals("null") || ord.equals("")) {
            return;
        }

        int numOrd = SystemTool.isInteger(ord, menu.length); // 0 when it is a name

        // Search by number OR by name
        for (int i = 0; i < menu.length; i++) {
            Item it = menu[i];
            if (numOrd == i + 1 || ord.equalsIgnoreCase(it.name)) {
                userOrder[orderCount] = it;
                orderCount++;
                System.out.println(it.name + " added to your order. (Rs. " + it.price + ") \n");
                return;
            }
        }
        System.out.println("Sorry, we don't have that item. Try again. \n");
    }

    // Change yor order
    void Change() {
        if (orderCount == 0) {
            System.out.println("You haven't ordered anything yet.");
            return;
        }
        System.out.println("========= Your Order ========");
        for (int i = 0; i < orderCount; i++) {
            Item it = userOrder[i];
            System.out.println((i + 1) + ". " + it.name + " - Rs. " + it.price);
        }
        System.out.println("============================");

        System.out.print("Enter Order number to Remove : ");
        String input = SystemTool.sc.nextLine();
        if (input.equals("null")) {
            return;
        }
        int orderNumber = SystemTool.isInteger(input, orderCount);

        if (orderNumber >= 1) {
            SystemTool.Remove(userOrder, orderCount, orderNumber - 1); // remove() shifts later items left
            System.out.println(userOrder[orderNumber - 1].name + " removed from your order.");
            userOrder[orderNumber - 1] = null;
            orderCount--;
        } else {
            System.out.println("Invalid order number.");
        }
    }

    // it gives bill to the user
    void Bill() {
        if (orderCount == 0) {
            System.out.println("You haven't ordered anything yet.");
            return;
        }
        int total = 0;
        System.out.println("========= Your bill ========");
        for (int i = 0; i < orderCount; i++) {
            Item it = userOrder[i];
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
        String chose = null;

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
            chose = SystemTool.sc.nextLine();
            if (chose.equals("null") || chose.equals("")) {
                System.out.println("wrong input Plese try agen..");
                continue;
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
