class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        if(grid == null || grid.length == 0){
            return 0;
        }
        int rows = grid.length;
        int cols = grid[0].length;

        int maxArea = 0;

        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                if(grid[r][c] == 1){
                    int area = dfs(r,c,rows,cols,grid);
                    maxArea = Math.max(area,maxArea);
                }
            }
        }
        return maxArea;
    }

    private int dfs(int r, int c, int rows, int cols, int[][]grid){
        if(r < 0 || c < 0 || c >= cols || r >= rows || grid[r][c] == 0){
            return 0;
        }
        grid[r][c] = 0;

        return 1 
            + dfs(r+1,c,rows,cols,grid)
            + dfs(r-1,c,rows,cols,grid)
            + dfs(r,c+1,rows,cols,grid)
            + dfs(r,c-1,rows,cols,grid);
    }
}
