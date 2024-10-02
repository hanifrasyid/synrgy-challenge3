package appController;

import appModel.FoodItem;
import appModel.OrderItem;
import appView.AppView;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class AppController {
    private final List<FoodItem> foodItems;
    private final List<OrderItem> orderItems;
    private final AppView view;

    public AppController() {
        foodItems = setupMenu();
        orderItems = new ArrayList<>();
        view = new AppView();
    }

    public void runApp() {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean isAppActive = true;

            do {
                view.mainMenu(foodItems);
                int option = view.getOption(scanner);

                if (option == 0) {
                    exitApp();
                    isAppActive = false;
                } else if (option > 0 && option <= foodItems.size()) {
                    executeItemChoice(option, scanner);
                } else if (option == 99) {
                    int choice = view.orderConf(orderItems, scanner);

                    if (choice == 1) {
                        receiptBuilder();
                        isAppActive = false;
                    } else if (choice == 0) {
                        exitApp();
                        isAppActive = false;
                    }
                }
            } while (isAppActive);
        }
    }

    public void executeItemChoice(int option, Scanner scanner) {
        FoodItem chosenItem = foodItems.get(option - 1);
        int qty = view.getQty(scanner, chosenItem);

        if (qty > 0) {
            OrderItem orderItem = new OrderItem(chosenItem, qty);
            orderItems.add(orderItem);
        }
    }

    private void exitApp() {
        System.out.println("\nTerima kasih sudah berkunjung di BinarFud!");
    }

    private List<FoodItem> setupMenu() {
        List<FoodItem> menuList = new ArrayList<>();
        menuList.add(new FoodItem("Nasi Goreng  ", 15000));
        menuList.add(new FoodItem("Mie Goreng   ", 13000));
        menuList.add(new FoodItem("Nasi + Ayam  ", 18000));
        menuList.add(new FoodItem("Es Teh Manis ", 3000));
        menuList.add(new FoodItem("Es Jeruk     ", 5000));
        return menuList;
    }

    private void receiptBuilder() {
        view.showReceipt();
        view.showOrders(orderItems);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Struk Pembayaran.txt"))) {
            writer.write("==========================\n");
            writer.write("Struk Pembayaran BinarFud\n");
            writer.write("==========================\n\n");
            writer.write("Terima kasih sudah memesan di BinarFud\n\n");
            writer.write("Di bawah ini adalah pesanan anda:\n\n");
            view.saveTable(writer, orderItems);
            writer.write("Pembayaran: BinarCash\n\n");
            writer.write("==========================\n");
            writer.write("Simpan struk ini sebagai bukti pembayaran\n");
            writer.write("==========================\n");
        } catch (IOException e) {
            System.out.println("Gagal mencetak pesanan ke file struk Pembayaran.txt.");
        }
    }
}