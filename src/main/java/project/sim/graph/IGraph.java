package project.sim.graph;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Abstraction Invariants:
 * A map cannot have more than 1 edge connecting 2 vertices
 * An edge corresponds to two vertices, it is non-directional
 */
public interface IGraph<V extends Vertex, E extends Edge<V>> {

    /* === TASK 1 === */
    /**
     * Adds a vertex to the map, cannot have been previously added
     *
     * @return boolean, whether the vertex was successfully added
     */
    boolean addVertex(V v);
    /**
     * Checks if the map contains a given vertex v
     *
     * @return boolean, whether the map contains the vertex
     */
    boolean hasVertex(V v);
    /**
     * Adds an edge to the map, two vertices can have at most, one edge
     *
     * @return boolean, whether the edge was successfully added
     */
    boolean addEdge(E e);
    /**
     * Checks if the map contains a given edge e
     *
     * @return boolean, whether the map contains the edge
     */
    boolean hasEdge(E e);
    /**
     * Checks if two vertices (v1, v2) have an edge on the map
     *
     * @return boolean, whether the vertices have an edge
     */
    boolean hasEdge(V v1, V v2);
    /**
     * Finds the length of the edge between 2 vertices (v1, v2)
     * requires: the vertices must have an edge
     *
     * @return int, the length of the edge.
     */
    int edgeLength(V v1, V v2);

    /* === TASK 2 === */
    /**
     * Determines the sum of all the edges' lengths on the map
     *
     * @return int, the sum of all the edge lengths
     */
    int edgeLengthSum();
    /**
     * Removes an edge (e) from the map
     *
     * @return boolean, whether the edge was successfully removed.
     * Returns false if the edge does not exist
     */
    boolean removeEdge(E e);
    /**
     * Removes the edge between two given vertices (v1, v2)
     *
     * @return boolean, whether the edge was successfully removed.
     * Returns false if the edge does not exist
     */
    boolean removeEdge(V v1, V v2);
    /**
     * Removes a vertex (v) from the map
     *
     * @return boolean, whether the vertex was successfully removed.
     * Returns false if the vertex does not exist
     */
    boolean removeVertex(V v);
    /**
     * Gets the edge between two vertices (v1, v2)
     *
     * @return E, the edge between the vertices.
     * Return null if no such edge exists
     */
    E getEdge(V v1, V v2);
    /**
     * Gets a set of all the vertices on the map
     *
     * @returns Set<V>, the Set of all vertices on the map
     */
    Set<V> allVertices();
    /**
     * Gets a set of all the edges on the map
     *
     * @returns Set<V>, the Set of all edges on the map
     */
    Set<E> allEdges();
    /**
     * Gets a set of all the edges connected to a given vertex (v)
     *
     * @returns Set<E>, the Set of all the connected edges
     */
    Set<E> allEdges(V v);
    /**
     * Gets a Map of all the vertices connected to a given vertex (v)
     *
     * @returns Map<V,E>, a map where the keys are the connected vertices
     * and the value is the edge connecting them to vertex v
     */
    Map<V, E> getNeighbours(V v);

    /* === TASK 3 === */
    /**
     * Calculates the cost of a given path
     * @param path, the path that the cost will be calculated from
     * @param costType, an enum that determines the measure of cost
     *
     * @return int, the cost of the given path
     */
    int pathCost(List<V> path, PathCostType costType);

    /**
     * Determines the path of least cost between two given vertices (v1, v2)
     * @param source, the starting vertex
     * @param sink, the ending vertex
     * @param costType, an enum that determines the measure of cost
     *
     * @return List<V>, the list of vertices that will produce the least cost
     */
    List<V> minimumCostPath(V source, V sink, PathCostType costType);
    /**
     * Determines the vertices that are within a given range of a vertex
     * (the cost is the sum of the edge lengths on the path)
     * @param v, the starting vertex
     * @param range, the distance away that is within the neighbourhood
     *
     * @return Map<V,E> a map where the keys are the vertices in the neighbourhood and
     *  the values are the last edge in the path connecting the starting vertex to the ending vertex
     */
    Map<V, E> getNeighbourhood(V v, int range);

    /* === TASK 4 === */
    /**
     * Finds the diameter (largest eccentricity) of the graph
     * The eccentricity is the maximum of the minimum cost paths to any vertex.
     * @param costType, an enum that determines the measure of cost
     *
     * @return maxEccentricity, the diameter of the graph
     * If more than one graph is present, maxEccentricity is based on the largest graph.
     * If there is a tie between the largest graphs, maxEccentricity is based on the graph whose first
     * vertex was added first.
     */
    int getDiameter(PathCostType costType);
    /**
     * Finds the vertex corresponding to the radius (smallest eccentricity) of the graph
     * The eccentricity is the maximum of the minimum cost paths to any vertex.
     * @param costType, an enum that determines the measure of cost
     *
     * @return center, the vertex that has the radius of the graph.
     * If the radius belongs to multiple vertices, the vertex is arbitrarily chosen
     * If more than one graph is present, center is based on the largest graph.
     * If there is a tie between the largest graphs, center is based on the graph whose first
     * vertex was added first.
     */
    V getCenter(PathCostType costType);

    /**
     * Abstraction function: prints out the ids of the vertices and their neighbouring nodes
     */
    void graph();
}



