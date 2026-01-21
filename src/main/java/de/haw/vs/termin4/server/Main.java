package de.haw.vs.termin4.server;

import de.haw.vs.termin4.common.Logger;
import de.haw.vs.termin4.common.network.Port;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Logger.setColorMode(true);
        List<String> argv = List.of(args);
        if (checkIllegalArgs(argv))
            return;
        int port = argv.isEmpty() ? Port.DEFAULT.port() : Integer.parseInt(argv.getFirst());
        Server server = new Server(port);
        server.start();
    }

    private static boolean checkIllegalArgs(List<String> argv) {
        if (!argv.isEmpty()) {
            try {
                Integer.parseInt(argv.getFirst());
            } catch (NumberFormatException e) {
                Logger.error("First argument must be a port number");
                return true;
            }
        }
        return false;
    }
}
