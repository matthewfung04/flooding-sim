package project.sim.sealevels;

import project.sim.graph.*;

import java.util.*;

public class SeaLevels {

    /**
     * Optimized is Submerged method that handles larger terrains such as in SeaLevelsGUI
     *
     * @param terrainLarge requires that all entries are not null and the size is greater than 0
     * @param sources      requires that the corresponding terrain location value to the watersource is less than or equal to the waterLevel
     * @param level        requires nothing
     * @return a truth matrix that shows if a cell is submerged or not (true or false) depending on the ith row and jth column
     */
    public static boolean[][] isSubmergedOptimized(double[][] terrainLarge,
                                                   GridLocation[] sources,
                                                   double level) {
        int rows = terrainLarge.length;
        int cols = terrainLarge[0].length;
        boolean[][] isSubmerged = new boolean[rows][cols];
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                GridLocation cell = new GridLocation(i, j);
                visited[i][j] = false;
                isSubmerged[i][j] = false;
            }
        }

        Queue<GridLocation> queue = new LinkedList<GridLocation>();

        for (GridLocation waterSource : sources) {

            queue.add(waterSource);
            isSubmerged[waterSource.getRow()][waterSource.getCol()] = true;

            while (!queue.isEmpty()) {
                GridLocation current = queue.poll();
                int currentRow = current.getRow();
                int currentCol = current.getCol();
                visited[currentRow][currentCol] = true;

                List<GridLocation> neighbours = getLocationNeighbours(current, rows, cols);

                for (GridLocation neighbour : neighbours) {
                    int neighbourRow = neighbour.getRow();
                    int neighbourCol = neighbour.getCol();
                    if (!visited[neighbourRow][neighbourCol] && terrainLarge[neighbourRow][neighbourCol] <= level &&
                            !isSubmerged[neighbourRow][neighbourCol]) {
                        isSubmerged[neighbourRow][neighbourCol] = true;
                        visited[neighbourRow][neighbourCol] = true;
                        queue.add(neighbour);
                    }
                }
            }
        }

        return isSubmerged;
    }


    /**
     * Checks if every grid of an elevation terrain is submerged or not
     *
     * @param terrain      requires that all entries are not null and the size is greater than 0
     * @param waterSources requires that the corresponding terrain location value to the watersource is less than or equal to the waterLevel
     * @param waterLevel   requires nothing
     * @return a truth matrix that shows if a cell is submerged or not (true or false) depending on the ith row and jth column
     */
    public static boolean[][] isSubmerged(double[][] terrain,
                                          GridLocation[] waterSources,
                                          double waterLevel) {
        int rows = terrain.length;
        int cols = terrain[0].length;
        boolean[][] isSubmerged = new boolean[rows][cols];
        boolean[][] visited = new boolean[rows][cols];

        if (rows > 100 || cols > 100) {
            return isSubmergedOptimized(terrain, waterSources, waterLevel);
        }

        //Create instance of ALgraph
        IGraph<GridLocation, Edge<GridLocation>> graph = makeGraph(rows, cols, terrain);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                visited[i][j] = false;
                isSubmerged[i][j] = false;
            }
        }

        Queue<GridLocation> queue = new LinkedList<GridLocation>();

        for (GridLocation waterSource : waterSources) {

            queue.add(waterSource);
            isSubmerged[waterSource.getRow()][waterSource.getCol()] = true;

            while (!queue.isEmpty()) {
                GridLocation current = queue.poll();
                int currentRow = current.getRow();
                int currentCol = current.getCol();
                visited[currentRow][currentCol] = true;

                //Call getNeighbours function from task 2
                Map<GridLocation, Edge<GridLocation>> map = graph.getNeighbours(current);

                for (Map.Entry<GridLocation, Edge<GridLocation>> entry : map.entrySet()) {
                    int neighbourRow = entry.getKey().getRow();
                    int neighbourCol = entry.getKey().getCol();

                    if (!visited[neighbourRow][neighbourCol] && terrain[neighbourRow][neighbourCol] <= waterLevel &&
                            !isSubmerged[neighbourRow][neighbourCol]) {
                        isSubmerged[neighbourRow][neighbourCol] = true;
                        visited[neighbourRow][neighbourCol] = true;
                        queue.add(entry.getKey());
                    }
                }
            }
        }

        return isSubmerged;
    }


    /**
     * Creates an instance of an AL graph with GridLocations as vertices
     *
     * @param terrain requires that all entries are not null and the size is greater than 0
     * @param rows    requires that rows is greater than 0
     * @param cols    requires that cols is greater than 0
     * @return an AL graph that has a GridLocation for each cell in
     * terrain and connects all of them via an edge
     */
    private static IGraph<GridLocation, Edge<GridLocation>> makeGraph(int rows, int cols, double[][] terrain) {

        IGraph<GridLocation, Edge<GridLocation>> graph = new ALGraph<>();
        int id = 0;
        //Initialize graph with vertices
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                GridLocation cell = new GridLocation(i, j, id, String.valueOf(i) + " x " + String.valueOf(0));
                graph.addVertex(cell);
                id++;
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                GridLocation currentCell = new GridLocation(i, j);
                List<GridLocation> neighbours = getLocationNeighbours(currentCell, rows, cols);
                for (GridLocation neighbour : neighbours) {
                    Edge<GridLocation> edge = new Edge<GridLocation>(currentCell, neighbour);
                    if (!graph.hasEdge(edge)) {
                        graph.addEdge(edge);
                    }
                }
            }
        }

        return graph;
    }

    /**
     * Gets the location of neighbours in a rectangular or square grid.
     * Neighbours refers to either up, down, left or right - Not diagonal
     *
     * @param source requires source is not null
     * @param rows   requires that rows is greater than 0
     * @param cols   requires that cols is greater than 0
     * @return a list of GridLocations with a maximum of 4 neighbours and a minimum of 2
     */
    private static List<GridLocation> getLocationNeighbours(GridLocation source, int rows, int cols) {

        List<GridLocation> listNeighbours = new ArrayList<>();

        int row = source.getRow();
        int col = source.getCol();

        if (row >= 0 && row < rows - 1) {
            listNeighbours.add(new GridLocation(row + 1, col));
        }
        if (row <= rows - 1 && row > 0) {
            listNeighbours.add(new GridLocation(row - 1, col));
        }
        if (col > 0) {
            listNeighbours.add(new GridLocation(row, col - 1));
        }
        if (col < cols - 1) {
            listNeighbours.add(new GridLocation(row, col + 1));
        }

        return listNeighbours;
    }


    /**
     * Finds the danger levels of each terrain. The danger level is the minimum water level at
     * the sources resulting in flooding
     *
     * @param terrain      requires that all entries are not null and the size is greater than 0
     * @param waterSources requires that the corresponding terrain location value
     *                     to the waterSource is less than or equal to the waterLevel
     * @return returns a matrix where each element corresponds to the matching terrain cell's danger level
     */
    public static double[][] dangerLevel(double[][] terrain,
                                         GridLocation[] waterSources) {
        int rows = terrain.length;
        int cols = terrain[0].length;
        double[][] dangerLevels = new double[rows][cols];

        if (rows > 50 || cols > 50) {
            return dangerLevelOptimized(terrain, waterSources);
        }

        IGraph<GridLocation, Edge<GridLocation>> graph = makeGraph(rows, cols, terrain);


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                GridLocation cell = new GridLocation(i, j);
                dangerLevels[i][j] = Double.MAX_VALUE;
            }
        }

        Queue<GridLocation> queue = new LinkedList<>();

        for (GridLocation waterSource : waterSources) {
            int waterSourceRow = waterSource.getRow();
            int waterSourceCol = waterSource.getCol();

            dangerLevels[waterSourceRow][waterSourceCol] = terrain[waterSourceRow][waterSourceCol];
            queue.add(waterSource);

            while (!queue.isEmpty()) {
                GridLocation current = queue.poll();

                Map<GridLocation, Edge<GridLocation>> map = graph.getNeighbours(current);

                for (Map.Entry<GridLocation, Edge<GridLocation>> entry : map.entrySet()) {
                    int neighbourRow = entry.getKey().getRow();
                    int neighbourCol = entry.getKey().getCol();
                    double newDangerLevel;

                    if (dangerLevels[current.getRow()][current.getCol()] <= terrain[neighbourRow][neighbourCol]) {
                        newDangerLevel = terrain[neighbourRow][neighbourCol];
                    } else {
                        newDangerLevel = dangerLevels[current.getRow()][current.getCol()];
                    }

                    if (newDangerLevel < dangerLevels[neighbourRow][neighbourCol]) {
                        dangerLevels[neighbourRow][neighbourCol] = newDangerLevel;
                        queue.add(entry.getKey());
                    }
                }
            }
        }

        return dangerLevels;
    }


    /**
     * An optimized dangerLevel function that handles larger cases (rows and cols > 50)
     *
     * @param terrain      requires that all entries are not null and the size is greater than 0
     * @param waterSources requires that the corresponding terrain location value
     *                     to the waterSource is less than or equal to the waterLevel
     * @return returns a matrix where each element corresponds to the matching terrain cell's danger level
     */
    public static double[][] dangerLevelOptimized(double[][] terrain,
                                                  GridLocation[] waterSources) {
        int rows = terrain.length;
        int cols = terrain[0].length;
        double[][] dangerLevels = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                GridLocation cell = new GridLocation(i, j);
                dangerLevels[i][j] = Double.MAX_VALUE;
            }
        }

        Queue<GridLocation> queue = new LinkedList<>();

        for (GridLocation waterSource : waterSources) {
            int waterSourceRow = waterSource.getRow();
            int waterSourceCol = waterSource.getCol();

            dangerLevels[waterSourceRow][waterSourceCol] = terrain[waterSourceRow][waterSourceCol];
            queue.add(waterSource);

            while (!queue.isEmpty()) {
                GridLocation current = queue.poll();

                List<GridLocation> neighbours = getLocationNeighbours(current, rows, cols);

                for (GridLocation neighbour : neighbours) {
                    int neighbourRow = neighbour.getRow();
                    int neighbourCol = neighbour.getCol();
                    double newDangerLevel;

                    if (dangerLevels[current.getRow()][current.getCol()] <= terrain[neighbourRow][neighbourCol]) {
                        newDangerLevel = terrain[neighbourRow][neighbourCol];
                    } else {
                        newDangerLevel = dangerLevels[current.getRow()][current.getCol()];
                    }

                    if (newDangerLevel < dangerLevels[neighbourRow][neighbourCol]) {
                        dangerLevels[neighbourRow][neighbourCol] = newDangerLevel;
                        queue.add(neighbour);
                    }
                }
            }
        }

        return dangerLevels;
    }
}
