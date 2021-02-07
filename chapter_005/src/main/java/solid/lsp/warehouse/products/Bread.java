package solid.lsp.warehouse.products;

import java.time.LocalDate;

public class Bread extends Food {
    public Bread(String name, int price, double discount, LocalDate createDate, LocalDate expiryDate) {
        super(name, price, discount, createDate, expiryDate);
    }
}
