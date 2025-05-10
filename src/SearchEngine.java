import java.util.*;
import java.io.*;
import java.nio.file.Path;

public class SearchEngine {
    private Map<String, Map<String, List<Integer>>> index; // Maps word -> document -> list of line numbers
    private Map<String, Map<String, List<String>>> fullLines; // Maps word -> document -> full lines where the word
                                                              // appears
    private Trie trie;

    public SearchEngine() {
        index = new HashMap<>();
        fullLines = new HashMap<>();
        trie = new Trie();
    }

    // Index a document and its content, also keeping track of line numbers and full
    // lines
    public void indexDocument(String documentName, String content, Path filePath) {
        String[] lines = content.split("\n");

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            String[] words = line.toLowerCase().split("\\W+");

            for (String word : words) {
                // Index the word with its line number in the document
                index.putIfAbsent(word, new HashMap<>());
                Map<String, List<Integer>> docMap = index.get(word);
                docMap.putIfAbsent(documentName, new ArrayList<>());
                docMap.get(documentName).add(i + 1); // Store line number (1-based index)

                // Store the full line for each word occurrence
                fullLines.putIfAbsent(word, new HashMap<>());
                Map<String, List<String>> fullLineMap = fullLines.get(word);
                fullLineMap.putIfAbsent(documentName, new ArrayList<>());
                fullLineMap.get(documentName).add(line); // Store the full line where the word occurs

                // Insert word into Trie for prefix search
                trie.insert(word);
            }
        }
    }

    // Search with strict or lazy modes
    public Map<String, List<String>> search(String keyword, String searchMode) {
        Map<String, List<String>> result = new HashMap<>();
        keyword = keyword.toLowerCase();

        // For lazy search, search if the word contains the keyword as a substring
        if (searchMode.equals("lazy")) {
            for (String word : index.keySet()) {
                if (word.contains(keyword)) {
                    for (String doc : index.get(word).keySet()) {
                        List<Integer> lineNumbers = index.get(word).get(doc);
                        List<String> lines = fullLines.get(word).get(doc);
                        for (int i = 0; i < lineNumbers.size(); i++) {
                            int line = lineNumbers.get(i);
                            String fullLine = lines.get(i);

                            result.putIfAbsent(doc, new ArrayList<>());
                            result.get(doc).add("Found '" + keyword + "' in word '" + word + "' in " + doc + " at line "
                                    + line + ": \n" + line + " : " + fullLine);
                        }
                    }
                }
            }
        }
        // For strict search, look for exact matches only
        else if (searchMode.equals("strict")) {
            if (index.containsKey(keyword)) {
                for (String doc : index.get(keyword).keySet()) {
                    List<Integer> lineNumbers = index.get(keyword).get(doc);
                    List<String> lines = fullLines.get(keyword).get(doc);
                    for (int i = 0; i < lineNumbers.size(); i++) {
                        int line = lineNumbers.get(i);
                        String fullLine = lines.get(i);

                        result.putIfAbsent(doc, new ArrayList<>());
                        result.get(doc).add("Found exact match of '" + keyword + "' in " + doc + " at line " + line
                                + ": \n" + line + " : " + fullLine);
                    }
                }
            }
        }
        return result;
    }

    // Get suggestions based on the prefix using Trie
    public List<String> suggest(String prefix) {
        return trie.startsWith(prefix.toLowerCase());
    }
}
