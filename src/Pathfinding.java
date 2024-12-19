import java.util.Map;

public class Pathfinding {
    static double calculateHeuristic(String node, String goal, Map<String, int[]> coordinates) {
        int[] currentCoords = coordinates.get(node);
        int[] goalCoords = coordinates.get(goal);
        return Math.sqrt(Math.pow(goalCoords[0] - currentCoords[0], 2) + Math.pow(goalCoords[1] - currentCoords[1], 2));
    }
}
