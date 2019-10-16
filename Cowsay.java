/*
 * AleK3y; 16 October 2019
 * - Cowsay for every OS with Java
 */

class Cowsay {
	
	// Maximum bubble width
	private static final int max = 39;

	private static String[] cow = {
		repeat(" ", 0) + "\\   ^__^",
		repeat(" ", 1) + "\\  (oo)\\_______",
		repeat(" ", 4) + "(__)\\       )\\/\\",
		repeat(" ", 8) + "||----w |",
		repeat(" ", 8) + "||     ||"
	};
	
	public static void main(String[] args) {
		boolean no_args = false;
		String text = "";
		if(args.length == 0)
			no_args = true;

		if(! no_args) {
			// Merges the array into one string
			text = args[0];
			if(args.length > 0)
				for(int i=1; i<args.length; i++)
					text += " " + args[i];
			
			// Checks if the text fits or not and prints it
			if(text.length() < max)
				_fits(text);
			else
				_notfits(text);
			
			// Prints the final cow
			_printcow();
		} else {
			// Just prints the cow if there are no args
			System.out.println();
			for(String i : cow)
				System.out.println(repeat(" ", 2) + i.substring(2, i.length()));
		}
	}
	
	private static void _fits(String text) {
		System.out.println(" " + repeat("_", text.length()+2));
		System.out.println("< " + text + " >");
		System.out.println(" " + repeat("-", text.length()+2));
	}
	
	private static void _notfits(String text) {

		// The text is split into words
		String[] div = text.split("\\s+");

		// The string result is used to merge every divided line
		String result = "";
		int len=0;
		for(int i=0; i<div.length; i++) {

			// Add the word to the result if it fits on one line
			if(len + div[i].length() + 1 <= max) {
				result += div[i] + " ";
				len += div[i].length() + 1;

			// Split the words that are too long and prints them
			// divided, by printing each char at once adding a
			// return when the length is bigger than the maximum width
			} else if(div[i].length() >= max) {
				for(int j=0; j<div[i].length(); j++) {
					if(len >= max-1) {
						len=0;
						result += "\n";
					}
					result += div[i].charAt(j);
					len++;
				}
				result += " ";

			// If the current word doesn't fit, adds return and goes back one word
			} else {
				len = 0;
				i--;
				result += "\n";
			}
		}

		// Prints the top of the bubble
		System.out.println(" " + repeat("_", max+1));

		// Splits the result into lines
		String[] to_print = result.split("\n");
		for(int i=0; i<to_print.length; i++) {

			// If the last char is a space it gets removed to keep
			// just one space at the end of the line (of the bubble)
			if(to_print[i].substring(to_print[i].length()-1, to_print[i].length()).equals(" "))
				to_print[i] = to_print[i].substring(0, to_print[i].length()-1);

			// By checking the line's number, the bubble border gets printed
			if(i == 0) {
				System.out.println("/ " + to_print[i] + repeat(" ", max-to_print[i].length()-1) + " \\");
			} else if(i >= to_print.length-1) {
				System.out.println("\\ " + to_print[i] + repeat(" ", max-to_print[i].length()-1) + " /");
			} else {
				System.out.println("| " + to_print[i] + repeat(" ", max-to_print[i].length()-1) + " |");
			}
		}

		// Prints bottom of the bubble
		System.out.println(" " + repeat("-", max+1));
	}
	
	// Prints the cow from the array
	private static void _printcow() {
		int dinstance_left = 8;
		for(String i : cow)
			System.out.println(repeat(" ", dinstance_left) + i);
	}

	// The String.repeat() method works only on latest versions
	private static String repeat(String t2r, int num) {
		String result = "";
		for(int i=0; i<num; i++)
			result += t2r;
		return result;
	}
}