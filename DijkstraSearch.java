import java.util.*;
public class DijkstraSearch<V> implements Search<V> {
    @Override
    public List<V> findPath(Vertex<V> source, Vertex<V> destination) {
        Map<Vertex<V>, Double> distanceMap = new HashMap<>();
        Map<Vertex<V>, Vertex<V>> parentMap = new HashMap<>();
        Set<Vertex<V>> visited = new HashSet<>();
        PriorityQueue<Vertex<V>> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(distanceMap::get));
        distanceMap.put(source, 0.0);
        priorityQueue.add(source);
        while (!priorityQueue.isEmpty()) {
            Vertex<V> currentVertex = priorityQueue.poll();
            visited.add(currentVertex);
            if (currentVertex == destination) {
                return reconstructPath(parentMap, destination);
            }
            for (Map.Entry<Vertex<V>, Double> entry : currentVertex.getAdjacentVertices().entrySet()) {
                Vertex<V> neighbor = entry.getKey();
                double edgeWeight = entry.getValue();
                double newDistance = distanceMap.get(currentVertex) + edgeWeight;
                if (!visited.contains(neighbor) && (distanceMap.get(neighbor) == null || newDistance < distanceMap.get(neighbor))) {
                    distanceMap.put(neighbor, newDistance);
                    priorityQueue.add(neighbor);
                    parentMap.put(neighbor, currentVertex);
                }
            }
        }
        return null;
    }
    private List<V> reconstructPath(Map<Vertex<V>, Vertex<V>> parentMap, Vertex<V> destination) {
        List<V> path = new ArrayList<>();
        Vertex<V> currentVertex = destination;
        while (currentVertex != null) {
            path.add(currentVertex.getData());
            currentVertex = parentMap.get(currentVertex);
        }
        Collections.reverse(path);
        return path;
    }
}