package duybach.example;

import java.util.ArrayList;

public class Prefix {
	private int MULTIPLIER = 31;
	private int numPrefs;
	private int numHash;
	private ArrayList<String> prefs;
	

	public Prefix(int numPrefs, int numHash) {
		this.numPrefs = numPrefs;
		this.numHash = numHash;
		prefs = new ArrayList<>();
	}
	
	@Override
	public int hashCode() {
		int h = 0;
		
		for (String pref : prefs) {
			h = MULTIPLIER * h + pref.hashCode();
		}
		
		return (h & 0x7FFFFFF) % numHash;
	}
}
