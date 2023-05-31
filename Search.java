import java.util.List;
public interface Search<V> {
    /**
     * Finds the path from the source vertex to the destination vertex in a graph
     * @param source      the source vertex
     * @param destination the destination vertex
     * @return the list of vertices representing the path from source to destination or null if no path exists
     */
    List<V> findPath(Vertex<V> source, Vertex<V> destination);
}