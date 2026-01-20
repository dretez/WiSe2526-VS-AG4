package de.haw.vs.termin4.server.request;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.haw.vs.termin4.common.DataStore;
import de.haw.vs.termin4.common.Request;
import de.haw.vs.termin4.common.json.JSON;
import de.haw.vs.termin4.common.network.CommunicationInterface;
import de.haw.vs.termin4.server.ClientInterface;

import java.io.IOException;

final class WriteRequest extends RequestHandler {
    @Override
    protected void handle(JsonNode json, ClientInterface client, DataStore dataStore) throws IOException {
        ObjectNode builder = Request.WRITE_REPLY.toObjectNode();
        try {
            JsonNode index = json.path("index");
            JsonNode data = json.path("data");
            if (index.isMissingNode())
                throw new IllegalArgumentException("Expected \"index\" argument");
            if (data.isMissingNode())
                throw new IllegalArgumentException("Expected \"data\" argument");
            dataStore.write(index.asInt(), data.asText());
        } catch (Exception e) {
            builder.put("exception", e.getClass().getSimpleName());
            builder.put("message", e.getMessage());
        } finally {
            CommunicationInterface.sendRequest(client.socket(), JSON.toString(builder));
        }
    }
}
