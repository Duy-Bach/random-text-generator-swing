package duybach.example;

import java.util.ArrayList;
import java.util.Random;

public class Prefix {
	private int MULTIPLIER = 31;
	private int numPrefs;
	private int numHash;
	private ArrayList<String> prefs;
	private ArrayList<String> sufs;

	public Prefix(int numPrefs, int numHash) {
		this.numPrefs = numPrefs;
		this.numHash = numHash;
		prefs = new ArrayList<>();
		sufs = new ArrayList<>();
	}
	
	public Prefix(Prefix other) {
		this.numPrefs = other.numPrefs;
		this.numHash = other.numHash;
		prefs = new ArrayList<>();
		sufs = new ArrayList<>();
		
		for (String pref : other.prefs) this.prefs.add(new String(pref));
	}
	
	public void addSuf(String suf) {
		sufs.add(suf);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() != Prefix.class) return false;
		Prefix other = (Prefix) obj;
		
		boolean isEqual = true;
		for (int i = 0; i < numPrefs; ++i) {
			isEqual &= (prefs.get(i).equals(other.prefs.get(i)));
		}
		return isEqual;
	}
	
	@Override
	public int hashCode() {
		int h = 0;
		
		for (String pref : prefs) {
			h = MULTIPLIER * h + pref.hashCode();
		}
		return (h & 0x7FFFFFF) % numHash;
	}
	
	public void init(String input[]) {
		for (int i = 0; i < numPrefs; ++i) {
			prefs.add(new String(input[i]));
		}
	}
	
	public String randomSuf() {
		Random rand = new Random();
		int randIndx = rand.nextInt(sufs.size());
		return sufs.get(randIndx);
	}
	
	public void update(String next) {
		prefs.remove(0);
		prefs.add(next);
	}
}
