package de.haw.vs.termin4.client.command;

import de.haw.vs.termin4.client.Terminal;

import java.io.IOException;
import java.util.List;

final class QuitCmd extends ClientCommand {
    @Override
    protected void handle(List<String> args, Terminal terminal) throws IOException {
        terminal.client().stop();
        terminal.stop();
    }
}
