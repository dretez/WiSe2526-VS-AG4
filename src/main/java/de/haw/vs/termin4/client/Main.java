package de.haw.vs.termin4.client;

import de.haw.vs.termin4.common.Logger;
import de.haw.vs.termin4.common.network.Port;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Logger.setColorMode(true);
        try {
            Terminal.start("127.0.0.1", Port.DEFAULT.port());
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
    }
}