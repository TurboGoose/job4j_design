package solid.lsp.productwarehouse;

import solid.lsp.productwarehouse.products.Food;
import solid.lsp.productwarehouse.storages.Storage;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private final List<Storage> storages = new ArrayList<>();

    public void addStorage(Storage storage) {
        storages.add(storage);
    }

    public void distribute(Food food) {
        for (Storage storage : storages) {
            if (storage.accept(food)) {
                storage.add(food);
                break;
            }
        }
    }

    public List<Storage> getStorages() {
        return storages;
    }
}
