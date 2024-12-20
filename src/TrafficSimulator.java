import java.util.*;
public class TrafficSimulator {
    public static void simulateTrafficUpdates(Graph graph) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Random rand = new Random();
                for (String node : graph.getNodes()) {
                    for (Map.Entry<String, Double> entry : graph.getNeighbors(node).entrySet()) {
                        double currentWeight = graph.getNeighbors(node).get(entry.getKey());
                        double newWeight = currentWeight + rand.nextDouble();
                        graph.updateTraffic(node, entry.getKey(), newWeight);
                    }
                }
                System.out.println("Traffic updated.");
            }
        }, 0, 20000);
    }
}
