package duybach.example;

import java.util.ArrayList;
import java.util.HashMap;

public class MarkovBuilder {
	
	private String input[];
	private final int NUM_PREFS = 2;
	private final int NUM_MAX_GEN = 100;
	private final HashMap<Integer, ArrayList<Prefix>> builderMap = new HashMap<>();
	
	public MarkovBuilder(String input[]) {
		this.input = input;
	}
	
	/* 
	 * This method is an interface to the application when user request text generation process
	 * @param none
	 * @return String result of Markov-Chain random text generation algorithm
	 */
	public String generate() {
		if (input.length <= 3 * NUM_PREFS) return "Builder string is too short...";
		
		build();
		String result = "";
		for (int words = NUM_PREFS; words <= NUM_MAX_GEN; words++) {
			
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
		
		
		
	}
}
