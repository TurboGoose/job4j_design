package generic.simplestore.stores;

import generic.simplestore.base.Base;

import java.util.ArrayList;
import java.util.List;

public class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        int index = mem.indexOf(findById(id));
        if (index == -1) {
            return false;
        }
        mem.set(index, model);
        return true;
    }

    @Override
    public boolean delete(String id) {
        return mem.remove(findById(id));
    }

    @Override
    public T findById(String id) {
        return mem.stream()
                .filter((element) -> element.getId().equals(id))
                .findAny()
                .orElseThrow();
    }
}
