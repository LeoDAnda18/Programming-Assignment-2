package model;

import java.util.Random;

/*
 * Webpage holds 6 variables and creates a random object. It also contains 3 "pointers" to be used in a binary search tree.
 * 	Rand - Used to randomly assign values to the 4 factors.
 * 	Relevancy_Score - holds the value for the PageRank. (may also be called PageRank)
 * 	Frequency_Location_Factor - holds the simulated value for how often word shows up in the page.
 * 	Existence_Time_Factor - holds the simulated value for how long the page existed.
 * 	Connected_Links_Factor - holds the simulated value for how many links connect to this page.
 * 	Advertisement_Payment_Factor - holds the simulated contribution to google.
 *  Index - holds the index the webpage was stored in originally (before the sorting).
 *  
 *  "Pointers":
 *  left - points to the left child.
 *  right - points to the right child.
 *  p - points to the parent.
 *  
 * This object goes into an ArrayList and then is used in the BinarySearchTree.
 */

public class WebPage 
{
	Random rand = new Random(); //Used for random number generator
	private String URL; // stores the url
	private int Index; // store the original index it is located in before sorting.
	private int PageRank; // stores the pagerank of the webpage.
	private int Relevancy_Score; // stores a final relevancy score
	private int Frequency_Location_Factor; // stores the first factor of location and frequency
	private int Existence_Time_Factor; // stores the second factor of existence of page
	private int Connected_Links_Factor; // stores the third factor of number of webpages linked to it. 
	private int Advertisement_Payment_Factor; // stores the fourth factor of payment for advertisement.
	
	// for BST
	private WebPage left = null; // "Points" to the left child in a BST. Default it points to null.
	private WebPage right = null; // "Points" to the right child in a BST. Default it points to null.
	private WebPage p = null; // "Points" to the parent node in a BST. Default it points to null.
	
	public WebPage() // base constructor, set every integer to 0, index and pagerank to temp value of -1.
	{
		Relevancy_Score = 0;
		Frequency_Location_Factor = 0;
		Existence_Time_Factor = 0;
		Connected_Links_Factor = 0;
		Advertisement_Payment_Factor = 0;
		Index = -1;
		PageRank = -1;
	}	
	public WebPage(String U) // base constructor, set URL to a value, set integers to 0, index and pagerank to temp value of -1.
	{
		URL = U;
		Relevancy_Score = 0;
		Frequency_Location_Factor = 0;
		Existence_Time_Factor = 0;
		Connected_Links_Factor = 0;
		Advertisement_Payment_Factor = 0;
		Index = -1;
		PageRank = -1;
	}
	
	//Setters
	public void setURL(String U) {URL = U;}
	public void setRel(int R) {Relevancy_Score = R;}
	public void setFreq(int F) {Frequency_Location_Factor = F;}
	public void setExis(int E) {Existence_Time_Factor = E;}
	public void setConn(int C) {Connected_Links_Factor = C;}
	public void setAdvt(int A) {Advertisement_Payment_Factor = A;}
	public void setPRank(int P) {PageRank = P;}
	public void setIndex(int I) {Index = I;}
	public void setLeft(WebPage L) {left = L;}
	public void setRight(WebPage R) {right = R;}
	public void setParent(WebPage P) {this.p = P;}
	
	//Getters
	public String getURL() {return URL;}
	public int getRel() {return Relevancy_Score;}
	public int getFreq() {return Frequency_Location_Factor;}
	public int getExis() {return Existence_Time_Factor;}
	public int getConn() {return Connected_Links_Factor;}
	public int getAdvt() {return Advertisement_Payment_Factor;}
	public int getPRank() {return PageRank;}
	public int getIndex() {return Index;}
	public WebPage getLeft() {return left;}
	public WebPage getRight() {return right;}
	public WebPage getParent() {return p;}
	
	//Random Number assignment. This is used to make up a relevency score for the WebPage.
	public void RandomSetRel()
	{
		Frequency_Location_Factor = 2 * rand.nextInt(99) + 1; // range of 1-100 with a relevency factor of 2x
		Existence_Time_Factor = rand.nextInt(99) + 1; // range of 1-100 with a relevency factor of 1x
		Connected_Links_Factor = rand.nextInt(99) + 1; // range of 1-100 with a relevency factor of 1x
		Advertisement_Payment_Factor = 4 * rand.nextInt(99) + 1; // range of 1-100 with a relevency factor of 4x
		Relevancy_Score = ((Frequency_Location_Factor + Existence_Time_Factor + 
							Connected_Links_Factor + Advertisement_Payment_Factor)/8); // Final score within a 1 - 100 range.
	}	
	
	/*
	 * sets pointers to null when we clear out the urlStorage array.
	 * This runs in O(1) time.
	 */
	public void clearPointers()
	{
		left = null;
		right = null;
		p = null;
	}
	
	/*
	 * Prints out the results specified in the requirements in a formatted manner.
	 */
	public void printContents()
	{
		System.out.printf("%3d \t %3d \t\t %3d \t %s \n", Index, PageRank, Relevancy_Score, URL);
	}
}