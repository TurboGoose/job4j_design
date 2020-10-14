package io.serialization.tutorials.baeldung.externalizable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Country implements Externalizable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int code;

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(name);
        out.writeInt(code);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.name = in.readUTF();
        this.code = in.readInt();
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", code=" + code +
                '}';
    }
}
