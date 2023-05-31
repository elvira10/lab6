import java.util.List;
public interface Search<V> {
    List<V> findPath(Vertex<V> source, Vertex<V> destination);
}