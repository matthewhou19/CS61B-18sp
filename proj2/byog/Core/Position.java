package byog.Core;

public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object x) {
        if (x == this) return true;
        if (x == null) return false;
        if (x.getClass() != this.getClass()) return false;
        Position a = (Position) x;
        return a.getX() == this.getX() && a.getY() == this.getY();
    }

    @Override
    public int hashCode(){
        return this.x * 99999 + y;
    }

}
