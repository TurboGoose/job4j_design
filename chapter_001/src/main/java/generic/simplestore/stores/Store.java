package generic.simplestore.stores;

import generic.simplestore.base.Base;

public interface Store<T extends Base> {
    void add(T model);

    boolean replace(String id, T model);

    boolean delete(String id);

    T findById(String id);
}
