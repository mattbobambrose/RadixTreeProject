import java.util.ArrayList;
import java.util.List;

class MashupNode {
    List<Edge> children = new ArrayList<>();
    RadixTree tree;
    int id;

    MashupNode(RadixTree tree) {
        this.tree = tree;
        id = this.tree.nextId();
    }

    // Calculates the length of the shared prefix of two words
    static int prefixMatch(String s1, String s2) {
        String result = "";
        for (int i = 0; i < Math.min(s1.length(), s2.length()); i++) {
            if (s1.charAt(i) == s2.charAt(i))
                result += s1.charAt(i);
            else
                break;
        }
        return result.length() - 1;
    }

    // Adds a MashupNode to a radix tree
    void add(String str) {
        for (Edge child : children) {
            int x = prefixMatch(str, child.prefix);
            // If they share a common prefix
            if (x != -1) {
                // First suffix
                String sub1 = child.prefix.substring(x + 1);
                // Checks if prefix is the str
                if (sub1.length() != 0)
                    child.node.add(sub1);

                // Second suffix
                String sub2 = str.substring(x + 1);
                // Checks if prefix is the str
                if (sub2.length() != 0)
                    child.node.add(sub2);

                child.prefix = child.prefix.substring(0, x + 1);

                child.isWord = sub1.length() == 0 || sub2.length() == 0;
                return;
            }
        }
        children.add(new Edge(str, new MashupNode(this.tree)));
    }

    // Searches for a MashupNode in a radix tree and returns where the node is
    int search(String str) {
        for (Edge child : children) {
            if (str.equals(child.prefix) && child.isWord)
                return child.node.id;

            if (str.startsWith(child.prefix)) {
                int retval = child.node.search(str.substring(child.prefix.length()));
                if (retval != -1)
                    return retval;
            }
        }
        return -1;
    }

    // Removes a string from a radix tree and returns its position
    int remove(String str) {
        for (Edge child : children) {
            // If child is a match and is a word
            if (str.equals(child.prefix) && child.isWord) {
                // If child has no children
                if (child.node.isEmpty()) {
                    children.remove(child);
                }
                // If child has one child
                else if (child.node.numChildren() == 1) {
                    Edge grandchild = child.node.children.get(0);
                    String childPre1 = child.prefix;
                    String childPre2 = grandchild.prefix;
                    child.isWord = grandchild.isWord;
                    child.node = grandchild.node;
                    child.prefix = childPre1 + childPre2;

                }
                // If child has more than one child
                else {
                    child.isWord = false;
                }
                return child.node.id;
            }

            if (str.startsWith(child.prefix)) {
                int retval = child.node.remove(str.substring(child.prefix.length()));
                if (retval != -1)
                    return retval;
            }
        }
        return -1;
    }

    // Checks to see if node is a leaf node
    boolean isEmpty() {
        return children.size() == 0;
    }

    // Returns the number of children
    int numChildren() {
        return children.size();
    }

    // Modifies a list of strings by adding all words in a tree to the list
    void nodeStrings(List<String> list, String str, boolean isWord) {
        if (isWord)
            list.add(str);

        for (Edge child : children)
            child.node.nodeStrings(list, str + child.prefix, child.isWord);
    }

    @Override
    public String toString() {
        return "" + children;
    }
}