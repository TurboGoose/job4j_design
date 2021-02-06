package solid.lsp.productwarehouse.storages;

import solid.lsp.productwarehouse.products.Food;

import java.util.List;

public interface Storage {
    void add(Food food);
    boolean accept(Food food);
    List<Food> clear();
}
