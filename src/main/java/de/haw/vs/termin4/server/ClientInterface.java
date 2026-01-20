package de.haw.vs.termin4.server;

import java.net.Socket;

public class ClientInterface {
    private final Socket socket;

    ClientInterface(Socket socket) {
        this.socket = socket;
    }

    public Socket socket() {
        return socket;
    }
}
