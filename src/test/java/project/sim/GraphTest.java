package project.sim;

import project.sim.graph.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {

    @Test
    public void testVertexMethods() {
        Vertex v1 = new Vertex(1, "A");
        assertEquals("A", v1.name());
        assertEquals(1, v1.id());
        v1.updateName("B");
    }

    @Test
    public void testMinimumCostSumEasy() {
        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Vertex v4 = new Vertex(4, "D");

        Edge<Vertex> e1 = new Edge<>(v1, v2, 5);
        Edge<Vertex> e2 = new Edge<>(v2, v3, 7);
        Edge<Vertex> e3 = new Edge<>(v1, v4, 9);

        IGraph<Vertex, Edge<Vertex>> g = new ALGraph<>();
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);

        assertEquals(e2, g.getEdge(v2, v3));
        assertEquals(21,
                g.pathCost(
                        g.minimumCostPath(v3, v4, PathCostType.SUM_EDGES),
                        PathCostType.SUM_EDGES
                )
        );
    }

    @Test
    public void testCreateAMGraph() {
        Vertex v1 = new Vertex(0, "A");
        Vertex v2 = new Vertex(1, "B");
        Vertex v3 = new Vertex(2, "C");
        Vertex v4 = new Vertex(3, "D");

        Edge<Vertex> e1 = new Edge<>(v1, v2, 5);
        Edge<Vertex> e2 = new Edge<>(v2, v3, 7);
        Edge<Vertex> e3 = new Edge<>(v1, v4, 9);

        IGraph<Vertex, Edge<Vertex>> g = new AMGraph<>(4);
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);

        assertEquals(e2, g.getEdge(v2, v3));
    }

    @Test
    public void testAMGraph() {

    Vertex v1 = new Vertex(0, "A");
    Vertex v2 = new Vertex(1, "B");
    Vertex v3 = new Vertex(2, "C");
    Vertex v4 = new Vertex(3, "D");
    Vertex v5 = new Vertex(4, "U");

    Edge<Vertex> e1 = new Edge<>(v1, v2, 5);
    Edge<Vertex> e2 = new Edge<>(v2, v3, 7);
    Edge<Vertex> e3 = new Edge<>(v1, v4, 9);
    Edge<Vertex> e4 = new Edge<>(v3, v4, 9);

    IGraph<Vertex, Edge<Vertex>> g = new AMGraph<>(4);
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);

    assertEquals(e2, g.getEdge(v2, v3));
    assertEquals(false, g.hasEdge(e4));
    assertEquals(false, g.hasVertex(v5));
    assertEquals(true, g.hasEdge(e3));
    assertEquals(-1, g.edgeLength(v3,v4));
    g.addEdge(e4);
    assertEquals(9, g.edgeLength(v3,v4));
    }

    @Test
    public void testIncident() {
        Vertex v1 = new Vertex(0, "A");
        Vertex v2 = new Vertex(1, "B");
        Vertex v3 = new Vertex(2, "C");
        Vertex v4 = new Vertex(3, "D");
        Vertex v5 = new Vertex(4, "U");
        Vertex v6 = null;

        Edge<Vertex> e1 = new Edge<>(v1, v2, 5);
        Edge<Vertex> e2 = new Edge<>(v2, v3, 7);
        Edge<Vertex> e3 = new Edge<>(v1, v4, 9);
        Edge<Vertex> e4 = new Edge<>(v3, v4, 9);

        assertFalse(e3.incident(v2));
        assertTrue(e4.incident(v3));
        assertFalse(e3.incident(v6));
    }

    @Test
    public void testDistinctVertex() {
        Vertex v1 = new Vertex(0, "A");
        Vertex v2 = new Vertex(1, "B");
        Edge<Vertex> e1 = new Edge<>(v1, v2, 5);
        assertEquals(v1, e1.distinctVertex(v2));
        assertEquals(v2, e1.distinctVertex(v1));
    }

    @Test
    public void testDistinctVertexEdge() {
        Vertex v1 = new Vertex(0, "A");
        Vertex v2 = new Vertex(1, "B");
        Vertex v3 = new Vertex(2, "C");


        Edge<Vertex> e1 = new Edge<>(v1, v2, 5);
        Edge<Vertex> e2 = new Edge<>(v2, v3, 7);
        assertEquals(v1, e1.distinctVertex(e2));
        assertEquals(v3, e2.distinctVertex(e1));
    }

}
