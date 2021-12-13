package src.Graph;

import java.util.ArrayList;
import java.util.List;

public class Node {

    public byte type;
    List<Node> connectedTo;
    List<Node> connectedFrom;

    public int ID;

    public Node(int id, byte _type) {
        type = _type;
        ID = id;

        connectedTo = new ArrayList<Node>();
        connectedFrom = new ArrayList<Node>();

        System.out.println("Created node (gate) with id: " + ID + " and type: " + type);
    }

    public void addConnection(Node node, Boolean from) {
        if (from) {
            connectedFrom.add(node);
            return;
        }
        connectedTo.add(node);
        node.addConnection(this, true);
    }

    public void describe() {
        String typeName;
        switch (type) {
            case 0:
                typeName = "AND";
                break;
            case 1:
                typeName = "OR";
                break;
            case 2:
                typeName = "XOR";
                break;
            case 3:
                typeName = "NAND";
                break;
            case 4:
                typeName = "NOR";
                break;
            case 5:
                typeName = "XNOR";
                break;
            default:
                typeName = "sussy bollocks";
                break;
        }
        System.out.println(
                "Node " + ID + " is of type " + typeName + " (" + type + ") and is connected to " + connectedTo.size()
                        + " and by " + connectedFrom.size() + " nodes.");
    }

}