package de.haw.vs.termin4.client.command;

import de.haw.vs.termin4.client.Terminal;

import java.util.List;

final class HelpCmd extends ClientCommand {
    @Override
    protected void handle(List<String> args, Terminal terminal) {
        System.out.println("Commands:");
        System.out.println("help - Prints this list");
        System.out.println("read <index> - Reads the value at the specified index");
        System.out.println("write <index> <data> - Sets the specified index to the provided data");
        System.out.println("quit - closes this terminal");
    }
}
