package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Relationships {

	public static String[] classList = { "AdamA", "HasanB", "CyrusC", "ReedD", "JackF", "DanielG", "ShourjoG", "AliH",
			"WilliamH", "JerryJ", "KolinaK", "ArohiK", "LucasL", "PatrickL", "KevinR", "AvaR", "PeterS", "SanyaS",
			"AlexS", "WilliamS", "TeodorS", "JasonS", "CooperT", "FredY", "GavinY", "DarwinZ", "EmilyZ", "KevinZ",
			"JonathanZ" };
	public static Map<String, Integer> nameToIndex = new HashMap<String, Integer>();

	public static double[][] relationships = new double[29][29];

	public static void importRelationships() {
		
		for (int i = 0; i < classList.length; i++) {
			nameToIndex.put(classList[i], i);
		}
		
		File file = new File("Relationships.txt");
		try {
			Scanner s = new Scanner(file);
			
			while (s.hasNext()) {
				String name = s.next();
				String data = s.next();
				int index = nameToIndex.get(name);
				double[] list = new double[29];
				for (int i = 0; i < data.length(); i++) {
					list[i] = data.charAt(i) - '0';
				}
				relationships[index] = normalized(list);
				System.out.println(Arrays.toString(relationships[index]));
				System.out.println(mean(relationships[index]));
			}
			
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static List<String> shuffleClassList() {
		List<String> list = new ArrayList<String>(), shuffled = new ArrayList<String>();
		for (String name : classList) list.add(name);
		while (!list.isEmpty()) {
			int index = (int) (list.size() * Math.random());
			shuffled.add(list.get(index));
			list.remove(index);
			
		}
		return shuffled;
	}
	
	public static double mean(double[] list) {
		double length = list.length;
		double sum = 0;
		for (double i : list) sum += i;
		return sum / length;
	}
	
	public static double[] normalized(double[] list) {
		double factor = 3 - mean(list);
		double[] normalized = new double[29];
		for (int i = 0; i < 29; i++) {
			normalized[i] = list[i] + factor;
			if (normalized[i] > 6) normalized[i] = 6;
		}
		return normalized;
	}

}
