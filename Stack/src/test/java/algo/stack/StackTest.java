package algo.stack;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StackTest {
    private Stack<Integer> stack;

    @Before
    public void setup() {
        stack = new Stack<>(1, 2, 3, 4, 5, 6);
    }

    @Test
    public void testPush() {
        stack.push(7);
        assertThat(stack.toString(), is("{1,2,3,4,5,6,7,}"));
    }

    @Test
    public void testPop() {
        Optional<Integer> popResult = stack.pop();
        assertThat(popResult.isPresent(), is(true));
        assertThat(popResult.get(), is(6));
        assertThat(stack.height(), is(5));
    }

    @Test
    public void testPeek() {
        Optional<Integer> peekResult = stack.peek();
        assertThat(peekResult.isPresent(), is(true));
        assertThat(peekResult.get(), is(6));
        assertThat(stack.height(), is(6));
    }

    @Test
    public void testIsEmpty() {
        while (stack.height() > 0) {
            stack.pop();
        }
        boolean isEmptyResult = stack.isEmpty();
        assertThat(isEmptyResult, is(true));
    }

    @Test
    public void testHeight() {
        int heightResult = stack.height();
        assertThat(heightResult, is(6));
    }

    @Test
    public void testToString() {
        String toStringResult = stack.toString();
        assertThat(toStringResult, is("{1,2,3,4,5,6,}"));
    }
}
