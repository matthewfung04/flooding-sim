package project.sim.graph;

import java.util.*;

public class ALGraph<V extends Vertex, E extends Edge<V>> extends ConcreteGraph<V, E>
        implements IGraph<V, E> {
    /**
     * Representation Invariants:
     * vertices contains all and only the vertices on the graph.
     * vertices does not contain duplicates.
     * edges contains all and only the edges on the graph.
     * edges does not contain duplicates.
     * graph maps all the immediately connected vertices to each vertex on the graph.
     * graph contains all the vertices on the map in its entry set.
     */
    Map<V, List<V>> graph;

    public ALGraph() {
        super();
        this.graph = new HashMap<>();
    }

    /**
     * Abstraction function: prints out the ids of the vertices and their neighbouring nodes
     */
    public void graph() {
        System.out.println("The graph:");
        for (Map.Entry<V, List<V>> entry : graph.entrySet()) {
            V vertex = entry.getKey();
            List<V> neighbors = entry.getValue();
            System.out.print("Vertex " + vertex.id() + " is connected to vertices: ");
            for (V neighbour : neighbors) {
                System.out.print(neighbour.id() + " ");
            }
            System.out.println();
        }
    }

    @Override
    public boolean addVertex(V v) {
        if (vertices.contains(v)) {
            return false;
        } else {
            vertices.add(v);
            graph.put(v, new ArrayList<>());
            return true;
        }
    }

    @Override
    public boolean hasVertex(V v) {
        return vertices.contains(v);
    }

    @Override
    public boolean addEdge(E e) {
        if (edges.contains(e) || !vertices.contains(e.v1()) || !vertices.contains(e.v2())) {
            return false;
        } else {
            edges.add(e);

            graph.get(e.v1()).add(e.v2());
            graph.get(e.v2()).add(e.v1());

            return true;
        }
    }

    @Override
    public boolean hasEdge(E e) {
        return edges.contains(e);
    }

    @Override
    public boolean hasEdge(V v1, V v2) {
        return graph.get(v1).contains(v2);
    }

    @Override
    public int edgeLength(V v1, V v2) {
        int length = -1;
        int edgeIndex = edgeExists(v1, v2);
        if (edgeIndex != -1) {
            length = edges.get(edgeIndex).length();
        }
        return length;
    }

    @Override
    public int edgeLengthSum() {
        int lengthSum = 0;
        for (E edge : edges) {
            lengthSum += edge.length();
        }
        return lengthSum;
    }

    @Override
    public boolean removeEdge(E e) {

        if (!edges.contains(e)) {
            return false;
        }
        edges.remove(e);
        graph.get(e.v1()).remove(e.v2());
        graph.get(e.v2()).remove(e.v1());

        return true;
    }

    @Override
    public boolean removeEdge(V v1, V v2) {
        int edgeIndex = edgeExists(v1, v2);

        if (edgeIndex != -1) {
            edges.remove(edgeIndex);
            graph.get(v1).remove(v2);
            graph.get(v2).remove(v1);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeVertex(V v) {
        if (!vertices.contains(v)) {
            return false;
        }
        for (List<V> connections : graph.values()) {
            connections.remove(v);
        }
        graph.remove(v);
        vertices.remove(v);

        for (int i = 0; i < edges.size(); i++) {
            E edge = edges.get(i);
            if (edge.v1().equals(v) || edge.v2().equals(v)) {
                edges.remove(edge);
                i--;
            }
        }

        return true;
    }

    @Override
    public E getEdge(V v1, V v2) {
        for (E edge : edges) {
            if ((edge.v1().equals(v1) && edge.v2().equals(v2))
                    || (edge.v1().equals(v2) && edge.v2().equals(v1))) {
                return edge;
            }
        }

        return null;
    }

    @Override
    public Set<V> allVertices() {
        return new HashSet<>(vertices);
    }

    @Override
    public Set<E> allEdges() {
        return new HashSet<>(edges);
    }

    @Override
    public Set<E> allEdges(V v) {
        Set<E> allEdges = new HashSet<>();

        for (E edge : edges) {
            if (edge.v1().equals(v) || edge.v2().equals(v)) {
                allEdges.add(edge);
            }
        }

        return allEdges;
    }

    @Override
    public Map<V, E> getNeighbours(V v) {
        Map<V, E> neighbourhood = new HashMap<>();
        List<V> neighbours = graph.get(v);

        for (V neighbour : neighbours) {
            int indexEdge = edgeExists(v, neighbour);
            neighbourhood.put(neighbour, edges.get(indexEdge));
        }

        return neighbourhood;
    }


}
