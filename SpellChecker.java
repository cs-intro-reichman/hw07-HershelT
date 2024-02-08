
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}
	public static String head(String str) {
		// Your code goes here
		if (str.length() == 1) {
			return str;
		}
		return str.substring(0, 1);
	}

	public static String tail(String str) {
		// Your code goes here
		if (str.length() == 1) {
			return "";
		}
		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		// Your code goes here
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		int lengthOne = word1.length();
		int lengthTwo = word2.length();
		if (lengthTwo == 0) {
			return lengthOne;
		}
		if (lengthOne == 0) {
			return lengthTwo;
		}
		if (head(word1).equals(head(word2))) {
			return levenshtein(tail(word1), tail(word2));
		}
		else {
			int distanceOne = levenshtein(tail(word1), word2);
			int distanceTwo = levenshtein(word1, tail(word2));
			int distanceThree = levenshtein(tail(word1), tail(word2));
			
			return 1 + minimum(distanceOne, minimum(distanceTwo, distanceThree));
		}
		// Non recrusive implementation
		// int[][] distance = new int[lengthOne + 1][lengthTwo + 1];
		// for (int i = 0; i <= lengthOne; i++) {
		// 	distance[i][0] = i;
		// }
		// for (int j = 0; j <= lengthTwo; j++) {
		// 	distance[0][j] = j;
		// }
		// for (int i = 1; i <= lengthOne; i++) {
		// 	for (int j = 1; j <= lengthTwo; j++) {
		// 		if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
		// 			distance[i][j] = distance[i - 1][j - 1];
		// 		} else {
		// 			distance[i][j] = Math.min(distance[i - 1][j - 1], Math.min(distance[i - 1][j], distance[i][j - 1])) + 1;
		// 		}
		// 	}
		// }
		// return distance[lengthOne][lengthTwo];

	}
	public static int minimum(int a, int b) {
		if (a < b) {
			return a;
		}
		return b;
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);
		int i = 0;
		while (!in.isEmpty()) {
			dictionary[i] = in.readString();
			i++;
		}
		// Your code here

		return dictionary;
	}

// 	Your task is to develop an iterative version of the spellChecker function. Write a loop that
//  calculates the edit distance between the given word and each word in the dictionary. Identify the word
//  with the minimum distance, check whether this distance exceeds the given threshold, and proceed with
//  the appropriate action based on this evaluation.
	public static String spellChecker(String word, int threshold, String[] dictionary) {

		// Your code goes here
		int minDistance = Integer.MAX_VALUE;
		String minWord = "";
		for (int i = 0; i < dictionary.length; i++) {
			if (dictionary[i] == null) {
				break;
			}
			int distance = levenshtein(word, dictionary[i]);
			if (distance < minDistance) {
				minDistance = distance;
				minWord = dictionary[i];
			}

		}
		if (minDistance <= threshold) {
			return minWord;
		} else {
			return word;
		}
	}


}
