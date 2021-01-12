package tdd.principles;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MaxMin {
    public <T> T max(List<T> values, Comparator<T> comparator) {
        return findByComparingCondition(values, comparator, i -> i > 0);
    }

    public <T> T min(List<T> values, Comparator<T> comparator) {
        return findByComparingCondition(values, comparator, i -> i < 0);
    }

    private <T> T findByComparingCondition(List<T> values, Comparator<T> comparator, Predicate<Integer> condition) {
        if (values.size() == 0) {
            return null;
        }
        T result = values.get(0);
        for (int i = 1; i < values.size(); i++) {
            T value =  values.get(i);
            if (condition.test(comparator.compare(value, result))) {
                result = value;
            }
        }
        return result;
    }
}
