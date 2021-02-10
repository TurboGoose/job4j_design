package solid.lsp.parking.parkings;

import solid.lsp.parking.vehicles.Parkable;

public class BigParking implements Parking {
    private final Parking carParking;
    private final Parking truckParking;

    public BigParking(int carParkingUnits, int truckParkingUnits) {
        carParking = new CarParking(carParkingUnits);
        truckParking = new TruckParking(truckParkingUnits);
    }

    @Override
    public boolean park(Parkable parkable) {
        return false;
    }

    @Override
    public boolean remove(Parkable parkable) {
        return false;
    }

    @Override
    public boolean isParked(Parkable parkable) {
        return false;
    }
}
