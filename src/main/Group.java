package main;

import java.util.ArrayList;
import java.util.List;

public class Group {
	
	List<Integer> students = new ArrayList<Integer>();
	
	public Group(Integer...arrayStudents) {
		for (int student : arrayStudents) {
			this.students.add(student);
		}
	}
	
	public double score() {
		double sum = 0;
		
		// Loop over every pair of people in the group and evaluate how much they want each other
		for (int i = 0; i < students.size(); i++) {
			for (int j = i + 1; j < students.size(); j++) {
				// Take the rating person A gave B and the rating person B gave A, and multiply them together.
				// You can derive for yourself that the product of two numbers is maximized when they are close together.
				double contribution = Relationships.relationships[students.get(i)][students.get(j)] * Relationships.relationships[students.get(j)][students.get(i)];
				sum += contribution;
			}
		}
		
		// In groups of 5, there are 10 pairs of any two people.
		// In groups of 4, there are only 6 pairs of any two people.
		// Since there's one group of 4, we need to take this into account.
		if (students.size() == 4) {
			sum *= 10 / 6;
		}
		
		return sum;
	}
	
	public List<String> names() {
		List<String> names = new ArrayList<String>();
		for (int student : students) {
			names.add(Relationships.classList[student]);
		}
		return names;
	}
	
	public String toString() {
		List<String> names = new ArrayList<String>();
		for (int student : students) {
			names.add(Relationships.classList[student]);
		}
		return "" + names;
	}
	
}
