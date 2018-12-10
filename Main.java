package main;

/*
 * This main allows the user to test out what is required of the assignment. Bucketsort is contained within function 6.
 * First for loop takes O(n) time.
 * Spider.search takes up to O(n^2) time.
 * Second for loop takes O(n) time.
 * Third for loop takes O(n) time.
 * 
 * From the 6 options:
 * Option 1 is quicksort, O(nlgn) time.
 * Option 2 is search, which takes O(lgn) time.
 * Option 3 is insert, takes O(n) time because display result. Insert is O(lgn).
 * Option 4 is delete, takes O(n) time to display result. Delete is O(lgn).
 * Option 5 is to display the BST in inorder. takes O(n) time.
 * Option 6 is to search again. takes up to O(n^2) if you have to search a new term.
 * All of this is in a do while loop, so the loop goes r times (where r is the number of times user decides to test something.
 * Therefore, this program takes rO(n^2) time at worst.
 */

import java.util.*;
import crawler.*;
import bst.*;
import model.*;

public class Main 
{
	public static void main(String[] args)
	{
		int mostPopularCount = 0; //stores the frequency of the most popular searched word.
		Scanner in = new Scanner(System.in);
		int User_input, size = 31; // size of array of webpages
		String searchTerm, mostPopularSearch; // user input stored into here.
		String[] mostPopularList = new String[size]; //used to store the URL's of the most popularly searched term.
		WebPage[] urlStorage = new WebPage[size]; //Array to store WebPages
		WebPage[] QSortArray = new WebPage[size]; //Used to sort the retrieved urls only (BST can insert and delete, don't want to include those).
		for(int i = 0; i < size; i++)
		//Initialize all of the objects in the arrays.
		{	
			mostPopularList[i] = new String();
			urlStorage[i] = new WebPage();
			QSortArray[i] = new WebPage();
		} 
		ArrayList<UniqueSearch> Unique = new ArrayList<UniqueSearch>(); //Holds the counter and search string for all search terms.
        Spider spider = new Spider(); //Web Crawler
      
        System.out.println("Enter a search word to browse for: ");
        searchTerm = in.nextLine(); // user input for searching
        System.out.println("Crawling the web and retrieving sites. This will take some time. Please be patient.");
        spider.search("https://www.facebook.com/", searchTerm, urlStorage); //Search the web for the word, starting at google.com
        
        UniqueSearch temp = new UniqueSearch(searchTerm);
        Unique.add(temp); // adds the search term into a unique arraylist (used at the end).
        
        BinarySearchTree f1 = new BinarySearchTree(urlStorage); // create a BST based off the urlStorage.
		for(int i = 0; i < QSortArray.length; i++)
		{
			override(QSortArray[i], urlStorage[i]); //assigns the WebPages within urlStorage into the QSortArray to do QuickSort.
		}
        mostPopularSearch = searchTerm;
        for(int i = 0; i < size; i++)
        {
        	mostPopularList[i] = urlStorage[i].getURL(); //Assigns the URL found for most popular search term into the mostPopularList.
        }
		
        do
        {
        	// Lists available options for users to select from.
    		System.out.println("Choose an option and enter the corresponding number:");
            System.out.println("1. use QuickSort to sort the original 31 URL retrieved from crawler and print results");
            System.out.println("2. Search by PageRank and display result.");
            System.out.println("3. Insert URL based off its total score and display result.");
            System.out.println("4. Delete a URL and display result.");
            System.out.println("5. Sorted list of URL's based off current BST.");
            System.out.println("6. Search for a new term");
            System.out.println("0. Quit");
            User_input = in.nextInt(); // takes in the user input
            in.nextLine();
            while(User_input < 0 || User_input > 6) //ensures user enters a valid score.
            {
        		System.out.println("Choose a valid option and enter the corresponding number:");
                User_input = in.nextInt(); // takes in the user input
                in.nextLine();
            }
            switch(User_input)
            {
            case 1:	//Does quickSort and then prints the results of quickSort.
            	{
            		quickSort(QSortArray, 0, QSortArray.length-1);
            		displayContents(QSortArray);
            		break;
            	}
            case 2: //Searches the BST for a specific PageRank. If it does not exist, a notification will say that pagerank does not exist.
            	{
                    int PageRankTemp;
                    System.out.println("Please enter a PageRank to search for.");
                    PageRankTemp = in.nextInt(); //Stores pagerank to search for
            		WebPage search = new WebPage(); //Create a webpage object to hold the object found from the search.
                    search = null;
                    search = f1.treeSearch(f1.getRoot(), PageRankTemp);
                    if(search != null)
                    {
                    search.printContents(); //if webpage found, display content.
                    }
                    break;
            	}
            case 3: //Inserts a WebPage into the BST and display result.
            	{
            		int TotalScoreTemp;
            		String URLTemp;
            		
                    WebPage add = new WebPage(); //instantiate an object to hold the webpage to add.
                    System.out.println("Please enter the URL of this new WebPage: ");
                    URLTemp = in.next();
                    System.out.println("Please enter a TotalScore to assign to this WebPage: ");
                    TotalScoreTemp = in.nextInt();
                    add.setRel(TotalScoreTemp);
                    add.setURL(URLTemp);
                    f1.treeInsert(add); //insert the webpage into the BST.
                    f1.InOrderTreeWalk(f1.getRoot()); //display the result.
            		break;
            	}
            case 4: //Delete a WebPage from the BST and display the result
            	{
            		int PageRankTemp;
                    f1.InOrderTreeWalk(f1.getRoot()); //display the current BST.
                    System.out.println("Please enter the PageRank value of the URL you would like to delete.");
                    PageRankTemp = in.nextInt(); //store user input.
                    WebPage search = new WebPage();
                    search = null;
                    search = f1.treeSearch(f1.getRoot(), PageRankTemp); //store the object found
                    if(search != null) //if object found isn't null
                    {
                        f1.treeDelete(search); //delete from tree
                        f1.InOrderTreeWalk(f1.getRoot()); //display results.
                    }
                    break;
            	}
            case 5: //Traverse the tree in order and print out the results.
            {
            	f1.InOrderTreeWalk(f1.getRoot()); //display the Tree.
            	break;
            }
            	
            case 6: //Search for a new term. If it is the most popular searchTerm, display the URL's stored in most popular.
            	{
          	    	 int i;
            		//Lets the user enter a new search word.
            		 System.out.println("Enter a search word to browse for: ");
            	     searchTerm = in.nextLine();
            	     if(searchTerm.equals(mostPopularSearch)) //if search term is the most popular, display the url stored in popular list.
            	     {
                         bucketSort(mostPopularList);
                         printPopularList(mostPopularList);
            	     }
            	     else
            	     {
            	    	 // call webcrawler and retrieve a new set of URL.
            	    	 System.out.println("Crawling the web and retrieving sites. This will take some time. Please be patient.");
            	    	 spider.search("https://www.google.com/", searchTerm, urlStorage); //Search the web for the word, starting at google.com
            	    	 f1.replaceStorage(urlStorage); //replaces the stored array in the bst to account for the new results.
            	    	 for(i = 0; i < QSortArray.length; i++) //replace the QSort array as well.
            	    	 {
            	    		 override(QSortArray[i], urlStorage[i]);
            	    	 }
            	     }
            	     for(i = 0; i < Unique.size(); i++) //Check if the searchterm has been searched before.
            	     {
            	    	 //check if the search term already exists.
            	    	 if(searchTerm.equals(Unique.get(i).getSearch()))
            	    	 {
            	    		 Unique.get(i).frequencyIncrement(); //if it does exist, increment frequency counter by 1.
            	    		 if(Unique.get(i).getFrequency() > mostPopularCount) //check if it is not greater than mostPopularCount.
            	   			 {
            	   				 mostPopularCount = Unique.get(i).getFrequency();
            	   				 mostPopularSearch = searchTerm;
            	   				 for(int j = 0; j < size; j++) //replace mostPopularList.
                				 {
           	    					 mostPopularList[j] = urlStorage[j].getURL();
           	    				 }
           	    			 }
           	    			 break;
           	    		 }
           	    	 }
            	     if(i == Unique.size()) // if it reaches the end and still hasn't found an entry, add it as a new entry
            	     {
            	    	 UniqueSearch temp2 = new UniqueSearch(searchTerm); 
            	    	 Unique.add(temp2);
            	     }
            	     break;
            	}
            } 
            
        }while(User_input != 0); //keep running until user quits (enters 0).
        
        System.out.println("Printing terms searched for");
        for(int i = 0; i < Unique.size() && i < 10; i++) //if number of terms less than 10, display all. if greater, display
        												// top 10 only.
        {
        	Unique.get(i).printContent();
        }
        in.close();
	}	
	
//QuickSortFunctions	
	/*
	 * This function sets the last element as a pivot and then determines whether the previous elements are less than,
	 * equal to, or greater than the pivot. It ends by putting the pivot in the middle, so that left < middle < right.
	 */
	public static int Partition(WebPage[] A, int p, int r)
	{
		WebPage x = new WebPage();
		x = A[r]; //last index is now the pivot
		int i = p-1;
		for(int j = p; j <= r-1; j++)
		{
			if(A[j].getPRank() <= x.getPRank()) //if current < pivot, keep pivot to left of i.
			{
				i = i + 1;
				exchange(A[i], A[j]);
			}
		}
		exchange(A[r], A[i+1]); //pivot now in middle, all to left are < pivot, all to right > pivot.
		return i + 1;
	}
	
