package algo.linkedlist;

import java.util.Optional;

public class LinkedList<T> {

    private Node<T> head;

    private int nodeCount;

    public static class Node<T> {
        private Node<T> next;
        public T value;
    }

    public LinkedList() {
        head = new Node<>();
    }

    public LinkedList(T... nodeValues) {
        this();

        Node<T> firstNode = new Node<>();
        firstNode.value = nodeValues[0];
        head.next = firstNode;

        Node<T> lastInsertedNode = firstNode;
        for (int i = 1; i < nodeValues.length; i++) {
            Node<T> newNode = new Node<>();
            newNode.value = nodeValues[i];
            lastInsertedNode.next = newNode;
            lastInsertedNode = newNode;
        }

        nodeCount += nodeValues.length;
    }

    public Optional<Node<T>> getFirst() {
        if (nodeCount > 0) {
            Node<T> firstNode = head.next;
            return Optional.of(firstNode);
        }

        return Optional.empty();
    }

    public Optional<Node<T>> get(int index) {
        if (index < nodeCount) {
            int currentIndex = 0;
            Node<T> node = head.next;
            while (currentIndex != index) {
                node = node.next;
                currentIndex ++;
            }

            return Optional.of(node);
        }

        return Optional.empty();
    }

    public Optional<Node<T>> getLast() {
        if (nodeCount > 0) {
            Node<T> lastNode = head;
            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }

            return Optional.of(lastNode);
        }

        return Optional.empty();
    }

    public void insertFirst(T nodeValue) {
        Node<T> fistNode = new Node<>();
        fistNode.value = nodeValue;
        fistNode.next = head.next;
        head.next = fistNode;
        nodeCount ++;
    }

    public boolean insert(T prevNodeValue, T nodeValue) {
        Node<T> prevNode = head;
        boolean found = false;
        while (prevNode.next != null) {
            prevNode = prevNode.next;
            if(prevNode.value == prevNodeValue) {
                found = true;
                break;
            }
        }

        if (!found) {
            return false;
        }

        Node<T> newNode = new Node<>();
        newNode.value = nodeValue;
        newNode.next = prevNode.next;
        prevNode.next = newNode;
        nodeCount ++;
        return true;
    }

    public void insertLast(T nodeValue) {
        Node<T> lastNode = head;
        while (lastNode.next != null) {
            lastNode = lastNode.next;
        }

        Node<T> newNode = new Node<>();
        newNode.value = nodeValue;
        lastNode.next = newNode;
        nodeCount ++;
    }

    public Optional<Node<T>> deleteFirst () {
        if (nodeCount > 0) {
            Node<T> nodeToDelete = head.next;
            head.next = nodeToDelete.next;
            nodeCount --;
            return Optional.of(nodeToDelete);
        }

        return Optional.empty();
    }

    public boolean delete (T nodeValue) {
        Node<T> nodeToDelete = head;
        Node<T> prevNode = null;
        while (nodeToDelete.next != null) {
            prevNode = nodeToDelete;
            nodeToDelete = nodeToDelete.next;
            if(nodeToDelete.value == nodeValue) {
                prevNode.next = nodeToDelete.next;
                nodeCount --;
                return true;
            }
        }

        return false;
    }

    public Optional<Node<T>> deleteLast () {
        Node<T> nodeToDelete = head;
        Node<T> prevNode = null;
        while (nodeToDelete.next != null) {
            prevNode = nodeToDelete;
            nodeToDelete = nodeToDelete.next;
        }

        if (nodeToDelete != head) {
            prevNode.next = null;
            nodeCount --;
            return Optional.of(nodeToDelete);
        }

        return Optional.empty();
    }

    public Optional<Integer> search(T nodeValue) {
        Node<T> nodeToFind = head;
        int currentIndex = 0;
        while (nodeToFind.next != null) {
            nodeToFind = nodeToFind.next;
            if(nodeToFind.value == nodeValue)
                return Optional.of(currentIndex);
            currentIndex ++;
        }

        return Optional.empty();
    }

    public Optional<Integer> searchAfter(int index, T nodeValue) {
        if (index < nodeCount) {
           Node<T> nodeToFind = head.next;
           int currentIndex = 0;
           while (currentIndex != index) {
               nodeToFind = nodeToFind.next;
               currentIndex ++;
           }

           while (nodeToFind.next != null) {
               nodeToFind = nodeToFind.next;
               currentIndex ++;
               if (nodeToFind.value == nodeValue)
                   return Optional.of(currentIndex);
           }

           return Optional.empty();
        }

        return Optional.empty();
    }

    public int length() {
        return nodeCount;
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("{");

        Node<T> node = head;
        while (node.next != null) {
            node = node.next;
            stringBuffer.append(node.value).append(",");
        }

        stringBuffer.append("}");
        return stringBuffer.toString();
    }
}
