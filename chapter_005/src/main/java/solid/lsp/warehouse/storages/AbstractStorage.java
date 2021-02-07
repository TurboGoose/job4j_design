package solid.lsp.warehouse.storages;

import solid.lsp.warehouse.products.Food;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStorage implements Storage {
    protected List<Food> store = new ArrayList<>();

    @Override
    public List<Food> clear() {
        List<Food> oldStore = store;
        store = new ArrayList<>();
        return oldStore;
    }
}
