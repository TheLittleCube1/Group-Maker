package main;

import java.util.List;

public class Configuration implements Comparable<Configuration> {
	
	Group[] groups = new Group[6];
	double score;
	
	public Configuration() {
		for (int i = 0; i < 6; i++) groups[i] = new Group();
		score = score();
	}
	
	public Configuration(Group...groups) {
		this.groups = groups;
		score = score();
	}
	
	public double score() {
		double sum = 0;
		for (Group group : groups) {
			sum += group.score();
		}
		return sum;
	}
	
	public static Configuration generateRandomConfiguration() {
		Configuration configuration = new Configuration();
		List<String> shuffledList = Relationships.shuffleClassList();
		for (int i = 0; i < shuffledList.size(); i++) {
			String name = shuffledList.get(i);
			int index = Relationships.nameToIndex.get(name);
			configuration.groups[i / 5].students.add(index);
		}
		configuration.score = configuration.score();
		return configuration;
	}
	
	public void print() {
		for (int i = 0; i < 6; i++) {
			System.out.println("Group " + (i + 1) + ": " + groups[i].names() + " --> " + groups[i].score());
		}
		System.out.println("Total score: " + score + "\n");
	}

	@Override
	public int compareTo(Configuration otherConfiguration) {
		double difference = score - otherConfiguration.score;
		if (difference > 0) return -1;
		else if (difference < 0) return 1;
		else return 0;
	}
	
}
