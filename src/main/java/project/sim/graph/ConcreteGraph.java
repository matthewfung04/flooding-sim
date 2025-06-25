package project.sim.graph;

import java.util.*;

public class ConcreteGraph<V extends Vertex, E extends Edge<V>> {

    protected List<V> vertices;
    protected List<E> edges;

    public ConcreteGraph() {
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    /**
     * checks if an edge between v1 and v2 exist
     *
     * @return the index in edges that points to the edge of v1 and v2
     */
    public int edgeExists(V v1, V v2) {
        for (int i = 0; i < edges.size(); i++) {
            if ((edges.get(i).v1().equals(v1) && edges.get(i).v2().equals(v2))
                    || (edges.get(i).v1().equals(v2) && edges.get(i).v2().equals(v1))) {
                return i;
            }
        }
        return -1;
    }

    public Map<V, E> getNeighbours(V v) {
        Map<V, E> map = new HashMap<>();

        for (E edge : edges) {
            if (edge.v1().equals(v)) {
                map.put(edge.v2(), edge);
            } else if (edge.v2().equals(v)) {
                map.put(edge.v1(), edge);
            }
        }
        return map;
    }

    public int pathCost(List<V> path, PathCostType costType) {
        int sum = 0;
        List<Integer> edgeLengths = new ArrayList<>();

        if (path.isEmpty()) {
            return Integer.MAX_VALUE;
        }

        for (int i = 1; i < path.size(); i++) {
            V vertex1 = path.get(i - 1);
            V vertex2 = path.get(i);
            int indexEdge = edgeExists(vertex1, vertex2);
            edgeLengths.add(edges.get(indexEdge).length());
        }


        switch (costType) {
            case SUM_EDGES:
                for (Integer edgeLength : edgeLengths) {
                    sum += edgeLength;
                }
                break;
            case MIN_EDGE:
                int minEdge = edgeLengths.get(0);
                for (Integer edgeLength : edgeLengths) {
                    if (edgeLength < minEdge) {
                        minEdge = edgeLength;
                    }
                }
                sum = minEdge;
                break;
            case MAX_EDGE:
                int maxEdge = edgeLengths.get(0);
                for (Integer edgeLength : edgeLengths) {
                    if (edgeLength > maxEdge) {
                        maxEdge = edgeLength;
                    }
                }
                sum = maxEdge;
                break;
            default: 
                System.out.println("Invalid Cost Type!");
                break;
            
        }

        return sum;
    }

    public Map<V, E> getNeighbourhood(V v, int range) {

        Map<V, E> result = new HashMap<>();
        Map<V, Integer> distances = new HashMap<>();
        PriorityQueue<V> queue = new PriorityQueue<>((v1, v2) -> Integer.compare(distances.get(v1), distances.get(v2)));


        for (V vertex : vertices) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(v, 0);
        queue.add(v);

        while (!queue.isEmpty()) {
            V current = queue.poll();
            int currentDistance = distances.get(current);

            if (currentDistance > range) {
                break;
            }

            Map<V, E> neighbours = getNeighbours(current);

            for (Map.Entry<V, E> entry : neighbours.entrySet()) {
                V neighbour = entry.getKey();
                E edge = entry.getValue();
                int newDistance = currentDistance + edge.length();

                if (newDistance <= range && newDistance < distances.get(neighbour)) {
                    distances.put(neighbour, newDistance);
                    result.put(neighbour, edge);
                    queue.add(neighbour);
                }
            }
        }

        return result;
    }

    public List<V> minimumCostPath(V source, V sink, PathCostType costType) {
        List<V> output = new ArrayList<>();

        switch (costType) {
            case SUM_EDGES:
                List<V> minSumPath = shortestPathSummed(source, sink);
                if (minSumPath.isEmpty()) {
                    output.addAll(minSumPath);
                } else {
                    output.add(source);
                    output.addAll(minSumPath);
                }
                break;
            case MIN_EDGE:
                Map<E, Boolean> visitedEdges = new HashMap<>();
                List<List<V>> allPaths = new ArrayList<>();
                List<V> currPath = new ArrayList<>();
                List<V> minPath = new ArrayList<>();
                int minEdgeLength = Integer.MAX_VALUE;

                for (E edge : edges) {
                    visitedEdges.put(edge, false);
                }

                findAllPaths(source, sink, visitedEdges, currPath, allPaths);

                for (List<V> path : allPaths) {
                    for (int i = 1; i < path.size(); i++) {
                        V vertex1 = path.get(i - 1);
                        V vertex2 = path.get(i);
                        int edge = edgeExists(vertex1, vertex2);
                        int edgeLength = edges.get(edge).length();

                        if (edgeLength < minEdgeLength) {
                            minEdgeLength = edgeLength;
                            minPath = path;
                        }
                    }
                }
                output = minPath;
                break;

            case MAX_EDGE:
                Map<E, Boolean> visitedEdges2 = new HashMap<>();
                List<List<V>> allPaths2 = new ArrayList<>();
                List<V> currPath2 = new ArrayList<>();
                List<V> maxPath = new ArrayList<>();
                int maxEdgeLength = -1;

                for (E edge : edges) {
                    visitedEdges2.put(edge, false);
                }

                findAllPaths(source, sink, visitedEdges2, currPath2, allPaths2);

                for (List<V> path : allPaths2) {
                    for (int i = 1; i < path.size(); i++) {
                        V vertex1 = path.get(i - 1);
                        V vertex2 = path.get(i);
                        int edge = edgeExists(vertex1, vertex2);
                        int edgeLength = edges.get(edge).length();

                        if (edgeLength > maxEdgeLength) {
                            maxEdgeLength = edgeLength;
                            maxPath = path;
                        }
                    }
                }
                output = maxPath;
                break;
            default:
                System.out.println("Invalid Cost Type!");
                break;
        }
        return output;
    }

    private List<V> shortestPathSummed(V source, V sink) {
        Map<V, Integer> distances = new HashMap<>();

        Map<V, Boolean> visitedVertex = new HashMap<>();

        Map<V, List<V>> paths = new HashMap<>();

        for (V vertex : vertices) {
            distances.put(vertex, Integer.MAX_VALUE);
            visitedVertex.put(vertex, false);
            paths.put(vertex, new ArrayList<V>());
        }

        distances.put(source, 0);

        for (int i = 0; i < vertices.size(); i++) {
            V minDistanceVertex = findClosestVertex(distances, visitedVertex);
            visitedVertex.put(minDistanceVertex, true);

            if (distances.get(minDistanceVertex) != Integer.MAX_VALUE) {

                Map<V, E> neighbourhood = getNeighbours(minDistanceVertex);


                for (Map.Entry<V, E> entry : neighbourhood.entrySet()) {

                    V neighbour = entry.getKey();
                    E edge = entry.getValue();

                    if (!visitedVertex.get(neighbour) && (distances.get(minDistanceVertex) + edge.length() < distances.get(neighbour))) {
                        distances.put(neighbour, distances.get(minDistanceVertex) + edge.length());

                        paths.get(neighbour).clear();
                        paths.get(neighbour).addAll(paths.get(minDistanceVertex));
                        paths.get(neighbour).add(neighbour);
                    }
                }

            }
        }

        if (distances.get(sink) == Integer.MAX_VALUE) {
            List<V> emptyList = new ArrayList<>();
            return emptyList;
        }

        return paths.get(sink);
    }

    private V findClosestVertex(Map<V, Integer> distances, Map<V, Boolean> visitedVertex) {
        int minDistance = Integer.MAX_VALUE;
        V minDistanceVertex = null;

        for (Map.Entry<V, Integer> entry : distances.entrySet()) {

            V vertex = entry.getKey();

            if (entry.getValue() <= minDistance && !visitedVertex.get(vertex)) {
                minDistanceVertex = entry.getKey();
                minDistance = entry.getValue();
            }
        }

        return minDistanceVertex;
    }

    public void findAllPaths(V current, V
            sink, Map<E, Boolean> visited, List<V> currentPath, List<List<V>> allPaths) {

        currentPath.add(current);

        if (current.equals(sink)) {
            allPaths.add(new ArrayList<>(currentPath));
        } else if (visited.values().stream().filter(Boolean::booleanValue).count() < edges.size()) {

            Map<V, E> neighbours = getNeighbours(current);

            for (Map.Entry<V, E> entry : neighbours.entrySet()) {
                V neighbour = entry.getKey();
                E edge = entry.getValue();

                if (!visited.get(edge)) {
                    visited.put(edge, true);
                    findAllPaths(neighbour, sink, visited, currentPath, allPaths);
                    visited.put(edge, false);
                }
            }
        }

        currentPath.remove(currentPath.size() - 1);
    }

    public Map<V, Integer> allEccentricities(PathCostType costType) {

        Map<V, Integer> allEccentricities = new HashMap<>();
        List<V> largestGroup = findLargestVerticesGroup();

        for (V source : largestGroup) {
            int maxPath = 0;
            for (V sink : largestGroup) {
                List<V> path = minimumCostPath(source, sink, costType);

                if (pathCost(path, costType) > maxPath && !path.isEmpty()) {
                    maxPath = pathCost(path, costType);
                }

                allEccentricities.put(source, maxPath);
            }

        }
        return allEccentricities;
    }

    public List<V> findLargestVerticesGroup() {

        List<V> group = new ArrayList<>();
        List<V> largestGroup = new ArrayList<>();

        Map<V, Boolean> visited = new HashMap<>();
        Queue<V> queue = new LinkedList<>();

        int largestVerticesGroup = 0;

        for (V vertex : vertices) {
            visited.put(vertex, false);
        }

        for (V vertex : vertices) {

            if (!visited.get(vertex)) {

                queue.add(vertex);
                visited.put(vertex, true);

               while (!queue.isEmpty()) {


                    V current = queue.poll();
                    group.add(current);

                    Map<V, E> neighbours = getNeighbours(current);

                    for (Map.Entry<V, E> entry : neighbours.entrySet()) {
                        if (!visited.get(entry.getKey())) {
                            visited.put(entry.getKey(), true);
                            queue.add(entry.getKey());
                        }
                    }

                }
            }

            if (group.size() > largestVerticesGroup) {
                largestVerticesGroup = group.size();
                largestGroup.addAll(group);
            }
            group.clear();
        }

        return largestGroup;
    }

    public int getDiameter(PathCostType costType) {
        Map<V, Integer> eccentricPaths = allEccentricities(costType);
        int maxEccentricity = 0;

        for (Map.Entry<V, Integer> entry : eccentricPaths.entrySet()) {
            if (entry.getValue() > maxEccentricity) {
                maxEccentricity = entry.getValue();
            }
        }

        return maxEccentricity;
    }

    public V getCenter(PathCostType costType) {
        Map<V, Integer> eccentricPaths = allEccentricities(costType);
        V center = vertices.get(0);
        int minEccentricity = eccentricPaths.get(center);

        for (Map.Entry<V, Integer> entry : eccentricPaths.entrySet()) {
            if (entry.getValue() < minEccentricity) {
                center = entry.getKey();
                minEccentricity = entry.getValue();
            }
        }
        return center;
    }
}


