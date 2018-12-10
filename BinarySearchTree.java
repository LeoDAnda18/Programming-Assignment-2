package bst;

import java.util.ArrayList;

import model.*;

/*
 * This class stores all of the required binary search tree functions specified in the book and in the guidelines.
 * These functions have been adjusted to work in Java and with the WebPage class. The root is Storage[0].
 * 
 */
public class BinarySearchTree 
{	
	int pageRankCounter = 1; //Used to assign a pageRank value to each node in the tree. 
						 //(Especially useful after an insert/delete, which needs to redo values).
	ArrayList<WebPage> Storage = new ArrayList<WebPage>(); //dynamic size useful for insert/delete operations.
	
	/*
	 * Constructor. Takes in a WebPage array, stores a copy into Storage, 
	 * and then calls buildTree to create a BST.
	 * For loop takes O(n) time, buildTree takes O(nlgn) time on a randomly built tree (O(lgn) for inserts, O(n) for loop, O(nlgn) total).
	 */
	public BinarySearchTree(WebPage[] a1)
	{
		for(int i = 0; i < a1.length; i++)
		{
			Storage.add(a1[i]); //Stores a copy into Storage, 
		}
		buildTree(); //Builds up a tree by calling multiple treeInserts.
	}
	
	/*
	 * This function takes the ArrayList and constructs a BST out of it. It calls insert starting with the 2nd element.
	 * This function takes O(nlgn) time because building it is O(n) and each insert takes O(lgn) time, so O(n*lgn).
	 */
	public void buildTree()
	{
		for(int i = 1; i < Storage.size(); i++) //First element in this array is the root, so it is already inserted appropriately.
		{
			treeInsert(Storage.get(i)); //insert elements into the tree appropriately.
		}
		resetPageRanks(getRoot()); //After tree is constructed, do an inorder traversal and set the PageRank accordingly.
	}
	
	/*
	 * When searching for new terms, can't use the same array. Therefore, have to clear the Storage and then have it contain a copy of the newArray.
	 * This function takes O(n) time for each for loop and O(nlgn) for build tree.
	 */
	public void replaceStorage(WebPage[] newArray)
	{
		for(int i = 0; i < Storage.size(); i++)
		{
			Storage.get(i).clearPointers(); //sets all pointers back to null within the array.
		}
		Storage.clear(); //empty the current storage.
		for(int i = 0; i < newArray.length; i++)
		{
			Storage.add(newArray[i]); //fills the ArrayList with the contents of newArray.
		}
		buildTree(); //builds up a BST based off the new Storage.
	}
	
	/*
	 * This function acts as an equal operation, such as (WebPage)p = (WebPage)d. Since there is no operator overload,
	 * this function is called to do the equal operation instead.
	 * This takes O(1) time.
	 */
	public void override(WebPage a1, WebPage a2)
	{
		a1.setURL(a2.getURL()); //overrides URL variable
		a1.setRel(a2.getRel()); //overrides PageRank variable
		a1.setFreq(a2.getFreq()); //overrides Frequency and Location variable
		a1.setExis(a2.getExis()); // overrides Existence variable
		a1.setConn(a2.getConn()); // overrides connection variable
		a1.setAdvt(a2.getAdvt()); // overrides advertisement variable.
		a1.setIndex(a2.getIndex()); //overrides index variable
		a1.setLeft(a2.getLeft()); //overrides the left "pointer".
		a1.setRight(a2.getRight()); //overrides the right "pointer".
		a1.setParent(a2.getParent()); //overrides the parent "pointer".
	}
	
	/*
	 * This function is called right after inserting or deleting from the tree. These operations will mess up the PageRanks,
	 * and so we have to recalculate the pageranks. This function does the recalculating for us.
	 * This takes O(1) + O(n) time, so total of O(n) time.
	 */
	public void resetPageRanks(WebPage x)
	{
		int BSTSize = pageRankCounter;
		setPageRanks(x); //Sets each node's pagerank equal to the value of pageRankCounter
		pageRankCounter = BSTSize;
	}
	
