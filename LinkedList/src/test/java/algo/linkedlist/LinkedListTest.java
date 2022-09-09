package algo.linkedlist;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LinkedListTest {
    private LinkedList<Integer> linkedList;

    @Before
    public void setup() {
        linkedList = new LinkedList<>(1, 2, 3, 4, 5);
    }

    @Test
    public void testGetFirst() {
        Optional<LinkedList.Node<Integer>> getResult = linkedList.getFirst();
        assertThat(getResult.isPresent(), is(true));
        assertThat(getResult.get().value, is(1));
    }

    @Test
    public void testGet() {
        Optional<LinkedList.Node<Integer>> getResult = linkedList.get(3);
        assertThat(getResult.isPresent(), is(true));
        assertThat(getResult.get().value, is(4));
    }

    @Test
    public void testGetLast() {
        Optional<LinkedList.Node<Integer>> getResult = linkedList.getLast();
        assertThat(getResult.isPresent(), is(true));
        assertThat(getResult.get().value, is(5));
    }

    @Test
    public void testInsertFirst() {
        linkedList.insertFirst(0);
        assertThat(linkedList.toString(), is("{0,1,2,3,4,5,}"));
    }

    @Test
    public void testInsert() {
        boolean insertionResult = linkedList.insert(4, -1);
        assertThat(insertionResult, is(true));
        assertThat(linkedList.toString(), is("{1,2,3,4,-1,5,}"));
    }

    @Test
    public void testInsertLast() {
        linkedList.insertLast(6);
        assertThat(linkedList.toString(), is("{1,2,3,4,5,6,}"));
    }

    @Test
    public void testDeleteFirst() {
        Optional<LinkedList.Node<Integer>> deleteResult = linkedList.deleteFirst();
        assertThat(deleteResult.isPresent(), is(true));
        assertThat(deleteResult.get().value, is(1));
        assertThat(linkedList.toString(), is("{2,3,4,5,}"));
    }

    @Test
    public void testDelete() {
        Optional<LinkedList.Node<Integer>> deleteResult = linkedList.delete(3);
        assertThat(deleteResult.isPresent(), is(true));
        assertThat(deleteResult.get().value, is(4));
        assertThat(linkedList.toString(), is("{1,2,3,5,}"));
    }

    @Test
    public void testDeleteLast() {
        Optional<LinkedList.Node<Integer>> deleteResult = linkedList.deleteLast();
        assertThat(deleteResult.isPresent(), is(true));
        assertThat(deleteResult.get().value, is(5));
        assertThat(linkedList.toString(), is("{1,2,3,4,}"));
    }

    @Test
    public void testSearch() {
        Optional<Integer> searchResult = linkedList.search(2);
        assertThat(searchResult.isPresent(), is(true));
        assertThat(searchResult.get(), is(1));
    }

    @Test
    public void testSearchAfter() {
        linkedList.insertLast(2);
        Optional<Integer> searchResult = linkedList.searchAfter(1, 2);
        assertThat(searchResult.isPresent(), is(true));
        assertThat(searchResult.get(), is(5));
    }

    @Test
    public void testCount() {
        linkedList.insertFirst(-1);
        linkedList.insertLast(6);
        linkedList.delete(3);
        int countResult = linkedList.length();
        assertThat(countResult, is(6));
    }

}
