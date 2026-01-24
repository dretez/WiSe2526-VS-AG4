package de.haw.vs.termin4.client.command;

import de.haw.vs.termin4.client.Terminal;
import de.haw.vs.termin4.common.Logger;

import java.util.List;
import java.util.NoSuchElementException;

final class ReadCmd extends ClientCommand {
    @Override
    protected void handle(List<String> args, Terminal terminal) {
        if (args.size() != 2)
            throw new IllegalArgumentException("Expected use: read <index>");
        try {
            int index = Integer.parseInt(args.get(1));
            long startTime = System.nanoTime();
            String data = terminal.client().read(index);
            long endTime = System.nanoTime();
            Logger.println(data);
            Logger.println("Read call took: " + ((endTime - startTime) / 10e6) + " ms");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("<index> must be a numerical value", e);
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
