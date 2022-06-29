package lab9;

import java.util.Iterator;
import java.util.*;

import static org.junit.Assert.assertTrue;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements lab9.Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) return null;
        if (p.key.equals(key)) return p.value;
        if (p.key.compareTo(key) > 0) {
            return getHelper(key, p.left);
        } else {
            return getHelper(key, p.right);
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            size = size + 1;
            return new Node(key, value);

        }
        if (p.key.equals(key)) {
            p.value = value;
        } else if (p.key.compareTo(key) > 0) {
            p.left = putHelper(key, value, p.left);
        } else {
            p.right = putHelper(key, value, p.right);
        }
        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key, value, root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        addSet(root, set);
        return  set;
    }

    private void addSet(Node root, Set<K> set) {
        if (root == null) return;
        set.add(root.key);
        addSet(root.left, set);
        addSet(root.right, set);
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        V value = get(key);
        if (value == null) return value;
        this. root = removeHelper(key, this.root);
        return value;
    }

    private Node removeHelper(K key, Node root) {
        if (root.key.equals(key)) {

            if (root.left == null && root.left == null) {
                size--;
                return null;
            } else if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                Node nextMax = findNextMax(root.right);
                root.value = nextMax.value;
                root.key = nextMax.key;
                nextMax.key = key;
                root.right = removeHelper(key, root.right);
            }
        }
        if (root.key.compareTo(key) > 0) {
            root.left = removeHelper(key, root.left);
        } else {
            root.right = removeHelper(key, root.right);
        }
        return root;
    }

    private Node findNextMax(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }



    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        V find = get(key);
        if (find.equals(value)) {
            remove(key);
            return find;
        } else {
            return null;
        }

    }

    @Override
    public Iterator<K> iterator() {
        return new KeyIterator(root);
    }

    public class KeyIterator implements Iterator<K> {
        private Deque<Node> dq;
        public KeyIterator(Node root) {
            dq = new ArrayDeque<>();
            if (root != null) {
                dq.addLast(root);
            }

        }
        public boolean hasNext() { return !dq.isEmpty(); }
        public K next() {
            Node c = dq.getFirst();
            if (c.right != null) {
                dq.addLast(c.right);
            }
            if (c.left != null) {
                dq.addLast(c.left);
            }
            return c.key;
        }
    }




}
