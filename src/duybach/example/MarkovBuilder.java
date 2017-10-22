package duybach.example;

import java.util.ArrayList;
import java.util.HashMap;

public class MarkovBuilder {
	
	private String input[];
	private int numPrefs = 2;
	private final int NUM_HASH = 4093;
	private final int NUM_MAX_GEN = 100;
	private final HashMap<Integer, ArrayList<Prefix>> builderMap = new HashMap<>();
	
	public MarkovBuilder(String input[], int numPrefs) {
		this.input = input;
		this.numPrefs = numPrefs;
	}
	
	/* 
	 * This method is an interface to the application when user request text generation process
	 * @param none
	 * @return String result of Markov-Chain random text generation algorithm
	 */
	public String generate() {
		if (input.length <= 3 * numPrefs) return "Builder string is too short...";
		
		build();
		String result = "";
		
		// Initial values
		for (int i = 0; i < numPrefs; ++i) result += input[i] + " ";
		Prefix cur = new Prefix(numPrefs, NUM_HASH);
		cur.init(input);
		
		for (int words = numPrefs; words <= NUM_MAX_GEN; words++) {
			int hash = cur.hashCode(), prefIndx = -1;
			
			ArrayList<Prefix> listPrefix = builderMap.get(hash);
			
			if (listPrefix == null || (prefIndx = listPrefix.indexOf(cur)) == -1)
				break;
			
			String next = listPrefix.get(prefIndx).randomSuf();
			result += next + " ";
			cur.update(next);
		}
		
		return result;
	}
	
	/*
	 * Markov-Chain random text generation algorithm requires 2 passes over the input string
	 * This is the first pass, creating the list of PREFIX and SUFFIX used to generate text
	 * @param none
	 * @return none This method only mutate is member with no return value for external purposes
	 */
	private void build() {
		Prefix cur = new Prefix(numPrefs, NUM_HASH);
		cur.init(input);
		
		for (int index = numPrefs; index < input.length; ++index) {
			int hash = cur.hashCode();
			if (!builderMap.containsKey(hash)) {
				builderMap.put(hash, new ArrayList<Prefix>());
				builderMap.get(hash).add(new Prefix(cur));
			}
			ArrayList<Prefix> listPrefix = builderMap.get(hash);
			
			int prefIndx = -1;
			if ((prefIndx = listPrefix.indexOf(cur)) == -1) {
				listPrefix.add(new Prefix(cur));
				prefIndx = listPrefix.size() - 1;
			}
				
			Prefix add = listPrefix.get(prefIndx);
			add.addSuf(input[index]);
			builderMap.put(hash, listPrefix);
			
			cur.update(input[index]);
		}
	}
}
