package solid.lsp.productwarehouse.storages;

import solid.lsp.productwarehouse.products.Food;

import java.time.LocalDate;

public class Warehouse extends AbstractStorage {
    @Override
    public void add(Food food) {
        store.add(food);
    }

    @Override
    public boolean accept(Food food) {
        double expiryPercent = food.calculateExpiryPercent(LocalDate.now());
        return expiryPercent >= 0 && expiryPercent <= 0.25;
    }
}
