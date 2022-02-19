package my.learning.algorithms.arraysStrings.string.problems;

public class StringCompare {

	public static void main(String[] args) {
		
		System.out.println((int) 'a');
		System.out.println((int) 'A');
		System.out.println((int) 'z');
		System.out.println((int) 'Z');
		
//		String str = "Hello,Hi,How,are,you,there";
//		
//		List<String> words = Arrays.asList(str.split(","));
//		words.forEach(word -> System.out.println(word));
				
		
//		String str1 = "";
//		String str2 = "";
		
		//compare(str1, str2);	
		
		//System.out.println(test("applicaplison", "plisoa"));
		
	}
	
	private static int compare(String str1, String str2) {
		
		for (int i=0; i < str1.length() && i < str2.length(); i++) {
			
			char ch1 = str1.charAt(i);
			char ch2 = str2.charAt(i);
			
			int unicodeValCh1 = (int) ch1;
			int unicodeValCh2 = (int) ch2;
			
			if (unicodeValCh1 != unicodeValCh2) {
				return unicodeValCh1 - unicodeValCh2;
			}
			
			if (str1.length() > str2.length()) {
				return str1.length() - str2.length();
			} else if (str2.length() > str1.length()) {
				return str2.length() - str1.length();
			}
			
		}
		return 0;
	}
	
	private static int test(String str, String substr) {
		
		char[] strs = str.toCharArray();
		char[] substrs = substr.toCharArray();
		
		int i=0, j = 0, k = 0;		
		while (i < strs.length) {
			
			if (strs[i] == substrs[k]) {
				if (k == substrs.length - 1) {
					break;
				}
				i++;
				k++;
			} else {
				j++;
				i = j;
				k = 0;
			}			
		}
		
		return j == strs.length? -1: j;
	}
	
}
