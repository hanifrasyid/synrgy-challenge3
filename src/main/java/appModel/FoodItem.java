package appModel;

import lombok.Data;

@Data
public class FoodItem {
    private final String name;
    private final int price;

    public FoodItem(String name, int price) {
        if (price < 0) {
            throw new IllegalArgumentException("Harga tidak boleh minus!");
        }
        this.name = name;
        this.price = price;
    }
}