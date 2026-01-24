package de.haw.vs.termin4.client.command;

import de.haw.vs.termin4.client.Terminal;
import de.haw.vs.termin4.common.Logger;

import java.io.IOException;
import java.util.List;

final class WriteCmd extends ClientCommand {
    @Override
    protected void handle(List<String> args, Terminal terminal) {
        if (args.size() != 3)
            throw new IllegalArgumentException("Expected use: write <index> <data>");
        try {
            int index = Integer.parseInt(args.get(1));
            String data = args.get(2);
            long startTime = System.nanoTime();
            terminal.client().write(index, data);
            long endTime = System.nanoTime();
            Logger.println("Write call took: " + ((endTime - startTime) / 10e6) + " ms");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("<index> must be a numerical value", e);
        }
    }
}
