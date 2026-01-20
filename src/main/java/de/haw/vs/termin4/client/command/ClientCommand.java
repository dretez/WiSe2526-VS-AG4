package de.haw.vs.termin4.client.command;

import de.haw.vs.termin4.client.Terminal;
import de.haw.vs.termin4.common.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract sealed class ClientCommand permits HelpCmd, QuitCmd, ReadCmd, WriteCmd {
    public static void handle(String[] args, Terminal terminal) {
        List<String> argList = new ArrayList<>(List.of(args));
        try {
            ClientCommand handler = switch (argList.getFirst()) {
                case "help" -> new HelpCmd();
                case "read" -> new ReadCmd();
                case "write" -> new WriteCmd();
                case "quit" -> new QuitCmd();
                default -> throw new IllegalArgumentException("Unknown command: " + argList.getFirst());
            };
            handler.handle(argList, terminal);
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
    }

    protected abstract void handle(List<String> args, Terminal terminal) throws IOException;
}
