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
