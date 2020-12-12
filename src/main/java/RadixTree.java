import java.util.ArrayList;
import java.util.List;

public class RadixTree {
    private MashupNode root = new MashupNode(this);
    private int nextId = 0;

    public static void main(String[] args) {
        RadixTree tree = new RadixTree();
        tree.add("cat");
        tree.add("catastrophe");
        tree.add("catatonic");
        System.out.println(tree.search("catatonic"));
        System.out.println(tree);
        System.out.println(tree.stringsInTree());
    }

    //Adds a MashupNode to a radix tree
    public RadixTree add(String str) {
        this.root.add(str);
        return this;
    }

    //Removes a MashupNode from a radix tree
    public RadixTree remove(String str) {
        this.root.remove(str);
        return this;
    }

    public int search(String str) {
        return this.root.search(str);
    }

    public int nextId() {
        return nextId++;
    }

    public List<String> stringsInTree() {
        List<String> list = new ArrayList<>();
        root.nodeStrings(list, "", false);
        return list;
    }

    public boolean contains(String word) {
        return search(word) != -1;
    }

    @Override
    public String toString() {
        return "" + root;
    }
}