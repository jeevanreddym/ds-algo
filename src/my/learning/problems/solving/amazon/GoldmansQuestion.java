package my.learning.problems.solving.amazon;



import java.util.HashMap;
import java.util.Map;

/*
 ** Instructions:
 **
 ** Given a list of student test scores, find the best average grade. Each
 * 	student may have more than one test score in the list.
 **
 ** Complete the bestAverageGrade function in the editor below. It has one
 * 	parameter, scores, which is an array of student test scores. Each element in
 * 	the array is a two-element array of the form [student name, test score] e.g.
 * 	[ "Bobby", "87" ]. Test scores may be positive or negative integers.
 **
 ** If you end up with an average grade that is not an integer, you should use a
 * 	floor function to return the largest integer less than or equal to the
 * 	average. Return 0 for an empty input.
 **
 ** Example:
 **
 ** Input: [ 
 *		[ "Bobby", "87" ], 
 *		[ "Charles", "100" ], 
 *		[ "Eric", "64" ], 
 *		[ "Charles", "22" ] 
 *	].
 **
 ** Expected output: 87 
 *	Explanation: The average scores are 87, 61, and 64 for
 * 	Bobby, Charles, and Eric, respectively. 
 * 	87 is the highest.
 * 
 */

class GoldmansQuestion {
	
	/*
	 ** Find the best average grade.
	 */
	public static Integer bestAverageGrade(String[][] scores) {
		
		if (scores == null || scores.length <= 0) {
			return 0;
		}
		
		Map<String, Integer[]> studentScoresMap = new HashMap<>();
		for (String[] studentDetail: scores) {
			Integer[] details = studentScoresMap.get(studentDetail[0]);
			if (details != null) {
				details[0] += Integer.valueOf(studentDetail[1]);
				details[1]++;
			} else {
				studentScoresMap.put(studentDetail[0], new Integer[] { Integer.valueOf(studentDetail[1]), 1 });
			}
		}
					
		int maxAvg = Integer.MIN_VALUE;			
		for (String name : studentScoresMap.keySet()) {
			Integer[] details = studentScoresMap.get(name);
			Integer currAvg = details[0]/details[1];				
			maxAvg = Integer.max(maxAvg, currAvg);
		}
		
		return maxAvg;
	}

	/*
	 ** Returns true if the tests pass. Otherwise, returns false;
	 */
	public static boolean doTestsPass() {
		// TODO: implement more test cases
		String[][] tc1 = { { "Bobby", "87" }, { "Charles", "100" }, { "Eric", "64" }, { "Charles", "22" } };

		return bestAverageGrade(tc1) == 87;
	}

	/*
	 ** Execution entry point.
	 */
	public static void main(String[] args) {
		// Run the tests
		if (doTestsPass()) {
			System.out.println("All tests pass");
		} else {
			System.out.println("Tests fail.");
		}
	}
	
}
