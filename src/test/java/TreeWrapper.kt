import java.util.ArrayList;
import java.util.List;

public class TreeWrapper {
    private List<String> wordsAdded = new ArrayList<>();
    private RadixTree tree;

    public TreeWrapper(RadixTree tree) {
        this.tree = tree;
    }

    public List<String> getWordsAdded() {
        return wordsAdded;
    }

    public RadixTree getTree() {
        return tree;
    }

    public TreeWrapper add(String word) {
        wordsAdded.add(word);
        tree.add(word);
        return this;
    }

    public TreeWrapper remove(String word) {
        wordsAdded.remove(word);
        tree.remove(word);
        return this;
    }
}