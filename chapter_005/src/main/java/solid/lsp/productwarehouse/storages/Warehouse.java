package solid.lsp.productwarehouse.storages;

import solid.lsp.productwarehouse.products.Food;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Storage {
    private final List<Food> store = new ArrayList<>();

    @Override
    public void put(Food food) {
        if (food != null) {
            store.add(food);
        }
    }

    public List<Food> getStore() {
        return store;
    }
}
