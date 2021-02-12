package solid.lsp.parking.parkings;

import solid.lsp.parking.vehicles.Parkable;

import java.util.ArrayList;
import java.util.List;

public class CarParking implements Parking {
    private final List<Parkable> vehicles = new ArrayList<>();
    private int units;

    public CarParking(int units) {
        this.units = units;
    }

    @Override
    public boolean park(Parkable parkable) {
        int dims = parkable.getDimensions();
        boolean canPark = !vehicles.contains(parkable) && dims >= 1 && units >= dims;
        if (canPark) {
            units -= dims;
            vehicles.add(parkable);
        }
        return canPark;
    }

    @Override
    public boolean remove(Parkable parkable) {
        boolean success = vehicles.remove(parkable);
        if (success) {
            units += parkable.getDimensions();
        }
        return success;
    }

    @Override
    public boolean isParked(Parkable parkable) {
        return vehicles.contains(parkable);
    }
}
