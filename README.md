# Programming-Assignment-2
Search Engine Internal Process Simulator

Search Engine Internal Process:
1. Based on scores of 30 URLs that you retrieved from your web crawler, use Quicksort algorithm to
sort the scores for PageRank instead of using Heapsort from PA1. (You MUST print a list of 30
URLs including Index, total score, PageRank, and URL. If not, your will lose 50 points.)
2. Based on scores of 30 URLs, use Binary Search Tree to manipulate the data. The following section
list the programming requirements for BST.
3. To speed up the search result, Google search engine dynamically collects a list of top N popular
keywords (N=10 for instance in PA1) and use Bucket Sort to sort their company’s names of URLs
in alphabetical order (example: http://www.abcde.com). You can store company’s name starting
with A in bucket “A”, starting with B in bucket “B”, and so on.

Programming Requirements
1. The Google Search Engine Internal Process MUST be written in Java and it is required to use
pseudo codes in the textbook. (Please be noted that you MUST use the pseudo codes provided in
textbook to write your Java codes. Any other codes will be treated as failing requirements and will
receive 0 points.)
2. You need to use a Web Crawler to enter keywords and then each keyword will receive 30 URLs for
further Google Internal Process. (You will receive 0 if you hardcode 30 URLs in your
program/code.)
3. You must follow the four PageRank factors from your PA1 to calculate score for PageRank.
4. Your simulation application MUST at least contain the following functions for URL Binary
Search Tress (BST):
a) Build up a Process BST. (MUST follow BST properties specified in textbook and ppt
slides. Your own tree structure will not be accepted.)
b) Users can search a specific PageRank and show the specific URL (User want to know the
score of a specific website.).
c) Users can insert a URL to the BST based on its total score and show the result.
d) User can delete a URL from the BST and show the result.
e) Users can make a sorted list of URLs according to score from the BST and show the result.
f) To show the result, you MUST print a list of URLs including Index, total score, PageRank,
and URL. If not, your will lose 50 points.
5. Each java file/class/subroutine/function call must contain a header comment in the beginning of it.
(You will lose 20 points for not putting comments in your program.)
