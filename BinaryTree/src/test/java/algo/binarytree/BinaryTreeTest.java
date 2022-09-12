package algo.binarytree;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BinaryTreeTest<T> {
    private BinaryTree<Integer> binaryTreePreOrder;
    private BinaryTree<Integer> binaryTreePostOrder;

    @Before
    public void setUp() {
        List<Integer> inOrderTraversal = List.of(5, 15, 18, 20, 25);
        List<Integer> preOrderTraversal = List.of(20, 15, 5, 18, 25);
        List<Integer> postOrderTraversal = List.of(5, 18, 15, 25, 20);
        binaryTreePreOrder = new BinaryTree<>(inOrderTraversal, BinaryTree.TraversalTypes.PRE_ORDER, preOrderTraversal);
        binaryTreePostOrder = new BinaryTree<>(inOrderTraversal, BinaryTree.TraversalTypes.POST_ORDER, postOrderTraversal);
    }

    @Test
    public void testToString() {
        String toStringResultPreOrder = binaryTreePreOrder.toString();
        String toStringResultPostOrder = binaryTreePostOrder.toString();

        assertThat(toStringResultPreOrder, is("20[15[5[null,null],18[null,null]],25[null,null]]"));
        assertThat(toStringResultPostOrder, is("20[15[5[null,null],18[null,null]],25[null,null]]"));
    }

    @Test
    public void testGetNodeCount() {
        int getNodeCountResult = binaryTreePreOrder.getNodeCount();

        assertThat(getNodeCountResult, is(5));
    }
}
