package my.learning.algorithms.arraysStrings.string.problems;

public class UnicodeValueOfCharacter {

	public static void main(String[] args) {

		System.out.println(toUnicode('a'));
		
		aaa('a');		
	}

	private static String toUnicode(char ch) {
		System.out.println(ch);
		System.out.println((int) ch);
		System.out.println(String.format("\\u%04x", (int) ch));
		return String.format("\\u%04x", (int) ch);
	}

	private static void aaa(char ch) {
		
		// Convert the integer to a hexadecimal code.

		String hexCode = Integer.toHexString((int) ch).toUpperCase();

		// but the it must be a four number value.
		String hexCodeWithAllLeadingZeros = "0000" + hexCode;
		String hexCodeWithLeadingZeros = hexCodeWithAllLeadingZeros.substring(hexCodeWithAllLeadingZeros.length() - 4);

		System.out.println("\\u" + hexCodeWithLeadingZeros);
	}

}
