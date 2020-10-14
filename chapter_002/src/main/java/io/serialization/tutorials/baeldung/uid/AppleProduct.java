package io.serialization.tutorials.baeldung.uid;

import java.io.Serializable;

public class AppleProduct implements Serializable {
    private static final long serialVersionUID = 12345L;
    public String headphonePort;
    public String thunderboltPort;
    public String lightningPorts;

    @Override
    public String toString() {
        return "AppleProduct{" +
                "headphonePort='" + headphonePort + '\'' +
                ", thunderboltPort='" + thunderboltPort + '\'' +
                ", lightningPort='" + lightningPorts + '\'' +
                '}';
    }
}
