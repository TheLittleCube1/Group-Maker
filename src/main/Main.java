package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	
	// Settings
	public static final int SECONDS = 1, CAPACITY = 10;
	
	public static void main(String[] args) {
		
		Relationships.importRelationships();
		
		List<Configuration> bestConfigurations = new ArrayList<Configuration>();
		
		int configurationsTried = 0;
		long start = System.nanoTime();
		while ((System.nanoTime() - start) / 1e9 < SECONDS) {
			Configuration candidate = Configuration.generateRandomConfiguration();
			bestConfigurations.add(candidate);
			Collections.sort(bestConfigurations);
			if (bestConfigurations.size() > CAPACITY) {
				bestConfigurations.remove(bestConfigurations.size() - 1);
			}
			configurationsTried++;
		}
		
		for (Configuration configuration : bestConfigurations) {
			configuration.print();
		}
		
		System.out.println(configurationsTried);
		
	}
	
}
