import java.util.*;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();

        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");
        graph.addNode("F");

        graph.addEdge("A", "B", 10);
        graph.addEdge("B", "C", 15);
        graph.addEdge("C", "D", 20);
        graph.addEdge("D", "E", 25);
        graph.addEdge("E", "F", 30);

        graph.addEdge("A", "D", 40);
        graph.addEdge("B", "E", 35);

        Map<String, int[]> coordinates = new HashMap<>();
        coordinates.put("A", new int[]{0, 0});
        coordinates.put("B", new int[]{1, 2});
        coordinates.put("C", new int[]{2, 4});
        coordinates.put("D", new int[]{4, 7});
        coordinates.put("E", new int[]{6, 9});
        coordinates.put("F", new int[]{8, 10});

        TrafficSimulator.simulateTrafficUpdates(graph);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Traffic Flow Optimization System!");

        while (true) {
            System.out.println("\nEnter Start Node (or type 'exit' to quit):");
            String startNode = scanner.nextLine();

            if (startNode.equalsIgnoreCase("exit")) {
                System.out.println("Exiting program. Goodbye!");
                break;
            }
            System.out.println("Enter End Node:");
            String endNode = scanner.nextLine();

            if (!graph.getNodes().contains(startNode) || !graph.getNodes().contains(endNode)) {
                System.out.println("Invalid nodes. Please enter valid start and end nodes.");
                continue;
            }
            // Dijkstra's algorithm
            long startTime = System.nanoTime();
            Map<String, Object> dijkstraResult = Pathfinding.findShortestPath(graph, startNode, endNode, null);
            long endTime = System.nanoTime();

            System.out.println("\nDijkstra Execution Time: " + (endTime - startTime) + " ns");
//            System.out.println("Visited Nodes: " + dijkstraResult.get("visited"));
            System.out.println("Path: " + dijkstraResult.get("path") + ", Cost: " + dijkstraResult.get("cost"));

            // A* algorithm with Euclidean heuristic
            Map<String, Double> heuristics = new HashMap<>();
            for (String node : graph.getNodes()) {
                double heuristic = Pathfinding.calculateHeuristic(node, endNode, coordinates);
                heuristics.put(node, heuristic);
            }

            startTime = System.nanoTime();
            Map<String, Object> aStarResult = Pathfinding.findShortestPath(graph, startNode, endNode, heuristics);
            endTime = System.nanoTime();

            System.out.println("\nA* Execution Time: " + (endTime - startTime) + " ns");
//            System.out.println("Visited Nodes: " + aStarResult.get("visited"));
            System.out.println("Path: " + aStarResult.get("path") + ", Cost: " + aStarResult.get("cost"));
        }

        scanner.close();
    }
}