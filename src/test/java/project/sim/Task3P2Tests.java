package project.sim;

import project.sim.graph.*;
import project.sim.sealevels.GridLocation;
import org.junit.jupiter.api.Test;

import static project.sim.sealevels.SeaLevels.dangerLevel;
import static project.sim.sealevels.SeaLevels.isSubmerged;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3P2Tests {

    @Test
    public void testIsSubmerged() {

        GridLocation[] sources = new GridLocation[2];
        sources[0] = new GridLocation(0,0);
        sources[1] = new GridLocation(6,6);

        double[][] terrain = {{-1,0,0,4,0,0,1},
                {0,0,4,0,-1,-1,0},
                {0,4,0,0,0,0,0,0},
                {4,0,-1,-1,0,0,3},
                {0,-1,-2,-1,0,3,0},
                {0,0,-1,0,3,0,0},
                {0,0,0,3,0,0,-1}};

        boolean[][] submerged = {{true,true,true,false,false,false,false},
                {true,true,false,false,false,false,false},
                {true,false,false,false,false,false,false},
                {false,false,false,false,false,false,false},
                {false,false,false,false,false,false,true},
                {false,false,false,false,false,true,true},
                {false,false,false,false,true,true,true}};

        boolean[][] output = isSubmerged(terrain, sources, 1);

        boolean good = true;
        loop: for(int i = 0; i<7; i++){
            for(int j = 0; j<7; j++){
                if(output[i][j] == submerged[i][j]){
                    good = true;
                } else{
                    good = false;
                    break loop;
                }
            }
        }

        assertEquals(true, good);
    }

    @Test
    public void testIsSubmerged2() {

        GridLocation[] sources = new GridLocation[2];
        sources[0] = new GridLocation(0,0);
        sources[1] = new GridLocation(6,6);

        double[][] terrain = {{-1,0,0,4,0,0,1},
                {0,0,4,0,-1,-1,0},
                {0,4,0,0,0,0,0,0},
                {4,0,-1,-1,0,0,3},
                {0,-1,-2,-1,0,3,0},
                {0,0,-1,0,3,0,0},
                {0,0,0,3,0,0,-1}};

        boolean[][] submerged = {{true,true,true,false,true,true,true},
                {true,true,false,true,true,true,true},
                {true,false,true,true,true,true,true},
                {false,true,true,true,true,true,true},
                {true,true,true,true,true,true,true},
                {true,true,true,true,true,true,true},
                {true,true,true,true,true,true,true}};

        boolean[][] output = isSubmerged(terrain, sources, 3);

        boolean good = true;
        loop: for(int i = 0; i<7; i++){
            for(int j = 0; j<7; j++){
                if(output[i][j] == submerged[i][j]){
                    good = true;
                } else{
                    good = false;
                    break loop;
                }
            }
        }

        assertEquals(true, good);
    }

    @Test
    public void testIsSubmerged1() {
        GridLocation[] sources = new GridLocation[5];
        sources[0] = new GridLocation(0,0);
        sources[1] = new GridLocation(6,6);
        sources[2] = new GridLocation(50,21);
        sources[3] = new GridLocation(99,99);
        sources[4] = new GridLocation(3,4);

        double[][] terrain = new double[100][100];

        for(int i = 0; i < 100; i++) {
            for(int j = 0; j < 100; j++) {
                terrain[i][j] = 0;
            }
        }

        boolean[][] submerged = new boolean[100][100];

        for(int i = 0; i < 100; i++) {
            for(int j = 0; j < 100; j++) {
                submerged[i][j] = true;
            }
        }

        boolean[][] output = isSubmerged(terrain, sources, 0);

        boolean good = true;
        loop: for(int i = 0; i<100; i++){
            for(int j = 0; j<100; j++){
                if(output[i][j] == submerged[i][j]){
                    good = true;
                } else {
                    good = false;
                    break loop;
                }
            }
        }

        assertEquals(true, good);

    }

    @Test
    public void testIsSubmergedLarge() {
        GridLocation[] sources = new GridLocation[5];
        sources[0] = new GridLocation(0,0);
        sources[1] = new GridLocation(6,6);
        sources[2] = new GridLocation(50,21);
        sources[3] = new GridLocation(99,99);
        sources[4] = new GridLocation(3,4);

        double[][] terrain = new double[1000][1000];

        for(int i = 0; i < 1000; i++) {
            for(int j = 0; j < 1000; j++) {
                terrain[i][j] = 0;
            }
        }

        boolean[][] submerged = new boolean[1000][1000];

        for(int i = 0; i < 1000; i++) {
            for(int j = 0; j < 1000; j++) {
                submerged[i][j] = true;
            }
        }

        boolean[][] output = isSubmerged(terrain, sources, 0);

        boolean good = true;
        loop: for(int i = 0; i<1000; i++){
            for(int j = 0; j<1000; j++){
                if(output[i][j] == submerged[i][j]){
                    good = true;
                } else {
                    good = false;
                    break loop;
                }
            }
        }

        assertEquals(true, good);

    }


    @Test
    public void testDangerLevels() {

        GridLocation[] sources = new GridLocation[1];
        sources[0] = new GridLocation(0,0);


        double[][] terrain = {{-1,0,5},{0,5,0},{1,0,0}};

        double[][] dangerLevels = {{-1,0,5},{0,5,1},{1,1,1}};

        double[][] output = dangerLevel(terrain, sources);

        boolean good = true;
        loop: for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                if(output[i][j] == dangerLevels[i][j]){
                    good = true;
                } else{
                    good = false;
                    break loop;
                }
            }
        }

        assertEquals(true, good);
    }

    @Test
    public void testDangerLevels4() {

        GridLocation[] sources = new GridLocation[2];
        sources[0] = new GridLocation(0,0);
        sources[1] = new GridLocation(2,2);


        double[][] terrain = {{0,5,0},
                            {5,0,5},
                            {-1,0,0}};

        double[][] dangerLevels = {{0,5,5},
                                    {5,0,5},
                                    {0,0,0}};

        double[][] output = dangerLevel(terrain, sources);

        boolean good = true;
        loop: for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                if(output[i][j] == dangerLevels[i][j]){
                    good = true;
                } else{
                    good = false;
                    break loop;
                }
            }
        }

        assertEquals(true, good);
    }

    @Test
    public void testDangerLevels2() {

        GridLocation[] sources = new GridLocation[2];
        sources[0] = new GridLocation(0,0);
        sources[1] = new GridLocation(3,3);


        double[][] terrain = {{-1,0,0,4,-1},
                {0,0,4,-1,3},
                {0,4,-1,3,0},
                {4,-1,3,0,-1}};

        double[][] dangerLevels = {{-1,0,0,4,3},
                {0,0,4,3,3},
                {0,4,3,3,0},
                {4,3,3,0,-1}};

        double[][] output = dangerLevel(terrain, sources);

        boolean good = true;
        loop: for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                if(output[i][j] == dangerLevels[i][j]){
                    good = true;
                } else{
                    good = false;
                    break loop;
                }
            }
        }

        assertEquals(true, good);
    }


    @Test
    public void testDangerLevels3() {

        GridLocation[] sources = new GridLocation[2];
        sources[0] = new GridLocation(0,0);
        sources[1] = new GridLocation(3,3);


        double[][] terrain = {{-1,0,0,4,-1},
                {0,0,4,-1,3},
                {0,4,-1,3,0},
                {4,-1,3,0,-1}};

        double[][] dangerLevels = {{-1,0,0,4,3},
                {0,0,4,3,3},
                {0,4,3,3,0},
                {4,3,3,0,-1}};

        double[][] output = dangerLevel(terrain, sources);

        boolean good = true;
        loop: for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                if(output[i][j] == dangerLevels[i][j]){
                    good = true;
                } else{
                    good = false;
                    break loop;
                }
            }
        }

        assertEquals(true, good);
    }

    @Test
    public void testDangerLevelsLarge() {
        GridLocation[] sources = new GridLocation[5];
        sources[0] = new GridLocation(0,0);
        sources[1] = new GridLocation(6,6);
        sources[2] = new GridLocation(50,21);
        sources[3] = new GridLocation(99,99);
        sources[4] = new GridLocation(3,4);

        double[][] terrain = new double[1000][1000];

        for(int i = 0; i < 1000; i++) {
            for(int j = 0; j < 1000; j++) {
                terrain[i][j] = 0;
            }
        }

        double[][] dangerLevels = new double[1000][1000];

        for(int i = 0; i < 1000; i++) {
            for(int j = 0; j < 1000; j++) {
                dangerLevels[i][j] = 0;
            }
        }

        double[][] output = dangerLevel(terrain, sources);

        boolean good = true;
        loop: for(int i = 0; i<1000; i++){
            for(int j = 0; j<1000; j++){
                if(output[i][j] == dangerLevels[i][j]){
                    good = true;
                } else {
                    good = false;
                    break loop;
                }
            }
        }

        assertEquals(true, good);

    }
}



