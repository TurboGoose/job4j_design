package solid.lsp.productwarehouse;

import solid.lsp.productwarehouse.products.Food;
import solid.lsp.productwarehouse.storages.Shop;
import solid.lsp.productwarehouse.storages.Storage;
import solid.lsp.productwarehouse.storages.Trash;
import solid.lsp.productwarehouse.storages.Warehouse;

import java.time.LocalDate;

public class ControlQuality {
    private final Warehouse warehouse = new Warehouse();
    private final Shop shop = new Shop();
    private final Trash trash = new Trash();

    public void distributeProduct(Food food) {
        Storage targetStorage;
        double expiryPercent = food.calculateExpiryPercent(LocalDate.now());
        if (expiryPercent < 0.25) {
            targetStorage = warehouse;
        } else if (expiryPercent < 1) {
            targetStorage = shop;
            if (expiryPercent > 0.75) {
                food.setDiscount(0.2);
            }
        } else {
            targetStorage = trash;
        }
        targetStorage.put(food);
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public Shop getShop() {
        return shop;
    }

    public Trash getTrash() {
        return trash;
    }
}
