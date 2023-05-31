import java.util.*;
public class BreadthFirstSearch<V> implements Search<V> {
    @Override
    public List<V> findPath(Vertex<V> source, Vertex<V> destination) {
        Map<Vertex<V>, Vertex<V>> parentMap = new HashMap<>();
        Queue<Vertex<V>> queue = new LinkedList<>();
        Set<Vertex<V>> visited = new HashSet<>();
        queue.add(source); //Add source vertex to the queue and mark it as visited
        visited.add(source);
        while (!queue.isEmpty()) {
            Vertex<V> currentVertex = queue.poll();
            if (currentVertex == destination) { //Check if we have reached the destination vertex
                return reconstructPath(parentMap, destination);
            }
            for (Vertex<V> neighbor : currentVertex.getAdjacentVertices().keySet()) { //Explore the neighbors of the current vertex
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor); //Add the neighbor to the queue, mark it as visited, and update its parent in the parent map
                    visited.add(neighbor);
                    parentMap.put(neighbor, currentVertex);
                }
            }
        }
        return null; //No path
    }


    /**
     * Reconstructs the path from the parent map starting from the destination vertex
     * @param parentMap   the map containing the parent vertices
     * @param destination the destination vertex
     * @return the reconstructed path as a list of vertices
     */
    private List<V> reconstructPath(Map<Vertex<V>, Vertex<V>> parentMap, Vertex<V> destination) {
        List<V> path = new ArrayList<>();
        Vertex<V> currentVertex = destination;
        while (currentVertex != null) { //Traverse the parent map from the destination vertex to the source vertex
            path.add(currentVertex.getData());
            currentVertex = parentMap.get(currentVertex);
        }
        Collections.reverse(path); //Reverse the path to get the correct order
        return path;
    }
}