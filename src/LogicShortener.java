package src;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

import org.json.*;

import src.Graph.Graph;
import src.Graph.Node;

public class LogicShortener {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(new File("path.txt"));
        String path = scanner.nextLine();

        Path p = Paths.get(path);

        Stream<String> lines = Files.lines(p);
        String data = lines.collect(Collectors.joining("\n"));
        lines.close();

        JSONObject blueprint = new JSONObject(data);
        JSONArray childsArray = blueprint.getJSONArray("bodies").getJSONObject(0).getJSONArray("childs");
        System.out.println(childsArray.length());

        List<JSONObject> jsonGates = new ArrayList<JSONObject>();
        for (int i = 0; i < childsArray.length(); i++) {
            JSONObject child = (JSONObject) childsArray.get(i);
            String shape = child.getString("shapeId");
            if (shape.equals("9f0f56e8-2c31-4d83-996c-d00a9b296c3f")) {
                jsonGates.add(child);
            }
        }

        List<Node> gates = new ArrayList<Node>();
        for (JSONObject child : jsonGates) {
            JSONObject controller = (JSONObject) child.get("controller");
            Node node = new Node(controller.getInt("id"), (byte) controller.getInt("mode"));
            gates.add(node);
        }

        // Groundbreaking O(n^2 * Δ(G)) algorithm 🤠
        for (int i = 0; i < jsonGates.size(); i++) {

            JSONObject controller = (JSONObject) jsonGates.get(i).get("controller");
            Object cont = controller.get("controllers");

            if (!cont.equals(null)) {
                JSONArray controllers = (JSONArray) cont;

                System.out.println("Gate " + gates.get(i).ID + " has inputs from gates:");
                for (int j = 0; j < controllers.length(); j++) {

                    int id = controllers.getJSONObject(j).getInt("id");
                    System.out.println(id);
                    for (Node gate : gates) {
                        if (id == gate.ID)
                            gates.get(i).addConnection(gate, false);

                    }
                }
            }
        }

        Graph graph = new Graph(gates);
        graph.describe();
    }
}
