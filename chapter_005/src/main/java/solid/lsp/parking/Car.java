package solid.lsp.parking;

public class Car extends Vehicle implements Parkable {
    @Override
    public int getDimensions() {
        return 1;
    }
}
