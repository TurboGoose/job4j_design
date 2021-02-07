package solid.lsp.warehouse;

import solid.lsp.warehouse.products.Food;
import solid.lsp.warehouse.storages.Storage;

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
