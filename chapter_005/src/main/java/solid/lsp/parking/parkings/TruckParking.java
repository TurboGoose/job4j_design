package solid.lsp.parking.parkings;

import solid.lsp.parking.vehicles.Parkable;

import java.util.List;

public class TruckParking implements Parking {
    private List<Parkable> vehicles;
    private final int units;
    private int availableUnits;

    public TruckParking(int units) {
        this.units = units;
        availableUnits = units;
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
