package de.haw.vs.termin4.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.haw.vs.termin4.common.json.JSON;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum Request {
    READ("read"),
    WRITE("write"),

    READ_REPLY("read_reply"),
    WRITE_REPLY("write_reply"),
    ;

    private final String request;

    Request(String request) {
        this.request = request;
    }

    public static Request fromString(String string) {
        Optional<Request> req = Arrays.stream(values()).filter(v -> v.request.equals(string)).findFirst();
        return req.orElse(null);
    }

    public ObjectNode toObjectNode() {
        ObjectNode node = JSON.getEmptyObject();
        node.put("request", request);
        return node;
    }
}
