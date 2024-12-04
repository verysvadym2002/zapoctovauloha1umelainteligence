import java.util.*;

class State implements Comparable<State> {
    int x, y, cost, direction;
    State parent;

    public State(int x, int y, int cost, int direction, State parent) {
        this.x = x;
        this.y = y;
        this.cost = cost;
        this.direction = direction; // 0=N, 1=E, 2=S, 3=W
        this.parent = parent;
    }

    @Override
    public int compareTo(State other) {
        return Integer.compare(this.cost, other.cost);
    }
}
