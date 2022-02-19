package my.learning.algorithms.recursion;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

public class NumberOfTxtFilesInADirectory {

	public static void main(String[] args) {
		
		File f = new File("C:\\Users\\madhje5\\g1-eclipse-workspaces\\DS&Algo\\src\\my\\learning\\algorithms\\recursion\\NumberOfTxtFilesInADirectory.java");
		System.out.println(count(f));
		System.out.println(countFilesInDirectory(f));
	}
	
	/**
	 * 	recursive approach.
	 */
	static int count(File f) {
		
		int cnt = 0;
		
		if (f.isFile()) {
			cnt++;
		}

		File[] files = f.listFiles();
		if (files != null && files.length > 0) {
			for (File file : files) {				
				if (file.isDirectory()) {			
					cnt += count(file);			
				}
				else if (file.isFile()) {
					cnt++;
				}
			}	
		}
			
		return cnt;
	}
	
	
	/**
	 * 	iterative approach.	
	 */
	static int countFilesInDirectory(File directory) {
		
		int cnt = 0;
		
		Queue<File> q = new LinkedList<>();
		q.offer(directory);

		while (!q.isEmpty()) {
			
			directory = q.poll();
		
			if (directory.isFile()) {
				cnt++;
			}
			
			File[] files = directory.listFiles();
			if (files != null && files.length > 0) {
				for (File file : files) {
					
					if (file.isDirectory()) {				
						q.offer(file);			
					}
					else if (file.isFile()) {
						cnt++;
					}
				}	
			}
					
		}		
		return cnt;
	}
	
}
