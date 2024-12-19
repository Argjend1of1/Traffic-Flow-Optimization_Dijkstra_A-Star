import java.util.*;
class Graph {
    private Map<String, Map<String, Double>> adjList = new HashMap<>();

    public void addNode(String node) {
        adjList.putIfAbsent(node, new HashMap<>());
    }

    public void addEdge(String from, String to, double weight) {
        adjList.putIfAbsent(from, new HashMap<>());
        adjList.putIfAbsent(to, new HashMap<>());
        adjList.get(from).put(to, weight);
        adjList.get(to).put(from, weight);
    }

    public Map<String, Double> getNeighbors(String node) {
        return adjList.getOrDefault(node, new HashMap<>());
    }

    public void updateTraffic(String from, String to, double newWeight) {
        if (adjList.containsKey(from) && adjList.get(from).containsKey(to)) {
            adjList.get(from).put(to, newWeight);
            adjList.get(to).put(from, newWeight);
        }
    }

    public Set<String> getNodes() {
        return adjList.keySet();
    }
}