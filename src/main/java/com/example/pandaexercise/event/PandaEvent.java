package com.example.pandaexercise.event;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class PandaEvent {
    private String event_type;
    private String data;
    private long timestamp;

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public static void main(String[] args) throws IOException {
        //
        ObjectMapper objectMapper = new ObjectMapper();
        String content = "{\"event_type\":\"baz\",\"data\":\"amet\",\"timestamp\":1553763831}";
        PandaEvent pandaEvent = objectMapper.readValue(content, PandaEvent.class);
        System.out.println(pandaEvent);
        System.out.println(objectMapper.writeValueAsString(pandaEvent).equals(content));
    }
}
