package my.learning.datastructures.array;


/**
 * 	Bit manipulation.
 */
public class MyBitSet {

	private byte[] byteArr;
	
	public MyBitSet(int capacity) {
		this.byteArr = new byte[capacity];
	}
	
	public boolean get(int i) {
		return (byteArr[i / 8] & 1 << (i % 8)) == 1 << (i % 8);
	}
	
	public void set(int i, boolean value) {
		if (value)
			byteArr[i / 8] |= 1 << (i % 8);
        else
        	byteArr[i / 8] ^= 1 << (i % 8);
	}
	
	public static void main(String[] args) {
		
		MyBitSet bitSet = new MyBitSet(10);
		bitSet.set(5, true);
		bitSet.set(6, true);
		bitSet.set(7, true);
		bitSet.set(9, true);
		
		System.out.println(bitSet.get(3));
		System.out.println(bitSet.get(6));
	}
	
}
