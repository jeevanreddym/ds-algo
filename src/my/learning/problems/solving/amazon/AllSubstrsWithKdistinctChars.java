package my.learning.problems.solving.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;



public class AllSubstrsWithKdistinctChars {


	public static void main(String[] args) {
				
		String str = "awaglknagawunagwkwagl";
		int k = 4;
		
		List<String> subStrs = allKLengthDistinctCharSubstrs(str, k);		
		System.out.println(Arrays.toString(subStrs.toArray()));		
		//{wagl, aglk, glkn, lkna, knag, gawu, awun, wuna, unag, nagw, agwk, kwag}
	}
	
	
	public static List<String> allKLengthDistinctCharSubstrs(String str, int k) {
		
		List<String> subStrs = new ArrayList<>();
		
		Set<Character> set = new LinkedHashSet<>();
		
		int lt = 0, rt = 0, currSubstrLen = 0;
		while (lt < str.length()) {
			
			if (!set.contains(str.charAt(lt)) && currSubstrLen < k) {
								
				set.add(str.charAt(lt));
				
				++lt;
				++currSubstrLen;				
				
			} else {				

				if (currSubstrLen == k) {
					subStrs.add(set.stream().map(c -> String.valueOf(c)).collect(Collectors.joining()));
				}
				
				set.remove(str.charAt(rt));
				--currSubstrLen;
				++rt;
			}			
					
		}
		
		return subStrs;
	}
	
}
