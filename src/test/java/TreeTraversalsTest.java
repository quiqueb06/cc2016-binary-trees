import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for {@link TreeTraversals}.
 *
 * Two trees are used across the test suite:
 *
 * <pre>
 *   Tree A (perfect / full):        Tree B (asymmetric):
 *
 *           1                               10
 *          / \                             /
 *         2   3                           5
 *        / \ / \                         / \
 *       4  5 6  7                       3   8
 *                                        \
 *                                         4
 * </pre>
 */
class TreeTraversalsTest {

    // -----------------------------------------------------------------------
    // Tree A – perfect binary tree with 7 nodes
    // -----------------------------------------------------------------------
    private BinaryTree<Integer> rootA;

    // -----------------------------------------------------------------------
    // Tree B – asymmetric tree
    // -----------------------------------------------------------------------
    private BinaryTree<Integer> rootB;

    @BeforeEach
    void setUp() {
        // Build Tree A
        BinaryTree<Integer> n4 = new BinaryTree<>(4);
        BinaryTree<Integer> n5 = new BinaryTree<>(5);
        BinaryTree<Integer> n6 = new BinaryTree<>(6);
        BinaryTree<Integer> n7 = new BinaryTree<>(7);
        BinaryTree<Integer> n2 = new BinaryTree<>(2, n4, n5);
        BinaryTree<Integer> n3 = new BinaryTree<>(3, n6, n7);
        rootA = new BinaryTree<>(1, n2, n3);

        // Build Tree B
        BinaryTree<Integer> n4b = new BinaryTree<>(4);
        BinaryTree<Integer> n3b = new BinaryTree<>(3);
        n3b.setRight(n4b);
        BinaryTree<Integer> n8b = new BinaryTree<>(8);
        BinaryTree<Integer> n5b = new BinaryTree<>(5, n3b, n8b);
        rootB = new BinaryTree<>(10);
        rootB.setLeft(n5b);
    }

    // -----------------------------------------------------------------------
    // Null / single-node edge cases
    // -----------------------------------------------------------------------

    @Nested
    @DisplayName("Edge cases")
    class EdgeCases {

        @Test
        @DisplayName("Traversing null produces an empty list")
        void nullNodeProducesEmptyList() {
            List<Integer> result = new ArrayList<>();
            TreeTraversals.preorder(null, result);
            assertTrue(result.isEmpty());

            TreeTraversals.inorder(null, result);
            assertTrue(result.isEmpty());

            TreeTraversals.postorder(null, result);
            assertTrue(result.isEmpty());

            TreeTraversals.levelorder(null, result);
            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Single-node tree: all traversals return [value]")
        void singleNode() {
            BinaryTree<Integer> single = new BinaryTree<>(42);
            List<Integer> expected = List.of(42);

            List<Integer> pre  = new ArrayList<>(); TreeTraversals.preorder(single, pre);
            List<Integer> in   = new ArrayList<>(); TreeTraversals.inorder(single, in);
            List<Integer> post = new ArrayList<>(); TreeTraversals.postorder(single, post);
            List<Integer> lvl  = new ArrayList<>(); TreeTraversals.levelorder(single, lvl);

            assertEquals(expected, pre,  "preorder  of single node");
            assertEquals(expected, in,   "inorder   of single node");
            assertEquals(expected, post, "postorder of single node");
            assertEquals(expected, lvl,  "levelorder of single node");
        }
    }

    // -----------------------------------------------------------------------
    // Pre-order  (root → left → right)
    // -----------------------------------------------------------------------

    @Nested
    @DisplayName("Pre-order traversal")
    class Preorder {

        @Test
        @DisplayName("Tree A: 1 2 4 5 3 6 7")
        void treeA() {
            List<Integer> result = new ArrayList<>();
            TreeTraversals.preorder(rootA, result);
            assertEquals(List.of(1, 2, 4, 5, 3, 6, 7), result);
        }

        @Test
        @DisplayName("Tree B: 10 5 3 4 8")
        void treeB() {
            List<Integer> result = new ArrayList<>();
            TreeTraversals.preorder(rootB, result);
            assertEquals(List.of(10, 5, 3, 4, 8), result);
        }
    }

