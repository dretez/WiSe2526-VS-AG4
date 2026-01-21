package de.haw.vs.termin4.client;

import de.haw.vs.termin4.client.command.ClientCommand;
import de.haw.vs.termin4.common.Logger;

import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class Terminal {
    private final Scanner sc;
    private final Client client;
    private boolean running;

    private Terminal(Scanner scanner, Client client) {
        this.sc = scanner;
        this.client = client;
        this.running = true;
    }

    public Client client() {
        return client;
    }

    public static void start(List<Socket> sockets) {
        login(sockets).terminalLoop();
    }

    private static Terminal login(List<Socket> sockets) {
        Scanner sc = new Scanner(System.in);
        Logger.println("Please enter a user name");
        Logger.print("> ");
        String name = sc.nextLine().trim();
        Client client = new Client(sockets, name);
        return new Terminal(sc, client);
    }

    public void terminalLoop() {
        welcome();
        while (running) {
            Logger.print("> ");
            String cmd = sc.nextLine().trim();
            ClientCommand.handle(cmd.trim().split("\\s+"), this);
        }
    }

    private void welcome() {
        Logger.println("Welcome " + client.name());
        Logger.println("Use help to get a list of available commands");
    }

    public void stop() {
        this.running = false;
    }
}
