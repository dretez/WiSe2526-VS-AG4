package de.haw.vs.termin4.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.haw.vs.termin4.client.exceptions.ServerCommunicationException;
import de.haw.vs.termin4.client.exceptions.WriteException;
import de.haw.vs.termin4.common.DataStore;
import de.haw.vs.termin4.common.Logger;
import de.haw.vs.termin4.common.Request;
import de.haw.vs.termin4.common.json.JSON;
import de.haw.vs.termin4.common.network.CommunicationInterface;

import java.io.IOException;
import java.net.Socket;
import java.util.NoSuchElementException;

public class DataStoreInterface implements DataStore {
    private final Socket socket;

    public DataStoreInterface(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void write(int index, String data) throws WriteException, ServerCommunicationException {
        ObjectNode builder = Request.WRITE.toObjectNode();
        builder.put("index", index);
        builder.put("data", data);
        try {
            JsonNode json = JSON.parse(CommunicationInterface.sendAndAwait(socket, JSON.toString(builder)));
            if (!json.path("exception").isMissingNode())
                throw new WriteException(json.path("exception").asText() + ": " + json.path("message").asText());
        } catch (IOException e) {
            throw new ServerCommunicationException(e.getMessage());
        }
    }

    @Override
    public String read(int index) throws NoSuchElementException, ServerCommunicationException {
        ObjectNode builder = Request.READ.toObjectNode();
        builder.put("index", index);
        try {
            JsonNode json = JSON.parse(CommunicationInterface.sendAndAwait(socket, JSON.toString(builder)));
            if (!json.path("exception").isMissingNode()) {
                if (json.path("exception").asText().equals("NoSuchElementException"))
                    throw new NoSuchElementException(json.path("message").asText());
                else
                    throw new IOException(json.path("exception").asText() + ": " + json.path("message").asText());
            }
            if (json.path("data").isMissingNode())
                throw new IOException("Missing expected \"data\" value, raw JSON:\n\t" + json);
            return json.path("data").asText();
        } catch (IOException e) {
            Logger.error(e.getMessage());
            throw new ServerCommunicationException(e.getMessage());
        }
    }
}
