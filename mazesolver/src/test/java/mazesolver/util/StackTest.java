package mazesolver.util;

import java.util.EmptyStackException;

import org.junit.Test;
import static org.junit.Assert.*;

public class StackTest {

    @Test(expected = EmptyStackException.class)
    public void popOnEmptyStackThrowsException() {
        Stack<String> stack = new Stack<>();
        stack.pop();
    }

    @Test
    public void pushMultipleElementsPopReturnsInReverseOrder() {
        Stack<String> stack = new Stack<>();
        stack.push("1st");
        stack.push("2nd");
        stack.push("3rd");
        assertEquals("3rd", stack.pop());
        assertEquals("2nd", stack.pop());
        assertEquals("1st", stack.pop());
    }

    @Test
    public void pushNullElementPopReturnsNull() {
        Stack<Object> stack = new Stack<>();
        stack.push(null);
        assertNull(stack.pop());
    }

    @Test
    public void pushThenPopReturnsSameElement() {
        Stack<Integer> stack = new Stack<>();
        stack.push(42);
        assertEquals(Integer.valueOf(42), stack.pop());
    }
}
