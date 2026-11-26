class Solution {
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0){
            return 0;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        int total = 0;

        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                if(grid[r][c] == '1'){
                   dfs(r,c,rows,cols,grid); 
                   total++;
                }
            }
        }
        return total;
        
    }

    private void dfs(int r, int c, int rows, int cols, char[][]grid){
        if( (r < 0) || (c < 0) || r >= rows || c >= cols || grid[r][c] == '0'){
            return; 
        }
        grid[r][c] = '0';
        dfs(r+1,c,rows,cols,grid);
        dfs(r,c+1,rows,cols,grid);
        dfs(r-1,c,rows,cols,grid);
        dfs(r,c-1,rows,cols,grid);

    }
}
