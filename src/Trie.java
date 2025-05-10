import java.util.*;

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Insert a word into the Trie
    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node = node.children.computeIfAbsent(c, k -> new TrieNode());
        }
        node.isEndOfWord = true;
    }

    // Get all words starting with the given prefix
    public List<String> startsWith(String prefix) {
        List<String> result = new ArrayList<>();
        TrieNode node = root;

        // Traverse to the end of the prefix
        for (char c : prefix.toCharArray()) {
            node = node.children.get(c);
            if (node == null) {
                return result; // No words with this prefix
            }
        }

        // Perform DFS to collect all words starting with the prefix
        collectWords(node, new StringBuilder(prefix), result);
        return result;
    }

    // Helper method to collect words from the Trie
    private void collectWords(TrieNode node, StringBuilder prefix, List<String> result) {
        if (node.isEndOfWord) {
            result.add(prefix.toString());
        }

        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            collectWords(entry.getValue(), prefix.append(entry.getKey()), result);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    // TrieNode class
    private static class TrieNode {
        private Map<Character, TrieNode> children = new HashMap<>();
        private boolean isEndOfWord = false;
    }
}
