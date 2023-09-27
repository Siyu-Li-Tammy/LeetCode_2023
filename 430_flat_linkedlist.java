class Solution {
  public Node flatten(Node head) {
    // Time: O(n); Space: O(1)
    Node temp = head;
    while (temp != null) {
      if (temp.child == null) temp = temp.next;

      else {
        if (temp.next != null) link(temp.child, temp.next);
        temp.next = temp.child;
        temp.child.prev = temp;
        temp.child = null;
      }
    }
    return head;
  }

  public void link(Node n1, Node n2) {
    while (n1.next != null)
      n1 = n1.next;
    n1.next = n2;
    n2.prev = n1;
  }
}