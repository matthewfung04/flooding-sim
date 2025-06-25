package project.sim;

import project.sim.graph.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class Task2Tests {
    IGraph<Vertex, Edge<Vertex>> gAL = new ALGraph<>();
    IGraph<Vertex, Edge<Vertex>> gAM = new AMGraph<>(100);
    @BeforeEach
    public void setUp() {
        Vertex v1 = new Vertex(0, "A");
        Vertex v2 = new Vertex(1, "B");
        Vertex v3 = new Vertex(2, "C");
        Vertex v4 = new Vertex(3, "D");

        Edge<Vertex> e1 = new Edge<>(v1, v2, 5);
        Edge<Vertex> e2 = new Edge<>(v2, v3, 7);
        Edge<Vertex> e3 = new Edge<>(v1, v4, 9);
        Edge<Vertex> e4 = new Edge<>(v3, v4, 9);

        gAL.addVertex(v1);
        gAL.addVertex(v2);
        gAL.addVertex(v3);
        gAL.addVertex(v4);
        gAL.addEdge(e1);
        gAL.addEdge(e2);
        gAL.addEdge(e3);
        gAL.addEdge(e4);
        gAM.addVertex(v1);
        gAM.addVertex(v2);
        gAM.addVertex(v3);
        gAM.addVertex(v4);
        gAM.addEdge(e1);
        gAM.addEdge(e2);
        gAM.addEdge(e3);
        gAM.addEdge(e4);

        gAM.graph();
        gAL.graph();
    }

    @Test
    public void testGraphEdgeLengthSum() {
        assertEquals(30, gAM.edgeLengthSum());
        assertEquals(30, gAL.edgeLengthSum());
    }

    @Test
    public void testRemoveEdge() {
        Vertex v1 = new Vertex(0, "A");
        Vertex v2 = new Vertex(1, "B");
        Vertex v3 = new Vertex(2, "R");
        Vertex v4 = new Vertex(3, "S");
        Edge<Vertex> e1 = new Edge<>(v1, v2, 5);
        Edge<Vertex> e2 = new Edge<>(v3, v4, 5);
        assertFalse(gAL.removeEdge(e2));
        assertFalse(gAM.removeEdge(e2));


        assertTrue(gAL.hasEdge(e1));
        assertTrue(gAM.hasEdge(e1));
        assertTrue(gAL.removeEdge(e1));
        assertTrue(gAM.removeEdge(e1));
        assertFalse(gAL.hasEdge(e1));
        assertFalse(gAM.hasEdge(e1));
    }

    @Test
    public void testRemoveEdgeVertices() {
        Vertex v1 = new Vertex(0, "A");
        Vertex v2 = new Vertex(1, "B");
        Vertex v3 = new Vertex(0, "C");
        Vertex v4 = new Vertex(1, "D");
        assertFalse(gAL.removeEdge(v3, v4));
        assertFalse(gAM.removeEdge(v3, v4));

        assertTrue(gAL.hasEdge(v1, v2));
        assertTrue(gAM.hasEdge(v1, v2));
        assertTrue(gAL.removeEdge(v1, v2));
        assertTrue(gAM.removeEdge(v1, v2));
        assertFalse(gAL.hasEdge(v1, v2));
        assertFalse(gAM.hasEdge(v1, v2));
    }

    @Test
    public void testRemoveVertex() {
        Vertex v1 = new Vertex(0, "A");
        Vertex v2 = new Vertex(9001, "Q");
        assertTrue(gAL.hasVertex(v1));
        assertTrue(gAM.hasVertex(v1));
        gAL.removeVertex(v1);
        gAM.removeVertex(v1);
        assertFalse(gAL.hasVertex(v1));
        assertFalse(gAL.removeVertex(v2));
        assertFalse(gAM.hasVertex(v1));
        assertFalse(gAM.removeVertex(v2));
    }

    @Test
    public void testGetEdge() {
        Vertex v1 = new Vertex(0, "A");
        Vertex v2 = new Vertex(1, "B");
        Vertex v3 = new Vertex(2, "X");
        Vertex v4 = new Vertex(3, "Y");
        Edge<Vertex> e1 = new Edge<>(v1, v2, 5);
        assertEquals(e1, gAL.getEdge(v1,v2));
        assertEquals(e1, gAM.getEdge(v1,v2));
        assertNull(gAM.getEdge(v3,v4));
        assertNull(gAL.getEdge(v3,v4));
    }

    @Test
    public void testAllVertices() {
        Vertex v1 = new Vertex(0, "A");
        Vertex v2 = new Vertex(1, "B");
        Vertex v3 = new Vertex(2, "C");
        Vertex v4 = new Vertex(3, "D");

        Set<Vertex> allVertices = Set.of(v1,v2,v3,v4);

        assertEquals(allVertices, gAL.allVertices());
        assertEquals(allVertices, gAM.allVertices());
    }

    @Test
    public void testAllEdges() {
        Vertex v1 = new Vertex(0, "A");
        Vertex v2 = new Vertex(1, "B");
        Vertex v3 = new Vertex(2, "C");
        Vertex v4 = new Vertex(3, "D");

        Edge<Vertex> e1 = new Edge<>(v1, v2, 5);
        Edge<Vertex> e2 = new Edge<>(v2, v3, 7);
        Edge<Vertex> e3 = new Edge<>(v1, v4, 9);
        Edge<Vertex> e4 = new Edge<>(v3, v4, 9);

        Set<Edge> allVertices = Set.of(e1,e2,e3,e4);

        assertEquals(allVertices, gAL.allEdges());
        assertEquals(allVertices, gAM.allEdges());
    }
    @Test
    public void testVertexAllEdges() {
        Vertex v1 = new Vertex(0, "A");
        Vertex v2 = new Vertex(1, "B");
        Vertex v4 = new Vertex(3, "D");
        Vertex v21 = new Vertex(21, "W");

        gAL.addVertex(v21);
        gAM.addVertex(v21);

        Edge<Vertex> e1 = new Edge<>(v1, v2, 5);
        Edge<Vertex> e3 = new Edge<>(v1, v4, 9);

        Set<Edge<Vertex>> edges = new HashSet<>();
        edges.add(e1);
        edges.add(e3);

        Set<Edge<Vertex>> noNeighbours = new HashSet<>();
        assertEquals(noNeighbours, gAL.allEdges(v21));
        assertEquals(noNeighbours, gAM.allEdges(v21));

        assertEquals(edges, gAL.allEdges(v1));
        assertEquals(edges, gAM.allEdges(v1));
    }

    @Test
    public void testGetNeighbours() {
        Vertex v1 = new Vertex(0, "A");
        Vertex v2 = new Vertex(1, "B");
        Vertex v4 = new Vertex(3, "D");
        Vertex v21 = new Vertex(21, "W");

        gAL.addVertex(v21);
        gAM.addVertex(v21);

        Edge<Vertex> e1 = new Edge<>(v1, v2, 5);
        Edge<Vertex> e3 = new Edge<>(v1, v4, 9);

        Map<Vertex,Edge<Vertex>> neighbours = new HashMap<>();
        neighbours.put(v2, e1);
        neighbours.put(v4, e3);

        Map<Vertex,Edge<Vertex>> noNeighbours = new HashMap<>();
        assertEquals(noNeighbours, gAL.getNeighbours(v21));
        assertEquals(noNeighbours, gAM.getNeighbours(v21));

        assertEquals(neighbours, gAL.getNeighbours(v1));
        assertEquals(neighbours, gAM.getNeighbours(v1));
    }
}
