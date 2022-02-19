package my.learning.problems.solving.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MostFrequentlyUsedWord {

	public static void main(String[] args) {
		
		String sentance = "Jack and Jill went to the market to buy bread and cheese. Cheese is Jack’s and Jill’s favorite food.";
		List<String> wordsToExclude = Arrays.asList("and", "he", "the", "to", "is", "Jack", "Jill");
		List<String> freqWords = findMostFreqUserWords(sentance, wordsToExclude);
		
		List<String> toys = new ArrayList<>();
		toys.stream().map(s -> s.toLowerCase()).collect(Collectors.toList());    
		
		System.out.println(Arrays.toString(freqWords.toArray()));		
	}

	private static List<String> findMostFreqUserWords(String sentance, List<String> wordsToExclude) {
		
		String[] words = sentance.replaceAll("[^a-zA-z ]", " ").split("\\s+");	
		
		wordsToExclude = wordsToExclude.stream().map(word -> word.toLowerCase()).collect(Collectors.toList());
		
		int highestCnt = 0;
		Map<String, Integer> map = new HashMap<>();		
		for (String word: words) {
			word = word.toLowerCase();
			if (!wordsToExclude.contains(word)) {
				if (map.containsKey(word)) {
					int cnt = map.get(word) + 1;
					map.put(word, cnt);
					if (cnt > highestCnt) {
						highestCnt = cnt;
					}
				} else {
					map.put(word, 1);
				}	
			}		
		}
		int[] highestArr = {highestCnt};
		return map.entrySet().stream().filter(e -> e.getValue() == highestArr[0]).map(e -> e.getKey()).collect(Collectors.toList());
	} 
	
}
