package solid.lsp.parking.vehicles;

public class Truck extends Vehicle implements Parkable {
    private final int dimensions;

    public Truck(int dimensions) {
        this.dimensions = dimensions;
    }

    @Override
    public int getDimensions() {
        return dimensions;
    }
}
