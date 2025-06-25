package project.sim;

import project.sim.graph.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task4Tests {

    @Test
    public void testGetDiameterAMSum() {
        Vertex A = new Vertex(1, "A");
        Vertex B = new Vertex(2, "B");
        Vertex C = new Vertex(3, "C");
        Vertex D = new Vertex(4, "D");
        Vertex X = new Vertex(5, "X");
        Vertex Y = new Vertex(6, "Y");
        Vertex Z = new Vertex(7,"Z");

        Edge<Vertex> AB1 = new Edge<>(A, B, 3);
        Edge<Vertex> AB2 = new Edge<>(A, D, 2);
        Edge<Vertex> AC = new Edge<>(A, C, 1);
        Edge<Vertex> AD1 = new Edge<>(B, C, 6);
        Edge<Vertex> AD2 = new Edge<>(C, D, 4);

        Edge<Vertex> XY = new Edge<>(X, Y, 4);
        Edge<Vertex> XZ = new Edge<>(X, Z, 4);
        Edge<Vertex> YZ = new Edge<>(Y, Z, 4);

        IGraph<Vertex, Edge<Vertex>> g = new AMGraph<>(100);
        g.addVertex(A);
        g.addVertex(B);
        g.addVertex(C);
        g.addVertex(D);
        g.addVertex(X);
        g.addVertex(Y);
        g.addVertex(Z);
        g.addEdge(AB2);
        g.addEdge(AB1);
        g.addEdge(AC);
        g.addEdge(AD2);
        g.addEdge(AD1);
        g.addEdge(XY);
        g.addEdge(XZ);
        g.addEdge(YZ);

        assertEquals(5, g.getDiameter(PathCostType.SUM_EDGES));
    }

    @Test
    public void testGetDiameterALSum() {
        Vertex A = new Vertex(1, "A");
        Vertex B = new Vertex(2, "B");
        Vertex C = new Vertex(3, "C");
        Vertex D = new Vertex(4, "D");
        Vertex X = new Vertex(5, "X");
        Vertex Y = new Vertex(6, "Y");
        Vertex Z = new Vertex(7,"Z");
        Vertex L = new Vertex(8,"L");
        Vertex M = new Vertex(9, "M");
        Vertex N = new Vertex(10, "N");
        Vertex O = new Vertex(11, "O");


        Edge<Vertex> AB1 = new Edge<>(A, B, 3);
        Edge<Vertex> AB2 = new Edge<>(A, D, 2);
        Edge<Vertex> AC = new Edge<>(A, C, 1);
        Edge<Vertex> AD1 = new Edge<>(B, C, 6);
        Edge<Vertex> AD2 = new Edge<>(B, D, 4);

        Edge<Vertex> LM = new Edge<>(L, M, 4);
        Edge<Vertex> MN = new Edge<>(M, N, 4);
        Edge<Vertex> NO = new Edge<>(N, O, 4);


        Edge<Vertex> XY = new Edge<>(X, Y, 4);
        Edge<Vertex> XZ = new Edge<>(X, Z, 4);
        Edge<Vertex> YZ = new Edge<>(Y, Z, 4);

        IGraph<Vertex, Edge<Vertex>> g = new ALGraph<>();
        g.addVertex(X);
        g.addVertex(Y);
        g.addVertex(Z);
        g.addVertex(L);
        g.addVertex(M);
        g.addVertex(N);
        g.addVertex(O);
        g.addVertex(A);
        g.addVertex(B);
        g.addVertex(C);
        g.addVertex(D);
        g.addEdge(AB2);
        g.addEdge(AB1);
        g.addEdge(AC);
        g.addEdge(AD2);
        g.addEdge(AD1);
        g.addEdge(XY);
        g.addEdge(XZ);
        g.addEdge(YZ);
        g.addEdge(LM);
        g.addEdge(MN);
        g.addEdge(NO);

        assertEquals(12, g.getDiameter(PathCostType.SUM_EDGES));
    }

    @Test
    public void testGetDiameterAMMin() {
        Vertex A = new Vertex(1, "A");
        Vertex B = new Vertex(2, "B");
        Vertex C = new Vertex(3, "C");
        Vertex D = new Vertex(4, "D");
        Vertex X = new Vertex(5, "X");
        Vertex Y = new Vertex(6, "Y");
        Vertex Z = new Vertex(7, "Z");
        Vertex L = new Vertex(8, "L");
        Vertex M = new Vertex(9, "M");
        Vertex N = new Vertex(10, "N");
        Vertex O = new Vertex(11, "O");


        Edge<Vertex> AB1 = new Edge<>(A, B, 3);
        Edge<Vertex> AB2 = new Edge<>(A, D, 2);
        Edge<Vertex> AC = new Edge<>(A, C, 1);
        Edge<Vertex> AD1 = new Edge<>(B, C, 6);
        Edge<Vertex> AD2 = new Edge<>(B, D, 4);

        Edge<Vertex> LM = new Edge<>(L, M, 4);
        Edge<Vertex> MN = new Edge<>(M, N, 4);
        Edge<Vertex> NO = new Edge<>(N, O, 4);


        Edge<Vertex> XY = new Edge<>(X, Y, 4);
        Edge<Vertex> XZ = new Edge<>(X, Z, 4);
        Edge<Vertex> YZ = new Edge<>(Y, Z, 4);

        IGraph<Vertex, Edge<Vertex>> g = new AMGraph<>(100);
        g.addVertex(X);
        g.addVertex(Y);
        g.addVertex(Z);
        g.addVertex(L);
        g.addVertex(M);
        g.addVertex(N);
        g.addVertex(O);
        g.addVertex(A);
        g.addVertex(B);
        g.addVertex(C);
        g.addVertex(D);
        g.addEdge(AB2);
        g.addEdge(AB1);
        g.addEdge(AC);
        g.addEdge(AD2);
        g.addEdge(AD1);
        g.addEdge(XY);
        g.addEdge(XZ);
        g.addEdge(YZ);
        g.addEdge(LM);
        g.addEdge(MN);
        g.addEdge(NO);

        assertEquals(4, g.getDiameter(PathCostType.MIN_EDGE));
    }
    @Test
    public void testGetDiameterALMin() {
        Vertex A = new Vertex(1, "A");
        Vertex B = new Vertex(2, "B");
        Vertex C = new Vertex(3, "C");
        Vertex D = new Vertex(4, "D");
        Vertex X = new Vertex(5, "X");
        Vertex Y = new Vertex(6, "Y");
        Vertex Z = new Vertex(7, "Z");
        Vertex L = new Vertex(8, "L");
        Vertex M = new Vertex(9, "M");
        Vertex N = new Vertex(10, "N");
        Vertex O = new Vertex(11, "O");


        Edge<Vertex> AB1 = new Edge<>(A, B, 3);
        Edge<Vertex> AB2 = new Edge<>(A, D, 2);
        Edge<Vertex> AC = new Edge<>(A, C, 1);
        Edge<Vertex> AD1 = new Edge<>(B, C, 6);
        Edge<Vertex> AD2 = new Edge<>(B, D, 4);

        Edge<Vertex> LM = new Edge<>(L, M, 4);
        Edge<Vertex> MN = new Edge<>(M, N, 4);
        Edge<Vertex> NO = new Edge<>(N, O, 4);


        Edge<Vertex> XY = new Edge<>(X, Y, 4);
        Edge<Vertex> XZ = new Edge<>(X, Z, 4);
        Edge<Vertex> YZ = new Edge<>(Y, Z, 4);

        IGraph<Vertex, Edge<Vertex>> g = new ALGraph<>();
        g.addVertex(X);
        g.addVertex(Y);
        g.addVertex(Z);
        g.addVertex(L);
        g.addVertex(M);
        g.addVertex(N);
        g.addVertex(O);
        g.addVertex(A);
        g.addVertex(B);
        g.addVertex(C);
        g.addVertex(D);
        g.addEdge(AB2);
        g.addEdge(AB1);
        g.addEdge(AC);
        g.addEdge(AD2);
        g.addEdge(AD1);
        g.addEdge(XY);
        g.addEdge(XZ);
        g.addEdge(YZ);
        g.addEdge(LM);
        g.addEdge(MN);
        g.addEdge(NO);

        assertEquals(4, g.getDiameter(PathCostType.MIN_EDGE));
    }

    @Test
    public void testGetDiameterAMMax() {
        Vertex A = new Vertex(1, "A");
        Vertex B = new Vertex(2, "B");
        Vertex C = new Vertex(3, "C");
        Vertex D = new Vertex(4, "D");
        Vertex X = new Vertex(5, "X");
        Vertex Y = new Vertex(6, "Y");
        Vertex Z = new Vertex(7, "Z");
        Vertex L = new Vertex(8, "L");
        Vertex M = new Vertex(9, "M");
        Vertex N = new Vertex(10, "N");
        Vertex O = new Vertex(11, "O");

        Edge<Vertex> AB1 = new Edge<>(A, B, 3);
        Edge<Vertex> AB2 = new Edge<>(A, D, 2);
        Edge<Vertex> AC = new Edge<>(A, C, 1);
        Edge<Vertex> AD1 = new Edge<>(B, C, 6);
        Edge<Vertex> AD2 = new Edge<>(B, D, 4);

        Edge<Vertex> LM = new Edge<>(L, M, 4);
        Edge<Vertex> MN = new Edge<>(M, N, 4);
        Edge<Vertex> NO = new Edge<>(N, O, 4);


        Edge<Vertex> XY = new Edge<>(X, Y, 4);
        Edge<Vertex> XZ = new Edge<>(X, Z, 4);
        Edge<Vertex> YZ = new Edge<>(Y, Z, 4);

        IGraph<Vertex, Edge<Vertex>> g = new AMGraph<>(100);
        g.addVertex(X);
        g.addVertex(Y);
        g.addVertex(Z);
        g.addVertex(L);
        g.addVertex(M);
        g.addVertex(N);
        g.addVertex(O);
        g.addVertex(A);
        g.addVertex(B);
        g.addVertex(C);
        g.addVertex(D);
        g.addEdge(AB2);
        g.addEdge(AB1);
        g.addEdge(AC);
        g.addEdge(AD2);
        g.addEdge(AD1);
        g.addEdge(XY);
        g.addEdge(XZ);
        g.addEdge(YZ);
        g.addEdge(LM);
        g.addEdge(MN);
        g.addEdge(NO);

        assertEquals(4, g.getDiameter(PathCostType.MAX_EDGE));
    }
    @Test
    public void testGetDiameterALMax() {
        Vertex A = new Vertex(1, "A");
        Vertex B = new Vertex(2, "B");
        Vertex C = new Vertex(3, "C");
        Vertex D = new Vertex(4, "D");
        Vertex X = new Vertex(5, "X");
        Vertex Y = new Vertex(6, "Y");
        Vertex Z = new Vertex(7, "Z");
        Vertex L = new Vertex(8, "L");
        Vertex M = new Vertex(9, "M");
        Vertex N = new Vertex(10, "N");
        Vertex O = new Vertex(11, "O");

        Edge<Vertex> AB1 = new Edge<>(A, B, 3);
        Edge<Vertex> AB2 = new Edge<>(A, D, 2);
        Edge<Vertex> AC = new Edge<>(A, C, 1);
        Edge<Vertex> AD1 = new Edge<>(B, C, 6);
        Edge<Vertex> AD2 = new Edge<>(B, D, 4);

        Edge<Vertex> LM = new Edge<>(L, M, 4);
        Edge<Vertex> MN = new Edge<>(M, N, 4);
        Edge<Vertex> NO = new Edge<>(N, O, 4);


        Edge<Vertex> XY = new Edge<>(X, Y, 4);
        Edge<Vertex> XZ = new Edge<>(X, Z, 4);
        Edge<Vertex> YZ = new Edge<>(Y, Z, 4);

        IGraph<Vertex, Edge<Vertex>> g = new ALGraph<>();
        g.addVertex(X);
        g.addVertex(Y);
        g.addVertex(Z);
        g.addVertex(L);
        g.addVertex(M);
        g.addVertex(N);
        g.addVertex(O);
        g.addVertex(A);
        g.addVertex(B);
        g.addVertex(C);
        g.addVertex(D);
        g.addEdge(AB2);
        g.addEdge(AB1);
        g.addEdge(AC);
        g.addEdge(AD2);
        g.addEdge(AD1);
        g.addEdge(XY);
        g.addEdge(XZ);
        g.addEdge(YZ);
        g.addEdge(LM);
        g.addEdge(MN);
        g.addEdge(NO);

        List<Vertex> expected = new ArrayList<>();
        expected.add(A);
        expected.add(B);
        expected.add(C);
        expected.add(D);

        assertEquals(4, g.getDiameter(PathCostType.MAX_EDGE));
    }

    @Test
    public void testGetDiameterShorterALSum() {
        Vertex A = new Vertex(1, "A");
        Vertex B = new Vertex(2, "B");
        Vertex C = new Vertex(3, "C");
        Vertex D = new Vertex(4, "D");

        Edge<Vertex> AB = new Edge<>(A,B,2);
        Edge<Vertex> AC = new Edge<>(A,C,4);
        Edge<Vertex> BC = new Edge<>(B,C, 1);
        Edge<Vertex> BD = new Edge<>(B,D,6);

        IGraph<Vertex, Edge<Vertex>> g = new ALGraph<>();
        g.addVertex(A);
        g.addVertex(B);
        g.addVertex(C);
        g.addVertex(D);

        g.addEdge(AB);
        g.addEdge(AC);
        g.addEdge(BC);
        g.addEdge(BD);

        assertEquals(8, g.getDiameter(PathCostType.SUM_EDGES));
    }

    @Test
    public void testGetDiameterShorterALMinPlease() {
        Vertex A = new Vertex(1, "A");
        Vertex B = new Vertex(2, "B");
        Vertex C = new Vertex(3, "C");
        Vertex D = new Vertex(4, "D");

        Edge<Vertex> AB = new Edge<>(A,B,2);
        Edge<Vertex> AC = new Edge<>(A,C,4);
        Edge<Vertex> BC = new Edge<>(B,C, 1);
        Edge<Vertex> BD = new Edge<>(B,D,6);

        IGraph<Vertex, Edge<Vertex>> g = new ALGraph<>();
        g.addVertex(A);
        g.addVertex(B);
        g.addVertex(C);
        g.addVertex(D);

        g.addEdge(AB);
        g.addEdge(AC);
        g.addEdge(BC);
        g.addEdge(BD);

        assertEquals(6, g.getDiameter(PathCostType.MIN_EDGE));
    }

    @Test
    public void testGetDiameterShorterAMSum() {
        Vertex A = new Vertex(1, "A");
        Vertex B = new Vertex(2, "B");
        Vertex C = new Vertex(3, "C");
        Vertex D = new Vertex(4, "D");

        Edge<Vertex> AB = new Edge<>(A,B,2);
        Edge<Vertex> AC = new Edge<>(A,C,4);
        Edge<Vertex> BC = new Edge<>(B,C, 1);
        Edge<Vertex> BD = new Edge<>(B,D,6);

        IGraph<Vertex, Edge<Vertex>> g = new AMGraph<>(10);
        g.addVertex(A);
        g.addVertex(B);
        g.addVertex(C);
        g.addVertex(D);

        g.addEdge(AB);
        g.addEdge(AC);
        g.addEdge(BC);
        g.addEdge(BD);

        assertEquals(8, g.getDiameter(PathCostType.SUM_EDGES));
    }

    @Test
    public void testGetCenterAL1Sum() {
        Vertex A = new Vertex(1, "A");
        Vertex B = new Vertex(2, "B");
        Vertex C = new Vertex(3, "C");
        Vertex D = new Vertex(4, "D");

        Edge<Vertex> AB = new Edge<>(A,B,2);
        Edge<Vertex> AC = new Edge<>(A,C,4);
        Edge<Vertex> BC = new Edge<>(B,C, 1);
        Edge<Vertex> BD = new Edge<>(B,D,6);

        IGraph<Vertex, Edge<Vertex>> g = new ALGraph<>();
        g.addVertex(A);
        g.addVertex(B);
        g.addVertex(C);
        g.addVertex(D);

        g.addEdge(AB);
        g.addEdge(AC);
        g.addEdge(BC);
        g.addEdge(BD);

        assertEquals(B, g.getCenter(PathCostType.SUM_EDGES));
    }

    @Test
    public void testGetCenterAM1Sum() {
        Vertex A = new Vertex(1, "A");
        Vertex B = new Vertex(2, "B");
        Vertex C = new Vertex(3, "C");
        Vertex D = new Vertex(4, "D");

        Edge<Vertex> AB = new Edge<>(A,B,2);
        Edge<Vertex> AC = new Edge<>(A,C,4);
        Edge<Vertex> BC = new Edge<>(B,C, 1);
        Edge<Vertex> BD = new Edge<>(B,D,6);

        IGraph<Vertex, Edge<Vertex>> g = new AMGraph<>(20);
        g.addVertex(A);
        g.addVertex(B);
        g.addVertex(C);
        g.addVertex(D);

        g.addEdge(AB);
        g.addEdge(AC);
        g.addEdge(BC);
        g.addEdge(BD);

        assertEquals(B, g.getCenter(PathCostType.SUM_EDGES));
    }

    @Test
    public void testGetCenterAL1Min() {
        Vertex A = new Vertex(1, "A");
        Vertex B = new Vertex(2, "B");
        Vertex C = new Vertex(3, "C");
        Vertex D = new Vertex(4, "D");

        Edge<Vertex> AB = new Edge<>(A,B,2);
        Edge<Vertex> AC = new Edge<>(A,C,4);
        Edge<Vertex> BC = new Edge<>(B,C, 1);
        Edge<Vertex> BD = new Edge<>(B,D,6);

        IGraph<Vertex, Edge<Vertex>> g = new ALGraph<>();
        g.addVertex(B);
        g.addVertex(D);
        g.addVertex(A);
        g.addVertex(C);

        g.addEdge(AB);
        g.addEdge(AC);
        g.addEdge(BC);
        g.addEdge(BD);

        assertEquals(B, g.getCenter(PathCostType.MIN_EDGE));
    }

    @Test
    public void testGetCenterAM1Min() {
        Vertex A = new Vertex(1, "A");
        Vertex B = new Vertex(2, "B");
        Vertex C = new Vertex(3, "C");
        Vertex D = new Vertex(4, "D");

        Edge<Vertex> AB = new Edge<>(A,B,2);
        Edge<Vertex> AC = new Edge<>(A,C,4);
        Edge<Vertex> BC = new Edge<>(B,C, 1);
        Edge<Vertex> BD = new Edge<>(B,D,6);

        IGraph<Vertex, Edge<Vertex>> g = new AMGraph<>(4);
        g.addVertex(D);
        g.addVertex(B);
        g.addVertex(A);
        g.addVertex(C);

        g.addEdge(AB);
        g.addEdge(AC);
        g.addEdge(BC);
        g.addEdge(BD);

        assertEquals(A.id(), g.getCenter(PathCostType.MIN_EDGE).id());
    }
    @Test
    public void testGetCenterAL1Max() {
        Vertex A = new Vertex(1, "A");
        Vertex B = new Vertex(2, "B");
        Vertex C = new Vertex(3, "C");
        Vertex D = new Vertex(4, "D");

        Edge<Vertex> AB = new Edge<>(A,B,2);
        Edge<Vertex> AC = new Edge<>(A,C,4);
        Edge<Vertex> BC = new Edge<>(B,C, 1);
        Edge<Vertex> BD = new Edge<>(B,D,6);

        IGraph<Vertex, Edge<Vertex>> g = new ALGraph<>();
        g.addVertex(B);
        g.addVertex(D);
        g.addVertex(A);
        g.addVertex(C);

        g.addEdge(AB);
        g.addEdge(AC);
        g.addEdge(BC);
        g.addEdge(BD);

        assertEquals(B, g.getCenter(PathCostType.MAX_EDGE));
    }

    @Test
    public void testGetCenterAM1Max() {
        Vertex A = new Vertex(1, "A");
        Vertex B = new Vertex(2, "B");
        Vertex C = new Vertex(3, "C");
        Vertex D = new Vertex(4, "D");

        Edge<Vertex> AB = new Edge<>(A,B,2);
        Edge<Vertex> AC = new Edge<>(A,C,4);
        Edge<Vertex> BC = new Edge<>(B,C, 1);
        Edge<Vertex> BD = new Edge<>(B,D,6);

        IGraph<Vertex, Edge<Vertex>> g = new AMGraph<>(4);
        g.addVertex(B);
        g.addVertex(D);
        g.addVertex(A);
        g.addVertex(C);

        g.addEdge(AB);
        g.addEdge(AC);
        g.addEdge(BC);
        g.addEdge(BD);

        assertEquals(B, g.getCenter(PathCostType.MAX_EDGE));
    }

    @Test
    public void testGetCenterAL2Sum() {
        Vertex A = new Vertex(1, "A");
        Vertex B = new Vertex(2, "B");
        Vertex C = new Vertex(3, "C");
        Vertex D = new Vertex(4, "D");

        Edge<Vertex> AB = new Edge<>(A,B,1);
        Edge<Vertex> AC = new Edge<>(A,C,1);
        Edge<Vertex> CD = new Edge<>(C,D,1);

        IGraph<Vertex, Edge<Vertex>> g = new ALGraph<>();
        g.addVertex(A);
        g.addVertex(B);
        g.addVertex(C);
        g.addVertex(D);

        g.addEdge(CD);
        g.addEdge(AB);
        g.addEdge(AC);

        assertEquals(A, g.getCenter(PathCostType.SUM_EDGES));
    }

    @Test
    public void testGetCenterAM2Sum() {
        Vertex B = new Vertex(2, "B");
        Vertex C = new Vertex(3, "C");
        Vertex A = new Vertex(1, "A");
        Vertex D = new Vertex(4, "D");

        Edge<Vertex> AB = new Edge<>(A,B,1);
        Edge<Vertex> AC = new Edge<>(C,A,1);
        Edge<Vertex> CD = new Edge<>(C,D,1);

        IGraph<Vertex, Edge<Vertex>> g = new AMGraph<>(20);
        g.addVertex(C);
        g.addVertex(B);
        g.addVertex(D);
        g.addVertex(A);

        g.addEdge(CD);
        g.addEdge(AB);
        g.addEdge(AC);

        assertEquals(3,g.getCenter(PathCostType.SUM_EDGES).id());
    }

    @Test
    public void testGetCenterAL2Min() {
        Vertex A = new Vertex(1, "A");
        Vertex B = new Vertex(2, "B");
        Vertex C = new Vertex(3, "C");
        Vertex D = new Vertex(4, "D");

        Edge<Vertex> AB = new Edge<>(A,B,1);
        Edge<Vertex> AC = new Edge<>(A,C,1);
        Edge<Vertex> CD = new Edge<>(C,D,1);

        IGraph<Vertex, Edge<Vertex>> g = new ALGraph<>();
        g.addVertex(B);
        g.addVertex(A);
        g.addVertex(C);
        g.addVertex(D);

        g.addEdge(CD);
        g.addEdge(AB);
        g.addEdge(AC);

        assertEquals(B, g.getCenter(PathCostType.MIN_EDGE));
    }

    @Test
    public void testGetCenterAM2Min() {
        Vertex A = new Vertex(1, "A");
        Vertex B = new Vertex(2, "B");
        Vertex C = new Vertex(3, "C");
        Vertex D = new Vertex(4, "D");

        Edge<Vertex> AB = new Edge<>(A,B,1);
        Edge<Vertex> AC = new Edge<>(A,C,1);
        Edge<Vertex> CD = new Edge<>(C,D,1);

        IGraph<Vertex, Edge<Vertex>> g = new AMGraph<>(20);
        g.addVertex(A);
        g.addVertex(B);
        g.addVertex(C);
        g.addVertex(D);

        g.addEdge(CD);
        g.addEdge(AB);
        g.addEdge(AC);

        assertEquals(A, g.getCenter(PathCostType.MIN_EDGE));
    }

    @Test
    public void testGetCenterAL2Max() {
        Vertex A = new Vertex(1, "A");
        Vertex B = new Vertex(2, "B");
        Vertex C = new Vertex(3, "C");
        Vertex D = new Vertex(4, "D");

        Edge<Vertex> AB = new Edge<>(A,B,1);
        Edge<Vertex> AC = new Edge<>(A,C,1);
        Edge<Vertex> CD = new Edge<>(C,D,1);

        IGraph<Vertex, Edge<Vertex>> g = new ALGraph<>();
        g.addVertex(B);
        g.addVertex(A);
        g.addVertex(C);
        g.addVertex(D);

        g.addEdge(CD);
        g.addEdge(AB);
        g.addEdge(AC);

        assertEquals(B, g.getCenter(PathCostType.MAX_EDGE));
    }

    @Test
    public void testGetCenterAM2Max() {
        Vertex A = new Vertex(1, "A");
        Vertex B = new Vertex(2, "B");
        Vertex C = new Vertex(3, "C");
        Vertex D = new Vertex(4, "D");

        Edge<Vertex> AB = new Edge<>(A,B,1);
        Edge<Vertex> AC = new Edge<>(A,C,1);
        Edge<Vertex> CD = new Edge<>(C,D,1);

        IGraph<Vertex, Edge<Vertex>> g = new ALGraph<>();
        g.addVertex(C);
        g.addVertex(B);
        g.addVertex(A);
        g.addVertex(D);

        g.addEdge(CD);
        g.addEdge(AB);
        g.addEdge(AC);

        assertEquals(C, g.getCenter(PathCostType.MAX_EDGE));
    }

    @Test
    public void testGetDiameterMultipleComponents() {
        Vertex A = new Vertex(1, "A");
        Vertex B = new Vertex(2, "B");
        Vertex C = new Vertex(3, "C");
        Vertex D = new Vertex(4, "D");
        Vertex P = new Vertex(5, "P");
        Vertex Q = new Vertex(6, "Q");
        Vertex R = new Vertex(7, "R");
        Vertex S = new Vertex(8, "S");

        Edge<Vertex> AB1 = new Edge<>(A, B, 2);
        Edge<Vertex> AB2 = new Edge<>(A, B, 3);
        Edge<Vertex> AC = new Edge<>(A, C, 1);
        Edge<Vertex> AD1 = new Edge<>(A, D, 4);
        Edge<Vertex> AD2 = new Edge<>(A, D, 6);

        IGraph<Vertex, Edge<Vertex>> g = new ALGraph<>();
        g.addVertex(A);
        g.addVertex(B);
        g.addVertex(C);
        g.addVertex(D);
        g.addEdge(AB1);
        g.addEdge(AB2);
        g.addEdge(AC);
        g.addEdge(AD1);
        g.addEdge(AD2);

        assertEquals(4, g.getDiameter(PathCostType.MIN_EDGE));
    }



}

