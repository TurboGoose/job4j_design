package solid.lsp.parking.parkings;

import solid.lsp.parking.vehicles.Parkable;

public interface Parking {
    boolean park(Parkable parkable);
    boolean remove(Parkable parkable);
    boolean isParked(Parkable parkable);
}
