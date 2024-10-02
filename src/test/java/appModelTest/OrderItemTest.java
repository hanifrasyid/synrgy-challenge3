package appModelTest;

import appModel.FoodItem;
import appModel.OrderItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrderItemTest {

    @Test
    @DisplayName("test constructor and getters")
    public void testPositiveCase() {
        FoodItem foodItem = new FoodItem("Nasi Goreng", 15000);
        OrderItem orderItem = new OrderItem(foodItem, 3);

        assertEquals(foodItem, orderItem.getFoodItem());
        assertEquals(3, orderItem.getQty());
        assertEquals(45000, orderItem.getTotalPrice());
    }

    @Test
    @DisplayName("test negative quantity")
    public void testNegativeCase() {
        FoodItem foodItem = new FoodItem("Mie Goreng", 13000);
        assertThrows(IllegalArgumentException.class, () -> new OrderItem(foodItem, -2));
    }
}