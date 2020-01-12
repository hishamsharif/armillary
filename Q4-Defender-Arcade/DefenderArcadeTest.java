import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by hishamsharif on 12/21/19.
 */
class DefenderArcadeTest {

	@Test
	@DisplayName("Should return the expected minimum no of defender arcades based on a given input list")
	void shouldCalculateTheMinRequiredDefenderAracades() {
		File f = new File("input1.txt");
		List input1 = null;
		try {
			input1 = Files.readAllLines(f.toPath(), StandardCharsets.UTF_8);
		} catch (Exception e) {
			e.printStackTrace();
		}

		int expectedForInput1 = 3;

		DefenderArcade defenderArcade = new DefenderArcade();
		int actual = defenderArcade.countArcades(input1);

		assertEquals(expectedForInput1, actual, "Results incorrect");
	}

}
