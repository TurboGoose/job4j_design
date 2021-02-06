package solid.lsp.productwarehouse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import solid.lsp.productwarehouse.products.Bread;
import solid.lsp.productwarehouse.storages.Shop;
import solid.lsp.productwarehouse.storages.Trash;
import solid.lsp.productwarehouse.storages.Warehouse;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ControlQualityTest {
    ControlQuality controlQuality;

    @BeforeEach
    public void init() {
        controlQuality = new ControlQuality();
        controlQuality.addStorage(new Warehouse());
        controlQuality.addStorage(new Shop());
        controlQuality.addStorage(new Trash());
    }

    @Test
    public void whenExpiredPercentLessThan25GoToTheWarehouse() {
        Bread bread = new Bread(
                "Bread",
                100,
                0,
                LocalDate.now().minusDays(1),
                LocalDate.now().plusDays(100)
        );
        controlQuality.distribute(bread);
        assertThat(controlQuality.getStorages().get(0).clear(), contains(bread));
        assertThat(controlQuality.getStorages().get(1).clear().isEmpty(), is(true));
        assertThat(controlQuality.getStorages().get(2).clear().isEmpty(), is(true));
    }

    @Test
    public void whenExpiredPercentIsBetween25And75GoToTheShop() {
        Bread bread = new Bread(
                "Bread",
                100,
                0,
                LocalDate.now().minusDays(50),
                LocalDate.now().plusDays(50)
        );
        controlQuality.distribute(bread);
        assertThat(controlQuality.getStorages().get(0).clear().isEmpty(), is(true));
        assertThat(controlQuality.getStorages().get(1).clear(), contains(bread));
        assertThat(bread.getDiscount(), is(0.0));
        assertThat(controlQuality.getStorages().get(2).clear().isEmpty(), is(true));


    }

    @Test
    public void whenExpiredPercentIsBetween75And100GoToTheShopWithDiscount() {
        Bread bread = new Bread(
                "Bread",
                100,
                0,
                LocalDate.now().minusDays(100),
                LocalDate.now().plusDays(1)
        );
        controlQuality.distribute(bread);
        assertThat(controlQuality.getStorages().get(0).clear().isEmpty(), is(true));
        assertThat(controlQuality.getStorages().get(1).clear(), contains(bread));
        assertThat(bread.getDiscount(), is(0.2));
        assertThat(controlQuality.getStorages().get(2).clear().isEmpty(), is(true));
    }

    @Test
    public void whenProductIsExpiredGoToTheTrash() {
        Bread bread = new Bread(
                "Bread",
                100,
                0,
                LocalDate.now().minusDays(100),
                LocalDate.now().minusDays(1)
        );
        controlQuality.distribute(bread);
        assertThat(controlQuality.getStorages().get(0).clear().isEmpty(), is(true));
        assertThat(controlQuality.getStorages().get(1).clear().isEmpty(), is(true));
        assertThat(controlQuality.getStorages().get(2).clear(), contains(bread));
    }
}
