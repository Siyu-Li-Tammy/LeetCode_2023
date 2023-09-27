class Node { // double linked-list
    public int key;
    public int val;
    public Node prev;
    public Node next;
    
    public Node(int key, int val) {
        this.key = key;
        this.val = val;
        prev = null; // not linked yet
        next = null; // not linked yet
    }

}
class LRUCache {
    // O(n) space for the cache, O(1) runtime for get() and put()
    private int capacity;
    private Map<Integer, Node> cache;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = new Node(-1, -1); // place holder
        this.tail = new Node(-1, -1); // place holder
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        // repop the node as well as being used
        if (!cache.containsKey(key)) return -1;
        
        Node curr = cache.get(key);
        deleteNode(curr);
        addNode(curr);
        cache.put(key, head.next);
        return head.next.val;
    }
    
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node curr = cache.get(key);
            deleteNode(curr);
            curr.val = value;
            addNode(curr);
            cache.put(key, head.next);
        } else {
            if (cache.size() == capacity) {
                // evict tail and add to head
                Node evict = tail.prev;
                deleteNode(evict);
                cache.remove(evict.key);
                addNode(new Node(key, value));
                cache.put(key, head.next);
            } else {
                addNode(new Node(key, value));
                cache.put(key, head.next);
            }
        }
    }

    private void deleteNode(Node n) {
        Node prevNode = n.prev;
        Node nextNode = n.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    private void addNode(Node n) {
        Node nextNode = head.next;
        head.next = n;
        n.prev = head;
        n.next = nextNode;
        nextNode.prev = n;
    }
}
