package algo.binarytree;

import algo.linkedlist.LinkedList;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BinaryTreeTest<T> {
    private BinaryTree<Integer> binaryTreePreOrder;
    private BinaryTree<Integer> binaryTreePostOrder;
    private BinaryTree<Integer> binaryTreeUsingVarArgs;

    @Before
    public void setUp() {
        LinkedList<Integer> inOrderTraversal = new LinkedList<>(5, 15, 18, 20, 25);
        LinkedList<Integer> preOrderTraversal = new LinkedList<>(20, 15, 5, 18, 25);
        LinkedList<Integer> postOrderTraversal = new LinkedList<>(5, 18, 15, 25, 20);

        binaryTreePreOrder = new BinaryTree<>(inOrderTraversal, BinaryTree.TraversalTypes.PRE_ORDER, preOrderTraversal);
        binaryTreePostOrder = new BinaryTree<>(inOrderTraversal, BinaryTree.TraversalTypes.POST_ORDER, postOrderTraversal);

        binaryTreeUsingVarArgs = new BinaryTree<>(1, 2, null, 3, 4, 5, null, 6, 7);
    }

    @Test
    public void testToString() {
        String toStringResultPreOrder = binaryTreePreOrder.toString();
        String toStringResultPostOrder = binaryTreePostOrder.toString();
        String toStringResultVarArgs = binaryTreeUsingVarArgs.toString();

        assertThat(toStringResultPreOrder, is("20[15[5[null,null],18[null,null]],25[null,null]]"));
        assertThat(toStringResultPostOrder, is("20[15[5[null,null],18[null,null]],25[null,null]]"));
        assertThat(toStringResultVarArgs, is("1[2[3[5[null,null],null],4[6[null,null],7[null,null]]],null]"));
    }

    @Test
    public void testNodeCount() {
        int nodeCountResultPreOrder = binaryTreePreOrder.nodeCount();
        int nodeCountResultPostOrder = binaryTreePostOrder.nodeCount();
        int nodeCountResultVarArgs = binaryTreeUsingVarArgs.nodeCount();

        assertThat(nodeCountResultPreOrder, is(5));
        assertThat(nodeCountResultPostOrder, is(5));
        assertThat(nodeCountResultVarArgs, is(7));
    }
}
