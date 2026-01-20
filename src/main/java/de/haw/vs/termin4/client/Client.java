package de.haw.vs.termin4.client;

import de.haw.vs.termin4.common.network.Port;

import java.io.IOException;
import java.net.Socket;

public class Client {
    private static final String DEFAULT_HOST = "127.0.0.1";

    private final Socket clientSocket;
    private final String name;

    public Client(String name, String host, int port) throws IOException {
        this.clientSocket = new Socket(host, port);
        this.name = name;
    }

    public Client(String name, String host) throws IOException {
        this(name, host, Port.DEFAULT.port());
    }

    public Client(String name) throws IOException {
        this(name, DEFAULT_HOST);
    }

    public String name() {
        return name;
    }

    public Socket socket() {
        return clientSocket;
    }

    public void stop() throws IOException {
        clientSocket.close();
    }
}

