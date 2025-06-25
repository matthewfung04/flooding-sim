package project.sim;

import project.sim.graph.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3P1Tests {

    // pathCost Tests

    // minimumCostPath Tests
    // SUM_EDGES, MIN_EDGE, MAX_EDGE
    @Test
    public void testSumEdgesAbdulBariExampleAL() {
        Vertex A = new Vertex(1, "A");
        Vertex B = new Vertex(2, "B");
        Vertex C = new Vertex(3, "C");
        Vertex D = new Vertex(4, "D");
        Vertex E = new Vertex(5, "E");
        Vertex F = new Vertex(6, "F");

        Edge<Vertex> e1 = new Edge<>(A, B, 2);
        Edge<Vertex> e2 = new Edge<>(A, C, 4);
        Edge<Vertex> e3 = new Edge<>(B, C, 1);
        Edge<Vertex> e4 = new Edge<>(B, D, 7);
        Edge<Vertex> e5 = new Edge<>(D, E, 2);
        Edge<Vertex> e6 = new Edge<>(C, E, 3);
        Edge<Vertex> e7 = new Edge<>(E, F, 5);


        IGraph<Vertex, Edge<Vertex>> g = new ALGraph<>();
        g.addVertex(A);
        g.addVertex(B);
        g.addVertex(C);
        g.addVertex(D);
        g.addVertex(E);
        g.addVertex(F);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        g.addEdge(e5);
        g.addEdge(e6);
        g.addEdge(e7);

        assertEquals(6,
                g.pathCost(
                        g.minimumCostPath(A, E, PathCostType.SUM_EDGES),
                        PathCostType.SUM_EDGES
                )
        );
    }

    @Test
    public void testSumEdgesAbdulBariExampleAM() {
        Vertex A = new Vertex(1, "A");
        Vertex B = new Vertex(2, "B");
        Vertex C = new Vertex(3, "C");
        Vertex D = new Vertex(4, "D");
        Vertex E = new Vertex(5, "E");
        Vertex F = new Vertex(6, "F");

        Edge<Vertex> e1 = new Edge<>(A, B, 2);
        Edge<Vertex> e2 = new Edge<>(A, C, 4);
        Edge<Vertex> e3 = new Edge<>(B, C, 1);
        Edge<Vertex> e4 = new Edge<>(B, D, 7);
        Edge<Vertex> e5 = new Edge<>(D, E, 2);
        Edge<Vertex> e6 = new Edge<>(C, E, 3);
        Edge<Vertex> e7 = new Edge<>(E, F, 5);


        IGraph<Vertex, Edge<Vertex>> g = new AMGraph<>(6);
        g.addVertex(A);
        g.addVertex(B);
        g.addVertex(C);
        g.addVertex(D);
        g.addVertex(E);
        g.addVertex(F);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        g.addEdge(e5);
        g.addEdge(e6);
        g.addEdge(e7);

        assertEquals(6,
                g.pathCost(
                        g.minimumCostPath(A, E, PathCostType.SUM_EDGES),
                        PathCostType.SUM_EDGES
                )
        );
    }

    @Test
    public void testAMGraphSumEdges() {
        Vertex A = new Vertex(1, "A");
        Vertex B = new Vertex(2, "B");
        Vertex C = new Vertex(3, "C");
        Vertex D = new Vertex(4, "D");
        Vertex E = new Vertex(5, "E");
        Vertex F = new Vertex(6, "F");

        Edge<Vertex> e1 = new Edge<>(A, B, 2);
        Edge<Vertex> e2 = new Edge<>(A, C, 4);
        Edge<Vertex> e3 = new Edge<>(B, C, 1);
        Edge<Vertex> e4 = new Edge<>(B, D, 7);
        Edge<Vertex> e5 = new Edge<>(D, E, 2);
        Edge<Vertex> e6 = new Edge<>(C, E, 3);
        Edge<Vertex> e7 = new Edge<>(E, F, 5);


        IGraph<Vertex, Edge<Vertex>> g = new AMGraph<>(100);
        g.addVertex(A);
        g.addVertex(B);
        g.addVertex(C);
        g.addVertex(D);
        g.addVertex(E);
        g.addVertex(F);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        g.addEdge(e5);
        g.addEdge(e6);
        g.addEdge(e7);

        assertEquals(6,
                g.pathCost(
                        g.minimumCostPath(A, E, PathCostType.SUM_EDGES),
                        PathCostType.SUM_EDGES
                )
        );
    }


    @Test
    public void testDisconnected() {
        Vertex A = new Vertex(1, "A");
        Vertex B = new Vertex(2, "B");
        Vertex X = new Vertex(7, "X");
        Vertex Y = new Vertex(8, "Y");


        Edge<Vertex> e1 = new Edge<>(A, B, 2);
        Edge<Vertex> e9 = new Edge<>(X,Y, 1);


        IGraph<Vertex, Edge<Vertex>> g = new ALGraph<>();
        g.addVertex(A);
        g.addVertex(B);
        g.addVertex(X);
        g.addVertex(Y);
        g.addEdge(e1);
        g.addEdge(e9);

        assertEquals(Integer.MAX_VALUE,
                g.pathCost(
                        g.minimumCostPath(A, X, PathCostType.MIN_EDGE),
                        PathCostType.MIN_EDGE
                )
        );
    }

    @Test
    public void testMinEdge1() {
        Vertex A = new Vertex(1, "A");
        Vertex B = new Vertex(2, "B");
        Vertex C = new Vertex(3, "C");
        Vertex D = new Vertex(4, "D");
        Vertex E = new Vertex(5, "E");
        Vertex F = new Vertex(6, "F");

        Edge<Vertex> e1 = new Edge<>(A, B, 2);
        Edge<Vertex> e2 = new Edge<>(A, C, 4);
        Edge<Vertex> e3 = new Edge<>(B, C, 2);
        Edge<Vertex> e4 = new Edge<>(B, D, 7);
        Edge<Vertex> e5 = new Edge<>(D, E, 2);
        Edge<Vertex> e6 = new Edge<>(C, E, 3);
        Edge<Vertex> e7 = new Edge<>(E, F, 5);
        Edge<Vertex> e8 = new Edge<>(D, F, 1);

        IGraph<Vertex, Edge<Vertex>> g = new ALGraph<>();
        g.addVertex(A);
        g.addVertex(B);
        g.addVertex(C);
        g.addVertex(D);
        g.addVertex(E);
        g.addVertex(F);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        g.addEdge(e5);
        g.addEdge(e6);
        g.addEdge(e7);
        g.addEdge(e8);

        assertEquals(1,
                g.pathCost(
                        g.minimumCostPath(A, B, PathCostType.MIN_EDGE),
                        PathCostType.MIN_EDGE
                )
        );
    }

    @Test
    public void testMinEdge2() {
        Vertex A = new Vertex(1, "A");
        Vertex B = new Vertex(2, "B");
        Vertex C = new Vertex(3, "C");
        Vertex D = new Vertex(4, "D");
        Vertex E = new Vertex(5, "E");
        Vertex F = new Vertex(6, "F");
        Vertex X = new Vertex(7, "X");
        Vertex Y = new Vertex(8,"Y");

        Edge<Vertex> e1 = new Edge<>(A, B, 3);
        Edge<Vertex> e2 = new Edge<>(A, D, 5);
        Edge<Vertex> e3 = new Edge<>(D, B, 6);
        Edge<Vertex> e4 = new Edge<>(B, C, 2);
        Edge<Vertex> e5 = new Edge<>(A, E, 2);
        Edge<Vertex> e7 = new Edge<>(A, F, 3);
        Edge<Vertex> e8 = new Edge<>(D, F, 4);

        IGraph<Vertex, Edge<Vertex>> g = new ALGraph<>();
        g.addVertex(A);
        g.addVertex(B);
        g.addVertex(C);
        g.addVertex(D);
        g.addVertex(E);
        g.addVertex(F);
        g.addVertex(X);
        g.addVertex(Y);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        g.addEdge(e5);
        g.addEdge(e7);
        g.addEdge(e8);

        assertEquals(3,
                g.pathCost(
                        g.minimumCostPath(A, F, PathCostType.MIN_EDGE),
                        PathCostType.MIN_EDGE
                )
        );
    }

    @Test
    public void testMinEdge3() {
        Vertex A = new Vertex(1, "A");
        Vertex B = new Vertex(2, "B");
        Vertex C = new Vertex(3, "C");
        Vertex D = new Vertex(4, "D");
        Vertex E = new Vertex(5, "E");
        Vertex F = new Vertex(6, "F");

        Edge<Vertex> e1 = new Edge<>(A, B, 2);
        Edge<Vertex> e2 = new Edge<>(A, C, 4);
        Edge<Vertex> e3 = new Edge<>(B, C, 5);
        Edge<Vertex> e4 = new Edge<>(B, D, 5);
        Edge<Vertex> e5 = new Edge<>(D, E, 5);
        Edge<Vertex> e6 = new Edge<>(C, D, 5);
        Edge<Vertex> e7 = new Edge<>(C, E, 1);
        Edge<Vertex> e8 = new Edge<>(E, F, 8);

        IGraph<Vertex, Edge<Vertex>> g = new ALGraph<>();
        g.addVertex(A);
        g.addVertex(B);
        g.addVertex(C);
        g.addVertex(D);
        g.addVertex(E);
        g.addVertex(F);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        g.addEdge(e5);
        g.addEdge(e6);
        g.addEdge(e7);
        g.addEdge(e8);

        assertEquals(1,
                g.pathCost(
                        g.minimumCostPath(A, B, PathCostType.MIN_EDGE),
                        PathCostType.MIN_EDGE
                )
        );
    }

    @Test
    public void testMinEdge4() {
        Vertex A = new Vertex(1, "A");
        Vertex B = new Vertex(2, "B");
        Vertex C = new Vertex(3, "C");
        Vertex D = new Vertex(4, "D");
        Vertex E = new Vertex(5, "E");
        Vertex F = new Vertex(6, "F");
        Vertex G = new Vertex(7, "G");
        Vertex H = new Vertex(8, "H");

        Edge<Vertex> AB = new Edge<>(A, B, 3);
        Edge<Vertex> BC = new Edge<>(B, C, 6);
        Edge<Vertex> CH = new Edge<>(C, H, 5);
        Edge<Vertex> AD = new Edge<>(A, D, 7);
        Edge<Vertex> DE = new Edge<>(D, E, 4);
        Edge<Vertex> EH = new Edge<>(E, H, 2);
        Edge<Vertex> AF = new Edge<>(A, F, 6);
        Edge<Vertex> FG = new Edge<>(F, G, 9);
        Edge<Vertex> GH = new Edge<>(G, H, 10);
        Edge<Vertex> DC = new Edge<>(D, C, 1);

        IGraph<Vertex, Edge<Vertex>> g = new ALGraph<>();
        g.addVertex(A);
        g.addVertex(B);
        g.addVertex(C);
        g.addVertex(D);
        g.addVertex(E);
        g.addVertex(F);
        g.addVertex(G);
        g.addVertex(H);
        g.addEdge(AB);
        g.addEdge(BC);
        g.addEdge(CH);
        g.addEdge(AD);
        g.addEdge(DE);
        g.addEdge(EH);
        g.addEdge(AF);
        g.addEdge(FG);
        g.addEdge(GH);
        g.addEdge(DC);

        assertEquals(1,
                g.pathCost(
                        g.minimumCostPath(A, C, PathCostType.MIN_EDGE),
                        PathCostType.MIN_EDGE
                )
        );
    }

    @Test
    public void testMaxEdgeDisconnected() {
        Vertex A = new Vertex(1, "A");
        Vertex B = new Vertex(2, "B");
        Vertex C = new Vertex(3, "C");
        Vertex D = new Vertex(4, "D");
        Vertex E = new Vertex(5, "E");
        Vertex F = new Vertex(6, "F");
        Vertex G = new Vertex(7, "G");
        Vertex H = new Vertex(8, "H");
        Vertex X = new Vertex(9, "X");

        Edge<Vertex> AB = new Edge<>(A, B, 3);
        Edge<Vertex> BC = new Edge<>(B, C, 6);
        Edge<Vertex> CH = new Edge<>(C, H, 5);
        Edge<Vertex> AD = new Edge<>(A, D, 7);
        Edge<Vertex> DE = new Edge<>(D, E, 4);
        Edge<Vertex> EH = new Edge<>(E, H, 2);
        Edge<Vertex> AF = new Edge<>(A, F, 6);
        Edge<Vertex> FG = new Edge<>(F, G, 9);
        Edge<Vertex> GH = new Edge<>(G, H, 10);
        Edge<Vertex> DC = new Edge<>(D, C, 1);

        IGraph<Vertex, Edge<Vertex>> g = new AMGraph<>(100);
        g.addVertex(A);
        g.addVertex(B);
        g.addVertex(C);
        g.addVertex(D);
        g.addVertex(E);
        g.addVertex(F);
        g.addVertex(G);
        g.addVertex(H);
        g.addEdge(AB);
        g.addEdge(BC);
        g.addEdge(CH);
        g.addEdge(AD);
        g.addEdge(DE);
        g.addEdge(EH);
        g.addEdge(AF);
        g.addEdge(FG);
        g.addEdge(GH);
        g.addEdge(DC);

        assertEquals(Integer.MAX_VALUE,
                g.pathCost(
                        g.minimumCostPath(A, X, PathCostType.MAX_EDGE),
                        PathCostType.MAX_EDGE
                )
        );
    }

    @Test
    public void testMaxEdge() {
        Vertex A = new Vertex(1, "A");
        Vertex B = new Vertex(2, "B");
        Vertex C = new Vertex(3, "C");
        Vertex D = new Vertex(4, "D");
        Vertex E = new Vertex(5, "E");
        Vertex F = new Vertex(6, "F");
        Vertex G = new Vertex(7, "G");
        Vertex H = new Vertex(8, "H");

        Edge<Vertex> AB = new Edge<>(A, B, 3);
        Edge<Vertex> BC = new Edge<>(B, C, 6);
        Edge<Vertex> CH = new Edge<>(C, H, 5);
        Edge<Vertex> AD = new Edge<>(A, D, 7);
        Edge<Vertex> DE = new Edge<>(D, E, 4);
        Edge<Vertex> EH = new Edge<>(E, H, 2);
        Edge<Vertex> AF = new Edge<>(A, F, 6);
        Edge<Vertex> FG = new Edge<>(F, G, 9);
        Edge<Vertex> GH = new Edge<>(G, H, 10);
        Edge<Vertex> DC = new Edge<>(D, C, 1);

        IGraph<Vertex, Edge<Vertex>> g = new ALGraph<>();
        g.addVertex(A);
        g.addVertex(B);
        g.addVertex(C);
        g.addVertex(D);
        g.addVertex(E);
        g.addVertex(F);
        g.addVertex(G);
        g.addVertex(H);
        g.addEdge(AB);
        g.addEdge(BC);
        g.addEdge(CH);
        g.addEdge(AD);
        g.addEdge(DE);
        g.addEdge(EH);
        g.addEdge(AF);
        g.addEdge(FG);
        g.addEdge(GH);
        g.addEdge(DC);

        assertEquals(10,
                g.pathCost(
                        g.minimumCostPath(A, H, PathCostType.MAX_EDGE),
                        PathCostType.MAX_EDGE
                )
        );
    }
    //getNeighbourhood tests
    @Test
    public void testGetNeighbourhood1() {
        Vertex A = new Vertex(1, "A");
        Vertex B = new Vertex(2, "B");
        Vertex C = new Vertex(3, "C");
        Vertex D = new Vertex(4, "D");
        Vertex E = new Vertex(5, "E");
        Vertex F = new Vertex(6, "F");
        Vertex G = new Vertex(7, "G");
        Vertex H = new Vertex(8, "H");

        Edge<Vertex> AB = new Edge<>(A, B, 3);
        Edge<Vertex> BG = new Edge<>(B, G, 6);
        Edge<Vertex> BF = new Edge<>(B, F, 2);
        Edge<Vertex> AE = new Edge<>(A, E, 4);
        Edge<Vertex> AC = new Edge<>(A, C, 2);
        Edge<Vertex> AD = new Edge<>(A, D, 5);
        Edge<Vertex> CD = new Edge<>(C, D, 2);
        Edge<Vertex> CH = new Edge<>(C, H, 4);
        Edge<Vertex> DH = new Edge<>(H, D, 3);

        Map<Vertex, Edge> expected = new HashMap<>();
        expected.put(B, AB);
        expected.put(C, AC);
        expected.put(D, CD);
        expected.put(E, AE);

        IGraph<Vertex, Edge<Vertex>> g = new ALGraph<>();
        g.addVertex(A);
        g.addVertex(B);
        g.addVertex(C);
        g.addVertex(D);
        g.addVertex(E);
        g.addVertex(F);
        g.addVertex(G);
        g.addVertex(H);
        g.addEdge(AB);
        g.addEdge(AE);
        g.addEdge(AD);
        g.addEdge(AC);
        g.addEdge(BF);
        g.addEdge(BG);
        g.addEdge(CD);
        g.addEdge(CH);
        g.addEdge(DH);


        assertEquals(expected, g.getNeighbourhood(A, 4));
    }

    @Test
    public void testGetNeighbourhood2 () {
        Vertex A = new Vertex(1, "A");
        Vertex B = new Vertex(2, "B");
        Vertex C = new Vertex(3, "C");
        Vertex D = new Vertex(4, "D");
        Vertex E = new Vertex(5, "E");
        Vertex F = new Vertex(6, "F");
        Vertex G = new Vertex(7, "G");
        Vertex H = new Vertex(8, "H");

        Edge<Vertex> AB = new Edge<>(A, B, 3);
        Edge<Vertex> BC = new Edge<>(B, C, 6);
        Edge<Vertex> CH = new Edge<>(C, H, 5);
        Edge<Vertex> AD = new Edge<>(A, D, 7);
        Edge<Vertex> DE = new Edge<>(D, E, 4);
        Edge<Vertex> EH = new Edge<>(E, H, 2);
        Edge<Vertex> AF = new Edge<>(A, F, 6);
        Edge<Vertex> FG = new Edge<>(F, G, 9);
        Edge<Vertex> GH = new Edge<>(G, H, 10);
        Edge<Vertex> DC = new Edge<>(D, C, 1);

        Map<Vertex, Edge> expected = new HashMap<>();
        expected.put(B, AB);
        expected.put(C, DC);
        expected.put(D, AD);
        expected.put(F, AF);

        IGraph<Vertex, Edge<Vertex>> g = new ALGraph<>();
        g.addVertex(A);
        g.addVertex(B);
        g.addVertex(C);
        g.addVertex(D);
        g.addVertex(E);
        g.addVertex(F);
        g.addVertex(G);
        g.addVertex(H);
        g.addEdge(AB);
        g.addEdge(BC);
        g.addEdge(CH);
        g.addEdge(AD);
        g.addEdge(DE);
        g.addEdge(EH);
        g.addEdge(AF);
        g.addEdge(FG);
        g.addEdge(GH);
        g.addEdge(DC);


        assertEquals(expected, g.getNeighbourhood(A, 9));
    }

    @Test
    public void testGetNeighbourhoodNoNeighboursWithinRange () {
        Vertex A = new Vertex(1, "A");
        Vertex B = new Vertex(2, "B");
        Vertex C = new Vertex(3, "C");
        Vertex D = new Vertex(4, "D");
        Vertex E = new Vertex(5, "E");
        Vertex F = new Vertex(6, "F");
        Vertex G = new Vertex(7, "G");
        Vertex H = new Vertex(8, "H");

        Edge<Vertex> AB = new Edge<>(A, B, 3);
        Edge<Vertex> BC = new Edge<>(B, C, 6);
        Edge<Vertex> CH = new Edge<>(C, H, 5);
        Edge<Vertex> AD = new Edge<>(A, D, 7);
        Edge<Vertex> DE = new Edge<>(D, E, 4);
        Edge<Vertex> EH = new Edge<>(E, H, 2);
        Edge<Vertex> AF = new Edge<>(A, F, 6);
        Edge<Vertex> FG = new Edge<>(F, G, 9);
        Edge<Vertex> GH = new Edge<>(G, H, 10);
        Edge<Vertex> DC = new Edge<>(D, C, 1);

        Map<Vertex, Edge> expected = new HashMap<>();
        expected.put(B, AB);
        expected.put(C, DC);
        expected.put(D, AD);
        expected.put(F, AF);
        expected.put(E, DE);
        expected.put(G, FG);
        expected.put(H, CH); // *** doesn't work for EH; goes in order of the vertices first added.

        IGraph<Vertex, Edge<Vertex>> g = new ALGraph<>();
        g.addVertex(A);
        g.addVertex(B);
        g.addVertex(C);
        g.addVertex(D);
        g.addVertex(E);
        g.addVertex(F);
        g.addVertex(G);
        g.addVertex(H);
        g.addEdge(AB);
        g.addEdge(BC);
        g.addEdge(CH);
        g.addEdge(AD);
        g.addEdge(DE);
        g.addEdge(EH);
        g.addEdge(AF);
        g.addEdge(FG);
        g.addEdge(GH);
        g.addEdge(DC);


        assertEquals(expected, g.getNeighbourhood(A, 100));
    }

    @Test
    public void testGetNeighbourhoodAllNeighboursWithinRange () {
        Vertex A = new Vertex(1, "A");
        Vertex B = new Vertex(2, "B");
        Vertex C = new Vertex(3, "C");
        Vertex D = new Vertex(4, "D");
        Vertex E = new Vertex(5, "E");
        Vertex F = new Vertex(6, "F");
        Vertex G = new Vertex(7, "G");
        Vertex H = new Vertex(8, "H");

        Edge<Vertex> AB = new Edge<>(A, B, 3);
        Edge<Vertex> BC = new Edge<>(B, C, 6);
        Edge<Vertex> CH = new Edge<>(C, H, 5);
        Edge<Vertex> AD = new Edge<>(A, D, 7);
        Edge<Vertex> DE = new Edge<>(D, E, 4);
        Edge<Vertex> EH = new Edge<>(E, H, 2);
        Edge<Vertex> AF = new Edge<>(A, F, 6);
        Edge<Vertex> FG = new Edge<>(F, G, 9);
        Edge<Vertex> GH = new Edge<>(G, H, 10);
        Edge<Vertex> DC = new Edge<>(D, C, 1);

        Map<Vertex, Edge> expected = new HashMap<>();

        IGraph<Vertex, Edge<Vertex>> g = new ALGraph<>();
        g.addVertex(A);
        g.addVertex(B);
        g.addVertex(C);
        g.addVertex(D);
        g.addVertex(E);
        g.addVertex(F);
        g.addVertex(G);
        g.addVertex(H);
        g.addEdge(AB);
        g.addEdge(BC);
        g.addEdge(CH);
        g.addEdge(AD);
        g.addEdge(DE);
        g.addEdge(EH);
        g.addEdge(AF);
        g.addEdge(FG);
        g.addEdge(GH);
        g.addEdge(DC);


        assertEquals(expected, g.getNeighbourhood(A, 1));
    }

}
