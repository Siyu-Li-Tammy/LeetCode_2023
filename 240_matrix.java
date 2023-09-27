class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // Time complexity: O(m * n / 2)    
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length; int n = matrix[0].length;
        int i = 0; int j = n - 1;

        while (i < m && j >= 0) { // compare diagonal
            if (matrix[i][j] == target) return true;
            if (matrix[i][j] > target) j--;
            else i++;
        }
        return false;
    }
}

/*
  public boolean searchMatrix(int[][] matrix, int target) {
      // Time: O(m * n); Space: O(1)
      int m = matrix.length - 1; // row size
      int n = matrix[0].length - 1; // col size
      int r = m, c = n;

      while (r >= 0) {
          if (target == matrix[r][0]) return true;
          else if (target < matrix[r][0]) r--;
          else break; // find the c value range
      }

      while (c >= 0) {
          if (target == matrix[0][c]) return true;
          else if (target < matrix[0][c]) c--;
          else break; // find the c value range
      }

      for (int i = 0; i <= r; i++) {
          for (int j = 0; j <= c; j++) {
              if (target == matrix[i][j]) return true;
          }
      }
      return false;
  }
*/