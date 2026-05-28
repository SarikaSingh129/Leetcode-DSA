class Solution {

    class TrieNode {
        TrieNode[] child = new TrieNode[26];
        int idx = -1;
        int len = Integer.MAX_VALUE;
    }
    TrieNode root = new TrieNode();
    private void update(TrieNode node, int index, int length) {
        if (length < node.len) {
            node.len = length;
            node.idx = index;
        }
        else if (length == node.len && index < node.idx) {
            node.idx = index;
        }
    }
    private void insert(String word, int index) {
        TrieNode curr = root;
        update(curr, index, word.length());
        for (int i = word.length() - 1; i >= 0; i--) {
            int ch = word.charAt(i) - 'a';
            if (curr.child[ch] == null) {
                curr.child[ch] = new TrieNode();
            }
            curr = curr.child[ch];
            update(curr, index, word.length());
        }
    }
    private int search(String word) {
        TrieNode curr = root;
        for (int i = word.length() - 1; i >= 0; i--) {
            int ch = word.charAt(i) - 'a';
            if (curr.child[ch] == null) {
                break;
            }
            curr = curr.child[ch];
        }
        return curr.idx;
    }

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        for (int i = 0; i < wordsContainer.length; i++) {
            insert(wordsContainer[i], i);
        }
        int[] ans = new int[wordsQuery.length];
        for (int i = 0; i < wordsQuery.length; i++) {
            ans[i] = search(wordsQuery[i]);
        }
        return ans;
    }
}