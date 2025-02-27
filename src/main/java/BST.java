

/**
 * Implementation of a Binary Search Tree (BST) with key-value pairs.
 * Students must complete the following functionality:
 * 
 * 1. contains(Key key) - Determine if the key exists in the BST
 *    - Should throw IllegalArgumentException for null keys
 *    - Hint: Consider reusing existing methods to avoid duplicate traversal logic
 * 
 * 2. get() methods - Retrieve values by key
 *    - Public get(Key key) entry point
 *    - Private recursive get(Node x, Key key) helper
 *    - Should implement standard BST search logic:
 *      • Compare key with current node
 *      • Traverse left/right accordingly
 *      • Return null for missing keys
 */

 public class BST<Key extends Comparable<Key>, Value> {
    private Node root;             // root of BST

    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private int size;          // number of nodes in subtree

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.size;
    }

    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null; // Reusing get method
    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    // Main method added

    public static void main(String[] args) {
        BST<Integer, String> bst = new BST<>();
        bst.put(10, "A");
        bst.put(5, "B");
        bst.put(15, "C");
        bst.put(7, "D");
        
        System.out.println("Contains Key 5: " + bst.contains(5)); // should print true
        System.out.println("Contains Key 20: " + bst.contains(20)); //should print  false
        System.out.println("Value for key 10 is : " + bst.get(10)); // Should print  A
        System.out.println("Value for key 7 is : " + bst.get(7)); // Should print D
        System.out.println("Size of BST is : " + bst.size()); //  should print  4
    }
}