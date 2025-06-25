package project.sim.sealevels;

import project.sim.graph.Vertex;

public final class GridLocation extends Vertex{
    public final int row;
    public final int col;

    public GridLocation(int row, int col, int id, String name) {
        super(id, name);
        this.row = row;
        this.col = col;
    }

    public int getRow(){return row;}

    public int getCol(){return col;}

    public GridLocation(int row, int col) {
        super(1,"placeholder");
        this.row = row;
        this.col = col;
    }
    
    @Override public boolean equals(Object rhs) {
        if (!(rhs instanceof GridLocation)) return false;
        
        var other = (GridLocation) rhs;
        return row == other.row && col == other.col;
    }
    
    @Override public int hashCode() {
        return 31 * row + col;
    }
    
    @Override public String toString() {
        var b = new StringBuilder();
        b.append("{ ");
        b.append(row);
        b.append(", ");
        b.append(col);
        b.append(" }");
        return b.toString();
    }
}
