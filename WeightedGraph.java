import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
public class WeightedGraph<V> {
    private Map<Vertex<V>, List<Vertex<V>>> adjacencyMap;
    public WeightedGraph() {
        this.adjacencyMap = new HashMap<>();
    }

    /**
     * Adds a vertex to the graph
     * @param vertex the vertex to add
     */
    public void addVertex(Vertex<V> vertex) {
        adjacencyMap.put(vertex, new ArrayList<>());
    }

    /**
     * Adds an edge between the source and destination vertices with the given weight
     * @param source      the source vertex
     * @param destination the destination vertex
     * @param weight      the weight of the edge
     */
    public void addEdge(Vertex<V> source, Vertex<V> destination, double weight) {
        source.addAdjacentVertex(destination, weight);
        destination.addAdjacentVertex(source, weight);
        adjacencyMap.get(source).add(destination);
        adjacencyMap.get(destination).add(source);
    }

    /**
     * Returns the list of neighbors of the specified vertex
     * @param vertex the vertex to get the neighbors of
     * @return the list of neighbors
     */
    public List<Vertex<V>> getNeighbors(Vertex<V> vertex) {
        return adjacencyMap.get(vertex);
    }

    /**
     * Returns the adjacency map of the graph
     * @return the adjacency map
     */
    public Map<Vertex<V>, List<Vertex<V>>> getAdjacencyMap() {
        return adjacencyMap;
    }
}
