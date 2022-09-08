package algo.queue;

import algo.linkedlist.LinkedList;

import java.util.Optional;

public class Queue<T> {
    private LinkedList<T> linkedList;

    public Queue() {
        linkedList = new LinkedList<>();
    }

    public Queue(T... values) {
        linkedList = new LinkedList<>(values);
    }

    public void enqueue(T value) {
        linkedList.insertLast(value);
    }

    public Optional<T> dequeue() {
        if (!isEmpty()) {
            Optional<LinkedList.Node<T>> deletionResult = linkedList.deleteFirst();
            if (deletionResult.isPresent()) {
                return Optional.of(deletionResult.get().value);
            }
        }

        return Optional.empty();
    }

    public Optional<T> peek() {
        if (!isEmpty()) {
            Optional<LinkedList.Node<T>> getResult = linkedList.getFirst();
            if (getResult.isPresent()) {
                return Optional.of(getResult.get().value);
            }
        }

        return Optional.empty();
    }

    public boolean isEmpty() {
        return linkedList.length() == 0;
    }

    public int length() {
        return linkedList.length();
    }

    @Override
    public String toString() {
        return linkedList.toString();
    }
}
