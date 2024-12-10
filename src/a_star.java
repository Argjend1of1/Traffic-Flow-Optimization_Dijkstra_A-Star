import java.util.*;

public class a_star {
    public List<String> findPathAStar(String start, String end, Map<String, Integer> heuristic, CityGraph cityGraph) {
        Map<String, Integer> gScore = new HashMap<>();
        Map<String, Integer> fScore = new HashMap<>();
        Map<String, String> previousNodes = new HashMap<>();
        PriorityQueue<CityGraph.Node> openSet = new PriorityQueue<>(Comparator.comparingInt(n -> n.fScore));

        // Inicimi
        for (String node : cityGraph.getGraph().keySet()) {
            gScore.put(node, Integer.MAX_VALUE);
            fScore.put(node, Integer.MAX_VALUE);
        }
        gScore.put(start, 0);
        fScore.put(start, heuristic.getOrDefault(start, 0));
        openSet.add(new CityGraph.Node(start, fScore.get(start)));

        while (!openSet.isEmpty()) {
            CityGraph.Node currentNode = openSet.poll();
            String currentIntersection = currentNode.name;

            // Nëse kemi arritur destinacionin
            if (currentIntersection.equals(end)) {
                return reconstructPath(previousNodes, end);
            }

            for (CityGraph.Edge edge : cityGraph.getGraph().getOrDefault(currentIntersection, new ArrayList<>())) {
                int tentativeGScore = gScore.get(currentIntersection) + edge.weight;

                if (tentativeGScore < gScore.get(edge.to)) {
                    gScore.put(edge.to, tentativeGScore);
                    fScore.put(edge.to, tentativeGScore + heuristic.getOrDefault(edge.to, 0));
                    previousNodes.put(edge.to, currentIntersection);

                    openSet.add(new CityGraph.Node(edge.to, fScore.get(edge.to)));
                }
            }
        }
        return new ArrayList<>(); // if no path found.
    }

    private List<String> reconstructPath(Map<String, String> previousNodes, String end) {
        List<String> path = new ArrayList<>();
        for (String at = end; at != null; at = previousNodes.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }
}

