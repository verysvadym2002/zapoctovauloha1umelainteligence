import java.util.*;

class AStarSolver {
    int[][] maze;
    int[] start;
    int[] goal;

    public AStarSolver(int[][] maze, int[] start, int[] goal) {
        this.maze = maze;
        this.start = start;
        this.goal = goal;
    }

    public void solve() {
        PriorityQueue<State> openSet = new PriorityQueue<>();
        HashSet<String> closedSet = new HashSet<>();

        // Startovací stav
        State initialState = new State(start[0], start[1], 0, 0, null);
        openSet.add(initialState);

        while (!openSet.isEmpty()) {
            State current = openSet.poll();

            // Kontrola cíle
            if (current.x == goal[0] && current.y == goal[1]) {
                printPath(current);
                return;
            }

            String key = current.x + "," + current.y + "," + current.direction;
            if (closedSet.contains(key)) continue;
            closedSet.add(key);

            // Generování následníků
            for (State neighbor : getNeighbors(current)) {
                openSet.add(neighbor);
            }
        }

        System.out.println("Cesta nenalezena.");
    }

    private List<State> getNeighbors(State state) {
        List<State> neighbors = new ArrayList<>();
        int[][] moves = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // N, E, S, W

        for (int i = 0; i < moves.length; i++) {
            int newX = state.x + moves[i][0];
            int newY = state.y + moves[i][1];

            if (isValid(newX, newY)) {
                int newCost = state.cost + (state.direction == i ? 3 : 4);
                neighbors.add(new State(newX, newY, newCost, i, state));
            }
        }
        return neighbors;
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 1;
    }

    private void printPath(State state) {
        List<State> path = new ArrayList<>();
        while (state != null) {
            path.add(state);
            state = state.parent;
        }
        Collections.reverse(path);

        for (State step : path) {
            System.out.println("(" + step.x + ", " + step.y + ")");
        }
    }
}
