package src;

import java.awt.Color;
import src.Graph.*;
import java.util.*;

public class LogicSynthesis {

    public static String synthesise(List<Node> gates) {

        int totalConnections = 0;
        int totalDeleted = 0;

        String blueprint = "{\"bodies\":[{\"childs\":[";
        List<String> gatesJSON = new ArrayList<String>();
        for (int i = 0; i < gates.size(); i++) {
            Node gate = gates.get(i);
            if (!gate.deleted) {
                totalConnections += gate.connectedTo.size();
                gatesJSON.add(gateToJSON(gate));
            } else {
                totalDeleted++;
            }
        }
        blueprint += String.join(",", gatesJSON);
        blueprint += "]}],\"version\": 3}";
        System.out.println("Total connections: " + totalConnections);
        System.out.println("Total gates deleted: " + totalDeleted);
        return blueprint;

    }

    static String gateToJSON(Node gate) {

        String connectionsJSON;
        if (gate.connectedTo.size() > 0) {
            Node[] connections = new Node[gate.connectedTo.size()];
            gate.connectedTo.toArray(connections);
            String[] connectionJSONparts = new String[connections.length];
            for (int i = 0; i < connections.length; i++) {
                connectionJSONparts[i] = "{\"id\": " + connections[i].ID + "}";
            }
            connectionsJSON = "[" + String.join(",", connectionJSONparts) + "]";
        } else
            connectionsJSON = "null";

        int x = gate.x;
        int y = gate.y;
        int z = gate.z;
        String JSON = "{\"color\":\"" + hexColor(gate.color) + "\",\"controller\":{\"active\":false,\"controllers\":"
                + connectionsJSON + ",\"id\":" + gate.ID + ",\"joints\":null,\"mode\":" + gate.type
                + "},\"pos\":{\"x\":" + x + ",\"y\":" + y + ",\"z\":" + z
                + "},\"shapeId\":\"9f0f56e8-2c31-4d83-996c-d00a9b296c3f\",\"xaxis\":1,\"zaxis\":-2}";

        return JSON;
    }

    static String hexColor(Color c) {
        int r = c.getRed();
        int g = c.getGreen();
        int b = c.getBlue();
        return String.format("#%02x%02x%02x", r, g, b);
    }

}