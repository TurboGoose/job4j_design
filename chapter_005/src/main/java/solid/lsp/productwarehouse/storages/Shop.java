package solid.lsp.productwarehouse.storages;

import solid.lsp.productwarehouse.products.Food;

import java.time.LocalDate;

public class Shop extends AbstractStorage {
    @Override
    public void add(Food food) {
        double expiryPercent = food.calculateExpiryPercent(LocalDate.now());
        if (expiryPercent > 0.75) {
            food.setDiscount(0.2);
        }
        store.add(food);
    }

    @Override
    public boolean accept(Food food) {
        double expiryPercent = food.calculateExpiryPercent(LocalDate.now());
        return expiryPercent > 0.25 && expiryPercent < 1;
    }
}
