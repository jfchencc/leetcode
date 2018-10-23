class LRUCache {
    // double linked list
    class Node {
        int key;
        int val;
        Node prev;
        Node next;
        
        public Node (int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    
    // use double linked list: oldest in the front, latest in the end
    Map<Integer, Node> map;
    int capacity;
    Node head;
    Node tail;
    
    public LRUCache(int capacity) {
        map = new HashMap();
        this.capacity = capacity;
        head = null;
        tail = null;
    }
    
    // case 0: the node is not exist
    // case 1: the node is a head
    // case 2: the node is in the middle
    // case 3: the node is a tail
    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1; // case 0
     
        if (node != tail) {
            if (node == head) { // case 1: move head to next node
                head = head.next;
            } else { // case 2: delete the node
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            // for case 1, 2: add the new tail
            tail.next = node;
            node.prev = tail;
            node.next = null;
            tail = node;
        }
        
        // if the node is a tail, just return the val
        return node.val;
    }
    
    public void put(int key, int value) {
        Node node = map.get(key);
        
        if (node != null) {
            node.val = value; // update val
            
            // the followings are same as get
            if (node != tail) {
                if (node == head) { // case 1: move head to next node, remove old head
                    head = head.next;
                } else { // case 2: delete the node
                    node.prev.next = node.next;
                    node.next.prev = node.prev;
                }
                // for case 1, 2: add the new tail; for case 3: no change, already updated the val 
                tail.next = node;
                node.prev = tail;
                node.next = null;
                tail = node;
            }
        } else { // the key, val does not exist
            Node newNode = new Node(key, value);
            if (capacity == 0) { // case 1: beyond capacity, delete the head
                map.remove(head.key);
                head = head.next;
                capacity++;
            }
            
            if (head == null && tail == null) { // empty cache
                head = newNode; 
            } else { // case 2: enough capacity; add the node to tail
                tail.next = newNode;
                newNode.prev = tail;
                newNode.next = null;
            }
            
            // for both cases
            tail = newNode;
            map.put(key, newNode);
            capacity--;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */