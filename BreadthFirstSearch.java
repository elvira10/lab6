import java.util.*;
public class BreadthFirstSearch<V> implements Search<V> {
    @Override
    public List<V> findPath(Vertex<V> source, Vertex<V> destination) {
        Map<Vertex<V>, Vertex<V>> parentMap = new HashMap<>();
        Queue<Vertex<V>> queue = new LinkedList<>();
        Set<Vertex<V>> visited = new HashSet<>();
        queue.add(source);
        visited.add(source);
        while (!queue.isEmpty()) {
            Vertex<V> currentVertex = queue.poll();
            if (currentVertex == destination) {
                return reconstructPath(parentMap, destination);
            }
            for (Vertex<V> neighbor : currentVertex.getAdjacentVertices().keySet()) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
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