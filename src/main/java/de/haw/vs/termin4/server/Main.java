package de.haw.vs.termin4.server;

import de.haw.vs.termin4.common.Logger;
import de.haw.vs.termin4.common.network.Port;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Logger.setColorMode(true);
        Server server = new Server(Port.DEFAULT.port());
        server.start();
    }
}
