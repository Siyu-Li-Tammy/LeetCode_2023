// Time complexity O(n); Space complexity O(n)
public class Solution {
  private class TreeColumnNode {
    public TreeNode treeNode;
    int col;

    public TreeColumnNode(TreeNode node, int col) {
      this.treeNode = node;
      this.col = col;
    }
  }

  public List<List<Integer>> verticalOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) {
      return res;
    }

    Queue<TreeColumnNode> queue = new LinkedList<>();
    Map<Integer, List<Integer>> map = new HashMap<>();
    queue.offer(new TreeColumnNode(root, 0)); // add to the end
    int curLevelCount = 1;
    int nextLevelCount = 0;

    while (!queue.isEmpty()) {
      TreeColumnNode node = queue.poll();

      if (map.containsKey(node.col)) map.get(node.col).add(node.treeNode.val);
      else map.put(node.col, new ArrayList<Integer>(Arrays.asList(node.treeNode.val)));
      curLevelCount--;

      if (node.treeNode.left != null) {
        queue.offer(new TreeColumnNode(node.treeNode.left, node.col - 1));
        nextLevelCount++;
      }
      if (node.treeNode.right != null) {
        queue.offer(new TreeColumnNode(node.treeNode.right, node.col + 1));
        nextLevelCount++;
      }
      if (curLevelCount == 0) {
        curLevelCount = nextLevelCount;
        nextLevelCount = 0;
      }
    }

    return new ArrayList<List<Integer>>(map.values());
  }
}