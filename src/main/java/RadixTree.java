public class RadixTree {
    MashupNode root = new MashupNode();

    public static void main(String[] args) {
        RadixTree tree = new RadixTree();
        tree.add("cat");
        tree.add("catastrophe");
        tree.add("catatonic");
        System.out.println(tree);
    }

    //Adds a MashupNode to a radix tree
    public RadixTree add(String str) {
        this.root.add(str);
        return this;
    }

    //Removes a MashupNode from a radix tree
    public void remove(String str) {
        for (int i = 0; i < str.length(); i++) {

        }
        compress();
    }

    //Checks if child node is an only child
    public void compress() {

    }

    @Override
    public String toString() {
        return "" + root;
    }
}