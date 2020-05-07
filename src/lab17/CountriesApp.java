package lab17;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CountriesApp {
	
	private static Scanner scnr;
	private static Map<Integer, String> menu = new HashMap<>();
	private static Path filePath = Paths.get("Countries.txt");
	
	public static void main(String[] args) {
		scnr = new Scanner(System.in);

		System.out.println("Welcome to the Countries Maintenance Application!");
		System.out.println();
		whileLoop();

	}
	
	private static void fillMovieMenu() {
		menu.put(1, "See list of countries");
		menu.put(2, "Add a country");
		menu.put(3, "Exit");

	}
	
	private static void printMenu() {
		for (Map.Entry<Integer, String> entry : menu.entrySet()) {
			System.out.printf("%-1d-%-1s\n", entry.getKey(), entry.getValue());
		}
	}
	
	private static void whileLoop() {
		boolean displayMenu = true;
		while (displayMenu) {
			System.out.println();
			fillMovieMenu();
			printMenu();
			System.out.println();
			int command = GrandCircusValidator.getInt(scnr, "Enter menu number: ");

			if (command == 3) {
				System.out.println();
				System.out.println("Buh-bye!");
				break;
			} else if (command == 1) {
				List<String> countries = readLinesOfFile();
				for (String country : countries) {
					System.out.println(country);
				}
			} else if (command == 2) {
				System.out.println();
				String country = GrandCircusValidator.getStringMatchingRegex(scnr, "Enter country: ", "[a-zA-z\\s]*");
				String population = GrandCircusValidator.getStringMatchingRegex(scnr, "Enter population: ", "^\\d{1,3}(,\\d{3})*(\\.\\d+)?$");
				appendLineToFile(country + " (pop " + population +  ")");
			} else {
				System.out.println("Invalid command.");
			}
		}
	}
	
	//method is from GC example
	public static List<String> readLinesOfFile() {
		try {
			return Files.readAllLines(filePath);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Unable to read file.");
			return new ArrayList<>();
		}
	}
	
	//method is from GC example
	public static void appendLineToFile(String line) {
		List<String> lines = Collections.singletonList(line);

		try {
			Files.write(filePath, lines, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Unable to write to file.");
		}
	}
}