	/*
	 * This function is called to calculate the PageRanks of each node. It does an InOrderTraversal, but instead of printing something,
	 * it assigns a PageRank value to the node. This runs in O(n) time, since it has to go through each node in the tree.
	 */
	public void setPageRanks(WebPage x)
	{
		//Recursively call this function so long as the current node isn't null.
		if(x != null)
		{
			setPageRanks(x.getLeft()); //Traverse left first
			x.setPRank(pageRankCounter); //Assign a pagerank value to the node equal to pageRankCounter.
			pageRankCounter--; //increment pageRankCounter
			setPageRanks(x.getRight()); //Traverse to the right after assigning a pageRank value.
		}
	}
	
	/*
	 * This function displays the contents of the tree in an ascending order manner based off the insert function.
	 * This runs in O(n) time, since it has to go through each node in the tree.
	 */
	public void InOrderTreeWalk(WebPage x)
	{
		//Recursively call this function so long as the current node isn't null.
		if(x != null)
		{
			InOrderTreeWalk(x.getLeft()); //Traverse left first
			x.printContents(); //Display the contents of the node.
			InOrderTreeWalk(x.getRight()); //Traverse right after displaying the node's content.
		}
	}
	
	/*
	 * This function takes in a PageRank value (assigned to k) and then, starting from the root, searches for the node containing that pageRank.
	 * If PageRank < k, it will traverse to the left. If PageRank > k, it will traverse to the right.
	 * If PageRank = k, it will return that node. If PageRank is not in the tree, it will print out a message saying it was not found.
	 * This function takes O(lgn) time on average (O(n) in worst case).
	 */
	public WebPage treeSearch(WebPage x, int k)
	{
		if(x == null) //If no node contains the given pageRank.
		{
			System.out.println("No webpage contains a pageRank of " + k); //display a message to notify the user.
			return null; 
		}
		if(x.getPRank() == k) //if a node is found to contain the pagerank value.
			return x; 
		if(k > x.getPRank()) //if the pagerank is less than the current node's pagerank value, traverse left (higher pagerank = smaller score, go left).
			return treeSearch(x.getLeft(), k);
		else //if the pagerank is greater than the current node's pagerank value, traverse right (lower pagerank = larger score.
			return treeSearch(x.getRight(), k);
	}
	
	/*
	 * This function finds the minimum totalScore node in the tree. As a result, it will traverse all the way left.
	 * This function takes O(lgn) time on average, O(n) in worst case (if tree is entirely skewed left).
	 */
	public WebPage treeMinimum(WebPage x)
	{
		while(x.getLeft() != null) //As long as the current node isn't null, traverse to the left child.
			x = x.getLeft();
		return x;
	}
	
	/*
	 * This function finds the maximum totalScore node in the tree. As a result, it will traverse all the way right.
	 * This function takes O(lgn) time on average, O(n) time worst case (if tree is entirely skewed right).
	 */
	public WebPage treeMaximum(WebPage x)
	{
		while(x.getRight() != null) //As long as the current node isn't null, traverse to the right child.
			x = x.getRight();
		return x;
	}
	
	/*
	 * This function finds the successor to a node. Based off the current node, it will first go to its right child, and then in that subtree,
	 * find the minimum in that subtree. This function takes O(lgn) time and returns the successor node. 
	 */
	public WebPage treeSuccessor(WebPage x)
	{
		if(x.getRight() != null) //If the node has a right child, find the minimum of the right child subtree.
			return treeMinimum(x.getRight());
		 //If the node does not have a right child, traverse up the tree until you find the smallest value > current value.
		WebPage y = x.getParent();
		while(y != null && x == y.getRight()) //if it is a right child, child > parent, so parent cant be successor. keep going up.
		{
			x = y; 
			y = y.getParent();
		}
		return y;
	}
	
