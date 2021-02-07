package solid.lsp.warehouse.products;

import java.time.LocalDate;
import java.util.Objects;

import static java.time.temporal.ChronoUnit.DAYS;

public abstract class Food {
    protected String name;
    protected int price;
    protected double discount;
    protected LocalDate createDate;
    protected LocalDate expiryDate;

    public Food(String name, int price, double discount, LocalDate createDate, LocalDate expiryDate) {
        validate(price, discount, createDate, expiryDate);
        this.name = name;
        this.price = price;
        this.discount = discount;       // 0 <= discount <= 1
        this.createDate = createDate;
        this.expiryDate = expiryDate;
    }

    private void validate(int price, double discount, LocalDate createDate, LocalDate expiryDate) {
        if (price < 0) {
            throw new IllegalArgumentException("Negative price");
        }
        if (discount < 0 || discount > 1) {
            throw new IllegalArgumentException("Illegal discount");
        }
        if (expiryDate.isBefore(createDate)) {
            throw new IllegalArgumentException("Expired date is before creation date");
        }
    }

    public double calculateExpiryPercent(LocalDate forDate) {
        long expiryPeriodInDays = DAYS.between(createDate, expiryDate);
        long daysSinceCreation = DAYS.between(createDate, forDate);
        double expiryPercent = (double) daysSinceCreation / expiryPeriodInDays;
        if (expiryPercent < 0) {
            throw new IllegalArgumentException("Passed date is before product creation");
        }
        return expiryPercent;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return (int) (price * (1 - discount));
    }

    public double getDiscount() {
        return discount;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return price == food.price && Objects.equals(name, food.name) && Objects.equals(createDate, food.createDate) && Objects.equals(expiryDate, food.expiryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, createDate, expiryDate);
    }
}
