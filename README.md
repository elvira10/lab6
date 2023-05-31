# Graph Implementation in Java

This Java project provides an implementation of a weighted graph using vertices and supports two search algorithms: Breadth-First Search and Dijkstra's Algorithm. The graph and search classes are generic and can be used with any type of vertex data.

## Classes

### Vertex

The `Vertex` class represents a vertex in the graph. It contains the following attributes and methods:

- `data`: The data associated with the vertex.
- `adjacentVertices`: A map of adjacent vertices and their corresponding edge weights.
- `distance`: The distance of the vertex from a source vertex (used in Dijkstra's Algorithm).
```
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
```

### WeightedGraph

The `WeightedGraph` class represents a weighted graph and contains a map of vertices and their adjacent vertices. It provides methods to add vertices, add edges between vertices with weights, and retrieve neighbors of a vertex.
```
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

```

### Search Interface

The `Search` interface defines the contract for search algorithms. It includes a method to find a path between two vertices in the graph.
```
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
```

### BreadthFirstSearch

The `BreadthFirstSearch` class implements the `Search` interface using the Breadth-First Search algorithm. It finds the shortest path between two vertices in an unweighted graph.
```
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
```

### DijkstraSearch

The `DijkstraSearch` class implements the `Search` interface using Dijkstra's Algorithm. It finds the shortest path between two vertices in a weighted graph.
```
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

    /**
     * Reconstructs the path from the parentMap starting from the destination vertex
     * @param parentMap   The map containing the parent-child relationship between vertices.
     * @param destination The destination vertex.
     * @return The reconstructed path as a list of vertices.
     */
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
```

## Usage

To use the graph implementation and perform searches, follow these steps:
1. Create instances of vertices using the `Vertex` class, providing the desired data for each vertex.
2. Create an instance of the `WeightedGraph` class.
3. Add the vertices to the graph using the `addVertex` method.
4. Add edges between vertices with weights using the `addEdge` method.
5. Create an instance of either `BreadthFirstSearch` or `DijkstraSearch` class.
6. Use the search algorithm to find a path between two vertices using the `findPath` method.

Example usage:

```
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Vertex<String> v1 = new Vertex<>("donkey");
        Vertex<String> v2 = new Vertex<>("sheep");
        Vertex<String> v3 = new Vertex<>("cow");
        Vertex<String> v4 = new Vertex<>("horse");

        WeightedGraph<String> graph = new WeightedGraph<>();
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);

        graph.addEdge(v1, v2, 9.0);
        graph.addEdge(v2, v3, 3.0);
        graph.addEdge(v3, v4, 5.0);
        graph.addEdge(v4, v1, 8.0);

        Search<String> bfs = new BreadthFirstSearch<>();
        Search<String> dijkstra = new DijkstraSearch<>();

        System.out.println("Breadth First Search:");
        List<String> bfsPath = bfs.findPath(v1, v4);
        if (bfsPath != null) {
            for (String vertex : bfsPath) {
                System.out.print(vertex + " ");
            }
        } else {
            System.out.println("No path found");
        }
        System.out.println();

        System.out.println("Dijkstra's:");
        List<String> dijkstraPath = dijkstra.findPath(v1, v4);
        if (dijkstraPath != null) {
            for (String vertex : dijkstraPath) {
                System.out.print(vertex + " ");
            }
        } else {
            System.out.println("No path found");
        }
    }
}
