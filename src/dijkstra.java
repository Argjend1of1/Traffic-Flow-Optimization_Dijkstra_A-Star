import java.util.*;

public class dijkstra {
//  Creating an instance, so when we make changes it applies only on this instance.
    private CityGraph cityGraph;

    public dijkstra(CityGraph cityGraph) {
        this.cityGraph = cityGraph;
    }

//  Applying Djikstra's Algorithm
    public List<String> findShortestPath(String start, String end) {
//      Saving the minimal distance from root/start.
        Map<String, Integer> distances = new HashMap<>();

//      To reconstruct the path from start to end if need be:
        Map<String, String> previousNodes = new HashMap<>();

//      To find the node/vertex with the minimal distance
        PriorityQueue<CityGraph.Node> pq = new PriorityQueue<>(
                Comparator.comparingInt(n -> n.distance)
        );

        for(String node : cityGraph.getGraph().keySet()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(start, 0); //start from the beginning/root

        pq.add(new CityGraph.Node(start, 0));

        while(!pq.isEmpty()) {
//          Pull nodes from the top, where at the top is the node with the minimal value:
            CityGraph.Node currentNode = pq.poll();
//          Get the name of the first intersection
            String currentIntersection = currentNode.name;

            if(currentIntersection.equals(end)) break;

//          go above, on add road we have graph.get(from).add(new Edge(to, weight), here we retrieve the value saved on graph on the currentIntersection which in our case is 'start' and the value are the roads/edges connected to that intersection.
            for(CityGraph.Edge edge : cityGraph.getGraph().getOrDefault(currentIntersection, new ArrayList<>())) {
                int newDist = distances.get(currentIntersection) + edge.weight;

                if(newDist < distances.get(edge.to)) {
                    distances.put(edge.to, newDist);
                    previousNodes.put(edge.to, currentIntersection);
                    pq.add(new CityGraph.Node(edge.to, newDist));
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
}