	/*
	 * This function inserts a new node into the tree. It starts by checking the totalScore of the new node z with the root, and then
	 * traversing left or right depending if it is less than or greater than the root. Once the location is found, pointer manipultion is done
	 * to insert the node into the tree. 
	 * This function takes O(lgn) time, and it then calls resetPageRanks to redo pagerank values.
	 */
	public void treeInsert(WebPage z)
	{
		WebPage y = new WebPage(); //Used to keep track of the previous node checked.
		WebPage x = new WebPage(); //used to keep track of the current node checked.
		y = null; //set y equal to null.
		x = null; //set x equal to null
		x = getRoot(); //set x equal to the root.
		while(x != null) //as long as x is not null, continue traversing.
		{
			y = x;
			if(z.getRel() < x.getRel()) //if the new node totalscore is less than x totalscore, go left.
				x = x.getLeft();
			else //if the new node totalscore is greater than x totalscore, go right.
				x = x.getRight();
		}
		//Once the location is determined, set z parent to y and then set y appropriate child to z.
		z.setParent(y);
		if(y == null)
			Storage.add(z);
		else if(z.getRel() < y.getRel()) //Left child
			y.setLeft(z);
		else //Right child.
			y.setRight(z); 
		pageRankCounter++;
		resetPageRanks(getRoot()); //Redo the pageranks because of the new node.
	}
	
	/*
	 * This function will replace a node with node v and its subtree. This is used in treeDelete.
	 * This function takes O(1) time.
	 */
	public void transplant(WebPage u, WebPage v)
	{
		//If u has no parent, it is the root. Assign it as the root (equivalent to element 0 in the arraylist).
		if(u.getParent() == null)
			Storage.add(v);
		else if (u == u.getParent().getLeft()) //if u is originally a left child of the parent, replace the parent's left child with v.
			u.getParent().setLeft(v);
		else  //if u is originally a right child of the parent, replace the parent's right child with v.
			u.getParent().setRight(v);
		if(v != null) //set the parent of v to be u's original parent so long as v is not null.
			v.setParent(u.getParent());
	}
	
	/*
	 * This function will determine how to delete the current node from the tree based off 3 cases.
	 * Case 1: No child - simply remove the node.
	 * Case 2: One child - remove the node and have the node's parent point to the node's child, 
	 * 						while the child parent pointer points to the nodes parent.
	 * Case 3: Two childs - find the node's successor and swap the current node with the successor. Then do either case 1 or 2 with the node.
	 * At the end,  redo pagerank value calculations.
	 * This takes O(lgn) time on average to delete a node.
	 */
	public void treeDelete(WebPage z)
	{
		WebPage y = new WebPage();
		y = null;
		if(z.getLeft() == null) //If it doesn't have a left child, replace it with the right child (if right is null, then it simply removes the node).
			transplant(z,z.getRight());
		else if(z.getRight() == null) //If it doesn't have a right child, replace it with left child (if left is null, then it removes the node).
			transplant(z,z.getLeft());
		else //if it has two children, then find the successor to that node.
		{
			y = treeMinimum(z.getRight()); //finds the successor.
			if(y.getParent() != z) //if it is not an immediate child, replace y with its right child, since it will be moving to replace z soon.
			{
				transplant(y,y.getRight());
				y.setRight(z.getRight());
				y.getRight().setParent(y);
			}
			transplant(z,y); //replace z in the BST with the successor y and its subtree.
			y.setLeft(z.getLeft());
			y.getLeft().setParent(y);
		}
		pageRankCounter--;
		resetPageRanks(getRoot()); //Redo the pageranks because of the deleted node.	
	}
	
	//Getters
	public WebPage getRoot() {return Storage.get(0);}
	public WebPage getElement(int i) {return Storage.get(i);}
	
	//Setters
	public void setRoot(WebPage R) {override(Storage.get(0), R);}
	public void setElement(int i, WebPage E) {override(Storage.get(i), E);}
}
