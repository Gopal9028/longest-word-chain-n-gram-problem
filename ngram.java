/* Longest Ngram dictionary code By Gopal Vishwakarma (dde911) */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ngram {

	public static void main(String[] args) {
		// ngram dictionary code
		Date startDateTime = new Date();
		System.out.println("hello: "+startDateTime);
		try {
			/* PLEASE GIVE PATH OF "Dictionary.txt" IN BELOW LINE */
			BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\sem1\\DS and Algorithm\\project\\Dictionary.txt")); //Please give path of dictionary here
			String line;

			int iCount = 0;
			List<String> allWords = new ArrayList<String>();   //arraylist initialization 
			while ((line = bufferedReader.readLine()) != null) {
				iCount++;
				allWords.add(line);								//reading all words and adding it to list
				// System.out.println(line);
			}
			System.out.println(iCount);
			
			allWords.sort(Comparator.comparing(String::length));

			//System.out.println("This is sorted list: " + allWords.toString());
			//System.out.println("*********************************************************");
			int startLength = allWords.get(0).length();
			int tempIndex = 0;
			List<List<String>> setofList = new ArrayList<List<String>>();      //set of list initialization
 
			List<String> list = new ArrayList<String>();

			for (int j = tempIndex; j < allWords.size(); j++) {				//loop for adding words into multiple sets
				if (allWords.get(j).length() == startLength) {
					list.add(allWords.get(j));
					tempIndex = j;
				} else {
					tempIndex = j;
					if (list.size() > 0) {
						setofList.add(list);
					}
					list = new ArrayList<String>();
					startLength++;
				}
			}


		/*	Set<String> newSet = new LinkedHashSet<String>();
			iCount = 10000;
			for (int i = 0; i < iCount; i++) {
				String s = allWords.get(i);
				for (int j = i + 1; j < iCount - 1; j++) {
					if (containsAllChars(allWords.get(j), s) && allWords.get(j).length()>s.length()) {
						//newSet.add(s);
						newSet.add(allWords.get(j));
						break;
						// System.out.println(newSet);
					}
				}
			}*/
	int chainCount = 0;
	PrintWriter writer = new PrintWriter("n-gram-output.txt", "UTF-8");
	Map<String, ArrayList<String>> mapOfChain = new HashMap<String, ArrayList<String>>();
	
	for (int mainSetsIndex = 0; mainSetsIndex < setofList.size()-1; mainSetsIndex++) { // Loop for Set of List (lettersSet)
		
		for (int oneSetIndex = 0; oneSetIndex < setofList.get(mainSetsIndex).size(); oneSetIndex++) {
			String key = setofList.get(mainSetsIndex).get(oneSetIndex);
			
			for (int mainSetsNextIndex = mainSetsIndex+1; mainSetsNextIndex < setofList.size(); mainSetsNextIndex++) {
				
				for (int nextIndex = 0; nextIndex < setofList.get(mainSetsNextIndex).size(); nextIndex++) {
					
					if(setofList.get(mainSetsNextIndex).get(nextIndex).length() == key.length()+1){
						
						//System.out.println("Comparing : "+key+" with :" +setofList.get(mainSetsNextIndex).get(nextIndex));
						
						if(containsAllChars(setofList.get(mainSetsNextIndex).get(nextIndex), key)){
							
							addIntoMapOfChain(setofList.get(mainSetsIndex).get(oneSetIndex), setofList.get(mainSetsNextIndex).get(nextIndex) ,mapOfChain );
							key = setofList.get(mainSetsNextIndex).get(nextIndex);
							
							break;
							
						}
						
					}
				}
				
			}
			chainCount++;
			List<String> chain = new ArrayList<String>();
			chain.add(setofList.get(mainSetsIndex).get(oneSetIndex));
			if(mapOfChain.get(setofList.get(mainSetsIndex).get(oneSetIndex))!=null){
				chain.addAll(mapOfChain.get(setofList.get(mainSetsIndex).get(oneSetIndex)));
				//List<String> reverseView = Collections.reverse(chain);
				System.out.println("Chain "+chainCount+". "+String.join(" -> ", chain));
				writer.println("Chain "+chainCount+". "+String.join(" -> ", chain));
			}
			
			

		}
		
		
	}
				
	//PrintWriter writer = new PrintWriter("n-gram-output.txt", "UTF-8");
	int len = 0;
	String key = "";
	for (Entry<String, ArrayList<String>> entry : mapOfChain.entrySet())
	{
		if(entry.getValue().size()>len){
			len = entry.getValue().size();
			key = entry.getKey();
		}
	   // System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		//writer.println(entry.getKey() + ":" + entry.getValue().toString());
	}
	//writer.close();
	List<String> chain = new ArrayList<String>();
	chain.add(key);
	chain.addAll(mapOfChain.get(key));
	Collections.reverse(chain);
	System.out.println("Longest chain : "+String.join(" -> ", chain));
	System.out.println("Length of chain: "+chain.size() + "words");
	//writing result in file
	writer.println("Longest chain : "+String.join(" -> ", chain));
	writer.println("Length of chain: "+chain.size() + "words");
	Date endDateTime = new Date();
	long mins = ((endDateTime.getTime()-startDateTime.getTime())/1000)/60; //calculation of total time required for execution
	System.out.println("total time: "+ mins + "minutes");
	writer.println("total time: "+ mins + "minutes");
	writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	private static void addIntoMapOfChain(String key, String value, Map<String, ArrayList<String>> mapOfChain) {
		ArrayList<String> chain; 
		if(mapOfChain.containsKey(key) && null!=mapOfChain.get(key)){
			 chain = mapOfChain.get(key);
		 } else {
			 chain = new ArrayList<String>();
		 }
		chain.add(value);
		mapOfChain.put(key, chain);
	}
	public static boolean containsAllChars (String container, String containee) {
	  return stringToCharacterSet(container).containsAll
	    (stringToCharacterSet(containee));
	 }
	
	public static Set<Character> stringToCharacterSet(String s) {
		Set<Character> set = new HashSet<Character>();
		for (char c : s.toCharArray()) {
			set.add(c);
		}
		return set;
	}

}




