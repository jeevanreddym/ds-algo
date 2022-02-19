package my.learning.problems.solving.amazon;

import java.util.List;

public class ShoppingOptions {

	public static void main(String[] args) {
		
	}
	

    public static long getNumberOfOptions(
    		List<Integer> priceOfJeans, 
    		List<Integer> priceOfShoes, 
    		List<Integer> priceOfSkirts, 
    		List<Integer> priceOfTops, 
    		int dollars) {    	
    	long numOfOptions = 0;
    	
        for(int i=0; i<priceOfJeans.size();i++) {
                for(int j=0; j<priceOfShoes.size();j++) {
                        for(int k=0; k<priceOfSkirts.size();k++) {
                                for(int l=0; l<priceOfTops.size();l++) {                                        
                                        if (priceOfJeans.get(i) + priceOfShoes.get(j)+priceOfSkirts.get(k)+priceOfTops.get(l) <= dollars)
                                        	numOfOptions++;
                                }
                        }
                }
        }
            	
    	return numOfOptions;
    }
	
}
