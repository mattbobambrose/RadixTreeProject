import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
        assertEquals(result, "[{'cat', t, [{'a', f, [{'tonic', t}, {'strophe', t}]}]}]");
    }

    @Test
    public void treeTest2() {
        String result =
                new RadixTree()
                        .add("cat")
                        .add("can")
                        .toString();
        assertEquals(result, "[{'ca', f, [{'t', t}, {'n', t}]}]");
    }

    @Test
    public void treeTest3() {
        String result =
                new RadixTree()
                        .add("ca")
                        .add("cat")
                        .add("can").toString();
        assertEquals(result, "[{'ca', t, [{'t', t}, {'n', t}]}]");
    }
}