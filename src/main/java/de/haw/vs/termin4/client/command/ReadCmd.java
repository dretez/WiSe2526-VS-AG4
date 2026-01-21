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
            Logger.println(terminal.client().read(index));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("<index> must be a numerical value", e);
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
