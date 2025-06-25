package project.sim;

import project.sim.graph.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Tests {

    @Test
    public void testMakeALGraph1() {
        Vertex v1 = new Vertex(0, "A");
        Vertex v2 = new Vertex(1, "B");
        Vertex v3 = new Vertex(2, "C");
        Vertex v4 = new Vertex(3, "D");
        Vertex v5 = new Vertex(4, "U");

        Edge<Vertex> e1 = new Edge<>(v1, v2, 5);
        Edge<Vertex> e2 = new Edge<>(v2, v3, 7);
        Edge<Vertex> e3 = new Edge<>(v1, v4, 9);
        Edge<Vertex> e4 = new Edge<>(v3, v4, 9);
        Edge<Vertex> e5 = new Edge<>(v3,v1);

        assertEquals(false, e5.intersects(null));
        assertEquals(true, e5.intersects(e1));
        assertEquals(v3, e5.intersection(e5));

        IGraph<Vertex, Edge<Vertex>> g = new ALGraph<>();
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);

        assertEquals(e2, g.getEdge(v2, v3));
        assertEquals(true, g.hasEdge(e4));
        assertEquals(false, g.hasVertex(v5));
        assertEquals(true, g.hasEdge(e3));
        assertEquals(9, g.edgeLength(v3,v4));
        assertEquals(7, g.edgeLength(v2,v3));
        assertEquals(5,g.edgeLength(v1,v2));
        assertEquals(9, g.edgeLength(v1,v4));
        assertEquals(-1, g.edgeLength(v1,v3));

        assertEquals(false, g.addVertex(v3));

    }

    @Test
    public void testMakeALGraphEmpty() {
        Vertex v3 = new Vertex(2, "C");
        Vertex v4 = new Vertex(3, "D");
        Edge<Vertex> e4 = new Edge<>(v3, v4, 9);

        IGraph<Vertex, Edge<Vertex>> g = new ALGraph<>();

        assertEquals(false, g.hasEdge(e4));
        assertEquals(false, g.hasVertex(v4));
        assertEquals(false, g.hasVertex(v3));
    }

    @Test
    public void testMakeAMGraph1() {
        Vertex v1 = new Vertex(0, "A");
        Vertex v2 = new Vertex(1, "B");
        Vertex v3 = new Vertex(2, "C");
        Vertex v4 = new Vertex(3, "D");
        Vertex v5 = new Vertex(4, "U");

        Edge<Vertex> e1 = new Edge<>(v1, v2, 5);
        Edge<Vertex> e2 = new Edge<>(v2, v3, 7);
        Edge<Vertex> e3 = new Edge<>(v1, v4, 9);
        Edge<Vertex> e4 = new Edge<>(v3, v4, 9);

        IGraph<Vertex, Edge<Vertex>> g = new AMGraph<>(3);
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);

        assertEquals(e2, g.getEdge(v2, v3));
        assertEquals(false, g.hasEdge(e4));
        assertEquals(false, g.hasVertex(v5));
        assertEquals(true, g.hasEdge(e1));
        assertEquals(7, g.edgeLength(v2,v3));
        assertEquals(5,g.edgeLength(v1,v2));
        assertEquals(-1, g.edgeLength(v1,v3));
    }

    @Test
    public void testMakeAMGraphEmpty1() {
            Vertex v3 = new Vertex(2, "C");
            Vertex v4 = new Vertex(3, "D");
            Edge<Vertex> e4 = new Edge<>(v3, v4, 9);

            IGraph<Vertex, Edge<Vertex>> g = new AMGraph<>(0);
            g.addVertex(v3);

            assertEquals(false, g.hasEdge(e4));
            assertEquals(false, g.hasVertex(v4));
            assertEquals(false, g.hasVertex(v3));
    }

    @Test
    public void testMakeALGraphEmpty2() {
        Vertex v3 = new Vertex(2, "C");
        Vertex v4 = new Vertex(3, "D");
        Edge<Vertex> e4 = new Edge<>(v3, v4, 9);

        IGraph<Vertex, Edge<Vertex>> g = new AMGraph<>(4);

        assertEquals(false, g.hasEdge(e4));
        assertEquals(false, g.hasVertex(v4));
        assertEquals(false, g.hasVertex(v3));
    }

}