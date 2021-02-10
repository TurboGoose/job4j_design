package solid.lsp.parking.parkings;

import org.junit.jupiter.api.Test;
import solid.lsp.parking.vehicles.Car;
import solid.lsp.parking.vehicles.Parkable;
import solid.lsp.parking.vehicles.Truck;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class BigParkingTest {
    @Test
    public void whenParkingCarThenSuccess() {
        Parking parking = new BigParking(1, 2);
        Parkable car = new Car();
        assertThat(parking.park(car), is(true));
        assertThat(parking.isParked(car), is(true));
    }

    @Test
    public void whenParkingTruckThenSuccess() {
        Parking parking = new BigParking(1, 2);
        Parkable truck = new Truck(2);
        assertThat(parking.park(truck), is(true));
        assertThat(parking.isParked(truck), is(true));
    }

    @Test
    public void whenParkingTooManyCarsThenFail() {
        Parking parking = new BigParking(1, 2);
        parking.park(new Car());
        Parkable car = new Car();
        assertThat(parking.park(car), is(false));
        assertThat(parking.isParked(car), is(false));
    }

    @Test
    public void whenTruckParkingIsFullThenParkTruckInTheCarParking() {
        Parking parking = new BigParking(2, 1);
        parking.park(new Truck(2));
        Parkable truck = new Truck(2);
        assertThat(parking.park(truck), is(true));
        assertThat(parking.isParked(truck), is(true));
    }

    @Test
    public void whenParkingTooManyTrucksThenFail() {
        Parking parking = new BigParking(1, 1);
        parking.park(new Truck(2));
        Parkable truck = new Truck(2);
        assertThat(parking.park(truck), is(false));
        assertThat(parking.isParked(truck), is(false));
    }

    @Test
    public void whenRemovingNonexistentCarThenFail() {
        Parking parking = new BigParking(1, 1);
        assertThrows(IllegalArgumentException.class, () -> parking.remove(new Car()));
    }

    @Test
    public void whenRemovingNonexistentTruckThenFail() {
        Parking parking = new BigParking(1, 1);
        assertThrows(IllegalArgumentException.class, () -> parking.remove(new Truck(2)));
    }

    @Test
    public void whenRemovingExistentCarThenSuccess() {
        Parking parking = new BigParking(1, 1);
        Parkable car = new Car();
        assertThat(parking.park(car), is(true));
        assertThat(parking.isParked(car), is(true));
        assertThat(parking.remove(car), is(true));
        assertThat(parking.isParked(car), is(false));
    }

    @Test
    public void whenRemovingExistentTruckFromTruckParkingThenSuccess() {
        Parking parking = new BigParking(1, 1);
        Parkable truck = new Truck(2);
        assertThat(parking.park(truck), is(true));
        assertThat(parking.isParked(truck), is(true));
        assertThat(parking.remove(truck), is(true));
        assertThat(parking.isParked(truck), is(false));
    }

    @Test
    public void whenRemovingExistentTruckFromCarParkingThenSuccess() {
        Parking parking = new BigParking(2, 1);
        parking.park(new Truck(2));
        Parkable truck = new Truck(2);
        assertThat(parking.park(truck), is(true));
        assertThat(parking.isParked(truck), is(true));
        assertThat(parking.remove(truck), is(true));
        assertThat(parking.isParked(truck), is(false));
    }
}
