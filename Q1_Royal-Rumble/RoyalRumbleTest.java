import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Arrays;

/**
 * Created by hishamsharif on 12/17/19.
 */
class RoyalRumbleTest {

    @Test
    @DisplayName("Should contain the sorted list of royal names by their name and then ordinal")
    void shouldContainSortedRoyalNames() {
        List<String> input = Arrays.asList("Louis IX", "Louis VIII", "David II");
        List<String> expected = Arrays.asList("David II", "Louis VIII", "Louis IX");

        RoyalRumble royalRumble = new RoyalRumble();
        List<String> actual = royalRumble.getSortedList(input);

        assertIterableEquals(expected, actual);
    }

}
