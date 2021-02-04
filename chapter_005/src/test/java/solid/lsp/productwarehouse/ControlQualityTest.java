package solid.lsp.productwarehouse;

import org.junit.jupiter.api.Test;
import solid.lsp.productwarehouse.products.Bread;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ControlQualityTest {
    @Test
    public void whenExpiredPercentLessThan25GoToTheWarehouse() {
        ControlQuality controlQuality = new ControlQuality();
        Bread bread = new Bread(
                "Bread",
                100,
                0,
                LocalDate.now().minusDays(1),
                LocalDate.now().plusDays(100)
        );
        controlQuality.distributeProduct(bread);
        assertThat(controlQuality.getWarehouse().getStore(), contains(bread));
        assertThat(controlQuality.getShop().getStore().isEmpty(), is(true));
        assertThat(controlQuality.getTrash().getStore().isEmpty(), is(true));
    }

    @Test
    public void whenExpiredPercentIsBetween25And75GoToTheShop() {
        ControlQuality controlQuality = new ControlQuality();
        Bread bread = new Bread(
                "Bread",
                100,
                0,
                LocalDate.now().minusDays(50),
                LocalDate.now().plusDays(50)
        );
        controlQuality.distributeProduct(bread);
        assertThat(controlQuality.getWarehouse().getStore().isEmpty(), is(true));
        assertThat(controlQuality.getShop().getStore(), contains(bread));
        assertThat(bread.getDiscount(), is(0.0));
        assertThat(controlQuality.getTrash().getStore().isEmpty(), is(true));
    }

    @Test
    public void whenExpiredPercentIsBetween75And100GoToTheShopWithDiscount() {
        ControlQuality controlQuality = new ControlQuality();
        Bread bread = new Bread(
                "Bread",
                100,
                0,
                LocalDate.now().minusDays(100),
                LocalDate.now().plusDays(1)
        );
        controlQuality.distributeProduct(bread);
        assertThat(controlQuality.getWarehouse().getStore().isEmpty(), is(true));
        assertThat(controlQuality.getShop().getStore(), contains(bread));
        assertThat(bread.getDiscount(), is(0.2));
        assertThat(controlQuality.getTrash().getStore().isEmpty(), is(true));
    }

    @Test
    public void whenProductIsExpiredGoToTheTrash() {
        ControlQuality controlQuality = new ControlQuality();
        Bread bread = new Bread(
                "Bread",
                100,
                0,
                LocalDate.now().minusDays(100),
                LocalDate.now().minusDays(1)
        );
        controlQuality.distributeProduct(bread);
        assertThat(controlQuality.getWarehouse().getStore().isEmpty(), is(true));
        assertThat(controlQuality.getShop().getStore().isEmpty(), is(true));
        assertThat(controlQuality.getTrash().getStore(), contains(bread));
    }
}
