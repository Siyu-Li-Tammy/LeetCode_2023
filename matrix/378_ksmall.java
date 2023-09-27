// Priority Queue - Time: O(k log n); Space: O(n)
import java.util.PriorityQueue;

class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        PriorityQueue<Tuple> pq = new PriorityQueue<Tuple>();
        for (int j = 0; j < n; j++)
            pq.offer(new Tuple(0, j, matrix[0][j]));
        for (int i = 0; i < k - 1; i++) { // 小根堆，去掉 k - 1 个堆顶元素，此时堆顶元素就是第 k 的数
            Tuple t = pq.poll();
            if (t.x == m - 1)
                continue;
            pq.offer(new Tuple(t.x + 1, t.y, matrix[t.x + 1][t.y]));
        }
        return pq.poll().val;
    }

    class Tuple implements Comparable<Tuple> {
        int x, y, val;

        public Tuple(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }

        @Override
        public int compareTo(Tuple that) {
            return this.val - that.val;
        }
    }
}

/*
 * divide by two
 * class Solution {
 * public int kthSmallest(int[][] matrix, int k) {
 * // Time: O(log(range) * m * n); Space: O(1)
 * int m = matrix.length, n = matrix[0].length;
 * int lo = matrix[0][0], hi = matrix[m - 1][n - 1];
 * while (lo <= hi) {
 * int mid = lo + (hi - lo) / 2;
 * int cnt = 0;
 * for (int i = 0; i < m; i++) {
 * for (int j = 0; j < n && matrix[i][j] <= mid; j++) {
 * cnt++;
 * }
 * }
 * if (cnt < k) lo = mid + 1;
 * else hi = mid - 1;
 * }
 * return lo;
 * }
 * }
 */