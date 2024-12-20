import java.util.*;

public class Pathfinding {
    static double calculateHeuristic(String node, String goal, Map<String, int[]> coordinates) {
        int[] currentCoords = coordinates.get(node);
        int[] goalCoords = coordinates.get(goal);
        return Math.sqrt(Math.pow(goalCoords[0] - currentCoords[0], 2) + Math.pow(goalCoords[1] - currentCoords[1], 2));
    }
    public static Map<String, Object> findShortestPath(Graph graph, String start, String end, Map<String, Double> heuristics) {
    Map<String, Double> gScore = new HashMap<>();
    Map<String, Double> fScore = new HashMap<>();
    Map<String, String> cameFrom = new HashMap<>();
    PriorityQueue<String> openSet = new PriorityQueue<>(Comparator.comparingDouble(fScore::get));
    List<String> visitedNodes = new ArrayList<>();

    for (String node : graph.getNodes()) {
        gScore.put(node, Double.MAX_VALUE);
        fScore.put(node, Double.MAX_VALUE);
    }

    gScore.put(start, 0.0);
    fScore.put(start, heuristics != null ? heuristics.getOrDefault(start, 0.0) : 0.0);
    openSet.add(start);

    while (!openSet.isEmpty()) {
        String current = openSet.poll();
        visitedNodes.add(current);

        if (current.equals(end)) {
            return reconstructPath(cameFrom, start, end, gScore.get(end), visitedNodes);
        }

        for (Map.Entry<String, Double> neighbor : graph.getNeighbors(current).entrySet()) {
            String neighborNode = neighbor.getKey();
            double tentativeGScore = gScore.get(current) + neighbor.getValue();

            if (tentativeGScore < gScore.get(neighborNode)) {
                cameFrom.put(neighborNode, current);
                gScore.put(neighborNode, tentativeGScore);

                double heuristic = heuristics != null ? heuristics.getOrDefault(neighborNode, 0.0) : 0.0;
                fScore.put(neighborNode, tentativeGScore + heuristic);

                if (!openSet.contains(neighborNode)) {
                    openSet.add(neighborNode);
                }
            }
        }
    }

    return null;
}

private static Map<String, Object> reconstructPath(Map<String, String> cameFrom, String start, String end, double totalCost, List<String> visitedNodes) {
    List<String> path = new LinkedList<>();
    String current = end;

    while (current != null) {
        path.add(0, current);
        current = cameFrom.get(current);
    }

    Map<String, Object> result = new HashMap<>();
    result.put("path", path);
    result.put("cost", totalCost);
    result.put("visited", visitedNodes);
    return result;
}
}
