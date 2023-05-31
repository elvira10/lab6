import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
public class WeightedGraph<V> {
    private Map<Vertex<V>, List<Vertex<V>>> adjacencyMap;
    public WeightedGraph() {
        this.adjacencyMap = new HashMap<>();
    }
    public void addVertex(Vertex<V> vertex) {
        adjacencyMap.put(vertex, new ArrayList<>());
    }
    public void addEdge(Vertex<V> source, Vertex<V> destination, double weight) {
        source.addAdjacentVertex(destination, weight);
        destination.addAdjacentVertex(source, weight);
        adjacencyMap.get(source).add(destination);
        adjacencyMap.get(destination).add(source);
    }
    public List<Vertex<V>> getNeighbors(Vertex<V> vertex) {
        return adjacencyMap.get(vertex);
    }
    public Map<Vertex<V>, List<Vertex<V>>> getAdjacencyMap() {
        return adjacencyMap;
    }
}
