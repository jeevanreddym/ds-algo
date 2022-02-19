package my.learning.problems.solving.amazon;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * 	Time Complexity: O(2^N)O(2N), where NN is the number of cells in the prison.
	Space Complexity: O(2^N * N)O(2N*N).
 *
 */
public class PrisonCellsStateAfterNDays {

	public static void main(String[] args) {
		
		int[] cells = {};
		
		int n = 4;
		int[] stateAfterNDays = prisonAfterNDays(cells, n);
		
		System.out.println(String.format("Cell's state after %s days %s", n, Arrays.toString(stateAfterNDays)));
		
	}
	
	public static int[] prisonAfterNDays(int[] cells, int n) {
				
        Map<Integer, Integer> seen = new HashMap<>();

        // state  = integer representing state of prison
        int state = 0;
        for (int i = 0; i < 8; ++i) {
            if (cells[i] > 0)
                state ^= 1 << i;
        }

        // While days remaining, simulate a day
        while (n > 0) {
            // If this is a cycle, fast forward by
            // seen.get(state) - n, the period of the cycle.
            if (seen.containsKey(state)) {
                n %= seen.get(state) - n;
            }
            seen.put(state, n);

            if (n >= 1) {
                n--;
                state = nextDay(state);
            }
        }

        // Convert the state back to the required answer.
        int[] ans = new int[8];
        for (int i = 0; i < 8; ++i) {
            if (((state >> i) & 1) > 0) {
                ans[i] = 1;
            }
        }

        return ans;
    }

    public static int nextDay(int state) {
        int ans = 0;

        // We only loop from 1 to 6 because 0 and 7 are impossible,
        // as those cells only have one neighbor.
        for (int i = 1; i <= 6; ++i) {
            if (((state >> (i-1)) & 1) == ((state >> (i+1)) & 1)) {
                ans ^= 1 << i;
            }
        }

        return ans;
    }
	
}