package de.haw.vs.termin4.client;

import de.haw.vs.termin4.client.command.ClientCommand;
import de.haw.vs.termin4.common.Logger;

import java.io.IOException;
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

    public static void start(String registryIP, int port) {
        login(registryIP, port).terminalLoop();
    }

    private static Terminal login(String registryIP, int port) {
        Scanner sc = new Scanner(System.in);
        Logger.println("Please enter a user name");
        while (true) {
            Logger.print("> ");
            String name = sc.nextLine().trim();
            try {
                Client client = new Client(name, registryIP, port);
                return new Terminal(sc, client);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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
