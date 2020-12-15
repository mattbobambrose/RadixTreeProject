import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TreeTests {

    @Test
    public void matchTest() {
        assertEquals(MashupNode.prefixMatch("cat", "dog"), -1);
        assertEquals(MashupNode.prefixMatch("cat", "can"), 1);
        assertEquals(MashupNode.prefixMatch("cat", "cat"), 2);
        assertEquals(MashupNode.prefixMatch("cat", "hat"), -1);
        assertEquals(MashupNode.prefixMatch("cat", "crtt"), 0);
        assertEquals(MashupNode.prefixMatch("cat", "c"), 0);
        assertEquals(MashupNode.prefixMatch("c", "cat"), 0);
        assertEquals(MashupNode.prefixMatch("", ""), -1);
    }

    @Test
    public void treeTest1() {
        String result =
                new RadixTree()
                        .add("cat")
                        .add("catastrophe")
                        .add("catatonic")
                        .toString();
        assertEquals("[{'cat', t, [{'a', f, [{'tonic', t}, {'strophe', t}]}]}]", result);
    }

    @Test
    public void treeTest2() {
        String result =
                new RadixTree()
                        .add("cat")
                        .add("can")
                        .toString();
        assertEquals("[{'ca', f, [{'t', t}, {'n', t}]}]", result);
    }

    @Test
    public void treeTest3() {
        String result =
                new RadixTree()
                        .add("ca")
                        .add("cat")
                        .add("can").toString();
        assertEquals("[{'ca', t, [{'t', t}, {'n', t}]}]", result);
    }

    @Test
    public void treeTest4() {
        RadixTree tree =
                new RadixTree()
                        .add("ca")
                        .add("cat")
                        .add("can");
        int result = tree.search("cat");
        assertEquals(1, result);
    }

    @Test
    public void treeTest5() {
        TreeWrapper wrapper =
                new TreeWrapper(new RadixTree())
                        .add("ca")
                        .add("cat")
                        .add("can")
                        .add("catatonic");
        RadixTree tree = wrapper.getTree();
        List<String> result = tree.allStrings();
        assertEquals(wrapper.getWordsAdded().size(), result.size());
        for (String word : wrapper.getWordsAdded()) {
            assertTrue(result.contains(word));
            assertTrue(tree.contains(word));
        }
    }

    @Test
    public void treeTest6() {
        TreeWrapper wrapper =
                new TreeWrapper(new RadixTree())
                        .add("ca")
                        .add("cat")
                        .add("can")
                        .add("catatonic")
                        .remove("cat");
        RadixTree tree = wrapper.getTree();
        List<String> result = tree.allStrings();
        assertEquals(result.size(), tree.getSize());
        assertEquals(wrapper.getWordsAdded().size(), result.size());
        for (String word : wrapper.getWordsAdded()) {
            assertTrue(result.contains(word));
            assertTrue(tree.contains(word));
        }
    }

    @Test
    public void treeTest7() {
        RadixTree tree = new RadixTree().add("ca")
                .add("cat")
                .add("can")
                .add("catatonic");
        RadixTree tree2 = new RadixTree().add("ca")
                .add("cat")
                .add("can")
                .add("catatonic");
        assertTrue(RadixTree.matchTrees(tree, tree2));
    }
}