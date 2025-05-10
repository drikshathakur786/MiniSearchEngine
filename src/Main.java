import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SearchEngine searchEngine = new SearchEngine();
        DocumentManager docManager = new DocumentManager(searchEngine);

        String folderPath;

        // Ask user for folder path
        System.out.print("Do you want to use the default database? (Y/N): ");
        String choice = scanner.nextLine().trim().toLowerCase();

        if (choice.equals("y")) {
            folderPath = "../documents"; // Default path
            System.out.println("Using default path: " + folderPath);
        } else {
            System.out.print("Enter the folder path containing .txt files: ");
            folderPath = scanner.nextLine().trim();
        }

        // Load & index documents
        try {
            docManager.loadDocuments(folderPath);
            System.out.println("Documents loaded and indexed successfully.");
        } catch (Exception e) {
            System.err.println("Error loading documents: " + e.getMessage());
            return;
        }

        while (true) {
            System.out.print("\nEnter command (search <word> / suggest <prefix> / exit): ");
            String[] input = scanner.nextLine().split(" ", 2);

            if (input[0].equalsIgnoreCase("exit")) {
                System.out.println("Exiting Mini Search Engine. Goodbye!");
                break;
            }

            if (input[0].equalsIgnoreCase("search") && input.length > 1) {
                // Prompt user for search mode each time they want to search
                System.out.print("Choose search mode (lazy/strict): ");
                String searchMode = scanner.nextLine().trim().toLowerCase();
                if (!searchMode.equals("lazy") && !searchMode.equals("strict")) {
                    System.out.println("Invalid choice. Defaulting to lazy search.");
                    searchMode = "lazy";
                }

                String keyword = input[1].toLowerCase();
                Map<String, List<String>> occurrences;

                if (searchMode.equals("strict")) {
                    occurrences = docManager.searchStrictWord(keyword); // Strict Search
                } else {
                    occurrences = docManager.searchLazyWord(keyword); // Lazy Search
                }

                // Write results to output.txt and print to console
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
                    if (occurrences.isEmpty()) {
                        System.out.println("No results found.");
                        writer.write("No results found for word: " + keyword);
                        writer.newLine();
                    } else {
                        writer.write("Search results for word: " + keyword);
                        writer.newLine();
                        for (String file : occurrences.keySet()) {
                            List<String> lines = occurrences.get(file);
                            System.out.println("\nResults from " + file);
                            System.out.println("=".repeat(40));
                            writer.write("\nResults from " + file);
                            writer.newLine();
                            writer.write("=".repeat(40));
                            writer.newLine();

                            System.out.println("Total Occurrences: " + lines.size());
                            writer.write("Total Occurrences: " + lines.size());
                            writer.newLine();

                            for (String line : lines) {
                                System.out.println(line);
                                writer.write(line);
                                writer.newLine();
                            }
                        }
                        System.out.println("\nResults written to output.txt");
                    }
                } catch (IOException e) {
                    System.err.println("Error writing to output.txt: " + e.getMessage());
                }

            } else if (input[0].equalsIgnoreCase("suggest") && input.length > 1) {
                List<String> suggestions = searchEngine.suggest(input[1]);

                try (BufferedWriter writer = new BufferedWriter(new FileWriter("suggestions.txt", true))) {
                    if (suggestions.isEmpty()) {
                        System.out.println("No suggestions found.");
                        writer.write("No suggestions found for prefix: " + input[1]);
                        writer.newLine();
                    } else {
                        System.out.println("\nSuggestions for prefix '" + input[1] + "':");
                        writer.write("\n\nSuggestions for prefix '" + input[1] + "':");
                        writer.newLine();
                        writer.write("=".repeat(40));
                        writer.newLine();

                        // Write suggestions to the file and print to console
                        for (String word : suggestions) {
                            System.out.println("- " + word);
                            writer.write("- " + word);
                            writer.newLine();
                        }
                        System.out.println("\nSuggestions written to suggestions.txt");
                    }
                } catch (IOException e) {
                    System.err.println("Error writing suggestions: " + e.getMessage());
                }

            } else {
                System.out.println("Invalid command. Please try again.");
            }
        }

        scanner.close();
    }
}
