package my.learning.algorithms.arraysStrings.array.problems;

import java.util.ArrayList;
import java.util.List;

public class MergeMeetingTimings {

	public static void main(String[] args) {
						
		List<Integer[]> meetings = new ArrayList<>();
		meetings.add(new Integer[] {0,1});
		meetings.add(new Integer[] {3,5});
		meetings.add(new Integer[] {4,8});
		meetings.add(new Integer[] {10,12});
		meetings.add(new Integer[] {9,10});
		
		MergeMeetingTimings merger = new MergeMeetingTimings();
		List<Integer[]> mergedMeetings = merger.mergeMeetings(meetings);
		
		
		mergedMeetings.forEach(mergedMeeting -> System.out.println(mergedMeeting[0] + "," + mergedMeeting[1]));
		
	}

	private List<Integer[]> mergeMeetings(List<Integer[]> meetings) {
		List<Integer[]> mergedMeetings = new ArrayList<>(meetings.size());
		
		meetings.sort((m1,m2) -> Integer.compare(m1[0], m2[0])); // sorting meetings based on meeting start times.
		
		mergedMeetings.add(meetings.get(0));
		
		meetings.forEach(currMeeting -> {
			
			Integer[] lastMeeting = mergedMeetings.get(mergedMeetings.size() - 1);
			
			if (currMeeting[0] <= lastMeeting[1]) {
				lastMeeting[1] = Integer.max(lastMeeting[1], currMeeting[1]);
			} else {
				mergedMeetings.add(currMeeting);
			}			
			
		});
		
		return mergedMeetings;
	}
	
}