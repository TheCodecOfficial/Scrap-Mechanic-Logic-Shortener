package src.Graph;

import java.util.*;

public class Graph {

    List<Node> nodes;

    public Graph(List<Node> _nodes) {
        nodes = _nodes;
        System.out.println("Created graph of size: " + nodes.size());
    }

    public void shorten(){
        for (Node n : nodes) {
            // n.describe();
            if (isLowGateAndOnlyHasOneInput(n)) {
                // System.out.println("^ will remove this :) ");

                // Get the single input we need to rewire
                Node in = n.connectedFrom.get(0);

                // Remove connections
                in.connectedTo.clear();
                n.connectedFrom.clear();
                for (Node next : n.connectedTo){
                    next.connectedFrom.remove(n);
                }

                // Rewire to output
                in.connectedTo.addAll(n.connectedTo);
                for (Node next : n.connectedTo){
                    next.connectedFrom.add(in);
                }

                // Remove rest
                n.connectedTo.clear();

                // Mark gate as deleted
                n.deleted = true;
            }
        }
    }

    // Rules
    boolean isLowGateAndOnlyHasOneInput(Node n) {
        boolean t = (n.type <= 2 && n.connectedFrom.size() == 1 && n.connectedTo.size() > 0);
        n.describe();
        if (t) System.out.println("DELTE");
        return t;
    }

}
