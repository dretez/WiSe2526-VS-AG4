package de.haw.vs.termin4.client.command;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.haw.vs.termin4.client.Terminal;
import de.haw.vs.termin4.common.Request;
import de.haw.vs.termin4.common.json.JSON;
import de.haw.vs.termin4.common.network.CommunicationInterface;

import java.io.IOException;
import java.util.List;

final class ReadCmd extends ClientCommand {
    @Override
    protected void handle(List<String> args, Terminal terminal) throws IOException {
        if (args.size() != 2)
            throw new IllegalArgumentException("Expected use: read <index>");
        ObjectNode builder = Request.READ.toObjectNode();
        try {
            int index = Integer.parseInt(args.get(1));
            builder.put("index", index);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("<index> must be a numerical value", e);
        }
        JsonNode json = JSON.parse(CommunicationInterface.sendAndAwait(terminal.client().socket(), JSON.toString(builder)));
        if (!json.path("exception").isMissingNode())
            throw new IOException(json.path("exception").asText() + ": " + json.path("message").asText());
        if (json.path("data").isMissingNode())
            throw new IOException("Missing expected \"data\" value, raw JSON:\n\t" + json);
        System.out.println(json.path("data").asText());
    }
}
