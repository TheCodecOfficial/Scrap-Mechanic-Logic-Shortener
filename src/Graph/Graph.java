package src.Graph;

import java.util.*;

public class Graph {

    List<Node> nodes;

    public Graph(List<Node> _nodes) {
        nodes = _nodes;
    }

    public void describe() {
        if (nodes.size() == 0) {
            System.out.println("Graph is empty.");
            return;
        }

        for (Node n : nodes){
            n.describe();
            if (isLowGateAndOnlyHasOneInput(n)){
                System.out.println("^ will remove this :) ");
            }
        }

        /*Node currentNode = nodes.get(0);
        while(currentNode.connectedTo.size() != 0){
            currentNode.describe();
            currentNode = currentNode.connectedTo.get(0);
            currentNode.describe();
        }*/

    }

    // Rules
    Boolean isLowGateAndOnlyHasOneInput(Node n){
        return (n.type <= 2 && n.connectedFrom.size() == 1);
    }

}
