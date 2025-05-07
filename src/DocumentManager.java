import java.io.*;
import java.nio.file.*;
import java.util.*;

public class DocumentManager {
    private SearchEngine searchEngine;

    public DocumentManager(SearchEngine searchEngine) {
        this.searchEngine = searchEngine;
    }

    public void loadDocuments(String folderPath) throws IOException {
        Files.walk(Paths.get(folderPath)).filter(Files::isRegularFile).filter(path -> path.toString().endsWith(".txt")).forEach(path -> {
            try {
                String content = new String(Files.readAllBytes(path));
                searchEngine.indexDocument(path.getFileName().toString(), content);
            } 
            catch (IOException e) {
                System.err.println("Failed to read: " + path);
            }
        });
    }
}