package appView;

import appModel.FoodItem;
import appModel.OrderItem;

import lombok.Data;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

@Data
public class AppView {
    public void mainMenu(List<FoodItem> foodItems) {
        clearApp();
        System.out.println("==========================");
        System.out.println("Selamat datang di BinarFud");
        System.out.println("==========================\n");
        System.out.println("Silakan pilih makanan:\n");

        for (int index = 0; index < foodItems.size(); index++) {
            FoodItem foodItem = foodItems.get(index);
            System.out.println((index + 1) + ". " + foodItem.getName() + " | " + foodItem.getPrice());
        }
        System.out.println("99. Pesan dan Bayar");
        System.out.println("0. Keluar aplikasi");
    }

    private void clearApp() {
        int clearLines = 50;
        int index = 0;

        while (index < clearLines) {
            System.out.println();
            index++;
        }
    }

    public int getOption(Scanner scanner) {
        System.out.print("\n=> ");
        return scanner.nextInt();
    }

    public int getQty(Scanner scanner, FoodItem selectedFoodItem) {
        clearApp();
        System.out.println("=====================");
        System.out.println("Berapa pesanan anda?");
        System.out.println("=====================\n");
        System.out.println(selectedFoodItem.getName() + " | " + selectedFoodItem.getPrice());
        System.out.println("(Input 0 untuk kembali)\n");
        System.out.print("Qty => ");
        return scanner.nextInt();
    }

    public int orderConf(List<OrderItem> orderItems, Scanner scanner) {
        clearApp();
        System.out.println("========================");
        System.out.println("Konfirmasi & pembayaran");
        System.out.println("========================\n");
        showOrderTable(orderItems);
        System.out.println("\n1. Konfirmasi dan bayar");
        System.out.println("2. Kembali ke menu utama");
        System.out.println("0. Keluar aplikasi\n");
        System.out.print("=> ");
        return scanner.nextInt();
    }

    public void showOrders(List<OrderItem> orderItems) {
        System.out.println("Di bawah ini adalah pesanan anda:\n");
        int totalQty = 0;
        int totalPrice = 0;
        int index = 0;
        while (index < orderItems.size()) {
            OrderItem orderItem = orderItems.get(index);
            System.out.println((index + 1) + ". " + orderItem.getFoodItem().getName() + " x" + orderItem.getQty() + " | Total: Rp " + orderItem.getTotalPrice());
            totalQty += orderItem.getQty();
            totalPrice += orderItem.getTotalPrice();
            index++;
        }
        System.out.println("--------------------------------");
        System.out.println("Total Qty: " + totalQty + " | Total Harga: Rp. " + totalPrice);
        System.out.println("--------------------------------");
    }

    public void showReceipt() {
        clearApp();
        System.out.println("=========================");
        System.out.println("Struk Pembayaran BinarFud");
        System.out.println("=========================\n");
        System.out.println("Terima kasih sudah memesan");
        System.out.println("di BinarFud\n");
        System.out.println("Pembayaran: BinarCash\n");
        System.out.println("=========================");
        System.out.println("Simpan struk ini sebagai");
        System.out.println("bukti pembayaran");
        System.out.println("=========================");
    }

    public void saveTable(BufferedWriter writer, List<OrderItem> orderItems) throws IOException {
        writer.write("Nama Makanan       Qty   Total\n");
        writer.write("--------------------------------\n");

        for (OrderItem orderItem : orderItems) {
            FoodItem foodItem = orderItem.getFoodItem();
            int qty = orderItem.getQty();
            int total = orderItem.getTotalPrice();
            writer.write(String.format("%-17s %4d %5d%n", foodItem.getName(), qty, total));
        }
        writer.write("--------------------------------\n");
    }

    private static void showStatement(int[] colWidth) {
        for (int width : colWidth) {
            for (int index = 0; index < width; index++) {
                System.out.print("-");
            }
            System.out.print("+");
        }
        System.out.println();
    }

    public static void showOrderTable(List<OrderItem> orderItems) {
        String[] header = {"Nama Makanan", "Qty", "Total"};
        int[] colWidth = {15, 5, 7};
        int totalQty = 0;
        int totalOrder = 0;

        int index = 0;
        while (index < header.length) {
            System.out.printf("%-" + colWidth[index] + "s", header[index]);
            index++;
        }
        System.out.println();
        showStatement(colWidth);

        Iterator<OrderItem> iterator = orderItems.iterator();
        while (iterator.hasNext()) {
            OrderItem orderItem = iterator.next();
            int qty = orderItem.getQty();
            int price = orderItem.getFoodItem().getPrice();
            int total = qty * price;
            totalQty += qty;
            totalOrder += total;
            String itemName = orderItem.getFoodItem().getName();

            System.out.printf("%-" + colWidth[0] + "s%-5d%-7d%n", itemName, qty, total);
        }

        showStatement(colWidth);
        System.out.printf("%-" + colWidth[0] + "s%-5d%-7d%n", "Total", totalQty, totalOrder);
    }
}
