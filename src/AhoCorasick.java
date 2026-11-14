import java.util.*;

public class AhoCorasick {

    static class Node {
        Map<Character, Node> children = new HashMap<>();
        Node fail;
        List<String> output = new ArrayList<>();
    }

    Node root = new Node();

    public void addPattern(String pattern) {
        Node current = root;

        for (char c : pattern.toCharArray()) {
            current.children.putIfAbsent(c, new Node());
            current = current.children.get(c);
        }

        current.output.add(pattern);
    }

    public void build() {
        Queue<Node> queue = new ArrayDeque<>();

        root.fail = root;

        for (Node child : root.children.values()) {
            child.fail = root;
            queue.add(child);
        }

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (Map.Entry<Character, Node> entry
                    : current.children.entrySet()) {

                char c = entry.getKey();
                Node child = entry.getValue();
                Node f = current.fail;

                while (f != root && !f.children.containsKey(c)) {
                    f = f.fail;
                }

                if (f.children.containsKey(c)) {
                    child.fail = f.children.get(c);
                } else {
                    child.fail = root;
                }

                child.output.addAll(child.fail.output);
                queue.add(child);
            }
        }
    }

    public int search(String text) {
        Node current = root;
        int matches = 0;

        for (char c : text.toCharArray()) {

            while (current != root
                    && !current.children.containsKey(c)) {
                current = current.fail;
            }

            if (current.children.containsKey(c)) {
                current = current.children.get(c);
            }

            matches += current.output.size();
        }

        return matches;
    }
}