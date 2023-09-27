class Solution {
    // Time: O(m * n * wordLen); Space: O(4 * wordLen)
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j< board[i].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (recursion(board, i, j, 0, word)) return true;
                }
            }
        }
        return false;
    }

    public boolean recursion(char[][] arr, int x, int y, int index, String word){
        if(x < 0 || y < 0 || arr[x][y] == '#') return false;
        if(index == word.length() - 1) return true;

        char temp = arr[x][y];
        arr[x][y] = '#';
        boolean a = false;
        boolean b = false;
        boolean c = false;
        boolean d = false;

        if(x < arr.length - 1 && arr[x+1][y] == word.charAt(index + 1)) 
            a = recursion(arr, x+1, y, index + 1, word);

        if(y < arr[x].length -1 && arr[x][y+1] == word.charAt(index + 1))
            b = recursion(arr, x, y+1, index + 1, word);

        if(x > 0 && arr[x-1][y] == word.charAt(index + 1))
            c = recursion(arr, x-1, y, index + 1, word);

        if(y > 0 && arr[x][y-1] == word.charAt(index + 1))
            d = recursion(arr, x, y-1, index + 1, word);

        arr[x][y] = temp;
        return a || b || c || d;
    }
}