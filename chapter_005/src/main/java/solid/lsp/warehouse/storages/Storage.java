package solid.lsp.warehouse.storages;

import solid.lsp.warehouse.products.Food;

import java.util.List;

public interface Storage {
    void add(Food food);
    boolean accept(Food food);
    List<Food> clear();
}
