package algo.binarytree;

import algo.linkedlist.LinkedList;
import algo.queue.Queue;

import java.util.stream.IntStream;

public class BinaryTree<T> {

    private Node<T> root;
    private int nodeCount;

    public static class Node<T> {
        private Node<T> left;
        private Node<T> right;
        public T value;
    }

    public enum TraversalTypes {
        IN_ORDER, PRE_ORDER, POST_ORDER
    }

    public BinaryTree(T... nodeValues) {
        root = new Node<>();
        root.value = nodeValues[0];
        nodeCount ++;

        Queue<Node<T>> nodeQueue = new Queue<>(root);

        for (int i = 1; i < nodeValues.length; i++) {
            if(nodeQueue.isEmpty()) {
                throw new IllegalArgumentException("Node values are not in proper order");
            }

            Node<T> peekNode;

            if ((i & 1) != 1) {
                peekNode = nodeQueue.dequeue().get();
                if (nodeValues[i] == null)
                    continue;
                peekNode.right = new Node<>();
                peekNode.right.value = nodeValues[i];
                nodeCount ++;
                nodeQueue.enqueue(peekNode.right);
            } else {
                if (nodeValues[i] == null)
                    continue;
                peekNode = nodeQueue.peek().get();
                peekNode.left = new Node<>();
                peekNode.left.value = nodeValues[i];
                nodeCount ++;
                nodeQueue.enqueue(peekNode.left);
            }
        }
    }

    public BinaryTree(LinkedList<T> inOrderTraversal, TraversalTypes otherTraversalType, LinkedList<T> otherTraversal) {
        if (otherTraversalType == TraversalTypes.IN_ORDER) {
            throw new IllegalArgumentException("Can not pass both in-order traversal for BinaryTree creation");
        } else if (inOrderTraversal.length() != otherTraversal.length()) {
            throw new IllegalArgumentException("Length of both traversal can not be same");
        } else if (inOrderTraversal.length() == 0 && otherTraversal.length() == 0) {
            throw new IllegalArgumentException("Length of both traversal can not be zero");
        }

        if (otherTraversalType == TraversalTypes.POST_ORDER)
            root = generateTree(inOrderTraversal.toArray().get(), 0, inOrderTraversal.length(), otherTraversalType, otherTraversal.toArray().get(), otherTraversal.length() - 1);
        else
            root = generateTree(inOrderTraversal.toArray().get(), 0, inOrderTraversal.length(), otherTraversalType, otherTraversal.toArray().get(), 0);
    }

    private Node<T> generateTree(T[] inOrderTraversal, int startIndex, int endIndex, TraversalTypes otherTraversalType, T[] otherTraversal, int index) {
        Node<T> root = new Node<>();

        if (otherTraversalType == TraversalTypes.PRE_ORDER) {
            root.value = otherTraversal[index];
            int indexOfRoot = IntStream.range(startIndex, endIndex).filter(x -> inOrderTraversal[x] == otherTraversal[index]).findFirst().getAsInt();
            if (startIndex < indexOfRoot) {
                root.left = generateTree(inOrderTraversal, startIndex, indexOfRoot, otherTraversalType, otherTraversal, index + 1);
            }
            if ((indexOfRoot + 1) < endIndex) {
                root.right = generateTree(inOrderTraversal, indexOfRoot + 1, endIndex, otherTraversalType, otherTraversal, index + (indexOfRoot - startIndex) + 1);
            }
        } else {
            root.value = otherTraversal[index];
            int indexOfRoot = IntStream.range(startIndex, endIndex).filter(x -> inOrderTraversal[x] == otherTraversal[index]).findFirst().getAsInt();
            if (startIndex < indexOfRoot) {
                root.left = generateTree(inOrderTraversal, startIndex, indexOfRoot, otherTraversalType, otherTraversal, index - (endIndex - indexOfRoot));
            }
            if ((indexOfRoot + 1) < endIndex) {
                root.right = generateTree(inOrderTraversal, indexOfRoot + 1, endIndex, otherTraversalType, otherTraversal, index - 1);
            }
        }

        nodeCount++;
        return root;
    }

    public int getNodeCount() {
        return nodeCount;
    }

    @Override
    public String toString(){
        StringBuffer line = new StringBuffer();
        linearize(line,root);
        return line.toString();
    }

    private void linearize(StringBuffer line, Node<T> node){
        if (node == null){
            line.append("null");
            return;
        }

        line.append(node.value);
        line.append("[");
        linearize(line, node.left);
        line.append(",");
        linearize(line, node.right);
        line.append("]");
    }
}
