package model;

import java.util.*;
/*
 * This class stores the search term and how often it was searched.
 * Search - holds the search term
 * Frequency - holds how often it was searched.
 */
public class UniqueSearch 
{
	private String search; //holds the user's search phrase
	private int frequency; // holds how many times the user searched that phrase
	
	//Constructors
	public UniqueSearch() //Default constructor
	{
		search = "";
		frequency = 1; //Since you had to search for it once, it comes with a frequency of one.
	}
	
	public UniqueSearch(String s)
	{
		search = s;
		frequency = 1; //Since you had to search for it once, it comes with a frequency of one.
	}
	
	//Setters
	public void setSearch(String s) {search = s;}
	public void setFrequency(int f) {frequency = f;}
	
	//Getters
	public String getSearch() {return search;}
	public int getFrequency() {return frequency;}
	
	//increments frequency by 1.
	public void frequencyIncrement()
	{
		frequency = frequency + 1;
	}
	
	//sets LHS properties equal to RHS properties.
	public void override(UniqueSearch item2)
	{
		search = item2.getSearch();
		frequency = item2.getFrequency();
	}
	
	//Displays the results in a simple format.
	public void printContent()
	{
		System.out.println(search + " was searched for " + frequency + " times.");
	}
}