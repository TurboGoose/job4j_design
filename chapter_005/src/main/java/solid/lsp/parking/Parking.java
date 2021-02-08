package solid.lsp.parking;

import java.util.List;

public class Parking {
    private List<Parkable> vehicles;
    private int unitsLeft;

    public void park(Parkable parkable) {
        int dimensions = parkable.getDimensions();
        // parking logic...
    }
}
