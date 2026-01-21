package de.haw.vs.termin4.client;

import de.haw.vs.termin4.common.Logger;
import de.haw.vs.termin4.common.network.Port;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Logger.setColorMode(true);
        List<Socket> sockets = new ArrayList<>();
        sockets.add(new Socket("127.0.0.1", 8000));
        sockets.add(new Socket("127.0.0.1", 8001));
        sockets.add(new Socket("127.0.0.1", 8002));
        sockets.add(new Socket("127.0.0.1", 8003));
        try {
            Terminal.start(sockets);
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
    }
}