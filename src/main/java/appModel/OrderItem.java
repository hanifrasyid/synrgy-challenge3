package appModel;
import java.util.Optional;
import lombok.Data;

@Data
public class OrderItem {
    private final FoodItem foodItem;
    private final int qty;

    public OrderItem(FoodItem foodItem, int qty) {
        this.foodItem = foodItem;
        this.qty = qty;
        if (qty < 0) {
            throw new IllegalArgumentException("Kuantitas tidak boleh minus!");
        }
    }

    public int getTotalPrice() {
        return Optional.ofNullable(foodItem)
                .map(FoodItem::getPrice)
                .map(price -> price * qty)
                .orElse(0);
    }
}