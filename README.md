# longest-word-chain-n-gram-problem

PROBLEM STATEMENT:
To find the longest word chain (n-gram) among all the possible word chains in the dictionary.  
PLATFORM USED:
The project was implemented using JAVA language.
DATA STRUCTURE USED:
This project used multiple data structures and methods implemented in JAVA.
1.	Array List
2.	HashMap
3.	HashSet
ALGORITHM:
•	Iteration method is used in this project.
•	Step1: The dictionary.txt is being read by using buffered reader as a first step. Words are added in array list named as ‘allwords’.

•	Step 2: All words are sorted.

•	Step 3: Another data structure ‘SetOfList’ is created. In this, Different array list are created based on number of alphabets in words. i.e. for example, “aa” two letter word is added into 1st setoflist and “abc” three letter word will get added in 2nd setoflist and so on.

•	 Step 4: 
1. The outermost for loop is used to iterate over all sets. (We got around 25 sets)
2. 2nd for loop is being used for to acquire words and initialize it as ‘Key’.
3. 3rd for loop iterates over ‘next adjacent set’ which contains one extra letter. 
4. 4th for loop gets all words one by one from next set (adjacent set which contains one extra letter words compared to previous set.

•	Step 5: “ContainsAllChars” method is written to check all alphabets contained in words to be compared. “addIntoMapOfChain” method is used to create new chain of words and add words into it.  Repeat Step 4 and Stpe 5 to get all chains.

•	Simultaneously, these chains are added into ‘n-gram-output.txt’. This process is carried on until all the end of the dictionary file.
COMPILE AND RUNNING PROGRAM:
1. Compiling program: $javac ngram.java
2. Running Program: $java ngram

COMPLEXITY ANALYSIS AND CONCLUSION: 
•	The algorithm designed works well for smaller inputs. For example, here the given dictionary is first sorted and divided into number of sets based on number of alphabets in a word. 
•	However, the real time is saved when current word compares with words in adjacent set only. This avoids iterating throughout dictionary. 
•	Here dictionary contains 173528 words. this program runs for approximately 19 mins to get longest Ngram chain.
•	So, the conclusion is the algorithm is only efficient for smaller inputs and the complexity of the algorithm is high for the larger inputs.
