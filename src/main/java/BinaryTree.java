import java.util.Iterator;

/**
 * A generic binary tree node that can act as the root of a binary tree or subtree.
 * Each node holds a value and references to its parent, left child, and right child.
 *
 * @param <V> the type of value stored in this node
 */
public class BinaryTree<V> {
    /** The value stored in this node. */
    private V value;

    /** The parent of this node, or {@code null} if this is the root. */
    private BinaryTree<V> parent;

    /** The left child of this node, or {@code null} if there is none. */
    private BinaryTree<V> left;

    /** The right child of this node, or {@code null} if there is none. */
    private BinaryTree<V> right;

    /** Creates an empty binary tree node with no value or children. */
    public BinaryTree() {}

    /**
     * Creates a binary tree node with the given value and no children.
     *
     * @param value the value to store in this node
     */
    public BinaryTree(V value) {
        this.value = value;
    }

    /**
     * Creates a binary tree node with the given value and the specified children.
     *
     * @param value the value to store in this node
     * @param left  the left child subtree
     * @param right the right child subtree
     */
    public BinaryTree(V value, BinaryTree<V> left, BinaryTree<V> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    /**
     * Returns the value stored in this node.
     *
     * @return the node's value
     */
    public V getValue() { return value; }

    /**
     * Sets the value stored in this node.
     *
     * @param value the new value
     */
    public void setValue(V value) { this.value = value; }

    /**
     * Returns the parent of this node.
     *
     * @return the parent node, or {@code null} if this is the root
     */
    public BinaryTree<V> getParent() { return parent; }

    /**
     * Sets the parent of this node. Intended for internal use when linking nodes.
     *
     * @param newParent the new parent node, or {@code null} to detach from the tree
     */
    protected void setParent(BinaryTree<V> newParent) {
        parent = newParent;
    }

    /**
     * Returns the left child of this node.
     *
     * @return the left child, or {@code null} if there is none
     */
    public BinaryTree<V> getLeft() { return left; }

    /**
     * Sets the left child of this node. If a left child already exists, it is
     * detached from this node before attaching the new one. Does nothing if
     * {@code newLeft} is {@code null}.
     *
     * @param newLeft the new left child
     */
    public void setLeft(BinaryTree<V> newLeft) {
        if (newLeft == null) return;

        // TODO: if empty node, return

        // If this node already has a left child, we must "disconnect" before.
        if (left != null && left.getParent() == this) {
            left.setParent(null);
        }

        // We connect the new node.
        left = newLeft;
        left.setParent(this);
    }

    /**
     * Returns the right child of this node.
     *
     * @return the right child, or {@code null} if there is none
     */
    public BinaryTree<V> getRight() { return right; }

    /**
     * Sets the right child of this node. If a right child already exists, it is
     * detached from this node before attaching the new one. Does nothing if
     * {@code newRight} is {@code null}.
     *
     * @param newRight the new right child
     */
    public void setRight(BinaryTree<V> newRight) {
        if (newRight == null) return;

        // TODO: if empty node, return

        // If this node already has a right child, we must "disconnect" before.
        if (right != null && right.getParent() == this) {
            right.setParent(null);
        }

        // We connect the new node.
        right = newRight;
        right.setParent(this);
    }

    /**
     * Returns whether this node is the left child of its parent.
     *
     * @return {@code true} if this node has a parent and is its left child
     */
    public boolean isLeftChild() {
        return parent != null && parent.getLeft() == this;
    }

    /**
     * Returns whether this node is the right child of its parent.
     *
     * @return {@code true} if this node has a parent and is its right child
     */
    public boolean isRightChild() {
        return parent != null && parent.getRight() == this;
    }

    /**
     * Returns the root of the tree containing this node by walking up the parent chain.
     *
     * @return the root node (the ancestor with no parent)
     */
    public BinaryTree<V> root() {
        if (parent == null)
            return this;
        return parent.root();
    }

    /**
     * Returns the depth of this node, defined as the number of edges from the root to this node.
     * The root has depth 0.
     *
     * @return the depth of this node
     */
    public int depth() {
        if (parent == null)
            return 0;
        return 1 + parent.depth();
    }

    /**
     * Returns the height of the subtree rooted at this node, defined as the number of edges
     * on the longest path from this node to a leaf. A leaf node has height 0.
     *
     * @return the height of this subtree
     */
    public int height() {
        int leftHeight = 0;
        int rightHeight = 0;

        if (left != null)
            leftHeight = 1 + left.height();
        if (right != null)
            rightHeight = 1 + right.height();

        return Math.max(leftHeight, rightHeight);
    }

    /**
     * Returns the number of nodes in the subtree rooted at this node, including this node.
     *
     * @return the size of this subtree
     */
    public int size() {
        int leftSize = 0;
        int rightSize = 0;

        if (left != null)
            leftSize = left.size();
        if (right != null)
            rightSize = right.size();

        return 1 + leftSize + rightSize;
    }

    /**
     * Returns whether the subtree rooted at this node is a full binary tree, i.e. every
     * level is completely filled. This holds when the size equals {@code 2^(h+1) - 1},
     * where {@code h} is the height.
     *
     * @return {@code true} if this subtree is a full (perfect) binary tree
     */
    public boolean isFull() {
        int h = height();
        int s = size();

        return s == (1<<(h+1))-1;
    }
}
