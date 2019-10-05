import java.util.Arrays;
import java.util.Collections;

class Finder {

    static Integer findMax(Integer[] intArray) {                                                                        // method to find maximum number from int array
        try {
            if (intArray.length < 1) return null;                                                                       // return null if the array is empty
            return Collections.max(Arrays.asList(intArray));                                                            // Java function to return max from int array
        } catch (NullPointerException e) {                                                                              // NullPointerException if the array is null
            return null;
        }
    }

    static Integer findMin(Integer[] intArray) {                                                                        // method to find minimum number from int array
        try {
            if (intArray.length < 1) throw new NullPointerException();                                                  // return null if the array is empty
            return Collections.min(Arrays.asList(intArray));                                                            // Java function to return min from int array
        } catch (NullPointerException e) {                                                                              // NullPointerException if the array is null
            return null;
        }
    }
}
