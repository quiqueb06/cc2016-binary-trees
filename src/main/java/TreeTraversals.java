import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeTraversals {

    public static <V> void preorder(BinaryTree<V> node, List<V> result) {
        if (node == null) {
            return;
        }
        result.add(node.getValue());
        preorder(node.getLeft(), result);
        preorder(node.getRight(), result);
    }

    public static <V> void inorder(BinaryTree<V> node, List<V> result) {
        if (node == null) {
            return;
        }
        inorder(node.getLeft(), result);
        result.add(node.getValue());
        inorder(node.getRight(), result);
    }

    public static <V> void postorder(BinaryTree<V> node, List<V> result) {
        if (node == null) {
            return;
        }
        postorder(node.getLeft(), result);
        postorder(node.getRight(), result);
        result.add(node.getValue());
    }

    public static <V> void levelorder(BinaryTree<V> node, List<V> result) {
        if (node == null) {
            return;
        }

        Queue<BinaryTree<V>> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            BinaryTree<V> current = queue.poll();
            result.add(current.getValue());

            if (current.getLeft() != null) {
                queue.add(current.getLeft());
            }
            if (current.getRight() != null) {
                queue.add(current.getRight());
            }
        }
    }
}