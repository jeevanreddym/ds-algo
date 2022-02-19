package zdummy;

public class CountFrequencies {

	public static void countFrequenciesNaive(int[] input) {
		int n = input.length;
		for (int i = 1; i <= n; i++) {
			int count = 0;
			for (int j = 0; j < n; j++) {
				if (input[j] == i) {
					count++;
				}
			}
			System.out.println(i + " " + count);
		}
	}

	public static void countFrequencies(int[] input) {
		int n = input.length;
		int[] count = new int[n];

		for (int i = 0; i < n; i++) {
			count[i] = 0;
		}

		for (int i = 0; i < n; i++) {
			count[input[i] - 1]++;
		}

		for (int i = 0; i < n; i++) {
			System.out.println(i + 1 + " " + count[i]);
		}
	}

	public static void countfrequenciesEfficient(int input[]) {

		int n = input.length;
		for (int i = 0; i < n; i++) {
			input[i]--;
		}

		for (int i = 0; i < n; i++) {
			input[input[i] % n] += n;
		}

		for (int i = 0; i < n; i++) {
			System.out.println((i + 1) + " " + input[i] / n);

			input[i] = input[i] % n + 1;
		}
	}

	public static void main(String[] args) {
		int[] input = {2,1,2};
		
		countFrequenciesNaive(input);
		System.out.println("");
		countFrequencies(input);
		System.out.println("");
		countfrequenciesEfficient(input);
	}
}
