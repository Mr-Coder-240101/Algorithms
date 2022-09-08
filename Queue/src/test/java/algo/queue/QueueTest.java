package algo.queue;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class QueueTest {
    private Queue<Integer> queue;

    @Before
    public void setup() {
        queue = new Queue<>(1, 2, 3, 4, 5, 6);
    }

    @Test
    public void testEnqueue() {
        queue.enqueue(7);
        assertThat(queue.toString(), is("{1,2,3,4,5,6,7,}"));
    }

    @Test
    public void testDequeue() {
        Optional<Integer> dequeueResult = queue.dequeue();
        assertThat(dequeueResult.isPresent(), is(true));
        assertThat(dequeueResult.get(), is(1));
        assertThat(queue.length(), is(5));
    }

    @Test
    public void testPeek() {
        Optional<Integer> peekResult = queue.peek();
        assertThat(peekResult.isPresent(), is(true));
        assertThat(peekResult.get(), is(1));
        assertThat(queue.length(), is(6));
    }

    @Test
    public void testIsEmpty() {
        while (queue.length() > 0) {
            queue.dequeue();
        }
        boolean isEmptyResult = queue.isEmpty();
        assertThat(isEmptyResult, is(true));
    }

    @Test
    public void testLength() {
        int lengthResult = queue.length();
        assertThat(lengthResult, is(6));
    }
}
