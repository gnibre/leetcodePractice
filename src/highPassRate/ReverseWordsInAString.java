package highPassRate;

import java.util.ArrayList;

public class ReverseWordsInAString {

	public void go() {

		String s = " 1 ";
		reverseWords(s);

	}

	public String reverseWords(String s) {

		// good thing, split function don't work good; for example. for " 1",
		// split with " " result is "" and "1",
		// shall we give up ""? makes it not that easy. ; so we'd rather do it
		// by owerselves, go use ArrayList.
		// String[] split = s.split(" ");
		// int len = split.length;
		// String swap;
		// for(int i=0;i<len/2;++i){
		// //swap i with [len-i-1];
		// swap = split[len-i-1];
		// split[len-i-1] = split[i];
		// split[i] = swap;
		// }
		if (s == null || s.length() < 1) {
			return s;
		}
		int len = s.length();

		ArrayList<String> words = new ArrayList<String>();
		

		int wordStartFrom = 0;

		while (wordStartFrom < len) {
			if (s.charAt(wordStartFrom) != ' ') {
				break;
			}
			wordStartFrom++;
		}
		int ind = s.indexOf(" ", wordStartFrom); // indexOf(s,from) is to find the index
		// start from from: index>=from
		// returned;

		// int wordEnd=0;
		while (ind > 0) {
			// find one;
			// wordEnd position = ind-1;
			if (ind > wordStartFrom) {
				System.out.println(" got startfrom and ind: "+wordStartFrom+"   "+ind);
				words.add(s.substring(wordStartFrom,ind));
				wordStartFrom = ind + 1;

				while (wordStartFrom < len) {
					if (s.charAt(wordStartFrom) != ' ') {
						break;
					}
					wordStartFrom++;
				}

				if (wordStartFrom >= len) {
					break;
				}
			} else {
				// ind == wordStartFrom;
				// it's blank after blank case;
				wordStartFrom++;
			}
			// next;
			ind = s.indexOf(" ", wordStartFrom);
		}

		// still have tail
		if (wordStartFrom < len) {
			words.add(s.substring(wordStartFrom));
		}

		if (words.size() == 0) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		for (String w : words) {
			System.out.println(" words:  " + w);
		}
		sb.append(words.get(words.size()-1));
        
        for(int i=words.size()-2;i>=0;--i){
            sb.append(" ");
            sb.append(words.get(i));
        }
		return sb.toString();
	}

}
