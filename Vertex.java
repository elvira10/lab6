import java.util.HashMap;
import java.util.Map;
public class Vertex<V> {
    private V data;
    private Map<Vertex<V>, Double> adjacentVertices;
    private double distance;

    /**
     * Constructs a new vertex with the given data
     * @param data the data associated with the vertex
     */
    public Vertex(V data) {
        this.data = data;
        this.adjacentVertices = new HashMap<>();
        this.distance = Double.POSITIVE_INFINITY;
    }

    /**
     * Constructs a new vertex with the given data
     * @param data the data associated with the vertex
     */
    public V getData() {
        return data;
    }

    /**
     * Adds an adjacent vertex with the specified weight to this vertex
     * @param destination the adjacent vertex
     * @param weight      the weight of the edge connecting this vertex to the adjacent vertex
     */
    public void addAdjacentVertex(Vertex<V> destination, double weight) {
        adjacentVertices.put(destination, weight);
    }

    /**
     * Returns the map of adjacent vertices and their corresponding edge weights
     * @return the map of adjacent vertices and weights
     */
    public Map<Vertex<V>, Double> getAdjacentVertices() {
        return adjacentVertices;
    }

    /**
     * Returns the distance associated with this vertex
     * @return the distance of the vertex
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Sets the distance for this vertex
     * @param distance the distance to set
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }
}