	/*
	 * This function will call upon partition until each subarray is sorted, which in turn makes the entire array sorted.
	 */
	public static void quickSort(WebPage[] A, int p, int r)
	{
		if (p < r)
		{
			int q = Partition(A, p, r); //split array into left < pivot, pivot, right > pivot.
			quickSort(A, p, q-1); //sort left half
			quickSort(A, q+1,r); // sort right half
		}
	}
	
	/*
	 * Displays the results (index, pagerank, total score, url) of each element in the array.
	 */
	public static void displayContents(WebPage[] A)
	{
		for(int i = 0; i < A.length; i++)
			A[i].printContents();
	}
	
//BucketSort functions
	
	/*
	 * This function takes an array of strings, places them into an appropriate bracket according to the first letter of the domain,
	 * and once all the strings are stored, sort them with insertion sort. Then, concatenate all of the strings into the original array.
	 * This function takes O(n^2) at worst, but because all the buckets are small in number, insertion sort is fast (at about O(n) speed).
	 */
	public static void bucketSort(String[] a1)
	{
		LinkedList<String> []Bucket = new LinkedList[26];
		String temp;
		int bucketIndex;
		
		//Initialize linkedlists so we have 26 buckets of linked lists.
		for(int i = 0; i < Bucket.length; i++)
		{
			Bucket[i] = new LinkedList();
		}
		
		//assign each element into a bucket.
		for(int i = 0; i < a1.length; i++)
		{
			temp = a1[i].toLowerCase();
			if(temp.startsWith("https://www.")) //domain is the first character after the .
			{
				bucketIndex = temp.charAt(12) - 97; //ASCII code of a = 97, so to get appropriate bucket index, we subtract by 97.
				Bucket[bucketIndex].add(temp);
			}
			else if(temp.startsWith("https://")) //domain is the first character after the /
			{
				bucketIndex = temp.charAt(8) - 97;  //ASCII code of a = 97, so to get appropriate bucket index, we subtract by 97.
				Bucket[bucketIndex].add(temp);
			}
			else if(temp.startsWith("http://www.")) //domain is the first character after the .
			{
				bucketIndex = temp.charAt(11) - 97;  //ASCII code of a = 97, so to get appropriate bucket index, we subtract by 97.
				Bucket[bucketIndex].add(temp);
			}
			else if(temp.startsWith("http://")) //domain is the first character after the /
			{
				bucketIndex = temp.charAt(7) - 97;  //ASCII code of a = 97, so to get appropriate bucket index, we subtract by 97.
				Bucket[bucketIndex].add(temp);
			}
		}
		
		//sort all of the buckets with insertion sort.
		for(int i = 0; i < Bucket.length; i++)
		{
			insertionSort(Bucket[i]);
		}
		
		//concatenate all of the elements in the buckets.
		int index = 0;
		for(int i = 0; i < Bucket.length; i++) //start with a, go to z.
		{
			for(int j = 0; j < Bucket[i].size(); j++) //start with first element, go till the last element.
			{
				a1[index] = Bucket[i].get(j); //add it to the array a1.
				index++;
			}
		}
	}
	
