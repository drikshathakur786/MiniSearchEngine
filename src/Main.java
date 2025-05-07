import java.util.*;

public class Main {
    public static void main(String[] args) {
        SearchEngine searchEngine = new SearchEngine();
        DocumentManager docManager = new DocumentManager(searchEngine);

        try {
            docManager.loadDocuments("documents");
        } catch (Exception e) {
            System.err.println("Error loading documents: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter command (search <word> / suggest <prefix> / exit): ");
            String[] input = scanner.nextLine().split(" ", 2);
            if (input[0].equalsIgnoreCase("exit"))
                break;

            if (input[0].equalsIgnoreCase("search") && input.length > 1) {
                Set<String> results = searchEngine.search(input[1]);
                if (results.isEmpty())
                    System.out.println("No results found.");
                else
                    results.forEach(System.out::println);

            } else if (input[0].equalsIgnoreCase("suggest") && input.length > 1) {
                List<String> suggestions = searchEngine.suggest(input[1]);
                if (suggestions.isEmpty())
                    System.out.println("No suggestions found.");
                else
                    suggestions.forEach(System.out::println);
            }
        }
        scanner.close();
    }
}
