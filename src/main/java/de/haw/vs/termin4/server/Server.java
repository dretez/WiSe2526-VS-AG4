package de.haw.vs.termin4.server;

import de.haw.vs.termin4.common.DataStore;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    private final ServerSocket serverSocket;
    private final DataStore ds;

    Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        ds = new ServerDataStore();
    }

    public void start() {
        while (true) {
            try {
                new ClientHandler(serverSocket.accept(), ds).start();
            } catch (IOException e) {
                return;
            }
        }
    }
}
