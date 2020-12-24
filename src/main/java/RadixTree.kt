import java.util.ArrayList;
import java.util.List;

public class RadixTree {
    private MashupNode root = new MashupNode(this);
    private int nextId = 0;
    private int size = 0;

    public static void main(String[] args) {
        RadixTree tree = new RadixTree();
        tree.add("catastrophe").add("cat").add("catatonic");
        System.out.println(tree.allStrings());
        System.out.println(tree.contains("catatonic"));
        System.out.println(tree.search("catastrophe"));
        System.out.println(tree.remove("cat"));
        System.out.println(tree.allStrings());
        RadixTree tree2 = new RadixTree();
        tree2.add("catastrophe").add("catatonic");
        System.out.println(matchTrees(tree, tree2));
    }

    // Checks if two trees are matching
    public static boolean matchTrees(RadixTree t1, RadixTree t2) {
        if (t1.getSize() != t2.getSize())
            return false;
        for (String word : t1.allStrings()) {
            if (!t2.contains(word))
                return false;
        }
        return true;
    }

    // Adds a MashupNode to a radix tree
    public RadixTree add(String str) {
        this.root.add(str);
        size++;
        return this;
    }

    // Searches for a MashupNode in a radix tree
    public int search(String str) {
        return this.root.search(str);
    }

    // Checks if a string is in a radix tree
    public boolean contains(String word) {
        return search(word) != -1;
    }

    // Removes a string from a radix tree
    public int remove(String word) {
        size--;
        return this.root.remove(word);
    }

    // Increments the nextId variable
    int nextId() {
        return nextId++;
    }

    // Lists out all of the strings in a radix tree
    public List<String> allStrings() {
        List<String> list = new ArrayList<>();
        root.nodeStrings(list, "", false);
        return list;
    }

    // Returns the size of a radix tree
    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "" + root;
    }
}