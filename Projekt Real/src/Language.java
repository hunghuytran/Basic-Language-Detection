import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Language {
	HashMap<String, HashMap<Character, Integer>> spr�k = new HashMap<String, HashMap<Character, Integer>>();

	public Language() {
		analyzefile();
		analyzetext();
	}

	private int characterCount(HashMap<Character, Integer> newpo�ngsum) {
		int total = 0;
		for (int count : newpo�ngsum.values()) {
			total += count;
		}
		return total;
	}

	private void analyzetext() {
		HashMap<Character, Integer> charOccurence = new HashMap<Character, Integer>();

		Scanner analyze = new Scanner(System.in);
		System.out.print("Write in the string you want to analyze. ");
		String tobeanalyzed = analyze.nextLine();
		System.out.println("You inputed following string: " + tobeanalyzed);
		analyze.close();
		for (int i = 0; i < tobeanalyzed.length(); i++) {
			char letters = tobeanalyzed.charAt(i);
			if (!charOccurence.containsKey(letters))
				charOccurence.put(letters, 1);
			else
				charOccurence.put(letters, charOccurence.get(letters) + 1);
		}

		for (Map.Entry<String, HashMap<Character, Integer>> langChars : spr�k
				.entrySet()) {
			String spr�kname = langChars.getKey();
			HashMap<Character, Integer> charWords = langChars.getValue(); // H�mtar spr�k och hashmap inne i hashmappen.
			int sumLetters = characterCount(charWords);
			double sumScore = 0;
			
			
			System.out.println(spr�kname); // Skriver ut spr�ket.
			for (Map.Entry<Character, Integer> occurence : charOccurence 
					.entrySet()) {
				Character occuChar = occurence.getKey();
				
				if (!charWords.containsKey(occuChar))
			    continue;
				int occuCount = occurence.getValue(); 
				int langCount = charWords.get(occuChar);
				double Points = (double)langCount*occuCount / sumLetters;
				sumScore += Points;
				
				}
			System.out.println(sumScore); // Skriver ut po�ng.
			}
		System.out.println("List out the letters found in each language");
		}


	private void analyzefile() {
		File direction = new File("language/");
		for (File item : direction.listFiles()) {
			System.out.println("Parsing " + item.getName());
			int totalfil = item.listFiles().length;

			HashMap<Character, Integer> f�rekomst = new HashMap<Character, Integer>();

			int countwords = 0;
			for (File readfile : item.listFiles()) {
				try {
					String path = readfile.getPath();
					byte[] bytes = Files.readAllBytes(Paths.get(path));
					String oldtext = new String(bytes, "UTF-8");
					int newtext = oldtext.split("\\s+").length;
					countwords += newtext;
					String text = oldtext.replaceAll("\\s", "")
							.replaceAll("\\p{Punct}", "");

					for (int i = 0; i < text.length(); i++) {
						char bokst�ver = text.charAt(i);
						if (!f�rekomst.containsKey(bokst�ver))
							f�rekomst.put(bokst�ver, 1);
						else
							f�rekomst.put(bokst�ver,
									f�rekomst.get(bokst�ver) + 1);

					}

				} catch (IOException e) {
					System.out.println("Could not read the file");
				}
			}
			System.out.println("Found files " + totalfil + ", containing "
					+ countwords + " words.");
			System.out.println("Character prevalence in language "
					+ item.getName() + ".");
			spr�k.put(item.getName(), f�rekomst);
		}
	}

	public static void main(String[] args) {
		new Language();
	}
}
