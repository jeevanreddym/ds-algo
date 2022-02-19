package my.learning.algorithms.arraysStrings.array.problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordFrequencies {

	
	public static void main(String[] args) {
		
		String str = "Hi hello how are you Hi hello how are you Hi hello how are you Hi Hi hello";
		
		Map<String, Integer> wordFreqs = new HashMap<>();
		String[] splitStrs = str.split(" ");
		for (String splitStr: splitStrs) {			
			wordFreqs.put(splitStr, wordFreqs.getOrDefault(splitStr, 0) + 1);
		}
		
		List<Map.Entry<String, Integer>> statistics = new ArrayList<>(wordFreqs.entrySet());

		Collections.sort(statistics, (e1, e2) -> e2.getValue().compareTo(e1.getValue()));
		
		statistics.stream().forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
	}
	
}
