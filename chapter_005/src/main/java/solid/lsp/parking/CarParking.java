package solid.lsp.parking;

import java.util.List;

public class CarParking implements Parking {
    private List<Parkable> vehicles;
    private int unitsLeft;

    @Override
    public boolean park(Parkable parkable) {
        int dimensions = parkable.getDimensions();
        // parking logic...
        return false;
    }
}
