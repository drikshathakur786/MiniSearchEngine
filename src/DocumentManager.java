import java.io.*;
import java.nio.file.*;
import java.util.*;

public class DocumentManager {
    private SearchEngine searchEngine;

    public DocumentManager(SearchEngine searchEngine) {
        this.searchEngine = searchEngine;
    }

    // Load documents and index them
    public void loadDocuments(String folderPath) throws IOException {
        Files.walk(Paths.get(folderPath))
                .filter(Files::isRegularFile)
                .filter(path -> path.toString().endsWith(".txt"))
                .forEach(path -> {
                    try {
                        String content = new String(Files.readAllBytes(path));
                        searchEngine.indexDocument(path.getFileName().toString(), content, path);
                    } catch (IOException e) {
                        System.err.println("Failed to read: " + path);
                    }
                });
    }

    // Perform strict search: exact matches of the word
    public Map<String, List<String>> searchStrictWord(String word) {
        return searchEngine.search(word, "strict");
    }

    // Perform lazy search: matches the word as a substring
    public Map<String, List<String>> searchLazyWord(String word) {
        return searchEngine.search(word, "lazy");
    }
}
