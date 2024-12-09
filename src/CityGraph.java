import java.util.Map;
import java.util.*;

public class CityGraph {
    private Map<String, List<Edge>> graph;
    public CityGraph() {
        graph = new HashMap<>();
    }

    //  adding a vertex(e.g., an intersection in a city)
    public void addIntersection(String intersection) {
//      intersection representing the String, and if it is absent we need to create a new list of edges/roads from that intersection.
        graph.putIfAbsent(intersection, new ArrayList<>());
    }

    //  This is where all the edges/roads are added from root node to neighbor nodes
    public void addRoad(String from, String to, int weight) {
        addIntersection(from);
        addIntersection(to);
//      add the vertexes only if absent, and then get the vertex from which we start, and add that edge/road with its weight.
        graph.get(from).add(new Edge(to, weight));
    }

    public void displayGraph() {
        for(Map.Entry<String, List<Edge>> entry : graph.entrySet()) {
            System.out.print(entry.getKey() + ": ");
            for(Edge edge: entry.getValue()) {
                System.out.print("(" + edge.to + ", " + edge.weight + ") ");
            }
            System.out.println();
        }
    }



    //  Edge represents a road between vertexes which we will refer to as intersections.
    private static class Edge {
        String to;
        int weight;

        public Edge(String to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    private static class Node {
        String name;
        int distance;
        public Node(String name, int distance) {
            this.name = name;
            this.distance = distance;
        }
    }

    // Main për testim
    public static void main(String[] args) {
        CityGraph city = new CityGraph();

        // Shtimi i nyjeve dhe skajeve
        city.addRoad("A", "B", 3);
        city.addRoad("A", "C", 2);
        city.addRoad("B", "C", 4);
        city.addRoad("C", "D", 8);
        city.addRoad("B", "D", 6);

        // Shfaqja e grafit
        System.out.println("Grafi:");
        city.displayGraph();
    }
}
