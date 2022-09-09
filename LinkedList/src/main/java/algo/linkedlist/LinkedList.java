package algo.linkedlist;

import java.util.Optional;

public class LinkedList<T> {

    private Node<T> head;
    private Node<T> tail;

    private int nodeCount;

    public static class Node<T> {
        private Node<T> next;
        private Node<T> prev;
        public T value;
    }

    public LinkedList() {
        head = new Node<>();
        tail = new Node<>();
        head.next = tail;
        tail.prev = head;
    }

    public LinkedList(T... nodeValues) {
        this();

        Node<T> firstNode = new Node<>();
        firstNode.value = nodeValues[0];
        head.next = firstNode;
        firstNode.prev = head;

        Node<T> lastInsertedNode = firstNode;
        for (int i = 1; i < nodeValues.length; i++) {
            Node<T> newNode = new Node<>();
            newNode.value = nodeValues[i];
            lastInsertedNode.next = newNode;
            newNode.prev = lastInsertedNode;
            lastInsertedNode = newNode;
        }

        lastInsertedNode.next = tail;
        tail.prev = lastInsertedNode;

        nodeCount += nodeValues.length;
    }

    public Optional<Node<T>> getFirst() {
        if (nodeCount > 0) {
            return Optional.of(head.next);
        }

        return Optional.empty();
    }

    public Optional<Node<T>> get(int index) {
        if (index < nodeCount) {
            Node<T> node;
            int currentIndex;

            if (index <= (nodeCount / 2)) {
                currentIndex = 0;
                node = head.next;
                while (currentIndex != index) {
                    node = node.next;
                    currentIndex ++;
                }
            } else {
                currentIndex = nodeCount - 1;
                node = tail.prev;
                while (currentIndex != index) {
                    node = node.prev;
                    currentIndex --;
                }
            }

            return Optional.of(node);
        }

        return Optional.empty();
    }

    public Optional<Node<T>> getLast() {
        if (nodeCount > 0) {
            return Optional.of(tail.prev);
        }

        return Optional.empty();
    }

    public void insertFirst(T nodeValue) {
        Node<T> firstNode = new Node<>();
        firstNode.value = nodeValue;
        firstNode.next = head.next;
        firstNode.prev = head;
        head.next.prev = firstNode;
        head.next = firstNode;
        nodeCount ++;
    }

    public boolean insert(int index, T nodeValue) {
        if (index < nodeCount) {
            Node<T> newNode = new Node<T>();
            newNode.value = nodeValue;
            int currntIndex;
            Node node;

            if(index <= (nodeCount / 2)) {
                currntIndex = 0;
                node = head.next;
                while (currntIndex != index) {
                    node = node.next;
                    currntIndex ++;
                }
            } else {
                currntIndex = nodeCount - 1;
                node = tail.prev;
                while (currntIndex != index) {
                    node = node.prev;
                    currntIndex --;
                }
            }

            newNode.next = node;
            newNode.prev = node.prev;
            node.prev.next = newNode;
            node.prev = newNode;

            nodeCount ++;
            return true;
        } else if (index == nodeCount) {
            insertLast(nodeValue);
            return true;
        }

        return false;
    }

    public void insertLast(T nodeValue) {
        Node<T> lastNode = new Node<T>();
        lastNode.value = nodeValue;
        lastNode.next = tail;
        lastNode.prev = tail.prev;
        tail.prev.next = lastNode;
        tail.prev = lastNode;
        nodeCount ++;
    }

    public Optional<Node<T>> deleteFirst () {
        if (nodeCount > 0) {
            Node<T> nodeToDelete = head.next;
            nodeToDelete.prev.next = nodeToDelete.next;
            nodeToDelete.next.prev = head;
            nodeCount --;
            return Optional.of(nodeToDelete);
        }

        return Optional.empty();
    }

    public Optional<Node<T>> delete (int index) {
        if (index < nodeCount) {
            int currentIndex;
            Node<T> nodeToDelete;

            if (index <= (nodeCount / 2)) {
                currentIndex = 0;
                nodeToDelete = head.next;
                while (currentIndex != index) {
                    nodeToDelete = nodeToDelete.next;
                    currentIndex++;
                }
            } else {
                currentIndex = nodeCount - 1;
                nodeToDelete = tail.prev;
                while (currentIndex != index) {
                    nodeToDelete = nodeToDelete.prev;
                    currentIndex--;
                }
            }

            nodeToDelete.prev.next = nodeToDelete.next;
            nodeToDelete.next.prev = nodeToDelete.prev;
            nodeCount--;

            return Optional.of(nodeToDelete);
        }

        return Optional.empty();
    }

    public Optional<Node<T>> deleteLast () {
        if (nodeCount > 0) {
            Node<T> nodeToDelete = tail.prev;
            nodeToDelete.prev.next = tail;
            nodeToDelete.next.prev = nodeToDelete.prev;
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
        while (node.next != tail) {
            node = node.next;
            stringBuffer.append(node.value).append(",");
        }

        stringBuffer.append("}");
        return stringBuffer.toString();
    }
}
