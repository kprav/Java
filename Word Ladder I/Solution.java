public class Solution {
    private class Node {
        String word;
        int lengthSoFar;
        public Node(String word, int lengthSoFar) {
            this.word = word;
            this.lengthSoFar = lengthSoFar;
        }
    }
    
    public int ladderLength(String start, String end, ArrayList<String> dictV) {
        Queue<Node> queue = new LinkedList<>();
        Node startNode = new Node(start, 1);
        queue.add(startNode);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            String word = node.word;
            if (word.equals(end)) {
                return node.lengthSoFar;
            }
            char[] w = word.toCharArray();
            for (int i = 0; i < w.length; i++) {
                char temp = w[i];
                for (char j = 'a'; j <= 'z'; j++) {
                    w[i] = j;
                    String tempWord = new String(w);
                    if (dictV.contains(tempWord)) {
                        queue.add(new Node(tempWord, node.lengthSoFar + 1));
                        dictV.remove(tempWord);
                    }
                }
                w[i] = temp;
            }
        }
        return 0;
    }
}
