package mazesolver.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayListTest {

    @Test
    public void addingElementsBeyondInitialCapacityIncreasesSizeAndStoresValues() {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            list.add(i);
        }

        assertEquals(20, list.size());
        assertEquals(Integer.valueOf(19), list.get(19));
    }

    @Test
    public void addingNullElementAllowsRetrievingNull() {
        ArrayList<String> list = new ArrayList<>();
        list.add(null);
        assertNull(list.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void gettingElementAtIndexEqualToSizeThrowsException() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.get(1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void gettingElementAtNegativeIndexThrowsException() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void gettingElementFromEmptyListThrowsException() {
        ArrayList<String> list = new ArrayList<>();
        list.get(0);
    }

    @Test
    public void sizeReturnsOneAfterAddingSingleElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        assertEquals(1, list.size());
    }

    @Test
    public void sizeReturnsZeroForEmptyList() {
        ArrayList<String> list = new ArrayList<>();
        assertEquals(0, list.size());
    }
}
