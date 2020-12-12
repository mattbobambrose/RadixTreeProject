import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TreeTests {

    @Test
    public void matchTest() {
        assertEquals(MashupNode.match("cat", "dog"), -1);
        assertEquals(MashupNode.match("cat", "can"), 1);
        assertEquals(MashupNode.match("cat", "cat"), 2);
        assertEquals(MashupNode.match("cat", "hat"), -1);
        assertEquals(MashupNode.match("cat", "crtt"), 0);
        assertEquals(MashupNode.match("cat", "c"), 0);
        assertEquals(MashupNode.match("c", "cat"), 0);
        assertEquals(MashupNode.match("", ""), -1);
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
        List<String> result = tree.stringsInTree();
        assertEquals(wrapper.getWordsAdded().size(), result.size());
        System.out.println(tree.stringsInTree());
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
        List<String> result = tree.stringsInTree();
        assertEquals(wrapper.getWordsAdded().size(), result.size());
        System.out.println(tree.stringsInTree());
        for (String word : wrapper.getWordsAdded()) {
            assertTrue(result.contains(word));
            assertTrue(tree.contains(word));
        }
    }
}