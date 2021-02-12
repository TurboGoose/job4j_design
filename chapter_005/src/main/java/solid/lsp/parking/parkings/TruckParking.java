package solid.lsp.parking.parkings;

import solid.lsp.parking.vehicles.Parkable;

import java.util.ArrayList;
import java.util.List;

public class TruckParking implements Parking {
    private final List<Parkable> vehicles = new ArrayList<>();
    private int units;

    public TruckParking(int units) {
        this.units = units;
    }

    @Override
    public boolean park(Parkable parkable) {
        boolean canPark = !vehicles.contains(parkable) && parkable.getDimensions() > 1 && units >= 1;
        if (canPark) {
            units--;
            vehicles.add(parkable);
        }
        return canPark;
    }

    @Override
    public boolean remove(Parkable parkable) {
        boolean success = vehicles.remove(parkable);
        if (success) {
            units++;
        }
        return success;
    }

    @Override
    public boolean isParked(Parkable parkable) {
        return vehicles.contains(parkable);
    }
}