	/*
	 * Sorts the buckets. This takes O(n^2), but since each bucket is small, insertion sort is fast.
	 */
	public static void insertionSort(LinkedList<String> A)
	{
		int j;  //hold current element to compare with the left.
		String key;
		int i; //hold all elements sorted to the left.
		
		for (j = 1; j < A.size(); j++)
		{
			key = A.get(j);
			//Insert Array[j] into the sorted sequence A[1..j-1]
			i = j - 1;
			while (i >= 0 && A.get(i).compareTo(key) == 1) //if the current element > key,
			{
				A.set(i + 1, A.get(i)); //swap elements.
				i = i - 1;
			}
			A.set(i+1, key); //swap elements and set key in appropriate place.
		}
	}
	
	/*
	 * Prints out the URL's in the PopularList
	 * This takes O(n) time.
	 */
	public static void printPopularList(String[] a1)
	{
		//Print out all the URL's in the popularlist.
		for(int i = 0; i < a1.length; i++)
		{
			System.out.println(a1[i]);
		}
	}
	
//Other functions
	
	/*
	 * This function acts as an equal operation, such as (WebPage)p = (WebPage)d. Since there is no operator overload,
	 * this function is called to do the equal operation instead.
	 * This takes O(1) time.
	 */
	public static void override(WebPage a1, WebPage a2)
	{
		a1.setURL(a2.getURL()); //overrides URL variable
		a1.setRel(a2.getRel()); //overrides PageRank variable
		a1.setFreq(a2.getFreq()); //overrides Frequency and Location variable
		a1.setExis(a2.getExis()); // overrides Existence variable
		a1.setConn(a2.getConn()); // overrides connection variable
		a1.setAdvt(a2.getAdvt()); // overrides advertisement variable.
		a1.setIndex(a2.getIndex()); //overrides index variable
		a1.setPRank(a2.getPRank()); // overrides pagerank variable.
		a1.setLeft(a2.getLeft()); //overrides Left child pointer.
		a1.setRight(a2.getRight()); // overrides Right child pointer.
		a1.setParent(a2.getParent()); // overrides Parent pointer.
	}

	/*
	 * This function is used to exchange 2 webpages. 
	 * It calls override 3 times, and it runs in O(1) time.
	 */
	public static void exchange(WebPage a1, WebPage a2)
	{
		WebPage temp = new WebPage();
		override(temp, a1); // Set the temp (temp = a1)
		override (a1, a2); // store the content of a2 into a1. (a1 = a2)
		override (a2, temp); // store the content of temp, which holds a1 content, into a2 (a2 = temp)
	}
}
