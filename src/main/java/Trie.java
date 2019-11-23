import java.io.*;
import java.util.*;

class Trie {

    public static class TrieNode {
        private List<TrieNode> children;
        private char val;

        public TrieNode(char val) {
            this.children = new ArrayList<>();
            this.val = val;
        }

        public List<TrieNode> getChildren() {
            return this.children;
        }

        public void addChild(TrieNode node) {
            this.children.add(node);
        }

        public char getVal() {
            return this.val;
        }
    }

    public static void addWord(TrieNode root, String word) {

        TrieNode curNode = root;
        boolean isNewBranch = false;

        // Add each letter of the word
        for (int i = 0; i < word.length(); i++) {
            char curChar = word.charAt(i);
            boolean isFound = false;
            List<TrieNode> children = curNode.getChildren();

            // If we're not adding a completely new branch,
            // Iterate through children and find if it has child for letter
            if (!isNewBranch) {
                for (int j = 0; j < children.size() && !isFound; j++) {
                    TrieNode curChild = children.get(i);
                    if (curChild.getVal() == curChar) {
                        curNode = curChild;
                        isFound = true;
                    }
                }
            }

            // If we don't find it, create a new node for the letter
            if (isNewBranch || !isFound) {
                TrieNode newNode = new TrieNode(curChar);
                curNode.addChild(newNode);
                curNode = newNode;
                isNewBranch = true;
            }
        }
    }

    public static void printTrie(TrieNode root) {
        TrieNode curNode = root;

        // Print the children, and recursively repeat for each child
        System.out.println("");
        for (TrieNode child : root.getChildren()) {
            System.out.print(child.getVal() + " ");
            printTrie(child);
        }
    }

    public static void main(String[] args) {

        // For now we start with some letter, but really we'll have multiple branches
        TrieNode root = new TrieNode('c');

        String word1 = "cat";
        String word2 = "catepillar";
        String word3 = "capture";
        String word4 = "crocodile";

        addWord(root, word1);
        addWord(root, word2);
        addWord(root, word3);
        addWord(root, word4);
    }
}