import java.util.*;

public class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isWord = false;
}