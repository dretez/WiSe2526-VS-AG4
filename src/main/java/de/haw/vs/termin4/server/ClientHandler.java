package de.haw.vs.termin4.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.haw.vs.termin4.common.DataStore;
import de.haw.vs.termin4.common.Logger;
import de.haw.vs.termin4.common.json.JSON;
import de.haw.vs.termin4.common.network.CommunicationInterface;
import de.haw.vs.termin4.server.request.RequestHandler;

import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread {
    private final ClientInterface client;
    private final DataStore ds;

    public ClientHandler(Socket client, DataStore dataStore) {
        this.client = new ClientInterface(client);
        this.ds = dataStore;
    }

    @Override
    public void run() {
        while (true) {
            try {
                handleNextRequest();
            } catch (IOException _) {
                break;
            }
        }
    }

    private void handleNextRequest() throws IOException {
        String request = CommunicationInterface.awaitReply(client.socket());
        if (request == null) throw new IOException("Connection closed");
        try {
            RequestHandler.handle(request, client, ds);
        } catch (JsonProcessingException | IllegalStateException e) {
            Logger.error(e.getMessage());
            ObjectNode builder = JSON.getEmptyObject();
            builder.put("exception", e.getClass().getSimpleName());
            builder.put("message", e.getMessage());
            CommunicationInterface.sendRequest(client.socket(), JSON.toString(builder));
        }
    }
}
