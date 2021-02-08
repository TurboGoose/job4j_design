package solid.lsp.parking;

public class Truck extends Vehicle implements Parkable {
    @Override
    public int getDimensions() {
        return 2;
    }
}
