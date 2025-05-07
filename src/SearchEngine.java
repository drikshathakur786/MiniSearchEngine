import java.util.*;

public class SearchEngine {
    private Map<String, Set<String>> index;
    private Trie trie;

    public SearchEngine() {
        index = new HashMap<>();
        trie = new Trie();
    }

    public void indexDocument(String documentName, String content) {
        String[] words = content.toLowerCase().split("\\W+");
        for (String word : words) {
            index.putIfAbsent(word, new HashSet<>());
            index.get(word).add(documentName);
            trie.insert(word); // Insert into Trie
        }
    }

    public Set<String> search(String keyword) {
        return index.getOrDefault(keyword.toLowerCase(), Collections.emptySet());
    }

    public List<String> suggest(String prefix) {
        return trie.startsWith(prefix.toLowerCase());
    }
}
