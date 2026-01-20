package de.haw.vs.termin4.common.network;

public enum Port {
    DEFAULT(3000);

    private final int port;

    Port(int value) {
        this.port = value;
    }

    public int port() {
        return port;
    }
}
