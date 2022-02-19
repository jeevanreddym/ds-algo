package my.learning.algorithms.hash;

import java.util.HashMap;
import java.util.Map;

public class Practice {

	public static void main(String[] args) {
		
		Map<Employee, String> map = new HashMap<>();
		
		
		Employee e1 = new Employee("Aaa", 250000d);
		Employee e2 = new Employee("bbb", 250000d);
		Employee e3 = new Employee("ccc", 250000d);
		Employee e4 = new Employee("ddd", 250000d);
		
		map.put(e1, "Aaa");
		map.put(e2, "bbb");
		map.put(e3, "ccc");
		map.put(e4, "ddd");
		
		System.out.println(map.getOrDefault(e4, "Dont know"));
		
		System.out.println(e1.hashCode());
		
		System.out.println(e1.hashCode());
		
		System.out.println(e1.hashCode());		
	}

	static class Employee {
		String name;
		Double salary;
		public Employee(String name, Double salary) {
			this.name = name;
			this.salary = salary;
		}
		
	}
	
}
