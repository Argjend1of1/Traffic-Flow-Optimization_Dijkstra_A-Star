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
                        double newWeight = 1 + rand.nextDouble() * 10;
                        graph.updateTraffic(node, entry.getKey(), newWeight);
                    }
                }
                System.out.println("Traffic updated.");
            }
        }, 0, 50000);
    }
}
