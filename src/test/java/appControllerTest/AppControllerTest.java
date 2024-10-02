package appControllerTest;

import appModel.FoodItem;
import appView.AppView;

import appController.AppController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Scanner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AppControllerTest {

    @Test
    @DisplayName("test executeItemChoice with positive qty")
    public void testPositiveCase() {
        AppController appController = new AppController();
        FoodItem foodItem = new FoodItem("Nasi + Ayam", 18000);
        int qty = 3;

        AppView viewMock = mock(AppView.class);
        when(viewMock.getQty(any(), any())).thenReturn(qty);

        try (Scanner scanner = new Scanner("3\n")) {
            appController.executeItemChoice(1, scanner);
        }
    }

    @Test
    @DisplayName("test executeItemChoice with zero qty")
    public void testNegativeCase() {
        AppController appController = new AppController();
        FoodItem foodItem = new FoodItem("Es Teh Manis", 3000);

        AppView viewMock = mock(AppView.class);
        when(viewMock.getQty(any(), any())).thenReturn(0);

        try (Scanner scanner = new Scanner("0\n")) {
            appController.executeItemChoice(1, scanner);
        }
    }
}