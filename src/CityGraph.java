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

    //  To use it on dijkstra and a_star implementation:
    public Map<String, List<Edge>> getGraph() {
        return graph;
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
    public static class Edge {
        String to;
        int weight;

        public Edge(String to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "(" + to + ", " + weight + ")";
        }
    }
    public static class Node {
        String name;
        int distance;
        int fScore;
        public Node(String name, int distance) {
            this.name = name;
            this.distance = distance;
            this.fScore = 0; //default fScore.
        }

//      Constructor for A* (distance + fScore)
        public Node(String name, int distance, int fScore) {
            this.name = name;
            this.distance = distance;
            this.fScore = fScore;
        }

        @Override
        public String toString() {
            return name + " (" + distance + ")";
        }
    }

    public static void main(String[] args) {
        CityGraph city = new CityGraph();

        city.addRoad("A", "B", 3);
        city.addRoad("A", "C", 2);
        city.addRoad("B", "C", 4);
        city.addRoad("C", "D", 8);
        city.addRoad("B", "D", 6);

        System.out.println("Grafi:");
        city.displayGraph();

//        dijkstra dijkstraAlgorithm = new dijkstra(city);
//
//        System.out.println("\nRruga më e shkurtër nga A në D:");
//        List<String> path = dijkstraAlgorithm.findShortestPath("A", "D");
//        System.out.println(path);


//      Straight line distance from certain node to end
        Map<String, Integer> heuristic = new HashMap<>();
        heuristic.put("A", 7); //
        heuristic.put("B", 6);
        heuristic.put("C", 2);
        heuristic.put("D", 0);

        a_star aStarAlgorithm = new a_star();
        System.out.println("\nRruga me e shkurter nga A ne D: ");
        List<String> pathAStar = aStarAlgorithm.findPathAStar("A", "D", heuristic, city);
        System.out.println(pathAStar);
    }
}
