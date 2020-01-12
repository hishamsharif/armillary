import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * An ordinal number is a word representing rank or sequential order. The naming
 * convention for royal names is to follow a given firstName with an ordinal
 * number using a Roman numeral to indicate the birth order of two people of the
 * same firstName. The Roman numerals from 1 to 50 are defined as follows: The
 * numbers 1 through 10 are written I, II, III, IV, V, VI, VII, VIII, IX, and X.
 * The Roman numerals corresponding to the numbers 20, 30, 40, and 50 are XX,
 * XXX, XL, and L. For any other two-digit number < 50, its Roman numeral
 * representation is constructed by concatenating the numeral(s) for its
 * multiples of ten with the numeral(s) for its values < 10. For example, 47 is
 * 40 + 7 = "XL" + "VII" = "XLVII".
 *
 * @author Hisham Sharifdeen
 * @since
 */
public class RoyalRumble {

	/**
	 *
	 * @param names - a list of royal names with their ordinal
	 * @return - a sorted list of royal names and their ordinal
	 */
	public List<String> getSortedList(List<String> names) {

		// use comparator to compare by firstName and then ordinal
		Comparator<NameWithOrdinal> compareByNameAndOrdinal = Comparator.comparing(NameWithOrdinal::getFirstName)
				.thenComparing(NameWithOrdinal::getValueOfRoman);

		// split the string , convert to a list of objects
		List<NameWithOrdinal> namesToObjectList = names.stream().limit(50).map(NameWithOrdinalFactory::create)
				.collect(Collectors.toList());

		// TODO: validate roman values and raise the error
		boolean isOrdinalLessThanFifty = namesToObjectList.stream()
				.allMatch(n -> n.getValueOfRoman() >= 1 && n.getValueOfRoman() <= 50);

		// TODO: validate first name length and raise the error
		boolean isFirstNameTwentyInlLength = namesToObjectList.stream().allMatch(n -> n.getFirstName().length() <= 20);

		List<String> sortedList = namesToObjectList.stream().sorted(compareByNameAndOrdinal)
				.map(NameWithOrdinal::toString).collect(Collectors.toList());
		// namesToObjectList.stream().forEach(System.out::println);

		return sortedList;
	}

	static class NameWithOrdinalFactory {

		static NameWithOrdinal create(String nameWithOrdinal) {
			String[] s = nameWithOrdinal.split(" ");
			return new NameWithOrdinal(s[0], s[1], romanNumberToDecimal(s[1]));
		}
	}

	static class NameWithOrdinal {

		private String firstName;
		private String ordinal;
		private int valueOfRoman;

		public NameWithOrdinal(String firstName, String ordinal, int valueOfRoman) {
			this.firstName = firstName;
			this.ordinal = ordinal;
			this.valueOfRoman = valueOfRoman;
		}

		// NameWithOrdinal create() {
		// return create();
		// }

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getOrdinal() {
			return ordinal;
		}

		public void setOrdinal(String age) {
			this.ordinal = ordinal;
		}

		public int getValueOfRoman() {
			return valueOfRoman;
		}

		public void setValueOfRoman(int valueOfRoman) {
			this.valueOfRoman = valueOfRoman;
		}

		@Override
		public String toString() {
			return firstName + " " + ordinal;
		}
	}

	static int romanNumberToDecimal(String romanNo) {
		int result = 0;

		// loop all except the last character
		for (int i = 0; i < romanNo.length() - 1; i++) {

			// compare this character and the next, and if this character has a lower value
			// than
			if (convertRomanCharToNumber(romanNo.charAt(i)) < convertRomanCharToNumber(romanNo.charAt(i + 1))) {
				// subtract it
				result -= convertRomanCharToNumber(romanNo.charAt(i));
			} else {
				// add it
				result += convertRomanCharToNumber(romanNo.charAt(i));
			}
		}
		// always add the last character
		result += convertRomanCharToNumber(romanNo.charAt(romanNo.length() - 1));
		return result;
	}

	static int convertRomanCharToNumber(char letter) {
		switch (letter) {
		case 'L':
			return 50;
		case 'X':
			return 10;
		case 'V':
			return 5;
		case 'I':
			return 1;
		default:
			return 0;
		}
	}
}
