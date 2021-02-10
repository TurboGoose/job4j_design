package solid.lsp.parking.vehicles;

public class Car extends Vehicle implements Parkable {
    private final int dimensions = 1;
    @Override
    public int getDimensions() {
        return dimensions;
    }
}
