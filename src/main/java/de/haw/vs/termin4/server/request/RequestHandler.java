package de.haw.vs.termin4.server.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import de.haw.vs.termin4.common.DataStore;
import de.haw.vs.termin4.common.Logger;
import de.haw.vs.termin4.common.Request;
import de.haw.vs.termin4.common.json.JSON;
import de.haw.vs.termin4.server.ClientInterface;

import java.io.IOException;

public abstract sealed class RequestHandler permits ReadRequest, WriteRequest {
    public static void handle(String request, ClientInterface client, DataStore dataStore) throws JsonProcessingException, IllegalStateException {
        JsonNode json = JSON.parse(request);
        if (json.path("request").isMissingNode())
            throw new IllegalStateException("Missing request field, raw JSON: " + json);
        RequestHandler handler = switch (Request.fromString(json.path("request").asText())) {
            case Request.READ-> new ReadRequest();
            case Request.WRITE-> new WriteRequest();
            default -> throw new IllegalStateException("Unexpected value: " + json.path("request"));
        };
        try {
            handler.handle(json, client, dataStore);
        } catch (IOException e) {
            Logger.error(e.getMessage());
        }
    }

    protected abstract void handle(JsonNode json, ClientInterface client, DataStore dataStore) throws IOException;
}
