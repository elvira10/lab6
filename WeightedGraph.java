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
}
