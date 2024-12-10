import java.util.*;

public class a_star {
    public List<String> findPathAStar(String start, String end, Map<String, Integer> heuristic, CityGraph cityGraph) {
//      Mapping each node to the cost of the shortest known path from start to a certain node
        Map<String, Integer> gScore = new HashMap<>();

//      Mapping each node to an estimated total cost (gscore[node] + heuristic[node]) to reach the end node
        Map<String, Integer> fScore = new HashMap<>();

//      Same as on dijkstra to track path, by mapping each node to its predecessor on the shortest path found so far.
        Map<String, String> previousNodes = new HashMap<>();

//      priority queue as on dijkstra, but here we order them by their `fscore`. Of course by the smallest value as min-heap does.
        PriorityQueue<CityGraph.Node> openSet = new PriorityQueue<>(Comparator.comparingInt(n -> n.fScore));

//      Here we get all the keys from graph, which as we know stores <key, value> pairs, and set them to ~infinity, at first.
        for (String node : cityGraph.getGraph().keySet()) {
            gScore.put(node, Integer.MAX_VALUE);
            fScore.put(node, Integer.MAX_VALUE);
        }
        gScore.put(start, 0);

//      here if we assume start = "A", and on heuristic its saved as "A:7", it will retrieve the value of A, in our case it will return 7, and then fScore("A", 7) will initialize fScore for "A" with 7.
        fScore.put(start, heuristic.getOrDefault(start, 0));
        openSet.add(new CityGraph.Node(start, gScore.get(start), fScore.get(start)));//A, 0, 7

        while (!openSet.isEmpty()) {
            CityGraph.Node currentNode = openSet.poll();
            String currentIntersection = currentNode.name;

            if (currentIntersection.equals(end)) {
                return reconstructPath(previousNodes, end);
            }

//          We iterate through each edge per node. Say if A got two neighbors B, C, we iterate through B, and then we calculate A's value + weight same as on dijkstra, but here this is the weight value only, we also add the heuristic(straight line to destination).
            for (CityGraph.Edge edge : cityGraph.getGraph().getOrDefault(currentIntersection, new ArrayList<>())) {
                int tentativeGScore = gScore.get(currentIntersection) + edge.weight;

//              As we said we set them all to ~infinity at first and then, we keep on comparing them with values, and if they get values, and we got smaller ones it makes sure it adds that as the new value, and all is possible by this if:
                if (tentativeGScore < gScore.get(edge.to)) {
                    gScore.put(edge.to, tentativeGScore);

//                  here we set the value of neighbor of current node to dhe gScore + heuristic. This line itself makes sure that A* is applied, because we pull elements based on fScore and here heuristic is applied, so it leads to A* algorithm being applied.
                    fScore.put(edge.to, tentativeGScore + heuristic.getOrDefault(edge.to, 0));
                    previousNodes.put(edge.to, currentIntersection);

                    openSet.add(new CityGraph.Node(edge.to, tentativeGScore, fScore.get(edge.to)));
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

