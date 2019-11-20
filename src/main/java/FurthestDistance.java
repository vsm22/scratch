import java.util.*;

public class FurthestDistance {

    public static int maxDistance(int[][] grid) {

        boolean isAllLand = true;
        List<Integer[]> land = new ArrayList<>(); // Each point has format {row, col, dist}
        int[][] distGrid = new int[grid.length][grid[0].length];
        int maxDist = Integer.MIN_VALUE;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                distGrid[row][col] = -1;
                if (grid[row][col] == 1) {
                    land.add(new Integer[] {row, col, 0});
                } else {
                    isAllLand = false;
                }
            }
        }

        if (isAllLand || land.isEmpty()) {
            return -1;
        }

        for (Integer[] landPoint : land) {

            Queue<Integer[]> bfsq = new LinkedList<>();

            int[][] visited = new int[grid.length][grid[0].length];

            bfsq.offer(new Integer[] {landPoint[0], landPoint[1], 0});

            while (!bfsq.isEmpty()) {

                Integer[] cur = bfsq.poll();

                int row = cur[0];
                int col = cur[1];
                int dist = cur[2];

                Integer[][] nextCoords = new Integer[][] {
                    {row - 1, col, dist + 1},
                    {row, col + 1, dist + 1},
                    {row + 1, col, dist + 1},
                    {row, col - 1, dist + 1}
                };

                for (Integer[] nextCoord : nextCoords) {
                    int nextRow = nextCoord[0];
                    int nextCol = nextCoord[1];
                    int nextDist = nextCoord[2];

                    if (nextRow >= 0 && nextCol >= 0 && nextRow < grid.length && nextCol < grid[nextRow].length
                        && visited[nextRow][nextCol] != 1
                        && grid[nextRow][nextCol] != 1) {

                        if (distGrid[nextRow][nextCol] == -1) {
                            distGrid[nextRow][nextCol] = nextDist;
                        } else {
                            distGrid[nextRow][nextCol] = Math.min(nextDist, distGrid[nextRow][nextCol]);
                        }

                        visited[nextRow][nextCol] = 1;
                        bfsq.offer(nextCoord);
                    }
                }
            }
        }

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                maxDist = Math.max(maxDist, distGrid[row][col]);
            }
        }

        return maxDist;
    }

    public static void main(String[] args) {
        
        int[][] grid = new int[][] {{1,0,1},{0,0,0},{1,0,1}};

        int max = maxDistance(grid);

        System.out.println(max);

    }
}
