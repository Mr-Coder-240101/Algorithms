package algo.stack;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import algo.linkedlist.LinkedList;

public class Stack<T> {
    private LinkedList<T> linkedList;

    public Stack() {
        linkedList = new LinkedList<>();
    }

    public Stack(T... values) {
        linkedList = new LinkedList<>(values);
    }

    public void push(T value) {
        linkedList.insertLast(value);
    }

    public Optional<T> pop() {
        if (!isEmpty()) {
            Optional<LinkedList.Node<T>> deletionResult = linkedList.deleteLast();
            if (deletionResult.isPresent()) {
                return Optional.of(deletionResult.get().value);
            }
        }

        return Optional.empty();
    }

    public Optional<T> peek() {
        if (!isEmpty()) {
            Optional<LinkedList.Node<T>> getResult = linkedList.getLast();
            if (getResult.isPresent()) {
                return Optional.of(getResult.get().value);
            }
        }

        return Optional.empty();
    }

    public boolean isEmpty() {
        return linkedList.length() == 0;
    }

    public int height() {
        return linkedList.length();
    }

    @Override
    public String toString() {
        return linkedList.toString();
    }
}
