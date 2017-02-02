package strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordLadder3 {

	public static void main(String[] args) {
		Set<String> dict = new HashSet<>();
		dict.add("cat");
		dict.add("bat");
		dict.add("cot");
		dict.add("cog");
		dict.add("cow");
		dict.add("rat");
		dict.add("but");
		dict.add("cut");
		dict.add("dog");
		dict.add("web");
//		dict.add("hit");
//		dict.add("hot");
//		dict.add("dot");
//		dict.add("lot");
//		dict.add("dog");
//		dict.add("log");
//		dict.add("cog");
		String start = "cat";
		String end = "dog";
		WordHolder result = computeShortestPath(start,end,dict);
		System.out.println("Result is: "+result.path);
		System.out.println("Result is: "+result.length);

	}

	private static WordHolder computeShortestPath(String start, String end, Set<String> dict) {

		if (dict.size() == 0) {
			return new WordHolder(0, null, null);
		}

		Queue<WordHolder> whQueue = new LinkedList<>();
		whQueue.add(new WordHolder(1, start, Arrays.asList(start)));

		while (!whQueue.isEmpty()) {
			WordHolder wh = whQueue.poll();
//			if (wh.word.equalsIgnoreCase(end)) {
//				return wh;
//			}
			String cw = wh.word;
			for (int i = 0; i < start.length(); i++) {
				char[] ca = cw.toCharArray();
				for (char c = 'a'; c <= 'z'; c++) {
					if (c != cw.toCharArray()[i]) {
						ca[i] = c;
					}
					String caString = new String(ca);
					if (dict.contains(caString)) {
						List<String> path = new ArrayList<>(wh.path);
						path.add(caString);
						WordHolder tmp = new WordHolder(wh.length+1, caString, path);
						whQueue.add(tmp);
						dict.remove(caString);
						if (caString.equalsIgnoreCase(end)) {
							return tmp;
						}
					}
				}
			}
		}

		return new WordHolder(0, null, null);
	}


	public static class WordHolder {
		int length;
		String word;
		List<String> path = new ArrayList<String>();

		public WordHolder(int length, String word, List<String> path) {
			this.length = length;
			this.word = word;
			this.path = path;
		}
	}

}
