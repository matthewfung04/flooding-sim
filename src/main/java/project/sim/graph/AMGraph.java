package project.sim.graph;

import java.util.*;

public class AMGraph<V extends Vertex, E extends Edge<V>> extends ConcreteGraph<V, E>
        implements IGraph<V, E> {
    /**
     * Representation Invariants:
     * vertices contains all and only the vertices on the graph,
     * vertices does not contain duplicates
     * edges contains all and only the edges on the graph
     * edges does not contain duplicates
     * matrix is a 2d array representing connections between vertices, where the ith row and ith column corresponds to
     *  the ith vertex added
     * matrix indicates a connection between the ith row vertex and jth column vertex with a 1, and 0 otherwise
     * numVertices is the number of vertices added to an AMGraph object
     * maxVertices >= numVertices
     * Cell [i][j] represents the connection between vertex i and vertex j
     * 1 represents the presence of an edge; 0, if there is not an edge
     */
    private int numVertices = 0;
    private final int[][] matrix;
    private final int maxVertices;

    /**
     * Create an empty graph with an upper-bound on the number of vertices
     *
     * @param maxVertices is greater than 1
     */
    public AMGraph(int maxVertices) {
        super();
        this.matrix = new int[maxVertices][maxVertices];
        this.maxVertices = maxVertices;
    }

    /**
     * Abstraction function: prints out the ids of the vertices and their neighbouring nodes
     */
    public void graph() {
        System.out.println("The graph:");
        for (V vertex : vertices) {
            Set<V> neighbors = super.getNeighbours(vertex).keySet();
            System.out.print("Vertex " + vertex.id() + " is connected to vertices: ");
            for (V neighbour : neighbors) {
                System.out.print(neighbour.id() + " ");
            }
            System.out.println();
        }
    }

    @Override
    public boolean addVertex(V v) {
        if (vertices.contains(v) || numVertices >= maxVertices) {
            return false;
        } else {
            vertices.add(v);
            matrix[vertices.indexOf(v)][vertices.indexOf(v)] = 1;
            numVertices++;
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
            int vertex1Index = vertices.indexOf(e.v1());
            int vertex2Index = vertices.indexOf(e.v2());

            matrix[vertex1Index][vertex2Index] = 1;
            matrix[vertex2Index][vertex1Index] = 1;

            edges.add(e);
            return true;
        }
    }

    @Override
    public boolean hasEdge(E e) {
        return edges.contains(e);
    }

    @Override
    public boolean hasEdge(V v1, V v2) {
        return matrix[v1.id()][v2.id()] == 1;
    }

    @Override
    public int edgeLength(V v1, V v2) {
        int length = -1;
        for (E edge : edges) {
            if ((edge.v1().equals(v1) && edge.v2().equals(v2))
                    || edge.v1().equals(v2) && edge.v2().equals(v1)) {
                length = edge.length();
            }
        }
        return length;
    }

    @Override
    public int edgeLengthSum() {
        int sum = 0;
        for (E edge : edges) {
            sum += edge.length();
        }
        return sum;
    }

    @Override
    public boolean removeEdge(E e) {

        if (!edges.contains(e)) {
            return false;
        }

        edges.remove(e);
        int vertex1Index = e.v1().id();
        int vertex2Index = e.v2().id();

        matrix[vertex1Index][vertex2Index] = 0;
        matrix[vertex2Index][vertex1Index] = 0;
        return true;
    }

    @Override
    public boolean removeEdge(V v1, V v2) {

        //if edge is found, remove from matrix and edges
        for (E edge : edges) {
            if ((edge.v1().equals(v1) && edge.v2().equals(v2))
                    || (edge.v1().equals(v2) && edge.v2().equals(v1))) {
                edges.remove(edge);
                int vertex1Index = v1.id();
                int vertex2Index = v2.id();
                matrix[vertex1Index][vertex2Index] = 0;
                matrix[vertex2Index][vertex1Index] = 0;
                return true;
            }

        }

        return false;

    }

    @Override
    public boolean removeVertex(V v) {
        if (!vertices.contains(v)) {
            return false;
        }

        //remove v from vertices
        vertices.remove(v);

        //remove any edge that has v
        for (int i = 0; i < edges.size(); i++) {
            E edge = edges.get(i);
            if (edge.v1().equals(v) || edge.v2().equals(v)) {
                edges.remove(edge);
                i--;
            }
        }

        //remove v from matrix
        for (int i = 0; i < maxVertices; i++) {
            matrix[v.id()][i] = 0;
            matrix[i][v.id()] = 0;
        }


        return true;
    }

    @Override
    public E getEdge(V v1, V v2) {
        for (E edge : edges) {
            if (edge.v1().equals(v1) && edge.v2().equals(v2)
                    || edge.v1().equals(v2) && edge.v2().equals(v1)) {
                return edge;
            }
        }

        return null;
    }

    @Override
    public Set<V> allVertices() {

        Set<V> vertexSet = new HashSet<>();
        for (V vertex : vertices) {
            vertexSet.add(vertex);
        }
        return vertexSet;
    }

    @Override
    public Set<E> allEdges() {
        Set<E> edgeSet = new HashSet<>();
        for (E edge : edges) {
            edgeSet.add(edge);
        }
        return edgeSet;
    }

    @Override
    public Set<E> allEdges(V v) {
        Set<E> edgeSet = new HashSet<>();
        for (E edge : edges) {
            if (edge.v1().equals(v) || edge.v2().equals(v)) {
                edgeSet.add(edge);
            }
        }
        return edgeSet;
    }
}

