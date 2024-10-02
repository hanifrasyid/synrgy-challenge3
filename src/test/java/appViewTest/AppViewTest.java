package appViewTest;

import appModel.FoodItem;
import appView.AppView;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class AppViewTest {

    @Test
    @DisplayName("test get qty with valid input")
    public void testPositiveCase() {
        AppView appView = new AppView();
        String input = "5\n";       // simulasi isi input valid
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        Scanner scanner = new Scanner(System.in);

        FoodItem foodItem = new FoodItem("Es Jeruk", 5000);
        int qty = appView.getQty(scanner, foodItem);

        assertEquals(5, qty);
    }

    @Test
    @DisplayName("test get qty with invalid input")
    public void testNegativeCase() {
        AppView appView = new AppView();
        String input = "hai\n";     // simulasi isi input invalid
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        Scanner scanner = new Scanner(System.in);

        FoodItem foodItem = new FoodItem("Nasi Goreng", 15000);

        assertThrows(java.util.InputMismatchException.class, () -> appView.getQty(scanner, foodItem));
    }
}