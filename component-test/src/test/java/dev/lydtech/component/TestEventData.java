package dev.lydtech.component;

public class TestEventData {

    public static String INBOUND_DATA = "event data";

    public static String buildCtfExampleInboundEvent(String id) {
        return "{\"id\": \""+id+"\", \"data\": \""+INBOUND_DATA+"\"}";
    }
}
