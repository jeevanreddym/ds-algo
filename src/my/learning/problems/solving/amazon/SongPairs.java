package my.learning.problems.solving.amazon;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * 
	here I have checked if 2 songs add up to a duration which is a multiple of 60 using the hashmap.
	if that is satisfied then i increment the count of  songs.

	Time Complexity is O(n) where n is the number of songs in the input list.

	Space Complexity is O(n) as I have used a hashmap to store the previously checked durations.
 *
 */
public class SongPairs {
	
    public static long getSongPairCount(List<Integer> songs) {
        if (songs == null || songs.size() == 0) {
            return 0;
        }
        
        Long distinctSongsCnt = 0l;
        
        Map<Integer, Integer> durationMap = new HashMap<>();
        
        // { 10, 50, 90, 30 }
        for (Integer songLen: songs) {            
            songLen = songLen % 60;            
            Integer correctMinutes = 60 - songLen;            
            if (durationMap.containsKey(correctMinutes == 60? 0: correctMinutes)) {
                distinctSongsCnt += durationMap.get(correctMinutes == 60? 0: correctMinutes);
            }
            durationMap.put(songLen, (durationMap.getOrDefault(songLen, 0) + 1));
        }

        return distinctSongsCnt;
    }

	public static void main(String[] args) {
		
		Integer[] nums1 = { 37, 23, 60, 5, 55, 51, 9 };
		Integer[] nums2 = { 10, 50, 90, 30 };
		Integer[] nums3 = { 30, 20, 150, 100, 40 };
		Integer[] nums4 = { 60, 60, 60 };
		System.out.println(getSongPairCount(Arrays.asList(nums1)));
		System.out.println(getSongPairCount(Arrays.asList(nums2)));
		System.out.println(getSongPairCount(Arrays.asList(nums3)));
		System.out.println(getSongPairCount(Arrays.asList(nums4)));
	}

}
