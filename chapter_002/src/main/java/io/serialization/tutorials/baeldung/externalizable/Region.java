package io.serialization.tutorials.baeldung.externalizable;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Region extends Country {
    private long population;
    private String climate;

    public void setPopulation(long population) {
        this.population = population;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeLong(population);
        out.writeUTF(climate);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        this.population = in.readLong();
        this.climate = in.readUTF();
    }

    @Override
    public String toString() {
        return "Region{" +
                "population=" + population +
                ", climate='" + climate + '\'' +
                "} " + super.toString();
    }
}
