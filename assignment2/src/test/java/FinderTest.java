
import org.junit.Assert;
import org.junit.Test;

public class FinderTest {

    private Integer[] intArray = {1,2,3,4,5,6,7,8,9};
    private Integer[] emptyArray = {};
    private Integer[] nullArray = null;

    @Test
    public void validMax() {                                                                                            // valid array to test max function
        Integer maxDefault = 9;
        Assert.assertEquals(maxDefault, Finder.findMax(intArray));
    }

    @Test
    public void emptyMax() {                                                                                            // invalid empty array to test max function
        Assert.assertNull(Finder.findMax(emptyArray));
    }

    @Test
    public void nullMax() {                                                                                             // invalid empty array to test max function
        Assert.assertNull(Finder.findMax(nullArray));
    }

    @Test
    public void validMin() {                                                                                            // valid array to test min function
        Integer minDefault = 1;
        Assert.assertEquals(minDefault, Finder.findMin(intArray));
    }

    @Test
    public void emptyMin() {                                                                                            // invalid empty array to test min function
        Assert.assertNull(Finder.findMin(emptyArray));
    }

    @Test
    public void nullMin() {                                                                                             // invalid empty array to test min function
        Assert.assertNull(Finder.findMin(nullArray));
    }
}