    // -----------------------------------------------------------------------
    // In-order  (left → root → right)
    // -----------------------------------------------------------------------

    @Nested
    @DisplayName("In-order traversal")
    class Inorder {

        @Test
        @DisplayName("Tree A: 4 2 5 1 6 3 7")
        void treeA() {
            List<Integer> result = new ArrayList<>();
            TreeTraversals.inorder(rootA, result);
            assertEquals(List.of(4, 2, 5, 1, 6, 3, 7), result);
        }

        @Test
        @DisplayName("Tree B: 3 4 5 8 10")
        void treeB() {
            List<Integer> result = new ArrayList<>();
            TreeTraversals.inorder(rootB, result);
            assertEquals(List.of(3, 4, 5, 8, 10), result);
        }
    }

    // -----------------------------------------------------------------------
    // Post-order  (left → right → root)
    // -----------------------------------------------------------------------

    @Nested
    @DisplayName("Post-order traversal")
    class Postorder {

        @Test
        @DisplayName("Tree A: 4 5 2 6 7 3 1")
        void treeA() {
            List<Integer> result = new ArrayList<>();
            TreeTraversals.postorder(rootA, result);
            assertEquals(List.of(4, 5, 2, 6, 7, 3, 1), result);
        }

        @Test
        @DisplayName("Tree B: 4 3 8 5 10")
        void treeB() {
            List<Integer> result = new ArrayList<>();
            TreeTraversals.postorder(rootB, result);
            assertEquals(List.of(4, 3, 8, 5, 10), result);
        }
    }

    // -----------------------------------------------------------------------
    // Level-order  (breadth-first, left to right per level)
    // -----------------------------------------------------------------------

    @Nested
    @DisplayName("Level-order traversal")
    class Levelorder {

        @Test
        @DisplayName("Tree A: 1 2 3 4 5 6 7")
        void treeA() {
            List<Integer> result = new ArrayList<>();
            TreeTraversals.levelorder(rootA, result);
            assertEquals(List.of(1, 2, 3, 4, 5, 6, 7), result);
        }

        @Test
        @DisplayName("Tree B: 10 5 3 8 4")
        void treeB() {
            List<Integer> result = new ArrayList<>();
            TreeTraversals.levelorder(rootB, result);
            assertEquals(List.of(10, 5, 3, 8, 4), result);
        }
    }

    // -----------------------------------------------------------------------
    // Result list size must equal total number of nodes
    // -----------------------------------------------------------------------

    @Nested
    @DisplayName("Result size equals tree size")
    class ResultSize {

        @Test
        @DisplayName("Tree A has 7 nodes")
        void sizeA() {
            List<Integer> pre  = new ArrayList<>(); TreeTraversals.preorder(rootA, pre);
            List<Integer> in   = new ArrayList<>(); TreeTraversals.inorder(rootA, in);
            List<Integer> post = new ArrayList<>(); TreeTraversals.postorder(rootA, post);
            List<Integer> lvl  = new ArrayList<>(); TreeTraversals.levelorder(rootA, lvl);

            assertEquals(7, pre.size(),  "preorder  size");
            assertEquals(7, in.size(),   "inorder   size");
            assertEquals(7, post.size(), "postorder size");
            assertEquals(7, lvl.size(),  "levelorder size");
        }

        @Test
        @DisplayName("Tree B has 5 nodes")
        void sizeB() {
            List<Integer> pre  = new ArrayList<>(); TreeTraversals.preorder(rootB, pre);
            List<Integer> in   = new ArrayList<>(); TreeTraversals.inorder(rootB, in);
            List<Integer> post = new ArrayList<>(); TreeTraversals.postorder(rootB, post);
            List<Integer> lvl  = new ArrayList<>(); TreeTraversals.levelorder(rootB, lvl);

            assertEquals(5, pre.size(),  "preorder  size");
            assertEquals(5, in.size(),   "inorder   size");
            assertEquals(5, post.size(), "postorder size");
            assertEquals(5, lvl.size(),  "levelorder size");
        }
    }
}