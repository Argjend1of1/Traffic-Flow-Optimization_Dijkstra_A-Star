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

    //  Applying Djikstra's Algorithm
    public List<String> findShortestPath(String start, String end) {
//      Saving the minimal distance from root/start.
        Map<String, Integer> distances = new HashMap<>();

//      To reconstruct the path from start to end if need be:
        Map<String, String> previousNodes = new HashMap<>();

//      To find the node/vertex with the minimal distance
        PriorityQueue<Node> pq = new PriorityQueue<>(
                Comparator.comparingInt(n -> n.distance)
        );

        for(String node : graph.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(start, 0); //start from the beginning/root

        pq.add(new Node(start, 0));

        while(!pq.isEmpty()) {
//          Pull nodes from the top, where at the top is the node with the minimal value:
            Node currentNode = pq.poll();
//          Get the name of the first intersection
            String currentIntersection = currentNode.name;

            if(currentIntersection.equals(end)) break;

//          go above, on add road we have graph.get(from).add(new Edge(to, weight), here we retrieve the value saved on graph on the currentIntersection which in our case is 'start' and the value are the roads/edges connected to that intersection.
            for(Edge edge : graph.getOrDefault(currentIntersection, new ArrayList<>())) {
                int newDist = distances.get(currentIntersection) + edge.weight;

                if(newDist < distances.get(edge.to)) {
                    distances.put(edge.to, newDist);
                    previousNodes.put(edge.to, currentIntersection);
                    pq.add(new Node(edge.to, newDist));
                }
            }
        }

        List<String> path = new ArrayList<>();
        for(String at = end; at != null; at = previousNodes.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);

//      as a precaution we must validate that the first node is the start node, after we receive the path and reverse it.
        if(!path.get(0).equals(start)) {
            return new ArrayList<>();
        }

        return path;
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

        // Gjetja e rrugës më të shkurtër
        System.out.println("\nRruga më e shkurtër nga A në D:");
        List<String> path = city.findShortestPath("A", "D");
        System.out.println(path);
    }
}
